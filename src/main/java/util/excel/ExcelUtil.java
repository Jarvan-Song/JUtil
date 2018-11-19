package util.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import util.collection.CollectionUtil;
import util.string.StringUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ExcelUtil {

    public static void write2Excel(HttpServletResponse response, String fileName, List data, Class entityType, List<ExcelFormat> formats) throws Exception {
        if(response == null || data == null || formats == null || entityType == null) return;
        response.setHeader("Pragma", "public");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/msexcel;charset=UTF-8");
        writeExcel2Stream(response.getOutputStream(), data, entityType, formats);
    }

    public static void writeExcel2Stream(OutputStream os, List data, Class entityType, List<ExcelFormat> formats) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();               // XSSF 07之后  HSSF 07 之前 后缀不一样
        XSSFSheet sheet = workbook.createSheet();                 // sheet
        XSSFCreationHelper creationHelper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        Row row = sheet.createRow(0);
        ExcelFormat format;
        // 表头
        for (int i = 0, l = formats.size(); i < l; i++) {
            format = formats.get(i);
            Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
            cell.setCellValue(format.getTh());
            sheet.setColumnWidth(i, format.getWidth() * 256);
            if (StringUtil.isNotEmpty(format.getComment())) {
                Comment comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 1, i + 1, 3, i + 5));
                RichTextString text = creationHelper.createRichTextString(format.getComment());
                comment.setString(text);
                comment.setAuthor("chp");
                cell.setCellComment(comment);
            }
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            style.setFont(font);//单元格样式
            cell.setCellStyle(style);//给cell1这个单元格设置样式
        }
        // 数据
        if (CollectionUtil.isNotEmpty(data)) {
            Row r;
            Object o;
            for (int i = 0, l = data.size(); i < l; i++) {
                r = sheet.createRow(i + 1);
                o = data.get(i);
                // 逐列写入数据
                ExcelFormat f;
                for (int j = 0, s = formats.size(); j < s; j++) {
                    f = formats.get(j);
                    // 通过反射和递归获取最终值
                    String value = getFinalValue(entityType, o, 0, f.getField().split("\\."));
                    Cell c = r.createCell(j, Cell.CELL_TYPE_STRING);
                    c.setCellValue(value);
                }
            }
            data.clear();
        }
        workbook.write(os);
    }

    /**
     * 通过反射获取属性值
     *
     * @param entityType
     * @param o
     * @param depth
     * @param splitFields
     * @return
     * @throws Exception
     * @author chaihaipeng
     */
    private static String getFinalValue(Class entityType, Object o, int depth, String[] splitFields) throws Exception {
        Field f = null;
        for (; entityType != Object.class; entityType = entityType.getSuperclass()) {
            try {
                f = entityType.getDeclaredField(splitFields[depth]);
                if (f != null) break;
            } catch (Exception e) {
            }
        }
        Class fieldClass = f.getType();
        if (!f.isAccessible()) {
            f.setAccessible(true);
        }
        if (depth < splitFields.length - 1) {
            return getFinalValue(fieldClass, f.get(o), depth + 1, splitFields);
        } else {
            String value;
            Object target = f.get(o);
            if (target instanceof Date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = format.format(target);
            } else {
                if (target == null) {
                    value = "";
                } else {
                    value = target.toString();
                }
            }
            return value;
        }
    }
}
