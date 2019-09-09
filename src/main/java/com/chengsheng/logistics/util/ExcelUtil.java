package com.chengsheng.logistics.util;

import com.chengsheng.logistics.business.order.vo.OrderExcelVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;

/**
 * @program: logistics->ExcelUtil
 * @description: Excel帮助类
 * @author: Gu Yu Long
 * @date: 2019/09/04 15:48:21
 **/
public class ExcelUtil {


    public static void createCustomerWorkBook(OrderExcelVo data, String[] keys,
                                              String[] titleArr, String fileName, HttpServletResponse response) {
        // 读取模版创建
        FileInputStream inputStream;

        // 创建excel工作簿
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        int pageSize = 8;
        float lineHeight = 18;
        try {
            // 读取模版创建
            File excel = ResourceUtils.getFile("/opt/excelTemplates/template.xlsx");
            response.reset();
            // xls的文件
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes("gb2312"), "iso-8859-1"));
//            HSSFWorkbook workbook = new HSSFWorkbook();
            // xlsx的文件
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes("gb2312"), "iso-8859-1"));
            // 通过模版创建
            inputStream = new FileInputStream(excel);
            Workbook workbook = new XSSFWorkbook(inputStream);
//            Workbook workbook = new XSSFWorkbook();
            // 读取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
