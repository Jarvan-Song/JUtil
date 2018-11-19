package util.excel;

import com.google.common.collect.Lists;

import java.util.List;

public class Constants {
    public static final List<ExcelFormat> DEFAULT_EXCEL_FORMAT = Lists.newArrayList(
            ExcelFormat.newInstance("id", 10, "id", null),
            ExcelFormat.newInstance("", 15, "", null),
            ExcelFormat.newInstance("",   10, "", null),
            ExcelFormat.newInstance("",   20, "", null),
            ExcelFormat.newInstance("", 20, "", null),
            ExcelFormat.newInstance("",   10, "", null),
            ExcelFormat.newInstance("",  20, "", null),
            ExcelFormat.newInstance("", 10, "", null),
            ExcelFormat.newInstance("", 10, "", null),
            ExcelFormat.newInstance("", 10, "", null),
            ExcelFormat.newInstance("状态",         10, "status", "")
    );
}