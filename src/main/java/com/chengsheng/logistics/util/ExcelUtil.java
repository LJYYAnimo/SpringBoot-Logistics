package com.chengsheng.logistics.util;

import com.chengsheng.logistics.business.order.vo.OrderExcelVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

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
        // 创建excel工作簿
//        File excel= new File("D:\\testTemplate.xls");
//        FileInputStream inputStream;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        int pageSize = 8;
        float lineHeight = 18;
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes("gb2312"), "iso-8859-1"));
            //定义表头
            // 通过path读取excel工作簿
//            inputStream = new FileInputStream(excel);
//            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 读取第一个sheet
            HSSFSheet sheet = workbook.createSheet("sheet1");
            // 设置宽度
            sheet.setColumnWidth((short) 0, (int)((8.25+0.71)*256));
            sheet.setColumnWidth((short) 1, (int)((15.63+0.71)*256));
            sheet.setColumnWidth((short) 2, (int)((7.13+0.71)*256));
            sheet.setColumnWidth((short) 3, (int)((6.75+0.71)*256));
            sheet.setColumnWidth((short) 4, (int)((5.38+0.71)*256));
            sheet.setColumnWidth((short) 5, (int)((7.75+0.71)*256));
            sheet.setColumnWidth((short) 6, (int)((10.38+0.71)*256));
            sheet.setColumnWidth((short) 7, (int)((5.25+0.71)*256));
            sheet.setColumnWidth((short) 8, (int)((9.38+0.71)*256));

            // 数据样式
            HSSFCellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            dataCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            dataCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            dataCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            dataCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            HSSFFont dataFont = workbook.createFont();
            dataFont.setFontName("微软雅黑");
            dataFont.setFontHeightInPoints((short) 9);// 设置字体大小
            dataCellStyle.setFont(dataFont);
            dataCellStyle.setWrapText(true);// 强制换行
            // 垂直局下 水平居中 无边框 9号微软雅黑
            HSSFCellStyle cellStyleNoBorder = workbook.createCellStyle();
            cellStyleNoBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
            cellStyleNoBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleNoBorder.setFont(dataFont);
            // 垂直局下 水平居左 无边框 9号微软雅黑
            HSSFCellStyle cellStyleNoBorderLeft = workbook.createCellStyle();
            cellStyleNoBorderLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
            cellStyleNoBorderLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cellStyleNoBorderLeft.setFont(dataFont);
            // 垂直局下 水平居右 无边框 10号微软雅黑
            HSSFCellStyle cellStyleNoBorderRight = workbook.createCellStyle();
            cellStyleNoBorderRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
            cellStyleNoBorderRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            HSSFFont titleFont = workbook.createFont();
            titleFont.setFontName("微软雅黑");
            titleFont.setFontHeightInPoints((short) 10);// 设置字体大小
            cellStyleNoBorderRight.setFont(titleFont);

            // 开始写入数据
            int headRowNum = 0;  // 间隔行数
            int allPage = data.getDataList().size()/pageSize;   // 总页数
            int yuShu = data.getDataList().size() % pageSize;   // 记录余数
            if(yuShu != 0){    // 判断余数是否为0
                allPage++;
                yuShu = pageSize - yuShu;
            }
            int page = 1;
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

                    // 创建公司行
                    HSSFRow companyRow = sheet.createRow(i+headRowNum);
                    HSSFCell companyCell = companyRow.createCell(0);
                    companyCell.setCellValue("南昌成晟实业有限公司    结算单");
                    companyRow.setHeightInPoints((float)22.5);
                    // 创建订单编号行
                    HSSFCell orderNoCell = companyRow.createCell(7);
                    orderNoCell.setCellValue(data.getOrderEntity().getOrderNo()==null?"":"No:"+data.getOrderEntity().getOrderNo());
                    orderNoCell.setCellStyle(cellStyleNoBorder);
                    // 创建地址行
                    HSSFRow addressRow = sheet.createRow(i+headRowNum+1);
                    addressRow.setHeightInPoints((float)16.5);
                    HSSFCell addressCell = addressRow.createCell(0);
                    addressCell.setCellValue("地址：南昌市解放东路进程国际49栋21号");
                    addressCell.setCellStyle(cellStyleNoBorderRight);
                    // 创建主营信息行
                    HSSFRow explainRow = sheet.createRow(i+headRowNum+2);
                    explainRow.setHeightInPoints((float)13.5);
                    HSSFCell explainCell = explainRow.createCell(0);
                    explainCell.setCellValue("主营：中厚板、热板、花纹板、低合金板、船板、热卷、低合金卷、花纹卷、角钢、槽钢、工字钢");
                    explainCell.setCellStyle(cellStyleNoBorder);
                    // 创建结算方式行
                    HSSFCell jieSuanCell = explainRow.createCell(7);
                    jieSuanCell.setCellValue("结算方式："+data.getOrderEntity().getPayStatus().getValue());
                    jieSuanCell.setCellStyle(cellStyleNoBorder);
                    // 创建购货单位行（客户信息）
                    HSSFRow customerRow = sheet.createRow(i+headRowNum+3);
                    customerRow.setHeightInPoints((float)18);
                    HSSFCell customerLabelCell = customerRow.createCell(0);
                    customerLabelCell.setCellValue("购货单位");
                    customerLabelCell.setCellStyle(dataCellStyle);
                    HSSFCell customerCell = customerRow.createCell(1);
                    customerCell.setCellValue(data.getOrderEntity().getCustomerName()==null?"":data.getOrderEntity().getCustomerName());
                    customerCell.setCellStyle(dataCellStyle);
                    // 创建发票类型行
                    HSSFCell invoiceTypeLabelCell = customerRow.createCell(5);
                    invoiceTypeLabelCell.setCellValue("发票类型");
                    invoiceTypeLabelCell.setCellStyle(dataCellStyle);
                    HSSFCell invoiceTypeCell = customerRow.createCell(6);
                    invoiceTypeCell.setCellValue(data.getOrderEntity().getInvoiceType()==null?"":data.getOrderEntity().getInvoiceType());
                    invoiceTypeCell.setCellStyle(dataCellStyle);
                    // 创建提送货日期行
                    HSSFCell dateLabelCell = customerRow.createCell(7);
                    dateLabelCell.setCellValue("日期");
                    dateLabelCell.setCellStyle(dataCellStyle);
                    HSSFCell dateCell = customerRow.createCell(8);
                    dateCell.setCellValue(DateUtil.getDate(data.getOrderEntity().getGetGoodsDate(),null));
                    dateCell.setCellStyle(dataCellStyle);

                    //创建第五行
                    HSSFRow row = sheet.createRow(i+headRowNum+4);
                    row.setHeightInPoints((float)18);
                    HSSFCell cell;
                    // 标题样式
                    HSSFCellStyle headStyle = workbook.createCellStyle();
                    headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
                    headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
                    headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
                    headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
                    headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
                    headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
                    HSSFFont headFont = workbook.createFont();
                    headFont.setFontName("微软雅黑");
                    headFont.setFontHeightInPoints((short) 9);// 设置字体大小
                    headStyle.setFont(headFont);
                    //插入第一行数据的表头
                    for (int h = 0; h < titleArr.length; h++) {
                        cell = row.createCell(h);
                        cell.setCellValue(titleArr[h]);
                        cell.setCellStyle(headStyle);
                    }
                    headRowNum += 5;
                }
                HSSFRow nRow = sheet.createRow(i+headRowNum);
                nRow.setHeightInPoints(lineHeight);
                for (int j = 0; j < keys.length; j++) {
                    HSSFCell nCell = nRow.createCell(j);
                    nCell.setCellValue(data.getDataList().get(i).get(keys[j])==null?"":data.getDataList().get(i).get(keys[j]).toString());
                    nCell.setCellStyle(dataCellStyle);
                }
                // 如果数据行数不满足8行，则进行补充空白单元格。
                if(i == data.getDataList().size()-1 && yuShu != 0){
                    for(int j = 0; j < yuShu; j++){
                        headRowNum += 1;
                        HSSFRow blankRow = sheet.createRow(i+headRowNum);
                        blankRow.setHeightInPoints((float)18);
                        for (int k = 0; k < keys.length; k++) {
                            HSSFCell blankCell = blankRow.createCell(k);
                            blankCell.setCellStyle(dataCellStyle);
                        }
                    }
                }

                // 数据填充完的合计
                if(i == data.getDataList().size()-1 || ((i+1)%8 == 0)) {
                    //  创建合计行
                    HSSFRow totalRow = sheet.createRow(i + headRowNum + 1);
                    totalRow.setHeightInPoints((float)18);
                    HSSFCell totalLabelCell = totalRow.createCell(0);
                    totalLabelCell.setCellValue("合计");
                    totalLabelCell.setCellStyle(dataCellStyle);
                    // 创建合计数量行
                    HSSFCell totalNumberCell = totalRow.createCell(2);
                    totalNumberCell.setCellValue(data.getOrderEntity().getTotalNumber() == null ? "" : data.getOrderEntity().getTotalNumber().toString());
                    totalNumberCell.setCellStyle(dataCellStyle);
                    // 创建合计重量行
                    HSSFCell totalWeightCell = totalRow.createCell(3);
                    totalWeightCell.setCellValue(data.getOrderEntity().getTotalWeight() == null ? "" : data.getOrderEntity().getTotalWeight().toString());
                    totalWeightCell.setCellStyle(dataCellStyle);
                    // 创建合计金额行
                    HSSFCell totalAmountCell = totalRow.createCell(6);
                    totalAmountCell.setCellValue(data.getOrderEntity().getTotalAmount() == null ? "" : data.getOrderEntity().getTotalAmount().toString());
                    totalAmountCell.setCellStyle(dataCellStyle);
                    // 创建空白合计行
                    HSSFCell blankTotalCell1 = totalRow.createCell(4);
                    blankTotalCell1.setCellStyle(dataCellStyle);
                    HSSFCell blankTotalCell2 = totalRow.createCell(5);
                    blankTotalCell2.setCellStyle(dataCellStyle);
                    HSSFCell blankTotalCell3 = totalRow.createCell(7);
                    blankTotalCell3.setCellStyle(dataCellStyle);

                    // 创建总计大写行
                    HSSFRow totalChineseLabelRow = sheet.createRow(i + headRowNum + 2);
                    totalChineseLabelRow.setHeightInPoints((float)18);
                    HSSFCell totalChineseLabelCell = totalChineseLabelRow.createCell(0);
                    totalChineseLabelCell.setCellValue("总计大写");
                    totalChineseLabelCell.setCellStyle(dataCellStyle);
                    // 创建总计大写数据行
                    HSSFCell totalChineseCell = totalChineseLabelRow.createCell(1);
                    totalChineseCell.setCellValue("大写数字");
                    totalChineseCell.setCellStyle(dataCellStyle);
                    // 创建提货人签字行
                    HSSFCell getGoodsPersonLabelCell = totalChineseLabelRow.createCell(5);
                    getGoodsPersonLabelCell.setCellValue("提货人签字（车牌号）");
                    getGoodsPersonLabelCell.setCellStyle(dataCellStyle);
                    // 创建提货人签字行
                    HSSFCell getGoodsPersonCell = totalChineseLabelRow.createCell(7);
                    getGoodsPersonCell.setCellValue(data.getOrderEntity().getGetGoodsPerson() == null ? "" : data.getOrderEntity().getGetGoodsPerson());
                    getGoodsPersonCell.setCellStyle(dataCellStyle);






                    // 创建说明行
                    HSSFRow explainRow = sheet.createRow(i + headRowNum + 3);
                    HSSFRow explainRow1 = sheet.createRow(i + headRowNum + 4);
                    explainRow.setHeightInPoints((float)18);
                    explainRow1.setHeightInPoints((float)25.5);

                    HSSFCell explainLabelCell = explainRow.createCell(0);
                    explainLabelCell.setCellValue("说明");
                    explainLabelCell.setCellStyle(dataCellStyle);
                    // 创建总计大写数据行
                    HSSFCell explainDataCell = explainRow.createCell(1);
                    explainDataCell.setCellValue("1.本结算单等同于购销合同，提货人签字后生效，购方在仓库当场清点出货数量规格，出货后销方概不负责；购方如有质量异议，请于三天内向销方说明，逾期视为货品合格。\n" +
                            "2.若不是购货单位负责人签收，将由提货人个人承担此购销合同货款。");
                    explainDataCell.setCellStyle(cellStyleNoBorderLeft);


                    // 创建联系电话行
                    HSSFRow contactRow = sheet.createRow(i + headRowNum + 5);
                    contactRow.setHeightInPoints((float)17.25);
                    HSSFCell contactCell = contactRow.createCell(0);
                    contactCell.setCellValue("          电话/传真：0791-88200107                                       联系人：18779898447（曾）  13767091020（邹）");
                    contactCell.setCellStyle(cellStyleNoBorderLeft);
                    // 使用RegionUtil类为合并后的单元格添加边框
//                    for(int index = 0; index < 11; index++){
//                        CellRangeAddress cra = new CellRangeAddress(i + headRowNum - 5 + 4 + index, i + headRowNum - 5 + 4 + index, 7, 8); // 备注
//                        sheet.addMergedRegion(cra);
//                    }
//                    RegionUtil.setBorderBottom(1, cra, sheet, workbook); // 下边框
//                    RegionUtil.setBorderLeft(1, cra, sheet, workbook); // 左边框
//                    RegionUtil.setBorderRight(1, cra, sheet, workbook); // 右边框
//                    RegionUtil.setBorderTop(1, cra, sheet, workbook); // 上边框
                }


                // 页码行
