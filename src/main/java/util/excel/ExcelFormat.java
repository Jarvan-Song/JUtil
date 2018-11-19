package util.excel;


public class ExcelFormat {
    private String th;      // 标题
    private int width;      // 列宽
    private String field;   // 字段映射
    private String comment; // 表头注释

    public static ExcelFormat newInstance(String th, int with, String field, String comment) {
        return new ExcelFormat(th, with, field, comment);
    }

    public ExcelFormat(String th, int with, String field, String comment) {
        this.th = th;
        this.width = with;
        this.field = field;
        this.comment = comment;
    }

    public String getTh() {
        return th;
    }

    public int getWidth() {
        return width;
    }

    public String getField() {
        return field;
    }

    public String getComment() {
        return comment;
    }
}
