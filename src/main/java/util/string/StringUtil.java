package util.string;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.string.support.MultiStringReplacer;
import util.string.support.Pair;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关工具
 */
public final class StringUtil {
	private static final Pattern NET_UNICODE_PATTERN = Pattern.compile("&#(\\d{1,5});");
	private static final Pattern UNICODE_PATTERN = Pattern.compile("\\\\u([0-9a-f]{1,4})");
	private static final Logger LOG = LoggerFactory.getLogger("StringUtil.log");
	private static final int[] CONVERT = new int[127];
	private static final List<String> InvalidCharList = Lists.newArrayList("%3C", "%3E", "%22", "%27", "&#60;", "&#62;",  "&#39;", "&#34;", "\\x3C", "\\x3E" ,  "\\x22", "\\x27");
	private static MultiStringReplacer ti = new MultiStringReplacer();

	static {
		for (int i = 0; i < 127; i++) {
			char c = (char)i;
			if ('0' <= c && c <= '9') {
				CONVERT[i] = 1;
			} else if ('a' <= c && c <= 'z') {
				CONVERT[i] = 2;
			} else if ('A' <= c && c <= 'Z') {
				CONVERT[i] = 4;
			} else {
				CONVERT[i] = 0;
			}
		}

		for(String ch : InvalidCharList) {
			ti.add(ch, "");
		}
	}

	private StringUtil() {}

	/**
	 * 判断是否是空字符串
	 *
	 * @param s 待检查的字符串
	 * @return 空指针或者长度为0返回true，否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	/**
	 * 判断字符是否为字母
	 * @param c 待判断的字符
	 * @return 字母返回true
	 */
	public static boolean isLetter(char c) {
		return c < 127 && CONVERT[c] > 1;
	}

	/**
	 * 判断字符是否为数字
	 * @param c 待判断的字符
	 * @return 数字返回true
	 */
	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * 判断字符是否为字母或数字
	 * @param c 待判断的字符
	 * @return 字母或数字返回true
	 */
	public static boolean isLetterOrDigit(char c) {
		return c < 127 && CONVERT[c] > 0;
	}

	/**
	 * 判断字符是否包含字母或数字
	 * @param word 待判断的字符串
	 * @return 只包含字母或数字返回true
	 */
	public static boolean hasLetterAndDigit(CharSequence word) {
		return hasLetterAndDigit(word, 0 ,word.length());
	}

	/**
	 * 判断字符是否包含字母或数字
	 * @param word 待判断的字符串
	 * @param start 起始位置
	 * @return 只包含字母或数字返回true
	 */
	public static boolean hasLetterAndDigit(CharSequence word, int start, int stop) {
		int val = 0;
		for (int i=start; i<stop; i++) {
			char c = word.charAt(i);
			if (c < 127) {
				val |= CONVERT[c];
			}
		}
		return val > 1 && (val % 2 == 1);
	}

	/**
	 * 判断字符是否只包含数字
	 * @param word 待判断的字符串
	 * @return 只包含数字返回true
	 */
	public static boolean hasOnlyDigit(CharSequence word) {
		return hasOnlyDigit(word, 0, word.length());
	}

	/**
	 * 判断字符是否只包含数字
	 * @param word 待判断的字符串
	 * @param start 起始位置
	 * @return 只包含数字返回true
	 */
	public static boolean hasOnlyDigit(CharSequence word, int start, int stop) {
		int val = 0;
		for (int i=start; i<stop; i++) {
			char c = word.charAt(i);
			if (c < 127) {
				val |= CONVERT[c];
			}
		}
		return val == 1;
	}

	/**
	 * 判断字符是否包含字母
	 * @param word 待判断的字符串
	 * @return 只包含字母返回true
	 */
	public static boolean hasOnlyLetter(CharSequence word) {
		return hasOnlyLetter(word, 0 ,word.length());
	}

	/**
	 * 判断字符是否只包含字母
	 * @param word 待判断的字符串
	 * @param start 起始位置
	 * @return 只包含字母返回true
	 */
	public static boolean hasOnlyLetter(CharSequence word, int start, int stop) {
		int val = 0;
		for (int i=start; i<stop; i++) {
			char c = word.charAt(i);
			if (c < 127) {
				val |= CONVERT[c];
			}
		}
		return val == 2 || val == 4;
	}

