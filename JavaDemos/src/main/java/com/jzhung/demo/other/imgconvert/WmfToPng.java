package com.jzhung.demo.other.imgconvert;

import net.arnx.wmf2svg.gdi.svg.SvgGdi;
import net.arnx.wmf2svg.gdi.wmf.WmfParser;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFPanel;
import org.freehep.graphicsio.emf.EMFRenderer;
import org.freehep.graphicsio.svg.SVGGraphics2D;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

public class WmfToPng {
    public static void main(String[] args) {
        String result = convert("E:\\test\\1d9aa.wmf");
        System.out.println(result);

    }

    @Test
    public void emf2png() {
        try {
            EMFInputStream inputStream = new EMFInputStream(new FileInputStream("E:\\test1\\161d04.emf"));
            EMFRenderer emfRenderer = new EMFRenderer(inputStream);
            EMFPanel emfPanel = new EMFPanel();
            emfPanel.setRenderer(emfRenderer);

            final int width = (int) inputStream.readHeader().getBounds().getWidth();
            final int height = (int) inputStream.readHeader().getBounds().getHeight();

            Properties p = new Properties();
            p.put(SVGGraphics2D.EMBED_FONTS, Boolean.toString(false));
            p.put(SVGGraphics2D.CLIP, Boolean.toString(false));
            p.put(SVGGraphics2D.COMPRESS, Boolean.toString(false));
            p.put(SVGGraphics2D.TEXT_AS_SHAPES, Boolean.toString(false));
            p.put(SVGGraphics2D.STYLABLE, Boolean.toString(false));
            p.put(SVGGraphics2D.IMAGE_SIZE, width + ", " + height);
//            p.put(SVGGraphics2D.EXPORT_IMAGES, Boolean.toString(true));//defaultProperties.setProperty(EXPORT_IMAGES, false);

            FileOutputStream svg = new FileOutputStream("E:\\test1\\161d04.svg");
            SVGGraphics2D graphics2D = new SVGGraphics2D(svg, emfPanel);
            graphics2D.setProperties(p);
            graphics2D.setDeviceIndependent(true);
            graphics2D.startExport();
            emfPanel.print(graphics2D);
            graphics2D.endExport();
            svg.flush();
            svg.close();

            svgToJpg("E:\\test1\\161d04.svg", "E:\\test1\\161d04.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static String convert2(String path) throws TranscoderException,
            IOException {
        String wmfPath = path;
        File wmf = new File(wmfPath);
        FileInputStream wmfStream = new FileInputStream(wmf);
        ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
        int noOfByteRead = 0;
        while ((noOfByteRead = wmfStream.read()) != -1) {
            imageOut.write(noOfByteRead);
        }
        imageOut.flush();
        wmfStream.close();
        // wmf 转换为svg
        WMFTranscoder transcoder = new WMFTranscoder();
        // TranscodingHints hints = new TranscodingHints();
        // hints.put(WMFTranscoder.KEY_HEIGHT, 1000f);
        // hints.put(WMFTranscoder.KEY_WIDTH, 1500f);
        // transcoder.setTranscodingHints(hints);
        TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(
                imageOut.toByteArray()));
        ByteArrayOutputStream svg = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(svg);
        transcoder.transcode(input, output);
        String svgFile = replace(wmfPath, "wmf", "svg");
        FileOutputStream fileOut = new FileOutputStream(svgFile);
        fileOut.write(svg.toByteArray());
        fileOut.flush();
        fileOut.close();
        svg.close();
        // svg -> jpg
        ImageTranscoder it = new JPEGTranscoder();
        it.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(0.5f));
        ByteArrayOutputStream jpg = new ByteArrayOutputStream();
        it.transcode(new TranscoderInput(new ByteArrayInputStream(svg
                .toByteArray())), new TranscoderOutput(jpg));
        String jpgFile = replace(wmfPath, "wmf", "jpg");
        FileOutputStream jpgOut = new FileOutputStream(jpgFile);
        jpgOut.write(jpg.toByteArray());
        jpgOut.flush();
        jpgOut.close();
        jpg.close();
        // Filor.deleteFile(svgFile);// 删除掉中间文件
        return jpgFile;
    }

    public static String convert(String path) {
        try {
            String svgFile = replace(path, "wmf", "svg");
            wmfToSvg(path, svgFile);
            String jpgFile = replace(path, "wmf", "png");
            svgToJpg(svgFile, jpgFile);
            return jpgFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Test
    public void test() {
        svgToJpg("E:/test1/162ace.svg", "E:/test1/1.png");
    }

    /**
     * 将svg转化为JPG
     *
     * @param src
     * @param dest
     */
    public static String svgToJpg(String src, String dest) {
        FileOutputStream jpgOut = null;
        FileInputStream svgStream = null;
        ByteArrayOutputStream svgOut = null;
        ByteArrayInputStream svgInputStream = null;
        ByteArrayOutputStream jpg = null;
        try {
            // 获取到svg文件
            File svg = new File(src);
            svgStream = new FileInputStream(svg);
            svgOut = new ByteArrayOutputStream();
            // 获取到svg的stream
            int noOfByteRead = 0;
            while ((noOfByteRead = svgStream.read()) != -1) {
                svgOut.write(noOfByteRead);
            }
            JPEGTranscoder it = new JPEGTranscoder();
            it.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(0.9f));
            it.addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(300));
            jpg = new ByteArrayOutputStream();
            svgInputStream = new ByteArrayInputStream(svgOut.toByteArray());
            it.transcode(new TranscoderInput(svgInputStream),
                    new TranscoderOutput(jpg));
            jpgOut = new FileOutputStream(dest);
            jpgOut.write(jpg.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (svgInputStream != null) {
                    svgInputStream.close();
                }
                if (jpg != null) {
                    jpg.close();
                }
                if (svgStream != null) {
                    svgStream.close();
                }
                if (svgOut != null) {
                    svgOut.close();
                }
                if (jpgOut != null) {
                    jpgOut.flush();
                    jpgOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dest;
    }

    /**
     * 将wmf转换为svg
     *
     * @param src
     * @param dest
     */
    public static void wmfToSvg(String src, String dest) {
        boolean compatible = false;
        try {
            InputStream in = new FileInputStream(src);
            WmfParser parser = new WmfParser();
            final SvgGdi gdi = new SvgGdi(compatible);
            parser.parse(in, gdi);

            Document doc = gdi.getDocument();
            OutputStream out = new FileOutputStream(dest);
            if (dest.endsWith(".svgz")) {
                out = new GZIPOutputStream(out);
            }

            output(doc, out);
//            File wmfFile = new File(src);
//            wmfFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void output(Document doc, OutputStream out) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
                "-//W3C//DTD SVG 1.0//EN");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                "http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
        transformer.transform(new DOMSource(doc), new StreamResult(out));
        out.flush();
        out.close();
    }

    public static String replace(String original, String find, String replace) {
        if (original == null || find == null || replace == null) {
            return original;
        }
        int findLen = find.length();
        int originalLen = original.length();
        if (originalLen == 0 || findLen == 0) {
            return original;
        }
        StringBuffer sb = new StringBuffer();
        int begin = 0; //下次检索开始的位置
        int i = original.indexOf(find); //找到的子串位置
        while (i != -1) {
            sb.append(original.substring(begin, i));
            sb.append(replace);
            begin = i + findLen;
            i = original.indexOf(find, begin);
        }
        if (begin < originalLen) {
            sb.append(original.substring(begin));
        }
        return sb.toString();
    }
}
