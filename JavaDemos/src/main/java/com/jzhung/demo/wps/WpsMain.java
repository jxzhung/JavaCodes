package com.jzhung.demo.wps;

import org.junit.Test;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.DispatchEvents;
import com.jacob.com.Variant;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jzhung on 2018/1/5.
 */
public class WpsMain {
    private static final int wdFormatPDF = 17;
    private static final int wdFormatXMLDocument = 8;

    @Test
    public void loadWord() {

        String word = "E:\\test.doc";
        String pdf = "E:\\test.docx";

        word2PDF(word, pdf);
        /*File pdfFile = new File(pdf);
        File wordFile = new File(word);
        ActiveXComponent wps = null;
        try {
            wps = new ActiveXComponent("kwps.application");
            System.out.println(wps);
            ActiveXComponent doc = wps.invokeGetComponent("Documents").invokeGetComponent("Open", new Variant(wordFile.getAbsolutePath()));
            System.out.println(doc);
            doc.invoke("ExportPdf", new Variant(pdfFile.getAbsolutePath()));
            doc.invoke("Close");
            doc.safeRelease();
        } catch (Exception ex) {
            Logger.getLogger(WpsMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Error ex) {
            Logger.getLogger(WpsMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (wps != null) {
                wps.invoke("Terminate");
                wps.safeRelease();
            }
        }
*/
    }

    /**
     * word转换为pdf
     * @param inputFile
     * @param pdfFile
     * @return
     */
    public static boolean word2PDF(String inputFile, String pdfFile) {
        try {
            // 打开word应用程序
            ActiveXComponent app = new ActiveXComponent("Word.Application");
            // 设置word不可见
            app.setProperty("Visible", false);
            // 获得word中所有打开的文档,返回Documents对象
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true)
                    .toDispatch();
            // 调用Document对象的SaveAs方法，将文档保存为pdf格式
            /*
             * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF
             * //word保存为pdf格式宏，值为17 );
             */
            Variant variant = Dispatch.call(doc, "SaveAs2", pdfFile, wdFormatXMLDocument);// word保存为pdf格式宏，值为17
// 关闭文档
            Dispatch.call(doc, "Close", false);
            // 关闭word应用程序
            app.invoke("Quit", 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
