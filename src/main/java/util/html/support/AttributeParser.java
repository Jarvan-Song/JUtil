package util.html.support;


import java.io.Serializable;

public class AttributeParser {
	
	// Name matcher for HTML event attributes
	private static KeywordMatcher attrNameMatcher;
	
	public static final int UNKNOWN = 0;
	
	// Event attributes
	public static final int ON_ABORT = 20;
	public static final int ON_BLUR = 21;
	public static final int ON_CHANGE = 22;
	public static final int ON_CLICK = 23;
	public static final int ON_DBL_CLICK = 24;
	public static final int ON_ERROR = 25;
	public static final int ON_FOCUS = 26;
	public static final int ON_KEY_DOWN = 27;
	public static final int ON_KEY_PRESS = 28;
	public static final int ON_KEY_RELEASE = 29;
	public static final int ON_KEY_UP = 30;
	public static final int ON_LOAD = 31;
	public static final int ON_MOUSE_DOWN = 32;
	public static final int ON_MOUSE_MOVE = 33;
	public static final int ON_MOUSE_OUT = 34;
	public static final int ON_MOUSE_OVER = 35;
	public static final int ON_MOUSE_UP = 36;
	public static final int ON_RESET = 37;
	public static final int ON_SELECT = 38;
	public static final int ON_SUBMIT = 39;
	public static final int ON_UNLOAD = 40;
	// Other attributes
	public static final int CLASS = 60;
	public static final int DIR = 61;
	public static final int ID = 62;
	public static final int LANG = 63;
	public static final int STYLE = 64;
	public static final int TITLE = 65;
	public static final int ACCESSKEY = 66;
	public static final int CHARSET = 67;
	public static final int COORDS = 68;
	public static final int HREF = 69;
	public static final int HREFLANG = 70;
	public static final int NAME = 71;
	public static final int REL = 72;
	public static final int REV = 73;
	public static final int SHAPE = 74;
	public static final int TABINDEX = 75;
	public static final int TARGET = 76;
	public static final int TYPE = 77;
	public static final int ALIGN = 78;
	public static final int ALT = 79;
	public static final int ARCHIVE = 80;
	public static final int CODE = 81;
	public static final int CODEBASE = 82;
	public static final int HEIGHT = 83;
	public static final int HSPACE = 84;
	public static final int OBJECT = 85;
	public static final int VSPACE = 86;
	public static final int WIDTH = 87;
	public static final int TABORDER = 88;
	public static final int COLOR = 89;
	public static final int FACE = 90;
	public static final int SIZE = 91;
	public static final int LOOP = 92;
	public static final int SRC = 93;
	public static final int CITE = 94;
	public static final int ALINK = 95;
	public static final int BACKGROUND = 96;
	public static final int BGCOLOR = 97;
	public static final int BGPROPERTIES = 98;
	public static final int LEFTMARGIN = 99;
	public static final int LINK = 100;
	public static final int TEXT = 101;
	public static final int TOPMARGIN = 102;
	public static final int VLINK = 103;
	public static final int CLEAR = 104;
	public static final int VALUE = 105;
	public static final int VALIGN = 106;
	public static final int CHAR = 107;
	public static final int CHAROFF = 108;
	public static final int SPAN = 109;
	public static final int HAS_DBL_EVENT = 110;
	
	
	static {
		// Build the event attribute name matcher
		attrNameMatcher = new KeywordMatcher(true);
		
		attrNameMatcher.addKeyword("onAbort", ON_ABORT);
		attrNameMatcher.addKeyword("onBlur", ON_BLUR);
		attrNameMatcher.addKeyword("onChange", ON_CHANGE);
		attrNameMatcher.addKeyword("onClick", ON_CLICK);
		attrNameMatcher.addKeyword("onDblClick", ON_DBL_CLICK);
		attrNameMatcher.addKeyword("onError", ON_ERROR);
		attrNameMatcher.addKeyword("onFocus", ON_FOCUS);
		attrNameMatcher.addKeyword("onKeyDown", ON_KEY_DOWN);
		attrNameMatcher.addKeyword("onKeyPress", ON_KEY_PRESS);
		attrNameMatcher.addKeyword("onKeyRelease", ON_KEY_RELEASE);
		attrNameMatcher.addKeyword("onKeyUp", ON_KEY_UP);
		attrNameMatcher.addKeyword("onLoad", ON_LOAD);
		attrNameMatcher.addKeyword("onMouseDown", ON_MOUSE_DOWN);
		attrNameMatcher.addKeyword("onMouseMove", ON_MOUSE_MOVE);
		attrNameMatcher.addKeyword("onMouseOut", ON_MOUSE_OUT);
		attrNameMatcher.addKeyword("onMouseOver", ON_MOUSE_OVER);
		attrNameMatcher.addKeyword("onMouseUp", ON_MOUSE_UP);
		attrNameMatcher.addKeyword("onReset", ON_RESET);
		attrNameMatcher.addKeyword("onSelect", ON_SELECT);
		attrNameMatcher.addKeyword("onSubmit", ON_SUBMIT);
		attrNameMatcher.addKeyword("onUnload", ON_UNLOAD);
		
		attrNameMatcher.addKeyword("class", CLASS);
		attrNameMatcher.addKeyword("dir", DIR);
		attrNameMatcher.addKeyword("id", ID);
		attrNameMatcher.addKeyword("lang", LANG);
		attrNameMatcher.addKeyword("style", STYLE);
		attrNameMatcher.addKeyword("title", TITLE);
		attrNameMatcher.addKeyword("accesskey", ACCESSKEY);
		attrNameMatcher.addKeyword("charset", CHARSET);
		attrNameMatcher.addKeyword("coords", COORDS);
		attrNameMatcher.addKeyword("href", HREF);
		attrNameMatcher.addKeyword("hreflang", HREFLANG);
		attrNameMatcher.addKeyword("name", NAME);
		attrNameMatcher.addKeyword("rel", REL);
		attrNameMatcher.addKeyword("rev", REV);
		attrNameMatcher.addKeyword("shape", SHAPE);
		attrNameMatcher.addKeyword("tabindex", TABINDEX);
		attrNameMatcher.addKeyword("target", TARGET);
		attrNameMatcher.addKeyword("type", TYPE);
		attrNameMatcher.addKeyword("align", ALIGN);
		attrNameMatcher.addKeyword("alt", ALT);
		attrNameMatcher.addKeyword("archive", ARCHIVE);
		attrNameMatcher.addKeyword("code", CODE);
		attrNameMatcher.addKeyword("codebase", CODEBASE);
		attrNameMatcher.addKeyword("height", HEIGHT);
		attrNameMatcher.addKeyword("hspace", HSPACE);
		attrNameMatcher.addKeyword("object", OBJECT);
		attrNameMatcher.addKeyword("vspace", VSPACE);
		attrNameMatcher.addKeyword("width", WIDTH);
		attrNameMatcher.addKeyword("taborder", TABORDER);
		attrNameMatcher.addKeyword("color", COLOR);
		attrNameMatcher.addKeyword("face", FACE);
		attrNameMatcher.addKeyword("size", SIZE);
		attrNameMatcher.addKeyword("loop", LOOP);
		attrNameMatcher.addKeyword("src", SRC);
		attrNameMatcher.addKeyword("cite", CITE);
		attrNameMatcher.addKeyword("alink", ALINK);
		attrNameMatcher.addKeyword("background", BACKGROUND);
		attrNameMatcher.addKeyword("bgcolor", BGCOLOR);
		attrNameMatcher.addKeyword("bgproperties", BGPROPERTIES);
		attrNameMatcher.addKeyword("leftmargin", LEFTMARGIN);
		attrNameMatcher.addKeyword("link", LINK);
		attrNameMatcher.addKeyword("text", TEXT);
		attrNameMatcher.addKeyword("topmargin", TOPMARGIN);
		attrNameMatcher.addKeyword("vlink", VLINK);
		attrNameMatcher.addKeyword("clear", CLEAR);
		attrNameMatcher.addKeyword("value", VALUE);
		attrNameMatcher.addKeyword("valign", VALIGN);
		attrNameMatcher.addKeyword("char", CHAR);
		attrNameMatcher.addKeyword("charoff", CHAROFF);
		attrNameMatcher.addKeyword("span", SPAN);
		attrNameMatcher.addKeyword("hasdbevent", HAS_DBL_EVENT);
	}
	