//                if((i+1) % pageSize == 0 && i != data.getDataList().size() -1){
//                    headRowNum += 1;
//                    CellRangeAddress regionPage = new CellRangeAddress(i+headRowNum,i+headRowNum,(short)0,(short)4);
//                    //加入合并单元格
//                    sheet.addMergedRegion(regionPage);
//                    //创建页码行
//                    HSSFRow pageRow = sheet.createRow(i+headRowNum);
//                    pageRow.setHeightInPoints(20);
//                    HSSFCellStyle pageStyle = workbook.createCellStyle();
//                    pageStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//                    pageStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
//                    HSSFFont pageFont = workbook.createFont();
//                    pageFont.setFontName("微软雅黑");
//                    pageFont.setFontHeightInPoints((short) 14);// 设置字体大小
//                    pageStyle.setFont(pageFont);
//                    HSSFCell ncell = pageRow.createCell(0);
//                    ncell.setCellValue("第 "+page+" 页 / 共 "+allPage+" 页");
//                    ncell.setCellStyle(pageStyle);
//                    page++;
//                }
            }
//            // 增加合计列
//            CellRangeAddress region4 = new CellRangeAddress(data.getDataList().size()+headRowNum,data.getDataList().size()+headRowNum,(short)0,(short)2);
//            //加入合并单元格
//            sheet.addMergedRegion(region4);
//            HSSFRow countRow = sheet.createRow(data.getDataList().size()+headRowNum);
//            countRow.setHeightInPoints(20);
//            HSSFCell countCell = countRow.createCell(0);
//            countCell.setCellValue("合计");
//            setCellStyle(data.getDataList().size()+headRowNum,0,data.getDataList().size()+headRowNum,4,dataCellStyle,sheet);
//            countCell.setCellStyle(dataCellStyle);
//            HSSFCell sumOrderFeeCell = countRow.createCell(3);
//            sumOrderFeeCell.setCellValue(sumOrderFee+"");
//            sumOrderFeeCell.setCellStyle(dataCellStyle);
//            HSSFCell sumServiceFeeCell = countRow.createCell(4);
//            sumServiceFeeCell.setCellValue(sumServiceFee+"");
//            sumServiceFeeCell.setCellStyle(dataCellStyle);
//
//            // 增加合计费用列
//            CellRangeAddress region5 = new CellRangeAddress(data.getDataList().size()+headRowNum+1,data.getDataList().size()+headRowNum+1,(short)0,(short)2);
//            CellRangeAddress region6 = new CellRangeAddress(data.getDataList().size()+headRowNum+1,data.getDataList().size()+headRowNum+1,(short)3,(short)4);
//            //加入合并单元格
//            sheet.addMergedRegion(region5);
//            sheet.addMergedRegion(region6);
//            // 最后一页页码
//            HSSFRow allCountRow = sheet.createRow(data.getDataList().size()+headRowNum+1);
//            allCountRow.setHeightInPoints(20);
//            HSSFCell pageEndCell = allCountRow.createCell(0);
//            HSSFCellStyle pageStyle = workbook.createCellStyle();
//            pageStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//            pageStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
//            HSSFFont pageFont = workbook.createFont();
//            pageFont.setFontName("微软雅黑");
//            pageFont.setFontHeightInPoints((short) 14);// 设置字体大小
//            pageStyle.setFont(pageFont);
//            pageEndCell.setCellValue("第 "+allPage+" 页 / 共 "+allPage+" 页");
//            pageEndCell.setCellStyle(pageStyle);
//            // 总费用合计
//            HSSFCell allCountCell = allCountRow.createCell(3);
//            allCountCell.setCellValue(sumOrderFee.add(sumServiceFee)+"");
//            // 数据样式
//            HSSFCellStyle allCountCellStyle = workbook.createCellStyle();
//            allCountCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
//            allCountCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
//            HSSFFont allCountFont = workbook.createFont();
//            allCountFont.setFontName("Arial");
//            allCountFont.setFontHeightInPoints((short) 12);// 设置字体大小
//            allCountCellStyle.setFont(allCountFont);
//            allCountCell.setCellStyle(allCountCellStyle);

            // 导出
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            if(workbook!=null){
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
     * @param hssfSheet 		sheet表
     * @return :void
     * @Creator:GU YU LONG
     * @Date:2019年7月16日 下午2:49:19
     */
    private static void setCellStyle(int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex, HSSFCellStyle cellStyle,HSSFSheet hssfSheet) {
        for (int i = rowIndex; i <= toRowIndex; i++) {
            for (int j = columnIndex; j < (toColumnIndex + columnIndex); j++) {
                HSSFRow row = hssfSheet.getRow(i);
                HSSFCell cell;
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
}
