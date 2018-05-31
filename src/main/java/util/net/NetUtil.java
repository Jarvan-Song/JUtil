package util.net;


import util.string.StringUtil;
import javax.servlet.ServletRequest;

/**
 * ServletRequest 常用方法封装
 */
public class NetUtil {

    public static String getStringParameter(ServletRequest request, String name, String defaults) {
        return getStringParameter(request, name, defaults, true);
    }

    public static String getStringParameter(ServletRequest request, String name, String defaults, boolean decode) {
        String str = request.getParameter(name);
        if (decode) {
            str = StringUtil.decodeNetUnicode(str);
        }
        return StringUtil.convertString(str, defaults);
    }


    public static int getIntParameter(ServletRequest request, String name, int defaults) {
        return StringUtil.convertInt(request.getParameter(name), defaults);
    }

    public static int getIntParameter(ServletRequest request, String name) {
        return getIntParameter(request, name, 0);
    }


    public static long getLongParameter(ServletRequest request, String name, long defaults) {
        return StringUtil.convertLong(request.getParameter(name), defaults);
    }


    public static long getLongParameter(ServletRequest request, String name) {
        return getLongParameter(request, name, 0);
    }


    public static double getDoubleParameter(ServletRequest request, String name, double defaults) {
        return StringUtil.convertDouble(request.getParameter(name), defaults);
    }


    public static double getDoubleParameter(ServletRequest request, String name) {
        return getDoubleParameter(request, name, 0.0);
    }


    public static short getShortParameter(ServletRequest request, String name, short defaults) {
        return StringUtil.convertShort(request.getParameter(name), defaults);
    }

    public static short getShortParameter(ServletRequest request, String name) {
        return getShortParameter(request, name, (short) 0);
    }

    public static float getFloatParameter(ServletRequest request, String name, float defaults) {
        return StringUtil.convertFloat(request.getParameter(name), defaults);
    }


    public static float getFloatParameter(ServletRequest request, String name) {
        return getFloatParameter(request, name, (float) 0.0);
    }


    public static boolean getBooleanParameter(ServletRequest request, String name, boolean defaults) {
        return StringUtil.convertBoolean(request.getParameter(name), defaults);
    }


    public static boolean getBooleanParameter(ServletRequest request, String name) {
        return getBooleanParameter(request, name, false);
    }

    public final static String getUrlHost(String url) {
        if (url == null || url.length() == 0) {
            return "";
        }
        int start = 0;
        if (url.startsWith("http://")) {
            start = 7;
        } else if (url.startsWith("https://")) {
            start = 8;
        }
        int maxlen = url.length();
        StringBuilder sbd = new StringBuilder();
        for (int i = start; i < maxlen; i++) {
            char c = url.charAt(i);
            if (c == '/' || c == '?' || c == ':' || c == '#' || c == '&' || c == '=' || c == ' ') {
                break;
            }
            sbd.append(c);
        }
        return
                sbd.toString();
    }
}