	/**
	 * Find out next attribute in the input string, starting from specified
	 * position.
	 * <p> 
	 * If an attribute is found, it returns an array of five integers. The 
	 * elements of this array are as follows: [nameBeginIndex, nameEndIndex,
	 * valueBeginIndex, valueEndIndex, parseEndIndex]. 
	 * <code>input.substring(nameBeginIndex, nameEndIndex)</code> is the 
	 * attribute name, and 
	 * <code>input.substring(valueBeginIndex, valueEndIndex)</code> is the
	 * attribute value. The <code>parseEndIndex</code> gives the starting 
	 * position of the next parse, i.e., 
	 * <code>nextAttribute(input, parseEndIndex)</code>.
	 * 
	 * @param input
	 * @param position
	 * @return
	 */
	public static Attribute nextAttribute(String input, int position) {
		if (input == null || input.length() <= position)
			return null;
		
		int nameBeginIndex = position;
		for (; nameBeginIndex < input.length(); nameBeginIndex++){
			char c = input.charAt(nameBeginIndex);
			if (!Character.isWhitespace(c) && c != '/')
				break;
		}
		if (nameBeginIndex == input.length())
			return null;

//		char c = input.charAt(nameBeginIndex);
//		if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) 
//			// malformed attribute name
//			return null;
//		int nameEndIndex = nameBeginIndex + 1;
//		for (; nameEndIndex < input.length(); nameEndIndex++) {
//			c = input.charAt(nameEndIndex);
//			if (!(c >= 'a' && c <= 'z') && 
//				!(c >= 'A' && c <= 'Z') &&
//				!(c >= '0' && c <= '9') &&
//				c != '-' && 
//				c != '_' && 
//				c != ':' && 
//				c != '.')
//				// read the end of attribute name
//				break;
//		}
		int nameEndIndex = nameBeginIndex + 1;
		for (; nameEndIndex < input.length(); nameEndIndex++) {
			char c = input.charAt(nameEndIndex);
			if (Character.isWhitespace(c) || c == '=' ||
				c == '/' || c == '>')
				break;
		}
		if (nameEndIndex == input.length())
			return new Attribute(nameBeginIndex, nameEndIndex, 
				nameEndIndex, nameEndIndex, input.length(), NO_DELIMITER);
		
		int valueBeginIndex = nameEndIndex;
		if (input.charAt(valueBeginIndex) != '=') {
			// Find the equal sign
			for (; valueBeginIndex < input.length(); valueBeginIndex++)  
				if (!Character.isWhitespace(input.charAt(valueBeginIndex)))
					break;
			if (valueBeginIndex == input.length() || 
					input.charAt(valueBeginIndex) != '=')
				// Miss the equal sign
				return new Attribute(nameBeginIndex, nameEndIndex,
					nameEndIndex, nameEndIndex, valueBeginIndex, NO_DELIMITER);
		}
		valueBeginIndex++;
		for (; valueBeginIndex < input.length(); valueBeginIndex++)
			if (!Character.isWhitespace(input.charAt(valueBeginIndex)))
				break;
		if (valueBeginIndex == input.length())
			// Attribute has no value
			return new Attribute(nameBeginIndex, nameEndIndex, 
				valueBeginIndex, valueBeginIndex, valueBeginIndex, 
				NO_DELIMITER);
		
		int valueEndIndex = valueBeginIndex + 1;
		boolean escaping = false;
		switch (input.charAt(valueBeginIndex)) {
		case '"':
			// Attribute value delimited by "
			for (; valueEndIndex < input.length(); valueEndIndex++) {
				if (escaping) 
					escaping = false;
				else if (input.charAt(valueEndIndex) == '\\') 
					escaping = true;
				else if (input.charAt(valueEndIndex) == '"')
					break;
			}
			if (valueEndIndex == input.length())
				return new Attribute(nameBeginIndex, nameEndIndex, 
					valueBeginIndex + 1, valueEndIndex, input.length(), 
					QUOTATION_DELIMITER);
			else
				return new Attribute(nameBeginIndex, nameEndIndex, 
					valueBeginIndex + 1, valueEndIndex, valueEndIndex + 1, 
					QUOTATION_DELIMITER);
			
		case '\'':
			// Attribute value delimited by '
			for (; valueEndIndex < input.length(); valueEndIndex++) {
				if (escaping) 
					escaping = false;
				else if (input.charAt(valueEndIndex) == '\\') 
					escaping = true;
				else if (input.charAt(valueEndIndex) == '\'')
					break;
			}
			if (valueEndIndex == input.length())
				return new Attribute(nameBeginIndex, nameEndIndex, 
					valueBeginIndex + 1, valueEndIndex, input.length(), 
					APOSTROPHE_DELIMITER);
			else
				return new Attribute(nameBeginIndex, nameEndIndex, 
					valueBeginIndex + 1, valueEndIndex, valueEndIndex +1, 
					APOSTROPHE_DELIMITER);
			
		default:
			// Attribute value has no delimiter
			for (; valueEndIndex < input.length(); valueEndIndex++) 
				if (Character.isWhitespace(input.charAt(valueEndIndex)))
					break;
		
			return new Attribute(nameBeginIndex, nameEndIndex, 
					valueBeginIndex, valueEndIndex, valueEndIndex, 
					NO_DELIMITER);
		}
			
	}
	
