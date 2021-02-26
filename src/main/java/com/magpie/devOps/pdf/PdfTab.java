package com.magpie.devOps.pdf;

import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfTab {

	public static PdfPTable get(List<String> cells) {
		// 画表头第一行
		PdfPTable table = new PdfPTable(6);
		table.setTotalWidth(480);
		
		float[] columnWidth = { 80, 80, 80, 80, 80, 80 };
		try {
			table.setTotalWidth(columnWidth);
		} catch (DocumentException e) {
			log.info(" setTotalWidth {}", e);
		}
		table.setLockedWidth(true);// 宽度算正确

		try {
			for (String cell : cells) {
				table.addCell(drawPdfPCell(cell));
			}
		} catch (Exception e) {
			log.info("add cell error {}", e);
		}
		return table;
	}

	/**
	 * 
	 * Method description : 画一行中的一列（格子） Author： linyi Create Date： 2018年7月24日
	 * 下午2:17:07 History: 2018年7月24日 下午2:17:07 linyi Created.
	 * 
	 * @param cellText  格子的文字
	 * @param baseFont  字体类型
	 * @param size      字体大小
	 * @param alignment 对齐方式
	 * @return
	 * @throws Exception
	 *
	 */
	private static PdfPCell drawPdfPCell(String cellText) throws Exception {
		// 为null会报错 防止报错
		if (cellText == null) {
			cellText = " ";
		}
		// 表格开始
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(1); // 对齐方式
		Font font = new Font(PdfPara.getBaseFont());// 字体
		font.setSize(10);// 字体大小
		paragraph.setFont(font);// 设置段落字体
		Chunk chunk = new Chunk(cellText);
		paragraph.add(chunk);
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setVerticalAlignment(5);// 设置cell垂直居中
		cell.setMinimumHeight(20);// 设置单元格最小高度，当前行最小高度
		cell.addElement(paragraph);
		return cell;
	}
}