	/**
	 * 判断字符串是否以一个字符串开头
	 * @param sbd 	待判断的字符串
	 * @param start 起始位置
	 * @param str  	开头的字符串
	 * @return 找到返回true
	 */
	public static boolean startsWith(StringBuilder sbd, int start, String str) {
		final int length = str.length();
		if (start + length > sbd.length()) {
			return false;
		}
		for (int i=0; i< length; i++) {
			if (sbd.charAt(i+start) != str.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 只替换第一个
	 *
	 * @param s   原始字符串
	 * @param src 要替换字符串
	 * @param dst 替换目标字符串
	 * @return 处理后的字符串
	 */
	public static String replaceFirst(String s, String src, String dst) {
		if (s == null || src == null || dst == null || src.length() == 0) {
			return s;
		}
		int pos = s.indexOf(src);
		if (pos < 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() - src.length() + dst.length());
		sb.append(s, 0, pos);
		sb.append(dst);
		sb.append(s, pos + src.length(), s.length());
		return sb.toString();
	}

	/**
	 * 只替换最后一个
	 *
	 * @param s   原始字符串
	 * @param src 要替换字符串
	 * @param dst 替换目标字符串
	 * @return 处理后的字符串
	 */
	public static String replaceLast(String s, String src, String dst) {
		if (s == null || src == null || dst == null || src.length() == 0) {
			return s;
		}
		int pos = s.lastIndexOf(src);
		if (pos < 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() - src.length() + dst.length());
		sb.append(s, 0, pos);
		sb.append(dst);
		sb.append(s, pos + src.length(), s.length());
		return sb.toString();
	}

	/**
	 * 字符串全量替换
	 *
	 * @param s   原始字符串
	 * @param src 要替换的字符串
	 * @param dst 替换目标字符串
	 * @return 处理后的字符串
	 */
	public static String replaceAll(String s, String src, String dst) {
		if (s == null || src == null || dst == null || src.length() == 0) {
			return s;
		}
		int pos = s.indexOf(src); // 查找第一个替换的位置
		if (pos < 0) {
			return s;
		}
		int capacity = dst.length() > src.length() ? s.length() * 2 : s.length();
		StringBuilder sb = new StringBuilder(capacity);
		int written = 0;
		for (; pos >= 0; ) {
			sb.append(s, written, pos); // append 原字符串不需替换部分
			sb.append(dst); // append 新字符串
			written = pos + src.length(); // 忽略原字符串需要替换部分
			pos = s.indexOf(src, written); // 查找下一个替换位置
		}
		sb.append(s, written, s.length()); // 替换剩下的原字符串
		return sb.toString();
	}

	public static String removeAll(String s, String src) {
		return replaceAll(s, src, "");
	}

	/**
	 * 去除字符串头尾空格，并不会去掉[\t\r\n]等空白符
	 *
	 * @param s 待检查的字符串
	 * @return 如果为空指针，则返回空串，否则返回去掉头尾空格后的串
	 */
	public static String trim(String s) {
		if (s == null) {
			return "";
		}
		return s.trim();
	}

	/**
	 * 对StringBuilder做trim操作
	 *
	 * @param sb 待处理的StringBuilder实例
	 * @return 去掉头尾空格后的字符串
	 */
	public static String trim(StringBuilder sb) {
		if (sb == null) {
			return "";
		}
		int len = sb.length();
		int st = 0;
		while ((st < len) && (sb.charAt(st) <= ' ')) {
			st++;
		}
		while ((st < len) && (sb.charAt(len - 1) <= ' ')) {
			len--;
		}
		return ((st > 0) || (len < sb.length())) ? sb.substring(st, len) : sb.toString();
	}

	/**
	 * 分割字符串
	 *
	 * @param line      原始字符串
	 * @param separator 分隔符
	 * @return 分割结果
	 */
	public static List<String> split(String line, String separator) {
		return split(line, separator, false);
	}

	/**
	 * 分割字符串
	 *
	 * @param line      原始字符串
	 * @param separator 分隔符
	 * @param trimSpace 是否去除每项的头尾空格
	 * @return 分割结果
	 */
	public static List<String> split(String line, String separator, boolean trimSpace) {
		if (line == null || separator == null || separator.length() == 0)
			return null;
		ArrayList<String> list = new ArrayList<String>();
		int pos1 = 0;
		int pos2;
		for (; ; ) {
			pos2 = line.indexOf(separator, pos1);
			if (pos2 < 0) {
				String s = line.substring(pos1);
				list.add(trimSpace ? s.trim() : s);
				break;
			}
			String s = line.substring(pos1, pos2);
			list.add(trimSpace ? s.trim() : s);
			pos1 = pos2 + separator.length();
		}
		// 去掉末尾的空串，和String.split行为保持一致
		for (int i = list.size() - 1; i >= 0 && list.get(i).length() == 0; --i) {
			list.remove(i);
		}
		return list;
	}

	/**
	 * 用特定字符分割一个字符串，并且把分割后的字符串做trim，避免每个再做trim
	 *
	 * @param str        要分割的字符串
	 * @param separator  分割字符， 避免忘记填同时也能提升性能
	 * @param separators 更多分割字符
	 * @return 分割后的字符串列表
	 */
	public static List<String> split(String str, char separator, char... separators) {
		return split(str, 0, str.length(), separator, separators);
	}

	public static List<String> split(String str, int start, int stop, char separator, char... separators) {
		List<String> items = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < stop; i++) {
			char c = str.charAt(i);
			if (c == separator || contains(separators, c)) {
				items.add(trim(sb));
				sb.setLength(0);
			} else {
				sb.append(c);
			}
		}
		items.add(trim(sb));
		// 去掉末尾的空串，和String.split行为保持一致
		for (int i = items.size() - 1; i >= 0 && items.get(i).length() == 0; --i) {
			items.remove(i);
		}
		return items;
	}

	/**
	 * 把字符串分割成KV对
	 *
	 * @param str       待分割的字符串
	 * @param separator 分割字符
	 * @return KV串
	 */
	public static Pair<String, String> splitKV(String str, char separator) {
		if (str == null) {
			return null;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return Pair.build(str, "");
		} else {
			return Pair.build(str.substring(0, pos).trim(), str.substring(pos + 1).trim());
		}
	}

	/**
	 * 把字符串分割成KV对
	 *
	 * @param str       待分割的字符串
	 * @param splitPos 分割位置
	 * @return KV串
	 */
	public static Pair<String, String> splitKV(String str, int splitPos) {
		if (str == null) {
			return null;
		}
		if(str.length() > splitPos){
			return Pair.build(str.substring(0, splitPos).trim(), str.substring(splitPos).trim());
		}else{
			return Pair.build("", "");
		}
	}

	/**
	 * 判断haystack中是否包含字符c
	 *
	 * @param haystack 从中查找字符
	 * @param c        要查找的字符
	 * @return 找到返回true
	 */
	public static boolean contains(char[] haystack, char c) {
		for (char i : haystack) {
			if (i == c) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将字符串截断到maxLen长度
	 *
	 * @param s      待截断字符串
	 * @param maxLen 字符串最大长度
	 * @return 截断后的字符串
	 */
	public static String truncate(String s, int maxLen) {
		return truncate(s, maxLen, null);
	}

	/**
	 * 把字符串截断到maxLen长度并以tail结尾
	 *
	 * @param s       待处理字符串
	 * @param maxLen  截断长度
	 * @param postfix 截断后缀
	 * @return 截取后的字符串
	 */
	public static String truncate(String s, int maxLen, String postfix) {
		if (s == null || maxLen < 0) {
			return "";
		}
		if (s.length() <= maxLen) {
			return s;
		}
		if (postfix == null || postfix.length() == 0) {
			return s.substring(0, maxLen);
		} else {
			final int end = maxLen - postfix.length();
			if (end < 0) {
				return s.substring(0, maxLen);
			}
			return s.substring(0, end) + postfix;
		}
	}

	/**
	 * 字符串转换为boolean，避免运行时异常
	 *
	 * @param str        待处理字符串
	 * @param defaultVal 默认值
	 * @return 转换失败返回指定默认值
	 */
	public static boolean toBool(String str, boolean defaultVal) {
		if (str == null || str.length() == 0) {
			return defaultVal;
		}
		return "true".equalsIgnoreCase(str);
	}

	/**
	 * 字符串转换为int，避免运行时异常
	 *
	 * @param str        待处理字符串
	 * @param defaultVal 默认值
	 * @return 转换失败返回指定默认值
	 */
	public static int toInt(String str, int defaultVal) {
		try {
			return Integer.parseInt(str.trim());
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * 字符串转换为Long，避免运行时异常
	 *
	 * @param str        待处理字符串
	 * @param defaultVal 默认值
	 * @return 转换失败返回指定默认值
	 */
	public static long toLong(String str, long defaultVal) {
		try {
			return Long.parseLong(str.trim());
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * 判断一个unicode值是否为合法的xml字符，从org.jdom.Verifier复制过来的
	 */
	public static boolean isXMLCharacter(int c) {
		if (c == '\n')
			return true;
		if (c == '\r')
			return true;
		if (c == '\t')
			return true;
		if (c < 0x20)
			return false;
		if (c <= 0xD7FF)
			return true;
		if (c < 0xE000)
			return false;
		if (c <= 0xFFFD)
			return true;
		if (c < 0x10000)
			return false;
		if (c <= 0x10FFFF)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public static String join(String separator, Collection c) {
		if (c.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator i = c.iterator();
		sb.append(i.next());
		if (separator.length() == 1) {
			char ch = separator.charAt(0);
			while (i.hasNext()) {
				sb.append(ch);
				sb.append(i.next());
			}
		} else {
			while (i.hasNext()) {
				sb.append(separator);
				sb.append(i.next());
			}
		}
		return sb.toString();
	}

	public static String join(String separator, String... s) {
		return joinObjects(separator, (Object[]) s);
	}

	public static String join(String separator, int... s) {
		if (s == null || s.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(s[0]);

		if (separator.length() == 1) {
			char ch = separator.charAt(0);
			for (int i = 1; i < s.length; ++i) {
				sb.append(ch);
				sb.append(s[i]);
			}
		} else {
			for (int i = 1; i < s.length; ++i) {
				sb.append(separator);
				sb.append(s[i]);
			}
		}
		return sb.toString();
	}

	public static String join(String separator, long... s) {
		if (s == null || s.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(s[0]);
		if (separator.length() == 1) {
			char ch = separator.charAt(0);
			for (int i = 1; i < s.length; ++i) {
				sb.append(ch);
				sb.append(s[i]);
			}
		} else {
			for (int i = 1; i < s.length; ++i) {
				sb.append(separator);
				sb.append(s[i]);
			}
		}
		return sb.toString();
	}

	public static String joinObjects(String separator, Object... c) {
		if (c == null || c.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(c[0] == null ? '-' : c[0]);
		if (separator.length() == 0) {
			for (int i = 1; i < c.length; ++i) {
				appendObject(sb, c[i]);
			}
		} else if (separator.length() == 1) {
			char ch = separator.charAt(0);
			for (int i = 1; i < c.length; ++i) {
				sb.append(ch);
				appendObject(sb, c[i]);
			}
		} else {
			for (int i = 1; i < c.length; ++i) {
				sb.append(separator);
				appendObject(sb, c[i]);
			}
		}
		return sb.toString();
	}

	private static void appendObject(StringBuilder sb, Object o) {
		if (o == null || ((o instanceof String) && ((String) o).length() == 0)) {
			sb.append('-');
		} else {
			sb.append(o.toString());
		}
	}

	/**
	 * 将字符串转为WML编码,用于wml页面显示
	 * 根据unicode编码规则Blocks.txt：E000..F8FF; Private Use Area
	 *
	 * @param str
	 * @return String
	 */
	public static String encodeWML(String str) {
		if (str == null) {
			return "";
		}
		// 不用正则表达式替换，直接通过循环，节省cpu时间
		final int length = str.length();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; ++i) {
			char c = str.charAt(i);
			switch (c) {
				case '\u00FF':
				case '\u200B'://ZERO WIDTH SPACE
				case '\uFEFF'://ZERO WIDTH NO-BREAK SPACE
				case '\u0024':
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '\t':
					sb.append("  ");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '\"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&apos;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					if (c >= '\u0000' && c <= '\u001F')
						break;
					if (c >= '\uE000' && c <= '\uF8FF')
						break;
					if (c >= '\uFFF0' && c <= '\uFFFF')
						break;
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * 转换&#123;这种编码为正常字符<br/>
	 * 有些手机会将中文转换成&#123;这种编码,这个函数主要用来转换成正常字符.
	 *
	 * @param str 待转换字符
	 * @return String
	 */
	public static String decodeNetUnicode(String str) {
		if (str == null)
			return null;
		Matcher m = NET_UNICODE_PATTERN.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String mcStr = m.group(1);
			int charValue = toInt(mcStr, -1);
			String s = charValue > 0 ? (char) charValue + "" : "";
			m.appendReplacement(sb, Matcher.quoteReplacement(s));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 转换\\u123这种编码为正常字符<br/>
	 *
	 * @param str 待转换字符
	 * @return String
	 */
	public static String decodeUnicode(String str) {
		if (str == null)
			return null;
		Matcher m = UNICODE_PATTERN.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String mcStr = m.group(1);
			try {
				char charValue = (char) Integer.parseInt(mcStr, 16);
				m.appendReplacement(sb, Matcher.quoteReplacement(String.valueOf(charValue)));
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 过滤SQL字符串,防止SQL inject
	 *
	 * @param sql
	 * @return String
	 */
	public static String encodeSQL(String sql) {
		if (sql == null) {
			return "";
		}
		// 不用正则表达式替换，直接通过循环，节省cpu时间
		final int length = sql.length();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; ++i) {
			char c = sql.charAt(i);
			switch (c) {
				case '\\':
					sb.append("\\\\");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\'':
					sb.append("\'\'");
					break;
				case '\"':
					sb.append("\\\"");
					break;
				case '\u200B'://ZERO WIDTH SPACE
				case '\uFEFF'://ZERO WIDTH NO-BREAK SPACE
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 新增一个可以多次执行去除非法字符以及进行xml转义的方法，将unicode私有区域的合法xml字符也移除了
	 *
	 * @param str
	 * @return 如果字符串中有已经转义的实体字符串，则跳过，否则转义避免amp;amp;这样的情形出现
	 */
	public static String removeInvalidWML(String str) {
		return removeInvalidWML(str, true);
	}

	/**
	 * 新增一个可以多次执行去除非法字符以及进行xml转义的方法<br/>
	 * 与removeInvalidWML区别如下：<br/>
	 * 1.本方法严格按照xml规范进行过滤，removeInvalidWML则还过滤了$,^,`,\u00FF,\UE000-\UF8FF(Unicode私有保留区Private Use Area)区间的字符<br/>
	 * 2.本方法按照xml规范对5个公共转义字符做了转义，removeInvalidWML未对单引号转义<br/>
	 * 3.本方法对$,\r,\n,\t字符使用了&#加unicode值的方式进行表示，removeInvalidWML则将$,\r\,\n直接去除，将\
	 * t转换为两个空格<br/>
	 * 4.本方法针对超过\uFFFF的Unicode字符做了高代理判断，支持将非法高代理或低代理字符去除，removeInvalidWML没有做过滤<br/>
	 * 测试用例为：<br/>
	 * String s = "0\u00031&2&amp;3&amp;amp;4&gt;5&lt;6&apos;7&quot;"<br/>
	 * + "8<9>10\'11\"12\n13\r14\t15&#37;16&#;17&#y;18&#7654321;19"<br/>
	 * +
	 * "&amp;lt;20&amp;gt;21&amp;quot;22&amp;apos;23$\uD860\uDEE224\uDEE2\uD860aaa"
	 * ;<br/>
	 * System.out.println("safeRemoveInvalidWML=" + safeRemoveInvalidWML(s));<br/>
	 * System.out.println("removeInvalidWML=" + removeInvalidWML(s));<br/>
	 *
	 * @param str                  需要进行过滤xml非法字符并进行xml转义的字符串
	 * @param removePrivateUseArea 是否移除虽然是xml合法字符但却是在unicode里私有保留区里的字符
	 * @return 如果字符串中有已经转义的实体字符串，则跳过，否则转义避免amp;amp;这样的情形出现
	 * @see
	 */
	public static String removeInvalidWML(String str, boolean removePrivateUseArea) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length() + 48);
		for (int i = 0, len = str.length(); i < len; i++) {
			char c = str.charAt(i);
			if (Character.isHighSurrogate(c)) {// 如果已经是高代理字符，则可能是超过\uFFFF的unicode了
				int codePoint = str.codePointAt(i);// 进行代码点解析
				if (codePoint == c) {// 解析后的值与单个字符想通，说明只有单个高代理字符，则编码有问题，需要过滤该字符
					continue;
				} else if (!isXMLCharacter(codePoint)) {// 非法xml字符滤掉
					i++;
					continue;
				} else if (removePrivateUseArea
						&& ((codePoint >= 0xF0000 && codePoint <= 0xFFFFD) || (codePoint >= 0x100000 && codePoint <= 0x10FFFD))) {
					// 过滤高代理的PrivateUseArea区的字符,
					// Supplementary Private Use Area-A Range: F0000–FFFFD
					// Supplementary Private Use Area-B Range: 100000–10FFFD
					i++;
					continue;
				} else {
					i++;
					sb.appendCodePoint(codePoint);
					continue;
				}
			}
			if (!isXMLCharacter(c)) {//跳过非法xml字符
				continue;
			}
			if (removePrivateUseArea && c >= '\uE000' && c <= '\uF8FF') {//过滤PrivateUseArea区的字符
				continue;
			}
			if (removePrivateUseArea && c == '\u202E') {// 过滤RIGHT-TO-LEFT
				// OVERRIDE转义字符
				// http://www.fileformat.info/info/unicode/char/202e/index.htm
				continue;
			}
			switch (c) {
				case '&':
					if (str.startsWith("&amp;amp;", i)) {// 把两个amp;的兼容掉
						sb.append("&amp;");
						i = i + 8;
					} else if (str.startsWith("&amp;gt;", i)) {// 把多encode了一次的导致amp;的兼容掉
						sb.append("&gt;");
						i = i + 7;
					} else if (str.startsWith("&amp;lt;", i)) {// 把多encode了一次的导致amp;的兼容掉
						sb.append("&lt;");
						i = i + 7;
					} else if (str.startsWith("&amp;apos;", i)) {// 把多encode了一次的导致amp;的兼容掉
						sb.append("&apos;");
						i = i + 9;
					} else if (str.startsWith("&amp;quot;", i)) {// 把多encode了一次的导致amp;的兼容掉
						sb.append("&quot;");
						i = i + 9;
                    }else if (str.startsWith("&amp;nbsp;", i)) {// 把多encode了一次的导致amp;的兼容掉
                            sb.append("&nbsp;");
                            i = i + 9;
					} else if (str.startsWith("&amp;", i)) {// 把已经encode的amp;的兼容掉
						sb.append("&amp;");
						i = i + 4;
					} else if (str.startsWith("&gt;", i)) {
						sb.append("&gt;");
						i = i + 3;
					} else if (str.startsWith("&lt;", i)) {
						sb.append("&lt;");
						i = i + 3;
					} else if (str.startsWith("&apos;", i)) {
						sb.append("&apos;");
						i = i + 5;
                    } else if (str.startsWith("&nbsp;", i)) {
                        sb.append("&nbsp;");
                        i = i + 5;
					} else if (str.startsWith("&quot;", i)) {
						sb.append("&quot;");
						i = i + 5;
					} else if (str.startsWith("&#", i)) {
						// 检测已经是&#37;这样编码字符串
						int index = -1;
						for (int j = i + 2; j < i + 10 && j < len; j++) {
							// xml字符用数字转义方式表示的最大值是&#111411;,因此往前最多检测到10位即可
							char cc = str.charAt(j);
							if (cc == ';') {
								index = j;
								break;
							}
						}
						if (index > i + 2) {// 说明&#和;之间有字符存在，则尝试反解析
							String unicodeVal = str.substring(i + 2, index);
							try {
								int val = Integer.parseInt(unicodeVal.substring(1),
										'x' == unicodeVal.charAt(0) ? 16 : 10);
								if (!isXMLCharacter(val)) {
									sb.append("&amp;");// &#后面的字符无法反解析为合法xml字符，因此继续转义
								} else {// 否则原样拼接
									sb.append("&#").append(unicodeVal).append(';');
									i = i + 2 + unicodeVal.length();
								}
							} catch (Exception e) {
								sb.append("&amp;");
							}
						} else {
							sb.append("&amp;");
						}
					} else {
						sb.append("&amp;");
					}
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '\'':
					sb.append("&apos;");
					break;
				case '\"':
					sb.append("&quot;");
					break;

				// wml中$在postfield的value中表示变量定义，因此需要展示真实的$时，需要转义
				case '$':
					sb.append("&#").append((int) c).append(';');
					break;
				//利用两个特殊字符做xss和sql注入的预防
				//@see http://www.cs.tut.fi/~jkorpela/chars/spaces.html
				case '\u200B'://ZERO WIDTH SPACE
				case '\uFEFF'://ZERO WIDTH NO-BREAK SPACE
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * 返回移除非法xml字符后的字符串，确保json和xml中的字符串能被正常解析
	 *
	 * @param str
	 * @return
	 */
	public static String removeInvalidXmlChar(String str) {
		if (str == null || str.length() < 1) {
			return str;
		}
		for (int k = 0, len = str.length(); k < len; k++) {
			char c = str.charAt(k);
			if (!isXMLCharacter(c)) {
				StringBuilder sb = new StringBuilder(str.length() + 48);
				sb.append(str, 0, k);
				for (int i = k; i < len; i++) {
					c = str.charAt(i);
					if (Character.isHighSurrogate(c)) {// 如果已经是高代理字符，则可能是超过\uFFFF的unicode了
						int codePoint = str.codePointAt(i);// 进行代码点解析
						if (codePoint == c) {// 解析后的值与单个字符相同，说明只有单个高代理字符，则编码有问题，需要过滤该字符
						} else if (!isXMLCharacter(codePoint)) {// 非法xml字符滤掉
							i++;
						} else {
							i++;
							sb.appendCodePoint(codePoint);
						}
					} else if (isXMLCharacter(c)) {
						sb.append(c);
					}
				}
				return sb.toString();
			}
		}
		return str;
	}

	/**
	 * 新增一个可以多次执行去除非法字符以及进行xml转义反解析的方法<br/>
	 *
	 * @param str 需要反解析xml的字符串
	 * @return 返回将xml转义字符反解析后的字符串，默认过滤掉xml字符允许但是是unicode私有区域的字符
	 */
	public static String decodeWML(String str) {
		return decodeWML(str, true);
	}

    /**
     * 删除所有的特殊字符
     * @param str
     * @return
     */
    public static String removeInvalidAllChar(String str){
        if(isEmpty(str)) return str;
        String txt = decodeWML(str);

        final int length = txt.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            char c = txt.charAt(i);
            switch (c) {
                case '\u00FF':
                case '\u200B'://ZERO WIDTH SPACE
                case '\uFEFF'://ZERO WIDTH NO-BREAK SPACE
                case '\u0024':
                case '&':
                case '\t':
                case '<':
                case '>':
                case '\"':
                case '\'':
                case '\n':
                    break;
                default:
                    if (c >= '\u0000' && c <= '\u001F')
                        break;
                    if (c >= '\uE000' && c <= '\uF8FF')
                        break;
                    if (c >= '\uFFF0' && c <= '\uFFFF')
                        break;
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

	/**
	 * XSS注入，删除URL参数里的非法字符
	 * @param txt
	 * @return
	 */
	public static String removeParameterInvalidChar(String txt){
		if(isEmpty(txt)) return txt;

		txt = decodeAsciiParam(txt);
		final int length = txt.length();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; ++i) {
			char c = txt.charAt(i);
			switch (c) {
				case '\u00FF':
				case '\u200B'://ZERO WIDTH SPACE
				case '\uFEFF'://ZERO WIDTH NO-BREAK SPACE
				case '\u0024':
				case '\t':
				case '<':
				case '>':
				case '\"':
				case '\'':
				case '\n':
					break;
				default:
					if (c >= '\u0000' && c <= '\u001F')
						break;
					if (c >= '\uE000' && c <= '\uF8FF')
						break;
					if (c >= '\uFFF0' && c <= '\uFFFF')
						break;
					sb.append(c);
					break;
			}
		}
		String query = sb.toString();
		if(StringUtil.isNotEmpty(query)){
			//分别过滤<>'"四种符号的不同编码方式
			/*for(String ch : InvalidCharList){
				query = replaceAll(query, ch ,"");
			}
			return query;
			*/
			return ti.replace(query);
		}
		return "";
	}

	/**
	 * 两段文本比较
	 * @param src_txt
	 * @param desc_txt
	 * @return
	 */
	public static boolean compareTxt(String src_txt, String desc_txt){
		if(isEmpty(src_txt) || isEmpty(desc_txt)) return false;

		if(src_txt.length() == desc_txt.length() && src_txt.equals(desc_txt)){
			return true;
		}
		return false;
	}

	/**
	 * ascii转换为char 直接int强制转换为char
	 * @param ascii
	 * @return
	 */
	public static char byteAsciiToChar(int ascii){
		char ch = (char)ascii;
		return ch;
	}

	/**
	 * 解码ascII码参数，如这样的参数，ascII码的处理
	 * "g_ut=3&title=\\61\\47\\42\\76\\74\\57\\164\\151\\164\\154\\145\\76\\74\\57\\164\\145\\170\\164\\141\\162\\145\\141\\76\\74\\57\\170\\155\\160\\76\\74\\57\\151\\146\\162\\141\\155\\145\\76\\74\\57\\156\\157\\163\\143\\162\\151\\160\\164\\76\\74\\57\\156\\157\\146\\162\\141\\155\\145\\163\\76\\74\\57\\160\\154\\141\\151\\156\\164\\145\\170\\164\\76\\74\\57\\146\\157\\162\\155\\76\\74\\57\\163\\143\\162\\151\\160\\164\\76\\74\\151\\146\\162\\141\\155\\145\\57\\157\\156\\154\\157\\141\\144\\75\\141\\154\\145\\162\\164\\50\\61\\71\\70\\71\\60\\66\\61\\61\\65\\62\\60\\51\\76\\74\\57\\151\\146\\162\\141\\155\\145\\76&lemmaId=30370262";
	 * @param str
	 * @return
	 */
	private static String decodeAsciiParam(String str){
		if(!str.contains("\\")) return str;

		StringBuilder sb = new StringBuilder();
		List<String> strs = StringUtil.split(str, "\\");
		for(String s : strs){
			if(StringUtil.isNotEmpty(s)){
				if(StringUtil.hasOnlyDigit(s)){
					try {
						sb.append(byteAsciiToChar(Integer.valueOf(s, 8)));
					}catch (Exception e){
						sb.append(s);
					}
				}else{
					sb.append(s);
				}
			}
		}
		return sb.toString();
	}


	/**
	 * 新增一个可以多次执行去除非法字符以及进行xml转义反解析的方法<br/>
	 *
	 * @param str                  需要反解析xml的字符串
	 * @param removePrivateUseArea 是否去除unicode私有区的字符
	 * @return 返回反解析后的字符串
	 */
	public static String decodeWML(String str, boolean removePrivateUseArea) {
		if (str == null || str.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0, len = str.length(); i < len; i++) {
			char c = str.charAt(i);
			if (Character.isHighSurrogate(c)) {// 如果已经是高代理字符，则可能是超过\uFFFF的unicode了
				int codePoint = str.codePointAt(i);// 进行代码点解析
				if (codePoint == c) {// 解析后的值与单个字符想通，说明只有单个高代理字符，则编码有问题，需要过滤该字符
					continue;
				} else if (!isXMLCharacter(codePoint)) {// 非法xml字符滤掉
					i++;
					continue;
				} else if (removePrivateUseArea
						&& ((codePoint >= 0xF0000 && codePoint <= 0xFFFFD) || (codePoint >= 0x100000 && codePoint <= 0x10FFFD))) {
					// 过滤高代理的PrivateUseArea区的字符,
					// Supplementary Private Use Area-A Range: F0000–FFFFD
					// Supplementary Private Use Area-B Range: 100000–10FFFD
					i++;
					continue;
				} else {
					i++;
					sb.appendCodePoint(codePoint);
					continue;
				}
			}
			if (!isXMLCharacter(c)) {// 跳过非法xml字符
				continue;
			}
			if (removePrivateUseArea && c >= '\uE000' && c <= '\uF8FF') {// 过滤PrivateUseArea区的字符
				continue;
			}
			if (removePrivateUseArea && c == '\u202E') {// 过滤RIGHT-TO-LEFT
				// OVERRIDE转义字符
				// http://www.fileformat.info/info/unicode/char/202e/index.htm
				continue;
			}
			switch (c) {
				case '&':
					if (str.startsWith("&amp;amp;", i)) {// 把两个amp;的兼容还原
						sb.append("&");
						i = i + 8;
					} else if (str.startsWith("&amp;gt;", i)) {// 把多encode了一次的导致amp;的兼容还原
						sb.append(">");
						i = i + 7;
					} else if (str.startsWith("&amp;lt;", i)) {// 把多encode了一次的导致amp;的兼容兼容还原
						sb.append("<");
						i = i + 7;
					} else if (str.startsWith("&amp;apos;", i)) {// 把多encode了一次的导致amp;的兼容兼容还原
						sb.append("'");
						i = i + 9;
					} else if (str.startsWith("&amp;quot;", i)) {// 把多encode了一次的导致amp;的兼容兼容还原
						sb.append("\"");
						i = i + 9;
					} else if (str.startsWith("&amp;nbsp;", i)) {// 把多encode了一次的导致amp;的兼容兼容还原
						sb.append(" ");
						i = i + 9;
					} else if (str.startsWith("&amp;", i)) {// 把已经encode的amp;的兼容兼容还原
						sb.append("&");
						i = i + 4;
					} else if (str.startsWith("&gt;", i)) {
						sb.append(">");
						i = i + 3;
					} else if (str.startsWith("&lt;", i)) {
						sb.append("<");
						i = i + 3;
					} else if (str.startsWith("&apos;", i)) {
						sb.append("'");
						i = i + 5;
					} else if (str.startsWith("&quot;", i)) {
						sb.append("\"");
						i = i + 5;
					} else if (str.startsWith("&nbsp;", i)) {
						sb.append(" ");
						i = i + 5;
					} else if (str.startsWith("&#", i)) {// 检测已经是&#37;这样编码字符串
						int index = -1;
						for (int j = i + 2; j < i + 10 && j < len; j++) {
							// xml字符用数字转义方式表示的最大值是&#111411;,因此往前最多检测到10位即可
							char cc = str.charAt(j);
							if (cc == ';') {
								index = j;
								break;
							}
						}
						if (index > i + 2) {// 说明&#和;之间有字符存在，则尝试反解析
							String unicodeVal = str.substring(i + 2, index);
							try {
								boolean hex = 'x' == unicodeVal.charAt(0);
								int val = hex ? Integer.parseInt(
										unicodeVal.substring(1), 16) : Integer
										.parseInt(unicodeVal, 10);
								if (!isXMLCharacter(val)) {
									sb.append("&");// &#后面的字符无法反解析为合法xml字符，因此继续保持转义
								} else {// 否则还原成unicode字符
									if (removePrivateUseArea) {// 反解析后再次过滤文字反向和私有区域字符
										if (!((val == '\u202E')
												|| (val >= '\uE000' && val <= '\uF8FF')
												|| (val >= 0xF0000 && val <= 0xFFFFD) || (val >= 0x100000 && val <= 0x10FFFD))) {
											sb.appendCodePoint(val);
										}
									} else {
										sb.appendCodePoint(val);
									}
									i = i + 2 + unicodeVal.length();
								}
							} catch (Exception e) {
								// 继续原始编码方式
								sb.append("&");
							}
						} else {
							sb.append("&");
						}
					} else {
						sb.append("&");
					}
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * 获取字符型参数，若输入字符串为null，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return 字符串参数
	 */
	public static String convertString(String str, String defaults) {
		if (str == null) {
			return defaults;
		} else {
			return str;
		}
	}

	/**
	 * 获取int参数，若输入字符串为null或不能转为int，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return int参数
	 */
	public static int convertInt(String str, int defaults) {
		if (str == null) {
			return defaults;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaults;
		}
	}

	/**
	 * 获取long型参数，若输入字符串为null或不能转为long，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return long参数
	 */
	public static long convertLong(String str, long defaults) {
		if (str == null) {
			return defaults;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return defaults;
		}
	}

	/**
	 * 获取double型参数，若输入字符串为null或不能转为double，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return double型参数
	 */
	public static double convertDouble(String str, double defaults) {
		if (str == null) {
			return defaults;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return defaults;
		}
	}

	/**
	 * 获取short型参数，若输入字符串为null或不能转为short，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return short型参数
	 */
	public static short convertShort(String str, short defaults) {
		if (str == null) {
			return defaults;
		}
		try {
			return Short.parseShort(str);
		} catch (Exception e) {
			return defaults;
		}
	}

	/**
	 * 获取float型参数，若输入字符串为null或不能转为float，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return float型参数
	 */
	public static float convertFloat(String str, float defaults) {
		if (str == null) {
			return defaults;
		}
		try {
			return Float.parseFloat(str);
		} catch (Exception e) {
			return defaults;
		}
	}

	/**
	 * 获取boolean型参数，若输入字符串为null或不能转为boolean，则返回设定的默认值
	 *
	 * @param str      输入字符串
	 * @param defaults 默认值
	 * @return boolean型参数
	 */
	public static boolean convertBoolean(String str, boolean defaults) {
		if (str == null) {
			return defaults;
		}
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
			return defaults;
		}
	}

	/**
	 * 格式化日期
	 *
	 * @param date   输入日期
	 * @param format 输出日期格式
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 分割字符串
	 *
	 * @param line      原始字符串
	 * @param seperator 分隔符
	 * @return 分割结果
	 */
	public static String[] split2Array(String line, String seperator) {
		List<String> list = split(line, seperator);
		if (list != null) {
			return list.toArray(new String[0]);
		}
		return null;
	}

	/**
	 * 分割字符串，并转换为int
	 *
	 * @param line      原始字符串
	 * @param seperator 分隔符
	 * @param def       默认值
	 * @return 分割结果
	 */
	public static int[] splitInt(String line, String seperator, int def) {
		String[] ss = split2Array(line, seperator);
		int[] r = new int[ss.length];
		for (int i = 0; i < r.length; ++i) {
			r[i] = convertInt(ss[i], def);
		}
		return r;
	}

	/**
	 * 以某一长度缩写字符串（1个中文或全角字符算2个长度单位，英文或半角算一个长度单位）.
	 * 如果要显示n个汉字的长度，则maxlen= 2* n
	 *
	 * @param src         utf-8字符串
	 * @param maxlen      缩写后字符串的最长长度（1个中文或全角字符算2个单位长度）
	 * @param replacement 替换的字符串，该串长度会计算到maxlen中
	 * @return String
	 */
	public static String abbreviate(String src, int maxlen, String replacement) {
		if (src == null) return "";
		if (replacement == null) {
			replacement = "";
		}
		StringBuffer dest = new StringBuffer();                         //初始值设定为源串
		try {
			maxlen = maxlen - computeDisplayLen(replacement);
			if (maxlen < 0) {
				return src;
			}
			int i = 0;
			for (; i < src.length() && maxlen > 0; ++i) {
				char c = src.charAt(i);
				if (c >= '\u0000' && c <= '\u00FF') {
					maxlen = maxlen - 1;
				} else {
					maxlen = maxlen - 2;
				}
				if (maxlen >= 0) {
					dest.append(c);
				}
			}
			//没有取完 src 所有字符时，才需要加后缀 ...
			if (i < src.length() - 1) {
				dest.append(replacement);
			}
			return dest.toString();
		} catch (Throwable e) {
			LOG.error("abbreviate error: ", e);
		}
		return src;
	}

	/**
	 * @param src
	 * @param maxlen
	 * @return
	 */
	public static String abbreviate(String src, int maxlen) {
		return abbreviate(src, maxlen, "");
	}

	/**
	 * 计算字符串的显示长度，半角算１个长度，全角算两个长度
	 *
	 * @param s
	 * @return
	 */
	public static int computeDisplayLen(String s) {
		int len = 0;
		if (s == null) {
			return len;
		}
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c >= '\u0000' && c <= '\u00FF') {
				len = len + 1;
			} else {
				len = len + 2;
			}
		}
		return len;
	}
	//==================以下为wyatt添加，可能有重复

	/**
	 * 将字符串截短,功能与abbreviate()类似
	 * 全角字符算一个字,半角字符算半个字,这样做的目的是为了显示的时候排版整齐,因为全角字占的位置要比半角字小
	 *
	 * @param str
	 * @param maxLen
	 * @return String
	 */
	public static String toShort(String str, int maxLen, String replacement) {
		if (str == null) {
			return "";
		}
		if (str.length() <= maxLen) {
			return str;
		}
		StringBuilder dest = new StringBuilder();
		double len = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '\u0000' && c <= '\u00FF') {//半角字算半个字
				len += 0.5;
			} else {
				len += 1;
			}
			if (len > maxLen)
				return dest.toString() + replacement;
			else
				dest.append(c);
		}
		return dest.toString();
	}

	public static String toShort(String str, int maxLen) {
		return toShort(str, maxLen, "...");
	}

	public static String removeChar(String str, char ch, char... delete) {
		int len = str.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			boolean skip = (c == ch || contains(delete, c));
			if (!skip) {
				sb.append(c);
			}
		}
		return sb.length() < len ? sb.toString() : str;
	}

	public static int indexOf(String str, char ch, char... chars) {
		return find(str, 0, ch, chars);
	}

	/**
	 * 避免和 indexOf同名导致有些编译器报告失败，所以改名find，查找多个字符出现的位置
	 *
	 * @param str
	 * @param start
	 * @param ch
	 * @param chars
	 * @return
	 */
	public static int find(String str, int start, char ch, char... chars) {
		for (int i = start, len = str.length(); i < len; i++) {
			char c = str.charAt(i);
			if (c == ch || contains(chars, c)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找出一个字符包含另外一个字符串的次数
	 *
	 * @param haystack 从中查找包含needle的个数
	 * @param needle 要查找的字符串
	 * @return
	 */
	public static int findCount(String haystack, String needle) {
		if (needle == null || haystack == null) return -1;
		int count = -1;
		if (needle.length() == 1) {
			char c = needle.charAt(0);
			int pos = -1;
			for (;;) {
				pos = haystack.indexOf(c, pos + 1);
				if (pos != -1) {
					++count;
				} else {
					break;
				}
			}
		} else {
			int pos = -1;
			for (;;) {
				pos = haystack.indexOf(needle, pos + 1);
				if (pos != -1) {
					++count;
				} else {
					break;
				}
			}
		}
		return count;
	}

	public static String toLower(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return str.toLowerCase();
		}
	}

	/**
	 * 如果包含类名全路径，则把类名缩短<br/>
	 * 比如：com.wenwen.IamSoLongName -> c.w.IamSoLongName
	 *
	 * @param className 名字
	 * @return 缩短后的名字
	 */
	public static String getShortClassName(String className) {
		int pos = className.indexOf('.');
		final int length = className.length();
		if (pos > 0) {
			StringBuilder sbd = new StringBuilder(32);
			sbd.append(className.charAt(0));
			int fromIndex = 0;
			while (pos > 0 && (pos + 1) < length) {
				fromIndex = pos + 1;
				sbd.append('.').append(className.charAt(fromIndex));
				pos = className.indexOf('.', fromIndex);
			}
			sbd.append(className, fromIndex + 1, length);
			className = sbd.toString();
		}
		return className;
	}

    /**
     * 替换oStr中的换行等字符
     * @param oStr
     * @return 替换后的字符串
     */
    public static final String removeWrapChars(String oStr) {
        if(oStr==null){
            return null;
        }
        char[] wrapChars = {'\t','\n','\r'};
        return deleteChars(oStr, wrapChars);
    }

    /**
     * 在所给字符串中(oStr)中删除某些字符(delChars)
     * 每个字符不论出现多少次，全都删除
     * @param oStr
     * @param delChars
     * @return
     */
    public static final String deleteChars(String oStr,char[] delChars) {
        if(oStr==null){
            return null;
        }
        if(delChars==null){
            return oStr;
        }

        //不含有待删字符时，直接返回
        boolean containDelChars = false;
        for(int i=0;i<delChars.length;i++){
            char c=delChars[i];
            //oStr中包含此字符
            if(oStr.indexOf(c)!=-1){
                containDelChars=true;
                break;
            }
        }
        if(!containDelChars){
            return oStr;
        }

        int len = oStr.length();
        char buf[] = new char[len];
        int j=0;
        for(int i=0;i<len;i++){
            char c = oStr.charAt(i);
            //判断是否需要删除
            boolean isDelChar=false;
            for(int k=0;k<delChars.length;k++){
                char delChar=delChars[k];
                if(c==delChar){
                    isDelChar=true;
                    break;
                }
            }

            if(isDelChar){
                continue;//无用字符过滤掉
            }else{
                buf[j]=c;
                j++;
            }
        }
        //返回有效字符
        return new String(buf, 0, j);
    }

	/**
	 * 将字符串中的空白符都换成空格，避免日志解析出错
	 *
	 * @param s 待处理字符串
	 * @return
	 */
	public static String replaceWhiteSpace(String s) {
		if (s == null || s.length() == 0) return s;
		StringBuilder sbd = null;
		for (int i = 0, len = s.length(); i < len; i++) {
			char c = s.charAt(i);
			if (c == '\t' || c == '\n' || c == '\r') {
				if (sbd == null) {
					sbd = new StringBuilder(len);
					sbd.append(s, 0, i);
				}
				sbd.append(' ');
			} else if (sbd != null) {
				sbd.append(c);
			}
		}
		return sbd == null ? s : sbd.toString();
	}

	public static String utf8Encode(String str) {
		if (StringUtil.isEmpty(str)) {
			return "";
		} else {
			String r = "";
			try {
				r = URLEncoder.encode(str, "UTF-8");
			} catch (Exception e) {
			}
			return r;
		}
	}
}
