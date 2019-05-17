package util.txt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * txt下载工具类，以\t为分隔符，方便粘贴到excel
 * 有点：文件小速度快
 */
public class TxtUtils {
    private static String header = "lemmaId" + "\t" + "lemmaTitle" + "\t" + "pvPv" + "\t" + "pvMb" + "\t" + "date";

    public String downTxt(HttpServletRequest request, HttpServletResponse response, List<Pv> list, String fileName) {
        long fileLength = -1;
        response.setHeader("Content-Length", String.valueOf(fileLength));
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentType("application/octet-stream; charset=gbk");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(header + "\n");
            if (list != null && list.size() > 0) {
                for (Pv pvBean : list) {
                    if (pvBean != null) {
                        printWriter.print(pvBean.getFormat() + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
        return null;
    }
}
