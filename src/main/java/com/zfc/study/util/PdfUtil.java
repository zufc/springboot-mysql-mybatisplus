package com.zfc.study.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Desc:导出pdf工具类
 */
public class PdfUtil {

	/**
	 * 生成诸如 ( 打印时间：20140531001 编号：20140531001)之类的信息 && 左对齐
	 *
	 * @param paragraph
	 * @param font
	 *            字体设置
	 * @param key
	 *            举个栗子---打印时间：20140531001 则 key = "打印时间："
	 * @param value
	 *            value= "20140531001"
	 * @param num
	 *            key之前多少个空格
	 * @throws DocumentException
	 */
	public static void addChunk(Paragraph paragraph, Font font, String key, String value, int num) throws DocumentException {
		StringBuffer sb = new StringBuffer();
		if (num > 0) {
			for (int i = 0; i < num; i++) {
				sb.append(" ");
			}
		}
		Chunk c1 = new Chunk(sb.toString() + key, font);
		Chunk c2 = new Chunk(value, font);
		paragraph.add(c1);
		paragraph.add(c2);
		paragraph.setAlignment(Element.ALIGN_LEFT);
	}
	
	/**
	 * 给文字添加下划线方法
	 * @param paragraph 具体的某个段落
	 * @param message 具体要添加下划线的文字
	 * @param isUnderline 是否需要添加下划线 ，true的时候添加下划线，false不添加
	 */
	public static void addUnderlineChunk(Paragraph paragraph, String message, boolean isUnderline) {
		Chunk underline = new Chunk(message);
		// 是否需要添加下划线
		if (isUnderline) {
			// 添加下划线
			underline.setUnderline(0.1f, -1f);
		}
		paragraph.add(underline);
	}

