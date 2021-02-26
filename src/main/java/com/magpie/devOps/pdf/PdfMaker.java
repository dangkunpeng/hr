package com.magpie.devOps.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.magpie.devOps.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfMaker {

	public static String create() throws Exception {
		Long start = System.currentTimeMillis();
		String filePath = "/dev/" + Utils.getKey() + ".pdf";
		// 创建文件
		Document document = new Document();
		// 创建PDFWriter
		PdfWriter.getInstance(document, new FileOutputStream(filePath));
		// 打开文件
		document.open();
		document.add(PdfPara.title("hello world pdf 埃森哲信息技术（大连）有限公司!"));
		// 添加文本
		StringBuffer text = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			text.append("hello world, this is my first pdf by iText!!!I want this pdf have 100MB or more,and show it is fast enough to work in the prd env!");
		}
		document.add(PdfPara.body(text.toString()));
		// 添加table
		List<String> tabHeader = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			tabHeader.add("header" + i);
		}
		document.add(PdfTab.get(tabHeader));
		
		for (int i = 0; i < 1000000; i++) {
			List<String> tabBody = new ArrayList<>();
			for (int j = 0; j < 6; j++) {
				tabBody.add("body" + i +"-" + j);
			}
			document.add(PdfTab.get(tabBody));
		}
		
		document.close();
		Long cost = System.currentTimeMillis() - start;
		File file = new File(filePath);
		log.info("生成文件花费时间{}ms,文件大小是{}KB", cost, file.length()/1024);
		file = null;
		return filePath;
	}

	// public static void main(String[] args) {
	// 	try {
	// 		log.info("生成文件的路径是{}", create());
	// 	} catch (Exception e) {
	// 		log.error("生成文件失败{}", e);
	// 	}
	// }
}