	// Attribute value delimiter
	public static final String NO_DELIMITER = "";
	public static final String QUOTATION_DELIMITER = "\"";
	public static final String APOSTROPHE_DELIMITER = "'";
	
	public static final class Attribute implements Serializable {
		
		private static final long serialVersionUID = -6482083913089406371L;
		
		public final int nameBeginIndex;
		public final int nameEndIndex;
		public final int valueBeginIndex;
		public final int valueEndIndex;
		public final int endIndex;
		// The delimiter character of the attribute value, ", ' or 0 (none).
		public final String delimiter;
		
		public Attribute(int nameBeginIndex, int nameEndIndex, 
				int valueBeginIndex, int valueEndIndex, int endIndex, 
				String delimiter) 
		{
			this.nameBeginIndex = nameBeginIndex;
			this.nameEndIndex = nameEndIndex;
			this.valueBeginIndex = valueBeginIndex;
			this.valueEndIndex = valueEndIndex;
			this.endIndex = endIndex;
			this.delimiter = delimiter;
		}
	}
	
	public static int getAttributeType(String attributeName) {
		if (attributeName == null || attributeName.length() == 0)
			return UNKNOWN;
		
		KeywordMatcher.Match match = attrNameMatcher.hereMatch(attributeName, 0);
		if (match != null && 
			match.beginIndex == 0 && match.endIndex == attributeName.length())
			return (int) match.info;
		else
			return UNKNOWN;
	}

/*	
	public static final class ThreadOrder {
		public static final void main(String args[]) throws Exception {
			
			Reader reader = new InputStreamReader(new FileInputStream(
					"F:/test/v4802.htm"), "UTF-8");
			StringBuilder sb = new StringBuilder();

			char[] buf = new char[4000];
			int nchars = 0;
			while ((nchars = reader.read(buf, 0, buf.length)) >= 0)
				sb.append(buf, 0, nchars);
			reader.close();
			
			String input = sb.toString();
			System.out.println("Input document length: " + input.length());
			
//			long begin = System.currentTimeMillis();
//			int passes = 10000;
//			for (int i = 0; i < passes; i++) {

			Writer writer = new OutputStreamWriter(new FileOutputStream(
					"F:/test/test4802.htm"), "UTF-8");
			Tag tag = null;
			int pos = 0;
			while ((tag = TagParser.nextTag(input, pos)) != null) {
				if (pos < tag.beginIndex)
					writer.write(input, pos, tag.beginIndex - pos);
				
//				writer.write("[========= ");
				if (tag.attrBeginIndex < tag.attrEndIndex) {
					// There's attribute
					writer.write(input, tag.beginIndex, 
							tag.attrBeginIndex - tag.beginIndex);
					if (tag.kind != TagParser.IS_COMMENT) {
						String attrs = input.substring(tag.attrBeginIndex, 
								tag.attrEndIndex);
						Attribute attr = null;
						int attrPos = 0;
						while ((attr = AttributeParser.nextAttribute(attrs, 
								attrPos)) != null) {
							writer.write(attrs, attr.nameBeginIndex, 
									attr.nameEndIndex - attr.nameBeginIndex);
							writer.write("=[");
							writer.write(attrs, attr.valueBeginIndex, 
									attr.valueEndIndex - attr.valueBeginIndex);
							writer.write("] ");
							attrPos = attr.endIndex;
						}
					}
					else
						writer.write(input, tag.attrBeginIndex, 
								tag.attrEndIndex - tag.attrBeginIndex);
					writer.write(input, tag.attrEndIndex, 
							tag.endIndex - tag.attrEndIndex);
				}
				else 
					writer.write(input, tag.beginIndex, 
							tag.endIndex - tag.beginIndex);
//				writer.write(" =========]");
				pos = tag.endIndex;
			}
			if (pos < input.length())
				writer.write(input, pos, input.length() - pos);
			writer.close();
//			}
//			long end = System.currentTimeMillis();
//			System.out.println("" + passes + " passes run in " + 
//				(end - begin) + " ms.");
			
		}
	}
*/	

}
