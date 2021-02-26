package com.magpie.devOps.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfPara {

	/**
	 * 标题
	 * 
	 * @param text
	 * @return
	 */
	public static Paragraph title(String text) {
		
		// 标题
		Paragraph paragraph = new Paragraph(40);// 边距
		// 1 2 3 中右左
		paragraph.setAlignment(1); // 对齐方式
		Font font = getFont();// 字体
		font.setSize(14);// 字体大小
		paragraph.setFont(font);// 设置段落字体
		Chunk chunk = new Chunk(text);
		paragraph.add(chunk);
		return paragraph;
	}

	public static Paragraph body(String text) {

		// 标题
		Paragraph paragraph = new Paragraph(40);// 边距
		// 1 2 3 中右左
		paragraph.setAlignment(3); // 对齐方式
		Font font = getFont();// 字体
		font.setSize(10);// 字体大小
		paragraph.setFont(font);// 设置段落字体
		Chunk chunk = new Chunk(text);
		paragraph.add(chunk);
		paragraph.setSpacingBefore(-10);
		paragraph.setSpacingAfter(10);
		paragraph.setFirstLineIndent(20);
		// 行间距
		paragraph.setLeading(20f);
//		paragraph.set
		return paragraph;
	}
	
	public static Font getFont() {
		try {
			 return new Font(getBaseFont());// 字体
		} catch ( Exception e) {
			log.info("使用默认字体，获取字体出错{}",e);
			return new Font();
		}
	}
	
	public static BaseFont getBaseFont() {
		try {
			 return BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 字体
		} catch ( Exception e) {
			log.info("使用默认字体，获取字体出错{}",e);
			return null;
		}
	}
}
