package com.jzhung.demo.other.imgconvert;

import net.arnx.wmf2svg.gdi.svg.SvgGdi;
import net.arnx.wmf2svg.gdi.wmf.WmfParser;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFPanel;
import org.freehep.graphicsio.emf.EMFRenderer;
import org.freehep.graphicsio.svg.SVGGraphics2D;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

/**
 * Created by xiawenjing on 2017/6/28.
 */
public class PictureUtil {
    public static void wmf2png(String src) {
        try {
            String svgFile = replace(src, "wmf", "svg");
            wmfToSvg(src, svgFile);
            String jpgFile = replace(src, "wmf", "png");
            svgToJpg(svgFile, jpgFile, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void svg2png(String src) {
        try {
            String pngFile = replace(src, "svg", "png");
            svgToJpg(src, pngFile, 150);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bmp2png(String src) {
        try {
            String pngFile = replace(src, "bmp", "png");
            File out = null;
            File in = new File(src);
            BufferedImage input = ImageIO.read(in);
            out = new File(pngFile);
            ImageIO.write(input, "bmp", out);
            input.flush();
            in.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void emf2png(String src) {
        try {
            String svgPath = replace(src, "emf", "svg");
            String dest = replace(src, "emf", "png");
            EMFInputStream inputStream = new EMFInputStream(new FileInputStream(src));
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
//            p.put(SVGGraphics2D.EXPORT_IMAGES, Boolean.toString(true));

            FileOutputStream svg = new FileOutputStream(svgPath);
            SVGGraphics2D graphics2D = new SVGGraphics2D(svg, emfPanel);
            graphics2D.setProperties(p);
            graphics2D.setDeviceIndependent(true);
            graphics2D.startExport();
            emfPanel.print(graphics2D);
            graphics2D.endExport();
            svg.flush();
            svg.close();

            svgToJpg(svgPath, dest, 300);
            /*File emf = new File(src);
            emf.delete();*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将svg转化为JPG
     *
     * @param src
     * @param dest
     */
    public static String svgToJpg(String src, String dest, int weight) {
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
            it.addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(weight));
            jpg = new ByteArrayOutputStream();
            svgInputStream = new ByteArrayInputStream(svgOut.toByteArray());
            it.transcode(new TranscoderInput(svgInputStream),
                    new TranscoderOutput(jpg));
            jpgOut = new FileOutputStream(dest);
            jpgOut.write(jpg.toByteArray());
        } catch (Exception e) {
            System.out.println("src:" + src);
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
                /*File svgFile = new File(src);
                svgFile.delete();*/
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
            File wmfFile = new File(src);
            wmfFile.delete();
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
        int i = original.toLowerCase().indexOf(find); //找到的子串位置
        while (i != -1) {
            sb.append(original.substring(begin, i));
            sb.append(replace);
            begin = i + findLen;
            i = original.toLowerCase().indexOf(find, begin);
        }
        if (begin < originalLen) {
            sb.append(original.substring(begin));
        }
        return sb.toString();
    }
}