//            Sheet sheet = workbook.createSheet("sheet1");

            // 设置宽度
            sheet.setColumnWidth((short) 0, 256*10+184);
            sheet.setColumnWidth((short) 1, 256*18+184);
            sheet.setColumnWidth((short) 2, 256*7+184);
            sheet.setColumnWidth((short) 3, 256*8+184);
            sheet.setColumnWidth((short) 4, 256*6+184);
            sheet.setColumnWidth((short) 5, 256*8+184);
            sheet.setColumnWidth((short) 6, 256*12+184);
            sheet.setColumnWidth((short) 7, 256*6+184);
            sheet.setColumnWidth((short) 8, 256*11+184);
            sheet.setColumnWidth((short) 9, 256*2+184);

            // 数据样式
            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框
            dataCellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框
            dataCellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框
            dataCellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框
            dataCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            dataCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            Font dataFont = workbook.createFont();
            dataFont.setFontName("微软雅黑");
            dataFont.setFontHeightInPoints((short) 9);// 设置字体大小
            dataCellStyle.setFont(dataFont);
            dataCellStyle.setWrapText(true);// 强制换行
            // 垂直居下 水平居中 无边框 9号微软雅黑
            CellStyle cellStyleNoBorder = workbook.createCellStyle();
            cellStyleNoBorder.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
            cellStyleNoBorder.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyleNoBorder.setFont(dataFont);
            // 垂直居下 水平居左 无边框 9号微软雅黑
            CellStyle cellStyleNoBorderLeft = workbook.createCellStyle();
            cellStyleNoBorderLeft.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
            cellStyleNoBorderLeft.setAlignment(CellStyle.ALIGN_LEFT);
            cellStyleNoBorderLeft.setWrapText(true);
            cellStyleNoBorderLeft.setFont(dataFont);
            // 垂直居下 水平居右 无边框 10号微软雅黑
            CellStyle cellStyleNoBorderRight = workbook.createCellStyle();
            cellStyleNoBorderRight.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
            cellStyleNoBorderRight.setAlignment(CellStyle.ALIGN_RIGHT);
            Font titleFont = workbook.createFont();
            titleFont.setFontName("微软雅黑");
            titleFont.setFontHeightInPoints((short) 10);// 设置字体大小
            cellStyleNoBorderRight.setFont(titleFont);
            // 抬头公司名称样式
            CellStyle companyCellStyle = workbook.createCellStyle();
            companyCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            companyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            Font companyFont = workbook.createFont();
            companyFont.setFontName("微软雅黑");
            companyFont.setFontHeightInPoints((short) 18);// 设置字体大小
            companyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
            companyCellStyle.setFont(companyFont);
            // 垂直居中 水平居中 无边框 9号微软雅黑 自动换行（页码样式）
            CellStyle pageCellStyle = workbook.createCellStyle();
            pageCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            pageCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            pageCellStyle.setFont(dataFont);
            pageCellStyle.setWrapText(true);

            // 开始写入数据
            int headRowNum = 0;  // 间隔行数
            int allPage = data.getDataList().size()/pageSize;   // 总页数
            int yuShu = data.getDataList().size() % pageSize;   // 记录余数
            if(yuShu != 0){    // 判断余数是否为0
                allPage++;
                yuShu = pageSize - yuShu;
            }
            int page = 1;
            BigDecimal pageTotalNumber = BigDecimal.ZERO;
            BigDecimal pageTotalWeight = BigDecimal.ZERO;
            BigDecimal pageTotalAmount = BigDecimal.ZERO;
            for (int i = 0; i < data.getDataList().size(); i++) {
                if(i % pageSize == 0){
                    // 统一合并所有该合并单元格 参数为 起始行, 终止行, 起始列, 终止列
                    CellRangeAddress cra0 = new CellRangeAddress(i + headRowNum, i + headRowNum , 0, 6); // 公司
                    CellRangeAddress cra1 = new CellRangeAddress(i + headRowNum, i + headRowNum , 7, 8); // 编号
                    CellRangeAddress cra2 = new CellRangeAddress(i + headRowNum + 1, i + headRowNum + 1, 0, 8); // 地址
                    CellRangeAddress cra3 = new CellRangeAddress(i + headRowNum + 2, i + headRowNum + 2, 0, 6); // 主营
                    CellRangeAddress cra4 = new CellRangeAddress(i + headRowNum + 2, i + headRowNum + 2, 7, 8); // 结算方式
                    CellRangeAddress cra5 = new CellRangeAddress(i + headRowNum + 3, i + headRowNum + 3, 1, 4); // 客户信息
                    CellRangeAddress cra6 = new CellRangeAddress(i + headRowNum + 13, i + headRowNum + 13, 0, 1); // 合计
                    CellRangeAddress cra7 = new CellRangeAddress(i + headRowNum + 14, i + headRowNum + 14, 1, 4); // 总计大写数据
                    CellRangeAddress cra8 = new CellRangeAddress(i + headRowNum + 14, i + headRowNum + 14, 5, 6); // 提货人签字
                    CellRangeAddress cra9 = new CellRangeAddress(i + headRowNum + 15, i + headRowNum + 16, 0, 0); // 说明
                    CellRangeAddress cra10 = new CellRangeAddress(i + headRowNum + 15, i + headRowNum + 16, 1, 8); // 说明内容
                    CellRangeAddress cra11 =new CellRangeAddress(i + headRowNum + 17, i + headRowNum + 17, 0, 8); // 联系方式
                    CellRangeAddress cra12 =new CellRangeAddress(i + headRowNum, i + headRowNum + 17, 9, 9); // 页码
                    for(int index = 0; index < 11; index++){
                        CellRangeAddress cra = new CellRangeAddress(i + headRowNum + 4 + index, i + headRowNum + 4 + index, 7, 8); // 备注
                        sheet.addMergedRegion(cra);
                    }

                    sheet.addMergedRegion(cra0);
                    sheet.addMergedRegion(cra1);
                    sheet.addMergedRegion(cra2);
                    sheet.addMergedRegion(cra3);
                    sheet.addMergedRegion(cra4);
                    sheet.addMergedRegion(cra5);
                    sheet.addMergedRegion(cra6);
                    sheet.addMergedRegion(cra7);
                    sheet.addMergedRegion(cra8);
                    sheet.addMergedRegion(cra9);
                    sheet.addMergedRegion(cra10);
                    sheet.addMergedRegion(cra11);
                    sheet.addMergedRegion(cra12);

                    // 创建公司行
                    Row companyRow = sheet.createRow(i+headRowNum);
                    companyRow.setHeightInPoints((float)22.5);
                    Cell companyCell = companyRow.createCell(0);
                    companyCell.setCellValue("                    南昌成晟实业有限公司    结算单");
                    companyCell.setCellStyle(companyCellStyle);
                    // 创建订单编号行
                    Cell orderNoCell = companyRow.createCell(7);
                    orderNoCell.setCellValue(data.getOrderEntity().getOrderNo()==null?"":"No:"+data.getOrderEntity().getOrderNo());
                    orderNoCell.setCellStyle(cellStyleNoBorder);
                    // 创建页码行
                    Cell pageCell = companyRow.createCell(9);
                    pageCell.setCellValue("第\n"+page+"\n页\n/\n共\n"+allPage+"\n页");
                    pageCell.setCellStyle(pageCellStyle);

                    // 创建地址行
                    Row addressRow = sheet.createRow(i+headRowNum+1);
                    addressRow.setHeightInPoints((float)16.5);
                    Cell addressCell = addressRow.createCell(0);
                    addressCell.setCellValue("地址：南昌市解放东路进程国际49栋21号");
                    addressCell.setCellStyle(cellStyleNoBorderRight);
                    // 创建主营信息行
                    Row explainRow = sheet.createRow(i+headRowNum+2);
                    explainRow.setHeightInPoints((float)13.5);
                    Cell explainCell = explainRow.createCell(0);
                    explainCell.setCellValue("主营：中厚板、热板、花纹板、低合金板、船板、热卷、低合金卷、花纹卷、角钢、槽钢、工字钢");
                    explainCell.setCellStyle(cellStyleNoBorder);
                    // 创建结算方式行
                    Cell jieSuanCell = explainRow.createCell(7);
                    jieSuanCell.setCellValue("结算方式："+data.getOrderEntity().getPayStatus().getValue());
                    jieSuanCell.setCellStyle(cellStyleNoBorder);
                    // 创建购货单位行（客户信息）
                    Row customerRow = sheet.createRow(i+headRowNum+3);
                    customerRow.setHeightInPoints((float)18);
                    Cell customerLabelCell = customerRow.createCell(0);
                    customerLabelCell.setCellValue("购货单位");
                    customerLabelCell.setCellStyle(dataCellStyle);
                    Cell customerCell = customerRow.createCell(1);
                    customerCell.setCellValue(data.getOrderEntity().getCustomerName()==null?"":data.getOrderEntity().getCustomerName());
                    customerCell.setCellStyle(cellStyleNoBorderLeft);
                    // 设置合并单元格边框样式
                    setCraBorder(i+headRowNum+3,i+headRowNum+3,1,4,sheet, workbook);
                    // 创建发票类型行
                    Cell invoiceTypeLabelCell = customerRow.createCell(5);
                    invoiceTypeLabelCell.setCellValue("发票类型");
                    invoiceTypeLabelCell.setCellStyle(dataCellStyle);
                    Cell invoiceTypeCell = customerRow.createCell(6);
                    invoiceTypeCell.setCellValue(data.getOrderEntity().getInvoiceType()==null?"":data.getOrderEntity().getInvoiceType());
                    invoiceTypeCell.setCellStyle(dataCellStyle);
                    // 创建提送货日期行
                    Cell dateLabelCell = customerRow.createCell(7);
                    dateLabelCell.setCellValue("日期");
                    dateLabelCell.setCellStyle(dataCellStyle);
                    Cell dateCell = customerRow.createCell(8);
                    dateCell.setCellValue(DateUtil.getDate(data.getOrderEntity().getGetGoodsDate(),null));
                    dateCell.setCellStyle(dataCellStyle);

                    //创建第五行
                    Row row = sheet.createRow(i+headRowNum+4);
                    row.setHeightInPoints((float)18);
                    Cell cell;
                    // 标题样式
                    CellStyle headStyle = workbook.createCellStyle();
                    headStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框
                    headStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框
                    headStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框
                    headStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框
                    headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
                    headStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
                    Font headFont = workbook.createFont();
                    headFont.setFontName("微软雅黑");
                    headFont.setFontHeightInPoints((short) 9);// 设置字体大小
                    headStyle.setFont(headFont);
                    //插入第一行数据的表头
                    for (int h = 0; h < titleArr.length; h++) {
                        cell = row.createCell(h);
                        cell.setCellValue(titleArr[h]);
                        if(h == titleArr.length -1){
                            // 备注行使用无边框样式，由其他地方添加合并单元格边框
                            cell.setCellStyle(cellStyleNoBorder);
                            // 设置合并单元格边框样式
                            setCraBorder(i+headRowNum+4,i+headRowNum+4,7,8,sheet, workbook);
                        }else {
                            cell.setCellStyle(headStyle);
                        }
                    }
                    headRowNum += 5;
                }

                Row nRow = sheet.createRow(i+headRowNum);
                nRow.setHeightInPoints(lineHeight);
                for (int j = 0; j < keys.length; j++) {
                    Cell nCell = nRow.createCell(j);
                    nCell.setCellValue(data.getDataList().get(i).get(keys[j])==null?"":data.getDataList().get(i).get(keys[j]).toString());
                    if(j == keys.length -1){
                        // 备注行使用无边框样式，由其他地方添加合并单元格边框
                        nCell.setCellStyle(cellStyleNoBorder);
                        // 设置合并单元格边框样式
                        setCraBorder(i+headRowNum,i+headRowNum,7,8,sheet, workbook);
                    }else {
                        nCell.setCellStyle(dataCellStyle);
                    }
                    // 统计页内合计
                    if(allPage > 1){
                        if(j == 2) {
                            pageTotalNumber = pageTotalNumber.add(data.getDataList().get(i).get(keys[j]) == null ? BigDecimal.ZERO : new BigDecimal(data.getDataList().get(i).get(keys[j]).toString()));
                        }
                        if(j == 3) {
                            pageTotalWeight = pageTotalWeight.add(data.getDataList().get(i).get(keys[j]) == null ? BigDecimal.ZERO : new BigDecimal(data.getDataList().get(i).get(keys[j]).toString()));
                        }
                        if(j == 6) {
                            pageTotalAmount = pageTotalAmount.add(data.getDataList().get(i).get(keys[j]) == null ? BigDecimal.ZERO : new BigDecimal(data.getDataList().get(i).get(keys[j]).toString()));
                        }
                    }
                }
                // 如果数据行数不满足8行，则进行补充空白单元格。
                if(i == data.getDataList().size()-1 && yuShu != 0){
                    for(int j = 0; j < yuShu; j++){
                        headRowNum += 1;
                        Row blankRow = sheet.createRow(i+headRowNum);
                        blankRow.setHeightInPoints((float)18);
                        for (int k = 0; k < keys.length; k++) {
                            Cell blankCell = blankRow.createCell(k);
                            if(k == keys.length -1){
                                // 备注行使用无边框样式，由其他地方添加合并单元格边框
                                blankCell.setCellStyle(cellStyleNoBorder);
                                // 设置合并单元格边框样式
                                setCraBorder(i+headRowNum,i+headRowNum,7,8,sheet, workbook);
                            }else {
                                blankCell.setCellStyle(dataCellStyle);
                            }
                        }
                    }
                }

                // 数据填充完的合计
                if(i == data.getDataList().size()-1 || ((i+1)%8 == 0)) {
                    //  创建合计行
                    Row totalRow = sheet.createRow(i + headRowNum + 1);
                    totalRow.setHeightInPoints((float)18);
                    Cell totalLabelCell = totalRow.createCell(0);
                    totalLabelCell.setCellValue("合计");
                    totalLabelCell.setCellStyle(dataCellStyle);
                    // 创建合计数量行
                    Cell totalNumberCell = totalRow.createCell(2);
                    if(allPage > 1){
                        totalNumberCell.setCellValue(pageTotalNumber.toString());   // 页内合计
                    }else {
                        totalNumberCell.setCellValue(data.getOrderEntity().getTotalNumber() == null ? "" : data.getOrderEntity().getTotalNumber().toString());
                    }
                    totalNumberCell.setCellStyle(dataCellStyle);
                    // 创建合计重量行
                    Cell totalWeightCell = totalRow.createCell(3);
                    if(allPage > 1){
                        totalWeightCell.setCellValue(pageTotalWeight.toString());   // 页内合计
                    }else {
                        totalWeightCell.setCellValue(data.getOrderEntity().getTotalWeight() == null ? "" : data.getOrderEntity().getTotalWeight().toString());
                    }
                    totalWeightCell.setCellStyle(dataCellStyle);
                    // 创建合计金额行
                    Cell totalAmountCell = totalRow.createCell(6);
                    if(allPage > 1){
                        totalAmountCell.setCellValue(pageTotalAmount.toString());   // 页内合计
                    }else {
                        totalAmountCell.setCellValue(data.getOrderEntity().getTotalAmount() == null ? "" : data.getOrderEntity().getTotalAmount().toString());
                    }
                    totalAmountCell.setCellStyle(dataCellStyle);
                    // 创建空白合计行
                    Cell blankTotalCell1 = totalRow.createCell(4);
                    blankTotalCell1.setCellStyle(dataCellStyle);
                    Cell blankTotalCell2 = totalRow.createCell(5);
                    blankTotalCell2.setCellStyle(dataCellStyle);
                    Cell blankTotalCell3 = totalRow.createCell(7);
                    blankTotalCell3.setCellStyle(dataCellStyle);
                    // 设置合并单元格边框样式
                    setCraBorder(i+headRowNum+1,i+headRowNum+1,7,8,sheet, workbook);

                    // 创建总计大写行
                    Row totalChineseLabelRow = sheet.createRow(i + headRowNum + 2);
                    totalChineseLabelRow.setHeightInPoints((float)18);
                    Cell totalChineseLabelCell = totalChineseLabelRow.createCell(0);
                    totalChineseLabelCell.setCellValue("总计大写");
                    totalChineseLabelCell.setCellStyle(dataCellStyle);
                    // 创建总计大写数据行
                    Cell totalChineseCell = totalChineseLabelRow.createCell(1);
                    if(allPage > 1){
                        totalChineseCell.setCellValue(NumberUtil.getChineseNumber(pageTotalAmount.toString()));
                    }else {
                        if (data.getOrderEntity().getTotalAmount() != null) {
                            totalChineseCell.setCellValue(NumberUtil.getChineseNumber(data.getOrderEntity().getTotalAmount().toString()));
                        }
                    }
                    totalChineseCell.setCellStyle(dataCellStyle);
                    // 创建提货人签字行
                    Cell getGoodsPersonLabelCell = totalChineseLabelRow.createCell(5);
                    getGoodsPersonLabelCell.setCellValue("提货人签字（车牌号）");
                    getGoodsPersonLabelCell.setCellStyle(dataCellStyle);
                    // 创建提货人签字行
                    Cell getGoodsPersonCell = totalChineseLabelRow.createCell(7);
                    getGoodsPersonCell.setCellValue(data.getOrderEntity().getGetGoodsPerson() == null ? "" : data.getOrderEntity().getGetGoodsPerson());
                    getGoodsPersonCell.setCellStyle(dataCellStyle);
                    // 设置合并单元格边框样式
                    setCraBorder(i+headRowNum+2,i+headRowNum+2,7,8,sheet, workbook);

                    // 创建说明行
                    Row explainRow = sheet.createRow(i + headRowNum + 3);
                    Row explainRow1 = sheet.createRow(i + headRowNum + 4);
                    explainRow.setHeightInPoints((float)18);
                    explainRow1.setHeightInPoints((float)25.5);

                    Cell explainLabelCell = explainRow.createCell(0);
                    explainLabelCell.setCellValue("说明");
                    explainLabelCell.setCellStyle(dataCellStyle);
                    // 设置合并单元格边框样式
                    setCraBorder(i+headRowNum+3,i+headRowNum+4,0,0,sheet, workbook);
                    // 创建总计大写数据行
                    Cell explainDataCell = explainRow.createCell(1);
                    explainDataCell.setCellValue("1.本结算单等同于购销合同，提货人签字后生效，购方在仓库当场清点出货数量规格，出货后销方概不负责；购方如有质量异议，请于三天内向销方说明，逾期视为货品合格。\n" +
                            "2.若不是购货单位负责人签收，将由提货人个人承担此购销合同货款。");
                    explainDataCell.setCellStyle(cellStyleNoBorderLeft);
                    // 设置合并单元格边框样式
                    setCraBorder(i+headRowNum+3,i+headRowNum+4,1,8,sheet, workbook);

                    // 创建联系电话行
                    Row contactRow = sheet.createRow(i + headRowNum + 5);
                    contactRow.setHeightInPoints((float)17.25);
                    Cell contactCell = contactRow.createCell(0);
                    contactCell.setCellValue("          电话/传真：0791-88200107                                       联系人：18779898447（曾）  13767091020（邹）");
                    contactCell.setCellStyle(cellStyleNoBorderLeft);

                    headRowNum+=5;
                    page++;// 页码增加
                    if(allPage > 1){
                        // 页内合计重置
                        pageTotalNumber = BigDecimal.ZERO;
                        pageTotalWeight = BigDecimal.ZERO;
                        pageTotalAmount = BigDecimal.ZERO;
                    }
                }

            }

            // 导出
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            if(workbook !=null){
                workbook.write(os);
            }
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @设置合并单元格样式
     * setCellStyle
     * @param rowIndex     		行开始
     * @param columnIndex  		列开始
     * @param toRowIndex 		行结束
     * @param toColumnIndex 	列结束
     * @param cellStyle			样式
     * @param sheet 		sheet表
     * @return :void
     * @Creator:GU YU LONG
     * @Date:2019年7月16日 下午2:49:19
     */
    private static void setCellStyle(int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex, CellStyle cellStyle,Sheet sheet) {
        for (int i = rowIndex; i <= toRowIndex; i++) {
            for (int j = columnIndex; j < (toColumnIndex + columnIndex); j++) {
                Row row = sheet.getRow(i);
                Cell cell;
                if (null != row) {
                    cell = row.getCell(j);
                    if (null == cell) {
                        cell = row.createCell(j);
                        cell.setCellStyle(cellStyle);
                    }
                }
            }
        }
    }

    /***
     * @description  设置合并单元格的边框样式
     * @param firstRow 起始行
     * @param lastRow  结束行
     * @param firstCol 起始列
     * @param lastCol  结束列
     * @param sheet    sheet表
     * @param workbook workbook工作簿
     * @return  void
     * @author  Gu Yu Long
     * @date    2019/9/5 15:53
     * @other
     */
    public static void setCraBorder(int firstRow, int lastRow, int firstCol, int lastCol, Sheet sheet, Workbook workbook) {
        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        RegionUtil.setBorderBottom(1, cra, sheet, workbook); // 下边框
        RegionUtil.setBorderLeft(1, cra, sheet, workbook); // 左边框
        RegionUtil.setBorderRight(1, cra, sheet, workbook); // 右边框
        RegionUtil.setBorderTop(1, cra, sheet, workbook); // 上边框
    }
}
