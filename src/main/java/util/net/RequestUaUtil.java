package util.net;


import javax.servlet.http.HttpServletRequest;

import util.string.StringUtil;


/**
 * Ua 工具类
 */
public final class RequestUaUtil {
    private static final String IPHONE_TAG = "iphone";
    private static final String ANDROID_TAG = "android";
    private static final String IPAD_TAG = "ipad";

    private static final String WINDOWS_PHONE_TAG = "windows phone";
    private static final String WINDOWS_PHONE_MSIE_TAG = "msie";
    private static final String XIAOMI_PHONE_TAG = "xiaomi";

    private static final String UCBROWSER_TAG = "ucbrowser";
    private static final String SOGOU_SEARCH_TAG = "sogousearch";

    private RequestUaUtil(){}

    /**
     * 判断是为pc浏览器
     *
     * @param request
     * @return
     */
    public static boolean isPcUA(HttpServletRequest request) {
        return !isMobileUA(request);
    }


    /**
     * 判断是否为微信浏览器
     *
     * @param request
     * @return
     */
    public static boolean isWechatUA(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent").toLowerCase();
        //微信浏览器ua含有 micromessenger
        if (StringUtil.isNotEmpty(ua) && ua.contains("micromessenger")) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为无线浏览器
     *
     * @param request
     * @return
     */
    public static boolean isMobileUA(HttpServletRequest request) {
        // 如果ua包含手机信息，则返回true
        String ua = NetUtil.getStringParameter(request, "_sg_ua", request.getHeader("User-Agent"));
        if (StringUtil.isEmpty(ua)) {
            return false;
        }
        String haystack = ua.toLowerCase();
        if (haystack.contains(IPHONE_TAG) || haystack.contains(ANDROID_TAG) || haystack.contains(UCBROWSER_TAG)) {
            return true;
        }

        if (haystack.contains(WINDOWS_PHONE_TAG) && haystack.contains(WINDOWS_PHONE_MSIE_TAG)) {
            return true;
        }

        if (haystack.contains(IPAD_TAG) && haystack.contains(XIAOMI_PHONE_TAG)) {
            return true;
        }

        String qua = request.getHeader("Q-UA2");
        if (StringUtil.isNotEmpty(qua) && StringUtil.findCount(qua, "&") > 3) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为搜狗搜索UA
     *
     * @param request
     * @return
     */
    public static boolean isSogouSearchUA(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent").toLowerCase();
        if (StringUtil.isNotEmpty(ua) && ua.contains(SOGOU_SEARCH_TAG)) {
            return true;
        }
        return false;
    }
}
