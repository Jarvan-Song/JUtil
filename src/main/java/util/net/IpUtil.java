package util.net;


import util.string.StringUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;


/**
 * IP相关的工具类
 *
 */
public final class IpUtil {
    private static String INNER_IP;

    private IpUtil() {}

    /**
     * 从request中获取IP<br/>
     * 首先从X-Forwarded-For的头信息中提取，否则取直连的IP.
     *
     * @param request 请求对象
     * @return 返回客户端IP
     */
    public static String getRemoteIP(ServletRequest request) {
        HttpServletRequest r = (HttpServletRequest) request;
        String ip = r.getHeader("X-Forwarded-For");
        if (ip != null && ip.length() > 7) {
            List<String> items = StringUtil.split(ip, ',');
            for (String i : items) {
                if (!isInnerIP(i)) {
                    return i;
                }
            }
        }
        return r.getRemoteAddr();
    }

    /**
     * 获取本机内网ip，ip会在第一次访问后缓存起来，并且不会再更新
     * 所以那个模式可能不适合你的机器，本类只是方便大多数人的使用，如果你的
     * 机器不能用该模式获得ip，请使用NetworkInterfaceEx类自行获取
     *
     * @return
     */
    public static String getServerInnerIP() {
        if (INNER_IP != null) {
            return INNER_IP;
        }
        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                Enumeration<InetAddress> en = ni.getInetAddresses();
                while (en.hasMoreElements()) {
                    InetAddress addr = en.nextElement();
                    String ip = addr.getHostAddress();
                    if (isInnerIP(ip)) {
                        if (! ip.equals("127.0.0.1") && !ip.equals("0:0:0:0:0:0:0:1")) {
                            INNER_IP = ip;
                            return ip;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断一个IP是不是内网IP段的IP<br/>
     * 10.0.0.0 – 10.255.255.255<br/>
     * 172.16.0.0 – 172.31.255.255<br/>
     * 192.168.0.0 – 192.168.255.255<br/>
     *
     * @param ip ip地址
     * @return 如果是内网返回true，否则返回false
     */
    public static boolean isInnerIP(String ip) {
        if (ip == null || ip.length() < 7) {
            return false;
        }

        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
            return true;
        }

        if (ip.startsWith("10.") || ip.startsWith("192.168.")) {
            return true;
        }

        if (ip.startsWith("172.")) {
            List<String> items = StringUtil.split(ip, '.');
            if (items.size() == 4) {
                int i = StringUtil.convertInt(items.get(1), -1);
                if (i > 15 && i < 32) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final int default_server_ip_mark = 0;
    /**
     * 获取本机内网IP的最后一位
     * @return
     */
    public static int getServerInnerIpMark() {
        String innerIp = getServerInnerIP();
        if(StringUtil.isNotEmpty(innerIp)){
            List<String> items = StringUtil.split(innerIp, '.');
            if (items.size() == 4) {
                return StringUtil.convertInt(items.get(3), default_server_ip_mark);
            }
        }
        return default_server_ip_mark;
    }

    public static int str2Ip(String ip) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(ip);// 在给定主机名的情况下确定主机的 IP 址。
        byte[] bytes = address.getAddress();// 返回此 InetAddress 对象的原始 IP 地址
        int a, b, c, d;
        a = byte2int(bytes[0]);
        b = byte2int(bytes[1]);
        c = byte2int(bytes[2]);
        d = byte2int(bytes[3]);
        int result = (a << 24) | (b << 16) | (c << 8) | d;
        return result;
    }

    public static int byte2int(byte b) {
        int l = b & 0x07f;
        if (b < 0) {
            l |= 0x80;
        }
        return l;
    }

    public static long ip2long(String ip) throws UnknownHostException {
        int ipNum = str2Ip(ip);
        return int2long(ipNum);
    }

    public static long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }

    public static String long2ip(long ip) {
        int[] b = new int[4];
        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        String x;
        Integer p;
        p = new Integer(0);
        x = p.toString(b[0]) + "." + p.toString(b[1]) + "." + p.toString(b[2])
                + "." + p.toString(b[3]);

        return x;
    }
}