	/**
	 * 添加表格信息方法
	 *
	 * @param table
	 *            创建的表格
	 * @param paragraphValue
	 *            填充表格的值信息
	 * @param font
	 *            字体的大小
	 * @param colSpan
	 *            是否跨列
	 * @param rowSpan
	 *            是否跨行
	 * @param colSize
	 *            具体跨几列
	 * @param rowSize
	 *            具体跨几行
	 */
	public static void addTableCell(PdfPTable table, String paragraphValue,
			Font font, boolean colSpan, boolean rowSpan, int colSize,
			int rowSize) {
		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Paragraph(paragraphValue, font));
		// 居中设置
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// 设置表格的高度
		cell.setMinimumHeight(26);
		if (rowSpan) {// 是否跨行
			cell.setRowspan(rowSize);
		}
		if (colSpan) {// 是否跨列
			cell.setColspan(colSize);
		}
		// 具体的某个cell加入到表格
		table.addCell(cell);
	}

	/**
	 * 格式化时间方法 生成诸如：2017年9月28日，2019-09-28
	 *
	 * @param forMatContext
	 *            具体的格式化格式 如：yyyyMMdd、 HH:mm:ss
	 * @return 格式化后的字符串
	 */
	public static String getDateFormatString(String forMatContext) {
		final ThreadLocal<DateFormat> df = ThreadLocal
				.withInitial(() -> new SimpleDateFormat(forMatContext));
		DateFormat dateFormat = df.get();
		return dateFormat.format(new Date());
	}


	// 定义全局的字体静态变量
	private static Font titlefont;
	private static Font headfont;
	private static Font keyfont;
	private static Font fontChina12;

	// 静态代码块
	static {
		try {
			// 不同字体（这里定义为同一种字体：包含不同字号、不同style）
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			titlefont = new Font(bfChinese, 16, Font.BOLD);
			headfont = new Font(bfChinese, 14, Font.BOLD);
			keyfont = new Font(bfChinese, 10, Font.BOLD);
			fontChina12 = new Font(bfChinese, 12, Font.NORMAL);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void createPdf(String filePath, OutputStream outputStream) throws Exception{

		//String filePath = "D:\\test.pdf";
	/*	File file = new File(filePath);
		if (!file.exists()){
			file.createNewFile();
		}*/
		//outputStream(file);

		Document document = new Document();
		PdfWriter.getInstance(document,outputStream);
		document.open();

		PdfPTable table = new PdfPTable(6);

		/* 企业全称 */
		PdfUtil.addTableCell(table, "企业全称", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "蔚来", fontChina12, true, false, 2, 0);//跨两列

		/* 企业简称 */
		PdfUtil.addTableCell(table, "企业简称", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "WL", fontChina12, true, false, 2, 0);//跨两列

		/* 法定代表人 */
		PdfUtil.addTableCell(table, "法定代表人", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "大口大口", fontChina12, true, false, 2, 0);//跨两列

		/* 联系电话 */
		PdfUtil.addTableCell(table, "联系电话", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "132431", fontChina12, true, false, 2, 0);//跨两列

		/* 电子邮箱 */
		PdfUtil.addTableCell(table, "电子邮箱", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "13431135@xxx.com", fontChina12, true, false, 2, 0);//跨两列

		/* 企业网址 */
		PdfUtil.addTableCell(table, "企业网址", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "www.wl.com", fontChina12, true, false, 2, 0);//跨两列

		/* 营业执照号 */
		PdfUtil.addTableCell(table, "营业执照号", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "djfdkfj1214312521", fontChina12, true, false, 2, 0);//跨两列

		/* 注册日期 */
		PdfUtil.addTableCell(table, "注册日期", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "2019-10-30", fontChina12, true, false, 2, 0);//跨两列

		/* 企业住址 */
		PdfUtil.addTableCell(table, "企业住址", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "地球", fontChina12, true, false, 2, 0);//跨两列

		/* 注册地 */
		PdfUtil.addTableCell(table, "注册地", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "大地球", fontChina12, true, false, 2, 0);//跨两列

		/* 注册资本（万元） */
		PdfUtil.addTableCell(table, "注册资本（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "11111", fontChina12, true, false, 5, 0);//跨五列

		/* 经营范围 */
		PdfUtil.addTableCell(table, "经营范围", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "sadfkljajfadklj", fontChina12, true, false, 5, 0);//跨五列

		/* 财务状况 */
		PdfUtil.addTableCell(table, "财务状况", fontChina12, false, true, 0, 4);// 跨四行

		PdfUtil.addTableCell(table, "年份\n（期限）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "总资产\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "净资产\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "营业收入\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "净利润\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		/* ------->> 财务状况结束 <<-------- */

		PdfPCell cell46 = new PdfPCell();
		cell46.setRowspan(2);
		cell46.setPhrase(new Paragraph("备注", fontChina12));
		cell46.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell46.setMinimumHeight(100);
		table.addCell(cell46);
		PdfPCell cell47 = new PdfPCell();
		cell47.setColspan(5);
		cell47.setPhrase(new Paragraph(" ", fontChina12));
		cell47.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell47.setMinimumHeight(100);
		table.addCell(cell47);

		document.add(table);

		document.close();


	}


	public static void main(String[] args) throws Exception{

		String filePath = "D:\\test.pdf";
		File file = new File(filePath);
		if (!file.exists()){
			file.createNewFile();
		}

		Document document = new Document();
		PdfWriter.getInstance(document,new FileOutputStream(file));
		document.open();

		PdfPTable table = new PdfPTable(6);

		/* 企业全称 */
		PdfUtil.addTableCell(table, "企业全称", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "蔚来", fontChina12, true, false, 2, 0);//跨两列

		/* 企业简称 */
		PdfUtil.addTableCell(table, "企业简称", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "WL", fontChina12, true, false, 2, 0);//跨两列

		/* 法定代表人 */
		PdfUtil.addTableCell(table, "法定代表人", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "大口大口", fontChina12, true, false, 2, 0);//跨两列

		/* 联系电话 */
		PdfUtil.addTableCell(table, "联系电话", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "132431", fontChina12, true, false, 2, 0);//跨两列

		/* 电子邮箱 */
		PdfUtil.addTableCell(table, "电子邮箱", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "13431135@xxx.com", fontChina12, true, false, 2, 0);//跨两列

		/* 企业网址 */
		PdfUtil.addTableCell(table, "企业网址", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "www.wl.com", fontChina12, true, false, 2, 0);//跨两列

		/* 营业执照号 */
		PdfUtil.addTableCell(table, "营业执照号", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "djfdkfj1214312521", fontChina12, true, false, 2, 0);//跨两列

		/* 注册日期 */
		PdfUtil.addTableCell(table, "注册日期", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "2019-10-30", fontChina12, true, false, 2, 0);//跨两列

		/* 企业住址 */
		PdfUtil.addTableCell(table, "企业住址", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "地球", fontChina12, true, false, 2, 0);//跨两列

		/* 注册地 */
		PdfUtil.addTableCell(table, "注册地", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "大地球", fontChina12, true, false, 2, 0);//跨两列

		/* 注册资本（万元） */
		PdfUtil.addTableCell(table, "注册资本（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "11111", fontChina12, true, false, 5, 0);//跨五列

		/* 经营范围 */
		PdfUtil.addTableCell(table, "经营范围", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "sadfkljajfadklj", fontChina12, true, false, 5, 0);//跨五列

		/* 财务状况 */
		PdfUtil.addTableCell(table, "财务状况", fontChina12, false, true, 0, 4);// 跨四行

		PdfUtil.addTableCell(table, "年份\n（期限）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "总资产\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "净资产\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "营业收入\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, "净利润\n（万元）", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		PdfUtil.addTableCell(table, " ", fontChina12, false, false, 0, 0);
		/* ------->> 财务状况结束 <<-------- */

		PdfPCell cell46 = new PdfPCell();
		cell46.setRowspan(2);
		cell46.setPhrase(new Paragraph("备注", fontChina12));
		cell46.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell46.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell46.setMinimumHeight(100);
		table.addCell(cell46);
		PdfPCell cell47 = new PdfPCell();
		cell47.setColspan(5);
		cell47.setPhrase(new Paragraph(" ", fontChina12));
		cell47.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell47.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell47.setMinimumHeight(100);
		table.addCell(cell47);

		document.add(table);

		document.close();


	}
	
}
