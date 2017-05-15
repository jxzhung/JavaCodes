package spider.jsoup;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试页面抓取和过滤
 */
public class PageContentParseTest {
    private static final String BASE_URL = "http://91.t9l.space/";
    private static final String RE_TIME = "&nbsp;\\d{2}:\\d{2}";

    public static void main(String[] args) {
        PageContentParseTest parseTest = new PageContentParseTest();
        parseTest.getPage();
    }

    public void getPage() {
        String url = "";
        Page page = new Page();
        page.setUrl(url);

        List<Res> resList = new ArrayList();

        Document doc;
        Connection.Response response = null;
        InputStream in = null;
        try {
            in = new FileInputStream("/Users/jzhung/t2.html");
            doc = Jsoup.parse(in, "utf-8", BASE_URL);

            String title = doc.title();
            if (title.equals("")) {
                System.out.println("载入页面失败 无标题 " + " 页面：" + url);
                return;
            }

            Element firstPost = doc.select("div.postmessage.firstpost").first();
            Element msg = firstPost.select("div.t_msgfontfix").first();

            List<TextNode> list = msg.textNodes();
            String html = msg.html();
            System.out.println(html);
            String regEx_html = "<[^><]+>"; // 定义HTML标签的正则表达式
            Pattern p = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(html);

            Pattern timePtn = Pattern.compile(RE_TIME, Pattern.CASE_INSENSITIVE);

            int lastEndIndex = 0;
            StringBuilder content = new StringBuilder();
            while (m.find()) {
                String data = m.group();
                //System.out.println("---------" + m.start() + " , " + m.end() + " last:" + lastEndIndex + " " + data);
                // 图片节点保留
                if (data.startsWith("<img")) {
                    if (data.contains("file")) {
                        Document document = Jsoup.parseBodyFragment(data, BASE_URL);
                        String imgUrl = document.body().child(0).attr("abs:file");
                        System.out.println(imgUrl);
                        //保存图片到数据库
                        Res res = new Res();
                        res.setNetUrl(imgUrl);
                        res.setFromUrl(imgUrl);

                        //System.out.println(data);
                        content.append("<img src=\"" + imgUrl + "\" />");
                    } else {
                        //System.out.println("跳过图片：" + data);
                    }
                } else if (data.startsWith("<br")) {
                    //有内容再加换行
                    if (content.length() > 0) {
                        content.append(data);
                    }
                }

                if (m.start() - lastEndIndex > 0) {
                    String textData = html.substring(lastEndIndex, m.start()).trim();
                    if (textData.equals("") || textData.startsWith("本帖最后") || textData.equals("下载") || textData.endsWith("B)")) {
                        //System.out.println("跳过：" + textData);
                        lastEndIndex = m.end();
                        continue;
                    }
                    Matcher timeMatcher = timePtn.matcher(textData);
                    if (!timeMatcher.find()) {
                        System.out.println("获取" + lastEndIndex + "," + m.start() + " 数据：" + textData);
                        content.append(textData);
                    }
                    //System.out.println(append);
                }

                lastEndIndex = m.end();
            }

            System.out.println("解析结果：" + content.toString());

            //System.out.println(m.group(0));;
            //String result = m.replaceAll(""); // 过滤script标签
            //System.out.println(result);
            if (1 > 0) {
                return;
            }

            List<Node> nodeList = msg.childNodes();
            StringBuilder contentBuilder = new StringBuilder();

            //下载完图片并替换好的内容
            contentBuilder = doNode(nodeList, contentBuilder, resList, page);
            System.out.println("内容:" + contentBuilder.toString());
            //System.out.println("发现并下载完成" + resList.size() + "张图片");
        } catch (HttpStatusException e) {
            e.printStackTrace();
            int code = -1;
            if (response != null) {
                code = response.statusCode();
            }
            System.out.println("返回状态码错误：" + code + " 地址：" + url);
        } catch (IOException e) {
            System.out.println("网络错误：" + " 地址：" + url);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public StringBuilder doNode(List<Node> list, StringBuilder builder, List<Res> localImgList, Page page) {

        for (Node node : list) {
            String outHtml = node.outerHtml().trim();
            //System.out.println("outHtml:" + outHtml);
            String cls = node.attr("class");
            // 排除图片提示
            if (cls.equals("imgtitle") || cls.equals("attach_popup") || cls.equals("t_attach")) {
                continue;
            }

            if (node.childNodeSize() != 0) {
                doNode(node.childNodes(), builder, localImgList, page);//递归 如果还有子节点继续循环操作

            } else {
                if (outHtml.indexOf("<img") >= 0) {//如果是 img标签进行标识type
                    String netUrl = node.attr("abs:file");
                    if (netUrl.equals("")) {
                        continue;
                    }
                    //todo 本地路径和网络路径的处理

                    String fileName = (localImgList.size() + 1) + FileNameUtil.getImgEnds(netUrl);
                    String halfDir = "images\\";
                    String savePath = "D:\\" + halfDir + fileName;

                    File downResultFile = new File(savePath);
                    //不存在才下载
                    if (!downResultFile.exists()) {
                        //downResultFile = client.download(netUrl, savePath);
                        //System.out.println("模拟下载图片:" + netUrl + " 保存到:" + savePath);
                    } else {
                        //System.out.println("文件已存在磁盘 " + savePath + " url:" + netUrl);
                    }

                    //下载成功后不再下载
                    if (downResultFile.exists()) {
                        long imgLen = downResultFile.length();
                        if (imgLen < 100) {
                            downResultFile.delete();
                            //System.out.println("下载图片失败 文件大小太小:" + imgLen + " " + savePath + " url:" + netUrl);
                            continue;
                        }
                    } else {
                        //System.out.println("下载图片失败， 文件不存在 " + savePath + " url:" + netUrl);
                    }

                    Res res = new Res();
                    res.setFromUrl(page.getUrl());
                    res.setNetUrl(netUrl);
                    res.setHashCode("hash");
                    res.setMd5("md5");
                    res.setSize(downResultFile.length());
                    res.setUri("/images/" + halfDir + fileName);
                    res.setName(fileName);
                    res.setMimeType("image/jpg");
                    Timestamp tt = new Timestamp(System.currentTimeMillis());
                    res.setCreateTime(tt);
                    res.setLastReadTime(tt);

                    localImgList.add(res);

                    builder.append("<img src=\"" + res.getUri() + "\">");
                    builder.append("<br/>");
                    System.out.println("图" + node.attr("file"));
                } else {

                    if (!("".equals(outHtml))) {//如果不是空的进行设置类型
                        String text = node.toString().trim();
                        //过滤帖子提示
                        if (text.startsWith("本帖最后由") && text.endsWith("编辑")) {
                            //System.out.println("过滤帖子提示");
                            continue;
                        }

                        if (text.equals("<br>") || text.endsWith("[/attach]")) {
                            //System.out.println("过滤<br>");
                            continue;
                        }
                        builder.append(text.replace("", "").replace(" ", ""));

                        //自动补全句号
                       /* if (!text.endsWith(".") && !text.endsWith("。") && !text.endsWith("，") && !text.endsWith(",")
                                && !text.endsWith("：") && !text.endsWith(":")
                                && !text.endsWith("！")
                                && !text.endsWith("!")
                                ) {
                            builder.append("。");
                        }*/

                        builder.append("<br/>");
                        System.out.println(text);
                        //System.out.println("html:" + node.parent().attr("class"));
                    } else {
                        //System.out.println("跳过空"+outHtml);
                        continue;
                    }
                }
            }
        }
        return builder;
    }
}

class Page {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

class FileNameUtil {
    public static String getImgEnds(String downimg) {
        String ends;
        String iname = downimg.toLowerCase();
        if (iname.endsWith("png")) {
            ends = ".png";
        } else {
            ends = ".jpg";
        }
        return ends;
    }
}

/**
 * 资源
 */
class Res {
    private Integer resId;
    private String hashCode;//hash码用来去重
    private String name;//文件名
    private String md5;//校验码
    private Long size;//文件大小
    private String uri;//保存路径
    private String mimeType;//文件类型
    private String fromUrl;//来源
    private String netUrl;//网络路径
    private Timestamp createTime;//文件创建时间
    private Timestamp lastReadTime;//最后访问时间
    private Integer downCount;//下载次数
    private Integer likeCount;//喜欢


    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Timestamp lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "Res{" +
                "resId=" + resId +
                ", hashCode='" + hashCode + '\'' +
                ", name='" + name + '\'' +
                ", md5='" + md5 + '\'' +
                ", size=" + size +
                ", uri='" + uri + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", fromUrl='" + fromUrl + '\'' +
                ", netUrl='" + netUrl + '\'' +
                ", createTime=" + createTime +
                ", lastReadTime=" + lastReadTime +
                ", downCount=" + downCount +
                ", likeCount=" + likeCount +
                '}';
    }
}