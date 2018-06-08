package util.html.support;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;

public class TagParser {
	
	// Tag name matcher
	private static KeywordMatcher tagMatcher;
	
	/* 
	 * Invariant: *_BEGIN_TAG is even, *_END_TAG is odd, and 
	 *            *_END_TAG - *_BEGIN_TAG = 1 
	 */
	public static final short UNKNOWN_BEGIN_TAG = 20;
	public static final short UNKNOWN_END_TAG = 21;
	public static final short A_BEGIN_TAG = 22;
	public static final short A_END_TAG = 23;
	public static final short BLOCKQUOTE_BEGIN_TAG = 24;
	public static final short BLOCKQUOTE_END_TAG = 25;
	
	public static final short ABBR_BEGIN_TAG = 30;
	public static final short ABBR_END_TAG = 31;
	public static final short ACRONYM_BEGIN_TAG = 32;
	public static final short ACRONYM_END_TAG = 33;
	public static final short ADDRESS_BEGIN_TAG = 34;
	public static final short ADDRESS_END_TAG = 35;
	public static final short APPLET_BEGIN_TAG = 36;
	public static final short APPLET_END_TAG = 37;
	public static final short AREA_BEGIN_TAG = 38;
	public static final short AREA_END_TAG = 39;
	
	public static final short B_BEGIN_TAG = 40;
	public static final short B_END_TAG = 41;
	public static final short BASE_BEGIN_TAG = 42;
	public static final short BASE_END_TAG = 43;
	public static final short BASEFONT_BEGIN_TAG = 44;
	public static final short BASEFONT_END_TAG = 45;
	public static final short BDO_BEGIN_TAG = 46;
	public static final short BDO_END_TAG = 47;
	public static final short BGSOUND_BEGIN_TAG = 48;
	public static final short BGSOUND_END_TAG = 49;
	
	public static final short BIG_BEGIN_TAG = 50;
	public static final short BIG_END_TAG = 51;
	public static final short BLINK_BEGIN_TAG = 52;
	public static final short BLINK_END_TAG = 53;
	public static final short BODY_BEGIN_TAG = 54;
	public static final short BODY_END_TAG = 55;
	public static final short BR_BEGIN_TAG = 56;
	public static final short BR_END_TAG = 57;
	public static final short BUTTON_BEGIN_TAG = 58;
	public static final short BUTTON_END_TAG = 59;
	
	public static final short CAPTION_BEGIN_TAG = 60;
	public static final short CAPTION_END_TAG = 61;
	public static final short CENTER_BEGIN_TAG = 62;
	public static final short CENTER_END_TAG = 63;
	public static final short CITE_BEGIN_TAG = 64;
	public static final short CITE_END_TAG = 65;
	public static final short CODE_BEGIN_TAG = 66;
	public static final short CODE_END_TAG = 67;
	public static final short COL_BEGIN_TAG = 68;
	public static final short COL_END_TAG = 69;
	
	public static final short COLGROUP_BEGIN_TAG = 70;
	public static final short COLGROUP_END_TAG = 71;
	public static final short DD_BEGIN_TAG = 72;
	public static final short DD_END_TAG = 73;
	public static final short DEL_BEGIN_TAG = 74;
	public static final short DEL_END_TAG = 75;
	public static final short DFN_BEGIN_TAG = 76;
	public static final short DFN_END_TAG = 77;
	public static final short DIR_BEGIN_TAG = 78;
	public static final short DIR_END_TAG = 79;
	
	public static final short DIV_BEGIN_TAG = 80;
	public static final short DIV_END_TAG = 81;
	public static final short DL_BEGIN_TAG = 82;
	public static final short DL_END_TAG = 83;
	public static final short DT_BEGIN_TAG = 84;
	public static final short DT_END_TAG = 85;
	public static final short EM_BEGIN_TAG = 86;
	public static final short EM_END_TAG = 87;
	public static final short EMBED_BEGIN_TAG = 88;
	public static final short EMBED_END_TAG = 89;
	
	public static final short FIELDSET_BEGIN_TAG = 90;
	public static final short FIELDSET_END_TAG = 91;
	public static final short FONT_BEGIN_TAG = 92;
	public static final short FONT_END_TAG = 93;
	public static final short FORM_BEGIN_TAG = 94;
	public static final short FORM_END_TAG = 95;
	public static final short FRAME_BEGIN_TAG = 96;
	public static final short FRAME_END_TAG = 97;
	public static final short FRAMESET_BEGIN_TAG = 98;
	public static final short FRAMESET_END_TAG = 99;

	public static final short H1_BEGIN_TAG = 100;
	public static final short H1_END_TAG = 101;
	public static final short H2_BEGIN_TAG = 102;
	public static final short H2_END_TAG = 103;
	public static final short H3_BEGIN_TAG = 104;
	public static final short H3_END_TAG = 105;
	public static final short H4_BEGIN_TAG = 106;
	public static final short H4_END_TAG = 107;
	public static final short H5_BEGIN_TAG = 108;
	public static final short H5_END_TAG = 109;

	public static final short H6_BEGIN_TAG = 110;
	public static final short H6_END_TAG = 111;
	public static final short HEAD_BEGIN_TAG = 112;
	public static final short HEAD_END_TAG = 113;
	public static final short HR_BEGIN_TAG = 114;
	public static final short HR_END_TAG = 115;
	public static final short HTML_BEGIN_TAG = 116;
	public static final short HTML_END_TAG = 117;
	public static final short I_BEGIN_TAG = 118;
	public static final short I_END_TAG = 119;
	
	public static final short IFRAME_BEGIN_TAG = 120;
	public static final short IFRAME_END_TAG = 121;
	public static final short ILAYER_BEGIN_TAG = 122;
	public static final short ILAYER_END_TAG = 123;
	public static final short IMG_BEGIN_TAG = 124;
	public static final short IMG_END_TAG = 125;
	public static final short INPUT_BEGIN_TAG = 126;
	public static final short INPUT_END_TAG = 127;
	public static final short INS_BEGIN_TAG = 128;
	public static final short INS_END_TAG = 129;
	
	public static final short ISINDEX_BEGIN_TAG = 130;
	public static final short ISINDEX_END_TAG = 131;
	public static final short KBD_BEGIN_TAG = 132;
	public static final short KBD_END_TAG = 133;
	public static final short LABEL_BEGIN_TAG = 134;
	public static final short LABEL_END_TAG = 135;
	public static final short LEGEND_BEGIN_TAG = 136;
	public static final short LEGEND_END_TAG = 137;
	public static final short LI_BEGIN_TAG = 138;
	public static final short LI_END_TAG = 139;
	
	public static final short LINK_BEGIN_TAG = 140;
	public static final short LINK_END_TAG = 141;
	public static final short LISTING_BEGIN_TAG = 142;
	public static final short LISTING_END_TAG = 143;
	public static final short MAP_BEGIN_TAG = 144;
	public static final short MAP_END_TAG = 145;
	public static final short MARQUEE_BEGIN_TAG = 146;
	public static final short MARQUEE_END_TAG = 147;
	public static final short MENU_BEGIN_TAG = 148;
	public static final short MENU_END_TAG = 149;
	
	public static final short META_BEGIN_TAG = 150;
	public static final short META_END_TAG = 151;
	public static final short MULTICOL_BEGIN_TAG = 152;
	public static final short MULTICOL_END_TAG = 153;
	public static final short NEXTID_BEGIN_TAG = 154;
	public static final short NEXTID_END_TAG = 155;
	public static final short NOBR_BEGIN_TAG = 156;
	public static final short NOBR_END_TAG = 157;
	public static final short NOEMBED_BEGIN_TAG = 158;
	public static final short NOEMBED_END_TAG = 159;
	
	public static final short NOFRAMES_BEGIN_TAG = 160;
	public static final short NOFRAMES_END_TAG = 161;
	public static final short NOSCRIPT_BEGIN_TAG = 162;
	public static final short NOSCRIPT_END_TAG = 163;
	public static final short OBJECT_BEGIN_TAG = 164;
	public static final short OBJECT_END_TAG = 165;
	public static final short OL_BEGIN_TAG = 166;
	public static final short OL_END_TAG = 167;
	public static final short OPTGROUP_BEGIN_TAG = 168;
	public static final short OPTGROUP_END_TAG = 169;
	
	public static final short OPTION_BEGIN_TAG = 170;
	public static final short OPTION_END_TAG = 171;
	public static final short P_BEGIN_TAG = 172;
	public static final short P_END_TAG = 173;
	public static final short PARAM_BEGIN_TAG = 174;
	public static final short PARAM_END_TAG = 175;
	public static final short PLAINTEXT_BEGIN_TAG = 176;
	public static final short PLAINTEXT_END_TAG = 177;
	public static final short PRE_BEGIN_TAG = 178;
	public static final short PRE_END_TAG = 179;
	
	public static final short Q_BEGIN_TAG = 180;
	public static final short Q_END_TAG = 181;
	public static final short S_BEGIN_TAG = 182;
	public static final short S_END_TAG = 183;
	public static final short SAMP_BEGIN_TAG = 184;
	public static final short SAMP_END_TAG = 185;
	public static final short SCRIPT_BEGIN_TAG = 186;
	public static final short SCRIPT_END_TAG = 187;
	public static final short SELECT_BEGIN_TAG = 188;
	public static final short SELECT_END_TAG = 189;
	
	public static final short SERVER_BEGIN_TAG = 190;
	public static final short SERVER_END_TAG = 191;
	public static final short SMALL_BEGIN_TAG = 192;
	public static final short SMALL_END_TAG = 193;
	public static final short SPACER_BEGIN_TAG = 194;
	public static final short SPACER_END_TAG = 195;
	public static final short SPAN_BEGIN_TAG = 196;
	public static final short SPAN_END_TAG = 197;
	public static final short STRIKE_BEGIN_TAG = 198;
	public static final short STRIKE_END_TAG = 199;
	
	public static final short STRONG_BEGIN_TAG = 200;
	public static final short STRONG_END_TAG = 201;
	public static final short STYLE_BEGIN_TAG = 202;
	public static final short STYLE_END_TAG = 203;
	public static final short SUB_BEGIN_TAG = 204;
	public static final short SUB_END_TAG = 205;
	public static final short SUP_BEGIN_TAG = 206;
	public static final short SUP_END_TAG = 207;
	public static final short TABLE_BEGIN_TAG = 208;
	public static final short TABLE_END_TAG = 209;
	
	public static final short TBODY_BEGIN_TAG = 210;
	public static final short TBODY_END_TAG = 211;
	public static final short TD_BEGIN_TAG = 212;
	public static final short TD_END_TAG = 213;
	public static final short TEXTAREA_BEGIN_TAG = 214;
	public static final short TEXTAREA_END_TAG = 215;
	public static final short TFOOT_BEGIN_TAG = 216;
	public static final short TFOOT_END_TAG = 217;
	public static final short TH_BEGIN_TAG = 218;
	public static final short TH_END_TAG = 219;
	
	public static final short THEAD_BEGIN_TAG = 220;
	public static final short THEAD_END_TAG = 221;
	public static final short TITLE_BEGIN_TAG = 222;
	public static final short TITLE_END_TAG = 223;
	public static final short TR_BEGIN_TAG = 224;
	public static final short TR_END_TAG = 225;
	public static final short TT_BEGIN_TAG = 226;
	public static final short TT_END_TAG = 227;
	public static final short U_BEGIN_TAG = 228;
	public static final short U_END_TAG = 229;
	
	public static final short UL_BEGIN_TAG = 230;
	public static final short UL_END_TAG = 231;
	public static final short VAR_BEGIN_TAG = 232;
	public static final short VAR_END_TAG = 233;
	public static final short WBR_BEGIN_TAG = 234;
	public static final short WBR_END_TAG = 235;
	public static final short XMP_BEGIN_TAG = 236;
	public static final short XMP_END_TAG = 237;
	
	// Special cases
	public static final short EOF = 300;
	public static final short HTML_COMMENT_TAG = 304;
	public static final short COMMENT_TAG = 305;
	
	/**
	 * Get the tag's start tag type.
	 * 
	 * @param tag
	 * @return
	 */
	public static int getStartTagType(int tag) {
		return tag & ~0x01;
	}
	
	/**
	 * Get the tag's end tag type.
	 * 
	 * @param tag
	 * @return
	 */
	public static int getEndTagType(int tag) {
		return tag | 0x01;
	}
	
	/**
	 * Get the tag's name.
	 * 
	 * @param tag
	 * @param tag
	 * @return
	 */
	public static String getTagName(int tag) {
		tag = getStartTagType(tag);
		switch (tag) {
		case A_BEGIN_TAG:
	        return "a";
		case BLOCKQUOTE_BEGIN_TAG:
		    return "blockquote";
		
		case ABBR_BEGIN_TAG:
		    return "abbr";
		case ACRONYM_BEGIN_TAG:
		    return "acronym";
		case ADDRESS_BEGIN_TAG:
		    return "address";
		case APPLET_BEGIN_TAG:
			return "applet";
		case AREA_BEGIN_TAG:
		    return "area";
		
		case B_BEGIN_TAG:
			return "b";
		case BASE_BEGIN_TAG:
		    return "base";
		case BASEFONT_BEGIN_TAG:
		    return "basefont";
		case BDO_BEGIN_TAG:
			return "bdo";
		case BGSOUND_BEGIN_TAG:
		    return "bgsound";
		
		case BIG_BEGIN_TAG:
			return "big";
		case BLINK_BEGIN_TAG:
			return "blink";
		case BODY_BEGIN_TAG:
		    return "body";
		case BR_BEGIN_TAG:
		    return "br";
		case BUTTON_BEGIN_TAG:
			return "button";
		
		case CAPTION_BEGIN_TAG:
			return "caption";
		case CENTER_BEGIN_TAG:
			return "center";
		case CITE_BEGIN_TAG:
			return "cite";
		case CODE_BEGIN_TAG:
			return "code";
		case COL_BEGIN_TAG:
		    return "col";
		
		case COLGROUP_BEGIN_TAG:
		    return "colgroup";
		case DD_BEGIN_TAG:
		    return "dd";
		case DEL_BEGIN_TAG:
			return "del";
		case DFN_BEGIN_TAG:
			return "dfn";
		case DIR_BEGIN_TAG:
			return "dir";
		
		case DIV_BEGIN_TAG:
		    return "div";
		case DL_BEGIN_TAG:
			return "dl";
		case DT_BEGIN_TAG:
		    return "dt";
		case EM_BEGIN_TAG:
			return "em";
		case EMBED_BEGIN_TAG:
		    return "embed";
		
		case FIELDSET_BEGIN_TAG:
			return "fieldset";
		case FONT_BEGIN_TAG:
			return "font";
		case FORM_BEGIN_TAG:
			return "form";
		case FRAME_BEGIN_TAG:
		    return "frame";
		case FRAMESET_BEGIN_TAG:
			return "frameset";

		case H1_BEGIN_TAG:
			return "h1";
		case H2_BEGIN_TAG:
			return "h2";
		case H3_BEGIN_TAG:
			return "h3";
		case H4_BEGIN_TAG:
			return "h4";
		case H5_BEGIN_TAG:
			return "h5";

		case H6_BEGIN_TAG:
			return "h6";
		case HEAD_BEGIN_TAG:
		    return "head";
		case HR_BEGIN_TAG:
		    return "hr";
		case HTML_BEGIN_TAG:
			return "html";
		case I_BEGIN_TAG:
			return "i";
		
		case IFRAME_BEGIN_TAG:
			return "iframe";
		case ILAYER_BEGIN_TAG:
		    return "ilayer";
		case IMG_BEGIN_TAG:
		    return "img";
		case INPUT_BEGIN_TAG:
		    return "input";
		case INS_BEGIN_TAG:
			return "ins";
		
		case ISINDEX_BEGIN_TAG:
		    return "isindex";
		case KBD_BEGIN_TAG:
			return "kbd";
		case LABEL_BEGIN_TAG:
			return "label";
		case LEGEND_BEGIN_TAG:
		    return "legend";
		case LI_BEGIN_TAG:
		    return "li";
		
		case LINK_BEGIN_TAG:
		    return "link";
		case LISTING_BEGIN_TAG:
			return "listing";
		case MAP_BEGIN_TAG:
			return "map";
		case MARQUEE_BEGIN_TAG:
			return "marquee";
		case MENU_BEGIN_TAG:
			return "menu";
		
		case META_BEGIN_TAG:
		    return "meta";
		case MULTICOL_BEGIN_TAG:
		    return "multicol";
		case NEXTID_BEGIN_TAG:
		    return "nextid";
		case NOBR_BEGIN_TAG:
			return "nobr";
		case NOEMBED_BEGIN_TAG:
			return "noembed";
		
		case NOFRAMES_BEGIN_TAG:
		    return "noframes";
		case NOSCRIPT_BEGIN_TAG:
			return "noscript";
		case OBJECT_BEGIN_TAG:
			return "object";
		case OL_BEGIN_TAG:
			return "ol";
		case OPTGROUP_BEGIN_TAG:
		    return "optgroup";
		
		case OPTION_BEGIN_TAG:
		    return "option";
		case P_BEGIN_TAG:
		    return "p";
		case PARAM_BEGIN_TAG:
		    return "param";
		case PLAINTEXT_BEGIN_TAG:
		    return "plaintext";
		case PRE_BEGIN_TAG:
			return "pre";
		
		case Q_BEGIN_TAG:
			return "q";
		case S_BEGIN_TAG:
			return "s";
		case SAMP_BEGIN_TAG:
			return "samp";
		case SCRIPT_BEGIN_TAG:
			return "script";
		case SELECT_BEGIN_TAG:
			return "select";
		
		case SERVER_BEGIN_TAG:
			return "server";
		case SMALL_BEGIN_TAG:
			return "small";
		case SPACER_BEGIN_TAG:
		    return "spacer";
		case SPAN_BEGIN_TAG:
			return "span";
		case STRIKE_BEGIN_TAG:
			return "strike";
		
		case STRONG_BEGIN_TAG:
			return "strong";
		case STYLE_BEGIN_TAG:
		    return "style";
		case SUB_BEGIN_TAG:
			return "sub";
		case SUP_BEGIN_TAG:
			return "sup";
		case TABLE_BEGIN_TAG:
			return "table";
		
		case TBODY_BEGIN_TAG:
		    return "tbody";
		case TD_BEGIN_TAG:
		    return "td";
		case TEXTAREA_BEGIN_TAG:
			return "textarea";
		case TFOOT_BEGIN_TAG:
		    return "tfoot";
		case TH_BEGIN_TAG:
		    return "th";
		
		case THEAD_BEGIN_TAG:
		    return "thead";
		case TITLE_BEGIN_TAG:
			return "title";
		case TR_BEGIN_TAG:
		    return "tr";
		case TT_BEGIN_TAG:
			return "tt";
		case U_BEGIN_TAG:
			return "u";
		
		case UL_BEGIN_TAG:
			return "ul";
		case VAR_BEGIN_TAG:
			return "var";
		case WBR_BEGIN_TAG:
		    return "wbr";
		case XMP_BEGIN_TAG:
			return "xmp";
		}
		return "unknown";
	}
	
	
	static {
		tagMatcher = new KeywordMatcher(true);
		
		tagMatcher.addKeyword("a", A_BEGIN_TAG);
		tagMatcher.addKeyword("abbr", ABBR_BEGIN_TAG);

		tagMatcher.addKeyword("acronym", ACRONYM_BEGIN_TAG);
		tagMatcher.addKeyword("address", ADDRESS_BEGIN_TAG);
		tagMatcher.addKeyword("applet", APPLET_BEGIN_TAG);
		tagMatcher.addKeyword("area", AREA_BEGIN_TAG);
		tagMatcher.addKeyword("b", B_BEGIN_TAG);

		tagMatcher.addKeyword("base", BASE_BEGIN_TAG);
		tagMatcher.addKeyword("basefont", BASEFONT_BEGIN_TAG);
		tagMatcher.addKeyword("bdo", BDO_BEGIN_TAG);
		tagMatcher.addKeyword("bgsound", BGSOUND_BEGIN_TAG);
		tagMatcher.addKeyword("big", BIG_BEGIN_TAG);

		tagMatcher.addKeyword("blink", BLINK_BEGIN_TAG);
		tagMatcher.addKeyword("blockquote", BLOCKQUOTE_BEGIN_TAG);
		tagMatcher.addKeyword("body", BODY_BEGIN_TAG);
		tagMatcher.addKeyword("br", BR_BEGIN_TAG);
		tagMatcher.addKeyword("button", BUTTON_BEGIN_TAG);
		
		tagMatcher.addKeyword("caption", CAPTION_BEGIN_TAG);
		tagMatcher.addKeyword("center", CENTER_BEGIN_TAG);
		tagMatcher.addKeyword("cite", CITE_BEGIN_TAG);
		tagMatcher.addKeyword("code", CODE_BEGIN_TAG);
		tagMatcher.addKeyword("col", COL_BEGIN_TAG);
		
		tagMatcher.addKeyword("colgroup", COLGROUP_BEGIN_TAG);
		tagMatcher.addKeyword("dd", DD_BEGIN_TAG);
		tagMatcher.addKeyword("del", DEL_BEGIN_TAG);
		tagMatcher.addKeyword("dfn", DFN_BEGIN_TAG);
		tagMatcher.addKeyword("dir", DIR_BEGIN_TAG);
		
		tagMatcher.addKeyword("div", DIV_BEGIN_TAG);
		tagMatcher.addKeyword("dl", DL_BEGIN_TAG);
		tagMatcher.addKeyword("dt", DT_BEGIN_TAG);
		tagMatcher.addKeyword("em", EM_BEGIN_TAG);
		tagMatcher.addKeyword("embed", EMBED_BEGIN_TAG);
		
		tagMatcher.addKeyword("fieldset", FIELDSET_BEGIN_TAG);
		tagMatcher.addKeyword("font", FONT_BEGIN_TAG);
		tagMatcher.addKeyword("form", FORM_BEGIN_TAG);
		tagMatcher.addKeyword("frame", FRAME_BEGIN_TAG);
		tagMatcher.addKeyword("frameset", FRAMESET_BEGIN_TAG);
		
		tagMatcher.addKeyword("h1", H1_BEGIN_TAG);
		tagMatcher.addKeyword("h2", H2_BEGIN_TAG);
		tagMatcher.addKeyword("h3", H3_BEGIN_TAG);
		tagMatcher.addKeyword("h4", H4_BEGIN_TAG);
		tagMatcher.addKeyword("h5", H5_BEGIN_TAG);

		tagMatcher.addKeyword("h6", H6_BEGIN_TAG);
		tagMatcher.addKeyword("head", HEAD_BEGIN_TAG);
		tagMatcher.addKeyword("hr", HR_BEGIN_TAG);
		tagMatcher.addKeyword("html", HTML_BEGIN_TAG);
		tagMatcher.addKeyword("i", I_BEGIN_TAG);
		
		tagMatcher.addKeyword("iframe", IFRAME_BEGIN_TAG);
		tagMatcher.addKeyword("ilayer", ILAYER_BEGIN_TAG);
		tagMatcher.addKeyword("img", IMG_BEGIN_TAG);
		tagMatcher.addKeyword("input", INPUT_BEGIN_TAG);
		tagMatcher.addKeyword("ins", INS_BEGIN_TAG);
		
		tagMatcher.addKeyword("isindex", ISINDEX_BEGIN_TAG);
		tagMatcher.addKeyword("kbd", KBD_BEGIN_TAG);
		tagMatcher.addKeyword("label", LABEL_BEGIN_TAG);
		tagMatcher.addKeyword("legend", LEGEND_BEGIN_TAG);
		tagMatcher.addKeyword("li", LI_BEGIN_TAG);
		
		tagMatcher.addKeyword("link", LINK_BEGIN_TAG);
		tagMatcher.addKeyword("listing", LISTING_BEGIN_TAG);
		tagMatcher.addKeyword("map", MAP_BEGIN_TAG);
		tagMatcher.addKeyword("marquee", MARQUEE_BEGIN_TAG);
		tagMatcher.addKeyword("menu", MENU_BEGIN_TAG);
		
		tagMatcher.addKeyword("meta", META_BEGIN_TAG);
		tagMatcher.addKeyword("multicol", MULTICOL_BEGIN_TAG);
		tagMatcher.addKeyword("nextid", NEXTID_BEGIN_TAG);
		tagMatcher.addKeyword("nobr", NOBR_BEGIN_TAG);
		tagMatcher.addKeyword("noembed", NOEMBED_BEGIN_TAG);
		
		tagMatcher.addKeyword("noframes", NOFRAMES_BEGIN_TAG);
		tagMatcher.addKeyword("noscript", NOSCRIPT_BEGIN_TAG);
		tagMatcher.addKeyword("object", OBJECT_BEGIN_TAG);
		tagMatcher.addKeyword("ol", OL_BEGIN_TAG);
		tagMatcher.addKeyword("optgroup", OPTGROUP_BEGIN_TAG);
		
		tagMatcher.addKeyword("option", OPTION_BEGIN_TAG);
		tagMatcher.addKeyword("p", P_BEGIN_TAG);
		tagMatcher.addKeyword("param", PARAM_BEGIN_TAG);
		tagMatcher.addKeyword("plaintext", PLAINTEXT_BEGIN_TAG);
		tagMatcher.addKeyword("pre", PRE_BEGIN_TAG);
		
		tagMatcher.addKeyword("q", Q_BEGIN_TAG);
		tagMatcher.addKeyword("s", S_BEGIN_TAG);
		tagMatcher.addKeyword("samp", SAMP_BEGIN_TAG);
		tagMatcher.addKeyword("script", SCRIPT_BEGIN_TAG);
		tagMatcher.addKeyword("select", SELECT_BEGIN_TAG);
		
		tagMatcher.addKeyword("server", SERVER_BEGIN_TAG);
		tagMatcher.addKeyword("small", SMALL_BEGIN_TAG);
		tagMatcher.addKeyword("spacer", SPACER_BEGIN_TAG);
		tagMatcher.addKeyword("span", SPAN_BEGIN_TAG);
		tagMatcher.addKeyword("strike", STRIKE_BEGIN_TAG);
		
		tagMatcher.addKeyword("strong", STRONG_BEGIN_TAG);
		tagMatcher.addKeyword("style", STYLE_BEGIN_TAG);
		tagMatcher.addKeyword("sub", SUB_BEGIN_TAG);
		tagMatcher.addKeyword("sup", SUP_BEGIN_TAG);
		tagMatcher.addKeyword("table", TABLE_BEGIN_TAG);
		
		tagMatcher.addKeyword("tbody", TBODY_BEGIN_TAG);
		tagMatcher.addKeyword("td", TD_BEGIN_TAG);
		tagMatcher.addKeyword("textarea", TEXTAREA_BEGIN_TAG);
		tagMatcher.addKeyword("tfoot", TFOOT_BEGIN_TAG);
		tagMatcher.addKeyword("th", TH_BEGIN_TAG);
		
		tagMatcher.addKeyword("thead", THEAD_BEGIN_TAG);
		tagMatcher.addKeyword("title", TITLE_BEGIN_TAG);
		tagMatcher.addKeyword("tr", TR_BEGIN_TAG);
		tagMatcher.addKeyword("tt", TT_BEGIN_TAG);
		tagMatcher.addKeyword("u", U_BEGIN_TAG);
		
		tagMatcher.addKeyword("ul", UL_BEGIN_TAG);
		tagMatcher.addKeyword("var", VAR_BEGIN_TAG);
		tagMatcher.addKeyword("wbr", WBR_BEGIN_TAG);
		tagMatcher.addKeyword("xmp", XMP_BEGIN_TAG);
		
	}
	
	// Begin tag, e.g., <h2>
	public static final short IS_START_TAG = 0;
	// End tag, e.g., </h2>
	public static final short IS_END_TAG = 1;
	// Self-closed tag, e.g., <br/>
	public static final short IS_EMPTY_ELEMENT_TAG = 2;
	// Comment tag, delimited by <!-- --> or <! > pair
	public static final short IS_COMMENT = 3;
	// Plain text
	public static final short IS_PLAIN_TEXT = 4;
	// Unknown tag
	public static final short IS_UNKNOWN_TAG = 5;
	
	/**
	 * Find the end of attributes part of a tag. The attributes part is like:
	 * <form^ action="http://baike.soso.com/Create.e" method="post">
	 * <table^ >
	 * <br^ />
	 * If the part starting at specified position of input string is not a
	 * well-formed attributes part, this method return -1. Otherwise does it
	 * return the index of the position past the attributes part.
	 *  
	 * @param input
	 * @param position
	 * @return
	 */
	private static int gotoAttributesEnd(String input, int position) {
		if (input == null || input.length() <= position)
			return -1;
		
		if (position < 0)
			position = 0;
		while (position < input.length()) {
			int i = position;
			// Skip leading white space
			for (; i < input.length(); i++)
				if (!Character.isWhitespace(input.charAt(i)))
					break;
			if (i == input.length())
				// malformed attributes part
				return -1;
			
			// Attributes part should ended at '>' or "/>"
			if (input.charAt(i) == '>')
				// we reach the end of the tag
				return i + 1;
			else if (input.charAt(i) == '/') {
				++i;
				if (i < input.length() && input.charAt(i) == '>')
					// we reach the end of the tag 
					return i + 1;
				else
					// malformed attributes part
//					return -1;
					position = i+1;
			}
			else {
				// Check an attribute and move position to next attribute
				char c;
				for (; i < input.length(); i++) {
					c = input.charAt(i);
					if (Character.isWhitespace(c) || 
						c == '=' || c == '/' || c == '>')
						break;
				}
				
//				char c = input.charAt(i);
//				if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z'))
//					// malformed attribute name
//					return -1;
//				for (i++; i < input.length(); i++) {
//					c = input.charAt(i);
//					if (!(c >= 'a' && c <= 'z') && 
//						!(c >= 'A' && c <= 'Z') &&
//						!(c >= '0' && c <= '9') &&
//						c != '-' && 
//						c != '_' && 
//						c != ':' && 
//						c != '.')
//						break;
//				}
				
				if (i == input.length())
					// malformed attribute
					return -1;
					
				for (; i < input.length(); i++)
					if (!Character.isWhitespace(input.charAt(i)))
						break;
				if (i == input.length())
					// malformed attribute
					return -1;
				if (input.charAt(i) != '=') {
					// attribute without equal sign and value part
					position = i;
					continue;
				}
				
				// Check attribute value
				i++;
				for (; i < input.length(); i++) 
					if (!Character.isWhitespace(input.charAt(i)))
						break;
				if (i == input.length())
					// malformed attributes part
					return -1;
				
				boolean escaping = false;
				switch (input.charAt(i)) {
				case '"':
					// Attribute value delimited by "
					for (++i; i < input.length(); i++) {
						if (escaping) 
							escaping = false;
						else if (input.charAt(i) == '\\') 
							escaping = true;
						else if (input.charAt(i) == '"')
							break;
					}
					if (i == input.length())
						// malformed attribute value
						return -1;
					else
						// continue to search next attribute
						position = i + 1;
					break;
					
				case '\'':
					// Attribute value delimited by '
					for (++i; i < input.length(); i++) {
						if (escaping) 
							escaping = false;
						else if (input.charAt(i) == '\\') 
							escaping = true;
						else if (input.charAt(i) == '\'')
							break;
					}
					if (i == input.length())
						// malformed attribute value
						return -1;
					else
						// continue to search next attribute
						position = i + 1;
					break;
					
				default:
					// Attribute value has no delimiter
					for (; i < input.length(); i++) {
						c = input.charAt(i);
						if (Character.isWhitespace(c) || c == '>' ||
							(c == '/' && i+1 < input.length() && 
									input.charAt(i+1) == '>'))
							break;
					}
				
					if (i == input.length())
						// malformed attributes part
						return -1;
					else
						position = i;
					break;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Search for next tag in the input string starting from specified position.
	 * If no tag is found, it returns null. If a tag is found, it returns an 
	 * array of six integers. The elements of the array
	 * are as follows: [beginIndex, endIndex, tag, flag, beginIndexOfAttr, 
	 * endIndexOfAttr].
	 * <p>   
	 * Note: For malformed tag, the left angle bracket '<' should be considered 
	 * as a normal character and escaped. In fact, malformed tag should be 
	 * considered as normal text instead of a tag.
	 *  
	 * @param input
	 * @param position
	 * @return
	 */
	public static Tag nextTag(String input, int position) {
		if (input == null || input.length() <= position)
			return null;
		
		if (position < 0)
			position = 0;
		KeywordMatcher.Match match = null;
		boolean matched;
		
		while (position < input.length()) {
			// Find next '<'
			int beginIndex = input.indexOf('<', position);
			if (beginIndex < 0 || beginIndex == input.length() - 1)
				// No more tag
				return null;
	
			switch (input.charAt(beginIndex + 1)) {
			
			case '/':
				// Possibility: a well known end tag
				match = tagMatcher.hereMatch(input, beginIndex + 2);
				matched = false;
				if (match != null)
					// Check to see whether the tag match is complete
					if (match.endIndex < input.length()) {
						char c = input.charAt(match.endIndex);
						if (!(c >= 'a' && c <= 'z') && 
							!(c >= 'A' && c <= 'Z') &&
							!(c >= '0' && c <= '9') &&
							c != '-' && 
							c != '_' && 
							c != ':' && 
							c != '.')
							matched = true;
					}
					else
						matched = true;
				
				if (matched) {
					// tag name matched
					int endIndex = gotoAttributesEnd(input, match.endIndex);
					if (endIndex < 0 || input.charAt(endIndex - 2) == '/') 
						// malformed. continue
						position = match.endIndex;
					else
						return new Tag(beginIndex, endIndex, 
								(short) getEndTagType((int) match.info), 
								IS_END_TAG, match.endIndex, endIndex - 1);
				}
				else {
					// Possibility: an unknown end tag
					int attrBeginIndex = beginIndex + 2;
					if (attrBeginIndex == input.length())  
						// malformed
						return null;
					
					char c = input.charAt(attrBeginIndex);
					if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
						// malformed, continue
						position = attrBeginIndex;
						break;
					}
					attrBeginIndex++;
					for (; attrBeginIndex < input.length(); attrBeginIndex++) {
						c = input.charAt(attrBeginIndex);
						if (!(c >= 'a' && c <= 'z') && 
							!(c >= 'A' && c <= 'Z') &&
							!(c >= '0' && c <= '9') &&
							c != '-' && 
							c != '_' && 
							c != ':' && 
							c != '.')
							// reach the end of the tag name
							break;
					}
					if (attrBeginIndex == input.length())
						// malformed
						return null;
					else {
						// Go to the end of attribute part
						int endIndex = gotoAttributesEnd(input, attrBeginIndex);
						if (endIndex < 0 || input.charAt(endIndex - 2) == '/')
							// malformed. continue
							position = attrBeginIndex;
						else
							return new Tag(beginIndex, endIndex, 
									UNKNOWN_END_TAG, IS_UNKNOWN_TAG, 
									attrBeginIndex, endIndex - 1);
					}
				}
				break;
			
			case '!':
			case '?':
				// Possibility: a comment or DTD
				int index = beginIndex + 2;
				if (index < input.length() - 2 && 
					input.charAt(index) == '-' &&
					input.charAt(index + 1) == '-') {
					// Possibility: a comment
					index = input.indexOf("-->", index + 2);
					if (index < 0) 
						// malformed
						return null;
					else
						return new Tag(beginIndex, index + 3, 
								UNKNOWN_BEGIN_TAG, IS_COMMENT, 
								beginIndex + 4, index);
				}
				else {
					// Possibility: a DTD
					index = input.indexOf('>', beginIndex + 2);
					if (index < 0) 
						// malformed
						return null;
					
					boolean valid = false;
					int i = beginIndex + 2;
					for (; i < index; i++)
						if (Character.isWhitespace(input.charAt(i))) {
							valid = true;
							break;
						}
					if (valid)
						return new Tag(beginIndex, index + 1,
								UNKNOWN_BEGIN_TAG, IS_COMMENT, 
								i, index);
					else 
						// malformed. continue
						position = beginIndex + 2;
				}
				break;
				
			default:
				// Possibility: a well-known start or empty-element tag
				match = tagMatcher.hereMatch(input, beginIndex + 1);
				matched = false;
				if (match != null)
					// Check to see whether the tag match is complete
					if (match.endIndex < input.length()) {
						char c = input.charAt(match.endIndex);
						if (!(c >= 'a' && c <= 'z') && 
							!(c >= 'A' && c <= 'Z') &&
							!(c >= '0' && c <= '9') &&
							c != '-' && 
							c != '_' && 
							c != ':' && 
							c != '.')
							matched = true;
					}
					else
						matched = true;
			
				if (matched) {
					int endIndex = gotoAttributesEnd(input, match.endIndex);
					if (endIndex < 0) 
						// malformed. continue
						position = match.endIndex;
					else if (input.charAt(endIndex - 2) == '/')
						return new Tag(beginIndex, endIndex, 
								(short) getStartTagType((int) match.info),
								IS_EMPTY_ELEMENT_TAG, 
								match.endIndex, endIndex - 2);
					else
						return new Tag(beginIndex, endIndex, 
								(short) getStartTagType((int) match.info),
								IS_START_TAG, match.endIndex, endIndex - 1);
				}
				else {
					// Possibility: an unknown start or empty-element tag
					int attrBeginIndex = beginIndex + 1;
					char c = input.charAt(attrBeginIndex);
					if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
						// malformed, continue
						position = attrBeginIndex;
						break;
					}
					attrBeginIndex++;
					for (; attrBeginIndex < input.length(); attrBeginIndex++) {
						c = input.charAt(attrBeginIndex);
						if (!(c >= 'a' && c <= 'z') && 
							!(c >= 'A' && c <= 'Z') &&
							!(c >= '0' && c <= '9') &&
							c != '-' && 
							c != '_' && 
							c != ':' && 
							c != '.')
							// reach the end of the tag name
							break;
					}
					if (attrBeginIndex == input.length())
						// malformed
						return null;
					else {
						// Go to the end of attribute part
						int endIndex = gotoAttributesEnd(input, attrBeginIndex);
						if (endIndex < 0) 
							// malformed. continue
							position = attrBeginIndex;
						else if (input.charAt(endIndex - 2) == '/') 
							return new Tag(beginIndex, endIndex, 
									UNKNOWN_BEGIN_TAG, IS_UNKNOWN_TAG, 
									attrBeginIndex, endIndex - 2);
						else
							return new Tag(beginIndex, endIndex, 
									UNKNOWN_BEGIN_TAG, IS_UNKNOWN_TAG,
									attrBeginIndex, endIndex - 1);
					}
				}
				break;
				
			}			
			
		}
		return null;
	}
	
	/**
	 * Whether this begin tag's end tag cannot be omitted?
	 * 
	 * @param tag
	 * @return
	 */
	public static boolean isEndTagNeverOmitted(int tag) {
		switch (tag) {
		case A_BEGIN_TAG:
		case ABBR_BEGIN_TAG:
		case ACRONYM_BEGIN_TAG:
		case ADDRESS_BEGIN_TAG:
		case APPLET_BEGIN_TAG:
		case B_BEGIN_TAG:
		case BDO_BEGIN_TAG:
		case BIG_BEGIN_TAG:
		case BLINK_BEGIN_TAG:
		case BLOCKQUOTE_BEGIN_TAG:
		case BUTTON_BEGIN_TAG:
		case CAPTION_BEGIN_TAG:
		case CENTER_BEGIN_TAG:
		case CITE_BEGIN_TAG:
		case CODE_BEGIN_TAG:
		case DEL_BEGIN_TAG:
		case DFN_BEGIN_TAG:
		case DIR_BEGIN_TAG:
		case DL_BEGIN_TAG:
		case EM_BEGIN_TAG:
		case FIELDSET_BEGIN_TAG:
		case FONT_BEGIN_TAG:
		case FORM_BEGIN_TAG:
		case FRAMESET_BEGIN_TAG:
		case H1_BEGIN_TAG:
		case H2_BEGIN_TAG:
		case H3_BEGIN_TAG:
		case H4_BEGIN_TAG:
		case H5_BEGIN_TAG:
		case H6_BEGIN_TAG:
		case I_BEGIN_TAG:
		case ILAYER_BEGIN_TAG:
		case IFRAME_BEGIN_TAG:
		case INS_BEGIN_TAG:
		case KBD_BEGIN_TAG:
		case LABEL_BEGIN_TAG:
		case LISTING_BEGIN_TAG:
		case MAP_BEGIN_TAG:
		case MARQUEE_BEGIN_TAG:
		case MENU_BEGIN_TAG:
		case MULTICOL_BEGIN_TAG:
		case NOBR_BEGIN_TAG:
		case NOEMBED_BEGIN_TAG:
		case NOSCRIPT_BEGIN_TAG:
		case OBJECT_BEGIN_TAG:
		case OL_BEGIN_TAG:
		case PRE_BEGIN_TAG:
		case Q_BEGIN_TAG:
		case S_BEGIN_TAG:
		case SAMP_BEGIN_TAG:
		case SCRIPT_BEGIN_TAG:
		case SELECT_BEGIN_TAG:
		case SERVER_BEGIN_TAG:
		case SMALL_BEGIN_TAG:
		case SPAN_BEGIN_TAG:
		case STRIKE_BEGIN_TAG:
		case STRONG_BEGIN_TAG:
		case SUB_BEGIN_TAG:
		case SUP_BEGIN_TAG:
		case TABLE_BEGIN_TAG:
		case TEXTAREA_BEGIN_TAG:
		case TITLE_BEGIN_TAG:
		case TT_BEGIN_TAG:
		case U_BEGIN_TAG:
		case UL_BEGIN_TAG:
		case VAR_BEGIN_TAG:
		case XMP_BEGIN_TAG:
			return true;
		}
		return false;
	}
	
	public static boolean isBeginEndTagPair(int beginTag, int endTag) {
		return (((beginTag & ~0x01) == beginTag) && (endTag == beginTag + 1)); 
	}
	
	/**
	 * Whether the begin tag could be ended by the other tag?
	 * 
	 * @param beginTag
	 * @param tag
	 * @return
	 */
	public static boolean tagCanBeEndedBy(int beginTag, int tag) {
		switch (beginTag) {
		case A_BEGIN_TAG:
	        return (tag == A_END_TAG);
		case BLOCKQUOTE_BEGIN_TAG:
		    return (tag == BLOCKQUOTE_END_TAG);
		
		case ABBR_BEGIN_TAG:
		    return (tag == ABBR_END_TAG);
		case ACRONYM_BEGIN_TAG:
		    return (tag == ACRONYM_END_TAG);
		case ADDRESS_BEGIN_TAG:
		    return (tag == ADDRESS_END_TAG);
		case APPLET_BEGIN_TAG:
			return (tag == APPLET_END_TAG);
		case AREA_BEGIN_TAG:
		    return true;
		
		case B_BEGIN_TAG:
			switch (tag) {
			case B_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case BASE_BEGIN_TAG:
		    return true;
		case BASEFONT_BEGIN_TAG:
		    return true;
		case BDO_BEGIN_TAG:
			return (tag == BDO_END_TAG);
		case BGSOUND_BEGIN_TAG:
		    return true;
		
		case BIG_BEGIN_TAG:
			switch (tag) {
			case BIG_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case BLINK_BEGIN_TAG:
			switch (tag) {
			case BLINK_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case BODY_BEGIN_TAG:
		    switch (tag) {
		    case BODY_END_TAG:
		    case HTML_END_TAG:
		    case EOF:
		    	return true;
		    }
		    return false;
		case BR_BEGIN_TAG:
		    return true;
		case BUTTON_BEGIN_TAG:
			return (tag == BUTTON_END_TAG);
		
		case CAPTION_BEGIN_TAG:
			return (tag == CAPTION_END_TAG);
		case CENTER_BEGIN_TAG:
			return (tag == CENTER_END_TAG);
		case CITE_BEGIN_TAG:
			return (tag == CITE_END_TAG);
		case CODE_BEGIN_TAG:
			return (tag == CODE_END_TAG);
		case COL_BEGIN_TAG:
		    return true;
		
		case COLGROUP_BEGIN_TAG:		// ********** TBD
		    switch (tag) {
		    case COLGROUP_END_TAG:
		    case COLGROUP_BEGIN_TAG:
		    case THEAD_BEGIN_TAG:
		    case TFOOT_BEGIN_TAG:
		    case TBODY_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case DD_BEGIN_TAG:		// ******** TBD
		    switch (tag) {
		    case DD_END_TAG:
		    case DT_BEGIN_TAG:
		    case DL_END_TAG:
		    	return true;
		    }
		    return false;
		case DEL_BEGIN_TAG:
			return (tag == DEL_END_TAG);
		case DFN_BEGIN_TAG:
			return (tag == DFN_END_TAG);
		case DIR_BEGIN_TAG:
			return (tag == DIR_END_TAG);
		
		case DIV_BEGIN_TAG:		// ********* TBD
			switch (tag) {
			case DIV_END_TAG:
			case DIV_BEGIN_TAG:	
			case BODY_END_TAG:
			case HTML_END_TAG:
			case EOF:
				return true;
			}
		    return false;
		case DL_BEGIN_TAG:
			return (tag == DL_END_TAG);
		case DT_BEGIN_TAG:		// ********* DT
		    switch (tag) {
		    case DT_END_TAG:
		    case DD_BEGIN_TAG:
		    	return true;
		    }
		    return false;
		case EM_BEGIN_TAG:
			return (tag == EM_END_TAG);
		case EMBED_BEGIN_TAG:
		    return true;
		
		case FIELDSET_BEGIN_TAG:
			return (tag == FIELDSET_END_TAG);
		case FONT_BEGIN_TAG:
			return (tag == FONT_END_TAG);
		case FORM_BEGIN_TAG:
			return (tag == FORM_END_TAG);
		case FRAME_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case FRAME_END_TAG:
		    case FRAME_BEGIN_TAG:
		    case FRAMESET_END_TAG:
		    	return true;
		    }
		    return false;
		case FRAMESET_BEGIN_TAG:
			return (tag == FRAMESET_END_TAG);

		case H1_BEGIN_TAG:
			return (tag == H1_END_TAG);
		case H2_BEGIN_TAG:
			return (tag == H2_END_TAG);
		case H3_BEGIN_TAG:
			return (tag == H3_END_TAG);
		case H4_BEGIN_TAG:
			return (tag == H4_END_TAG);
		case H5_BEGIN_TAG:
			return (tag == H5_END_TAG);

		case H6_BEGIN_TAG:
			return (tag == H6_END_TAG);
		case HEAD_BEGIN_TAG:
		    switch (tag) {
		    case HEAD_END_TAG:
		    case BODY_BEGIN_TAG:
		    case HTML_END_TAG:
		    case EOF:
		    	return true;
		    }
		    return false;
		case HR_BEGIN_TAG:
		    return true;
		case HTML_BEGIN_TAG:
			return (tag == HTML_END_TAG) || (tag == EOF);
		case I_BEGIN_TAG:
			switch (tag) {
			case I_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		
		case IFRAME_BEGIN_TAG:
			return (tag == IFRAME_END_TAG);
		case ILAYER_BEGIN_TAG:
		    return (tag == ILAYER_END_TAG);
		case IMG_BEGIN_TAG:
		    return true;
		case INPUT_BEGIN_TAG:
		    return true;
		case INS_BEGIN_TAG:
			return (tag == INS_END_TAG);
		
		case ISINDEX_BEGIN_TAG:
		    return true;
		case KBD_BEGIN_TAG:
			return (tag == KBD_END_TAG);
		case LABEL_BEGIN_TAG:
			return (tag == LABEL_END_TAG);
		case LEGEND_BEGIN_TAG:
		    return true;
		case LI_BEGIN_TAG:		// ******* TBD
		    switch (tag) {
		    case LI_END_TAG:
		    case LI_BEGIN_TAG:
		    case UL_END_TAG:
		    case OL_END_TAG:
		    	return true;
		    }
		    return false;
		
		case LINK_BEGIN_TAG:
		    return true;
		case LISTING_BEGIN_TAG:
			return (tag == LISTING_END_TAG);
		case MAP_BEGIN_TAG:
			return (tag == MAP_END_TAG);
		case MARQUEE_BEGIN_TAG:
			return (tag == MARQUEE_END_TAG);
		case MENU_BEGIN_TAG:
			return (tag == MENU_END_TAG);
		
		case META_BEGIN_TAG:
		    return true;
		case MULTICOL_BEGIN_TAG:
		    return (tag == MULTICOL_END_TAG);
		case NEXTID_BEGIN_TAG:
		    return true;
		case NOBR_BEGIN_TAG:
			return (tag == NOBR_END_TAG);
		case NOEMBED_BEGIN_TAG:
			return (tag == NOEMBED_END_TAG);
		
		case NOFRAMES_BEGIN_TAG:
		    return true;
		case NOSCRIPT_BEGIN_TAG:
			return (tag == NOSCRIPT_END_TAG);
		case OBJECT_BEGIN_TAG:
			return (tag == OBJECT_END_TAG);
		case OL_BEGIN_TAG:
			return (tag == OL_END_TAG);
		case OPTGROUP_BEGIN_TAG:
		    return true;
		
		case OPTION_BEGIN_TAG:
		    return true;
		case P_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case P_END_TAG:
		    case P_BEGIN_TAG:
		    case BODY_END_TAG:
		    case HTML_END_TAG:
		    case EOF:
		    	return true;
		    }
		    return false;
		case PARAM_BEGIN_TAG:
		    return true;
		case PLAINTEXT_BEGIN_TAG:
		    return true;
		case PRE_BEGIN_TAG:
			return (tag == PRE_END_TAG);
		
		case Q_BEGIN_TAG:
			return (tag == Q_END_TAG);
		case S_BEGIN_TAG:
			switch (tag) {
			case S_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case SAMP_BEGIN_TAG:
			return (tag == SAMP_END_TAG);
		case SCRIPT_BEGIN_TAG:
			switch (tag) {
			case SCRIPT_END_TAG:
			case HEAD_END_TAG:
			case BODY_END_TAG:
			case HTML_END_TAG:
			case EOF:
				return true;
			}
			return false;
		case SELECT_BEGIN_TAG:
			return (tag == SELECT_END_TAG);
		
		case SERVER_BEGIN_TAG:
			return (tag == SERVER_END_TAG);
		case SMALL_BEGIN_TAG:
			switch (tag) {
			case SMALL_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case SPACER_BEGIN_TAG:
		    return true;
		case SPAN_BEGIN_TAG:
			return (tag == SPAN_END_TAG);
		case STRIKE_BEGIN_TAG:
			switch (tag) {
			case STRIKE_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		
		case STRONG_BEGIN_TAG:
			return (tag == STRONG_END_TAG);
		case STYLE_BEGIN_TAG:
		    return true;
		case SUB_BEGIN_TAG:
			switch (tag) {
			case SUB_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case SUP_BEGIN_TAG:
			switch (tag) {
			case SUP_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case TABLE_BEGIN_TAG:
			return (tag == TABLE_END_TAG);
		
		case TBODY_BEGIN_TAG:		// *********** TBD
		    switch (tag) {
		    case TBODY_END_TAG:
		    case TABLE_END_TAG:
		    case TBODY_BEGIN_TAG:
		    	return true;
		    }
		    return false;
		case TD_BEGIN_TAG:		// ********** TBD
		    switch (tag) {
		    case TD_END_TAG:
		    case TD_BEGIN_TAG:
		    case TH_BEGIN_TAG:
		    case TR_END_TAG:
		    case TR_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case TEXTAREA_BEGIN_TAG:
			return (tag == TEXTAREA_END_TAG);
		case TFOOT_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case TFOOT_END_TAG:
		    case TBODY_BEGIN_TAG:
		    	return true;
		    }
		    return false;
		case TH_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case TH_END_TAG:
		    case TH_BEGIN_TAG:
		    case TD_BEGIN_TAG:
		    case TR_END_TAG:
		    case TR_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		
		case THEAD_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case THEAD_END_TAG:
		    case TBODY_BEGIN_TAG:
		    case TFOOT_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case TITLE_BEGIN_TAG:
			return (tag == TITLE_END_TAG);
		case TR_BEGIN_TAG:		// ********** TBD
		    switch (tag) {
		    case TR_END_TAG:
		    case TR_BEGIN_TAG:
		    case TBODY_END_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case TT_BEGIN_TAG:
			switch (tag) {
			case TT_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case U_BEGIN_TAG:
			switch (tag) {
			case U_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		
		case UL_BEGIN_TAG:
			return (tag == UL_END_TAG);
		case VAR_BEGIN_TAG:
			return (tag == VAR_END_TAG);
		case WBR_BEGIN_TAG:
		    return true;
		case XMP_BEGIN_TAG:
			return (tag == XMP_END_TAG);
		}
		return false;
	}
	
	/**
	 * Whether the begin tag is self closed (i.e., standalone)? Of course, its
	 * corresponding end tag can close it, too. Normally, these tags have no
	 * content.
	 * 
	 * @param beginTag
	 * @param beginTag
	 * @return
	 */
	public static boolean isStandaloneBeginTag(int beginTag) {
		switch (beginTag) {
		case AREA_BEGIN_TAG:
		case BASE_BEGIN_TAG:
		case BGSOUND_BEGIN_TAG:
		case BR_BEGIN_TAG:
		case COL_BEGIN_TAG:
		case EMBED_BEGIN_TAG:
		case HR_BEGIN_TAG:
		case IMG_BEGIN_TAG:
		case INPUT_BEGIN_TAG:
		case ISINDEX_BEGIN_TAG:
		case LINK_BEGIN_TAG:
		case META_BEGIN_TAG:
		case NEXTID_BEGIN_TAG:
		case PARAM_BEGIN_TAG:
		case PLAINTEXT_BEGIN_TAG:	// This's incorrect. 
									// But since it's obsolete, who cares?
		case SPACER_BEGIN_TAG:
		case WBR_BEGIN_TAG:
		    return true;
		}
		return false;
	}
	
	/**
	 * Whether the begin tag must be closed if the other tag is encountered?
	 * 
	 * @param beginTag
	 * @param tag
	 * @return
	 */
	public static boolean isBeginTagClosedBy(int beginTag, int tag) {
		switch (beginTag) {
		case A_BEGIN_TAG:
	        return (tag == A_END_TAG);
		case BLOCKQUOTE_BEGIN_TAG:
		    return (tag == BLOCKQUOTE_END_TAG);
		
		case ABBR_BEGIN_TAG:
		    return (tag == ABBR_END_TAG);
		case ACRONYM_BEGIN_TAG:
		    return (tag == ACRONYM_END_TAG);
		case ADDRESS_BEGIN_TAG:
		    return (tag == ADDRESS_END_TAG);
		case APPLET_BEGIN_TAG:
			return (tag == APPLET_END_TAG);
		case AREA_BEGIN_TAG:
		    return (tag == AREA_END_TAG);
		
		case B_BEGIN_TAG:
			switch (tag) {
			case B_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case BASE_BEGIN_TAG:
		    return (tag == BASE_END_TAG);
		case BASEFONT_BEGIN_TAG:
		    return (tag == BASEFONT_END_TAG);
		case BDO_BEGIN_TAG:
			return (tag == BDO_END_TAG);
		case BGSOUND_BEGIN_TAG:
		    return true;
		
		case BIG_BEGIN_TAG:
			switch (tag) {
			case BIG_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case BLINK_BEGIN_TAG:
			switch (tag) {
			case BLINK_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case BODY_BEGIN_TAG:
		    switch (tag) {
		    case BODY_END_TAG:
		    case HTML_END_TAG:
		    case EOF:
		    	return true;
		    }
		    return false;
		case BR_BEGIN_TAG:
		    return true;
		case BUTTON_BEGIN_TAG:
			return (tag == BUTTON_END_TAG);
		
		case CAPTION_BEGIN_TAG:
			return (tag == CAPTION_END_TAG);
		case CENTER_BEGIN_TAG:
			return (tag == CENTER_END_TAG);
		case CITE_BEGIN_TAG:
			return (tag == CITE_END_TAG);
		case CODE_BEGIN_TAG:
			return (tag == CODE_END_TAG);
		case COL_BEGIN_TAG:
			switch (tag) {
			case COL_END_TAG:
			case COL_BEGIN_TAG:
			case COLGROUP_BEGIN_TAG:
			case COLGROUP_END_TAG:
				return true;
			}
		    return true;
		
		case COLGROUP_BEGIN_TAG:		// ********** TBD
		    switch (tag) {
		    case COLGROUP_END_TAG:
		    case COLGROUP_BEGIN_TAG:
		    case THEAD_BEGIN_TAG:
		    case TFOOT_BEGIN_TAG:
		    case TBODY_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case DD_BEGIN_TAG:		// ******** TBD
		    switch (tag) {
		    case DD_END_TAG:
		    case DT_BEGIN_TAG:
		    case DL_END_TAG:
		    	return true;
		    }
		    return false;
		case DEL_BEGIN_TAG:
			return (tag == DEL_END_TAG);
		case DFN_BEGIN_TAG:
			return (tag == DFN_END_TAG);
		case DIR_BEGIN_TAG:
			return (tag == DIR_END_TAG);
		
		case DIV_BEGIN_TAG:		// ********* TBD
			switch (tag) {
			case DIV_END_TAG:
			case BODY_END_TAG:
			case HTML_END_TAG:
			case EOF:
				return true;
			}
		    return false;
		case DL_BEGIN_TAG:
			return (tag == DL_END_TAG);
		case DT_BEGIN_TAG:		// ********* DT
		    switch (tag) {
		    case DT_END_TAG:
		    case DD_BEGIN_TAG:
		    case DL_END_TAG:
		    	return true;
		    }
		    return false;
		case EM_BEGIN_TAG:
			return (tag == EM_END_TAG);
		case EMBED_BEGIN_TAG:
		    return true;
		
		case FIELDSET_BEGIN_TAG:
			return (tag == FIELDSET_END_TAG);
		case FONT_BEGIN_TAG:
			return (tag == FONT_END_TAG);
		case FORM_BEGIN_TAG:
			return (tag == FORM_END_TAG);
		case FRAME_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case FRAME_END_TAG:
		    case FRAME_BEGIN_TAG:
		    case FRAMESET_END_TAG:
		    	return true;
		    }
		    return false;
		case FRAMESET_BEGIN_TAG:
			return (tag == FRAMESET_END_TAG);

		case H1_BEGIN_TAG:
			return (tag == H1_END_TAG);
		case H2_BEGIN_TAG:
			return (tag == H2_END_TAG);
		case H3_BEGIN_TAG:
			return (tag == H3_END_TAG);
		case H4_BEGIN_TAG:
			return (tag == H4_END_TAG);
		case H5_BEGIN_TAG:
			return (tag == H5_END_TAG);

		case H6_BEGIN_TAG:
			return (tag == H6_END_TAG);
		case HEAD_BEGIN_TAG:
		    switch (tag) {
		    case HEAD_END_TAG:
		    case BODY_BEGIN_TAG:
		    case HTML_END_TAG:
		    case EOF:
		    	return true;
		    }
		    return false;
		case HR_BEGIN_TAG:
		    return true;
		case HTML_BEGIN_TAG:
			return (tag == HTML_END_TAG) || (tag == EOF);
		case I_BEGIN_TAG:
			switch (tag) {
			case I_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		
		case IFRAME_BEGIN_TAG:
			return (tag == IFRAME_END_TAG);
		case ILAYER_BEGIN_TAG:
		    return (tag == ILAYER_END_TAG);
		case IMG_BEGIN_TAG:
		    return true;
		case INPUT_BEGIN_TAG:
		    return true;
		case INS_BEGIN_TAG:
			return (tag == INS_END_TAG);
		
		case ISINDEX_BEGIN_TAG:
		    return true;
		case KBD_BEGIN_TAG:
			return (tag == KBD_END_TAG);
		case LABEL_BEGIN_TAG:
			return (tag == LABEL_END_TAG);
		case LEGEND_BEGIN_TAG:
		    return (tag == LEGEND_END_TAG);
		case LI_BEGIN_TAG:		// ******* TBD
		    switch (tag) {
		    case LI_END_TAG:
		    case LI_BEGIN_TAG:
		    case UL_END_TAG:
		    case OL_END_TAG:
		    	return true;
		    }
		    return false;
		
		case LINK_BEGIN_TAG:
		    return true;
		case LISTING_BEGIN_TAG:
			return (tag == LISTING_END_TAG);
		case MAP_BEGIN_TAG:
			return (tag == MAP_END_TAG);
		case MARQUEE_BEGIN_TAG:
			return (tag == MARQUEE_END_TAG);
		case MENU_BEGIN_TAG:
			return (tag == MENU_END_TAG);
		
		case META_BEGIN_TAG:
		    return true;
		case MULTICOL_BEGIN_TAG:
		    return (tag == MULTICOL_END_TAG);
		case NEXTID_BEGIN_TAG:
		    return true;
		case NOBR_BEGIN_TAG:
			return (tag == NOBR_END_TAG);
		case NOEMBED_BEGIN_TAG:
			return (tag == NOEMBED_END_TAG);
		
		case NOFRAMES_BEGIN_TAG:
		    return (tag == NOFRAMES_END_TAG);
		case NOSCRIPT_BEGIN_TAG:
			return (tag == NOSCRIPT_END_TAG);
		case OBJECT_BEGIN_TAG:
			return (tag == OBJECT_END_TAG);
		case OL_BEGIN_TAG:
			return (tag == OL_END_TAG);
		case OPTGROUP_BEGIN_TAG:
			switch (tag) {
			case OPTGROUP_END_TAG:
			case OPTGROUP_BEGIN_TAG:
			case SELECT_END_TAG:
				return true;
			}
		    return true;
		
		case OPTION_BEGIN_TAG:
			switch (tag) {
			case OPTION_END_TAG:
			case OPTGROUP_END_TAG:
			case OPTGROUP_BEGIN_TAG:
			case SELECT_END_TAG:
				return true;
			}
		    return false;
		case P_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case P_END_TAG:
		    case P_BEGIN_TAG:
		    case H1_BEGIN_TAG:
		    case H2_BEGIN_TAG:
		    case H3_BEGIN_TAG:
		    case H4_BEGIN_TAG:
		    case H5_BEGIN_TAG:
		    case H6_BEGIN_TAG:
		    case BODY_END_TAG:
		    case HTML_END_TAG:
		    case EOF:
		    	return true;
		    }
		    return false;
		case PARAM_BEGIN_TAG:
		    return true;
		case PLAINTEXT_BEGIN_TAG:
		    return (tag == EOF);
		case PRE_BEGIN_TAG:
			return (tag == PRE_END_TAG);
		
		case Q_BEGIN_TAG:
			return (tag == Q_END_TAG);
		case S_BEGIN_TAG:
			switch (tag) {
			case S_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case SAMP_BEGIN_TAG:
			return (tag == SAMP_END_TAG);
		case SCRIPT_BEGIN_TAG:
			switch (tag) {
			case SCRIPT_END_TAG:
			case HEAD_END_TAG:
			case BODY_END_TAG:
			case HTML_END_TAG:
				return true;
			}
			return false;
		case SELECT_BEGIN_TAG:
			return (tag == SELECT_END_TAG);
		
		case SERVER_BEGIN_TAG:
			return (tag == SERVER_END_TAG);
		case SMALL_BEGIN_TAG:
			switch (tag) {
			case SMALL_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case SPACER_BEGIN_TAG:
		    return true;
		case SPAN_BEGIN_TAG:
			return (tag == SPAN_END_TAG);
		case STRIKE_BEGIN_TAG:
			switch (tag) {
			case STRIKE_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		
		case STRONG_BEGIN_TAG:
			return (tag == STRONG_END_TAG);
		case STYLE_BEGIN_TAG:
		    return true;
		case SUB_BEGIN_TAG:
			switch (tag) {
			case SUB_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case SUP_BEGIN_TAG:
			switch (tag) {
			case SUP_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case TABLE_BEGIN_TAG:
			return (tag == TABLE_END_TAG);
		
		case TBODY_BEGIN_TAG:		// *********** TBD
		    switch (tag) {
		    case TBODY_END_TAG:
		    case TABLE_END_TAG:
		    case TBODY_BEGIN_TAG:
		    	return true;
		    }
		    return false;
		case TD_BEGIN_TAG:		// ********** TBD
		    switch (tag) {
		    case TD_END_TAG:
		    case TD_BEGIN_TAG:
		    case TH_BEGIN_TAG:
		    case TR_END_TAG:
		    case TR_BEGIN_TAG:
		    case TBODY_END_TAG:
		    case TBODY_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case TEXTAREA_BEGIN_TAG:
			return (tag == TEXTAREA_END_TAG);
		case TFOOT_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case TFOOT_END_TAG:
		    case TBODY_BEGIN_TAG:
		    	return true;
		    }
		    return false;
		case TH_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case TH_END_TAG:
		    case TH_BEGIN_TAG:
		    case TD_BEGIN_TAG:
		    case TR_END_TAG:
		    case TR_BEGIN_TAG:
		    case THEAD_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		
		case THEAD_BEGIN_TAG:		// ********* TBD
		    switch (tag) {
		    case THEAD_END_TAG:
		    case TBODY_BEGIN_TAG:
		    case TFOOT_BEGIN_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case TITLE_BEGIN_TAG:
			return (tag == TITLE_END_TAG);
		case TR_BEGIN_TAG:		// ********** TBD
		    switch (tag) {
		    case TR_END_TAG:
		    case TR_BEGIN_TAG:
		    case TBODY_END_TAG:
		    case TABLE_END_TAG:
		    	return true;
		    }
		    return false;
		case TT_BEGIN_TAG:
			switch (tag) {
			case TT_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		case U_BEGIN_TAG:
			switch (tag) {
			case U_END_TAG:
			case P_BEGIN_TAG:
			case P_END_TAG:
				return true;
			}
			return false;
		
		case UL_BEGIN_TAG:
			return (tag == UL_END_TAG);
		case VAR_BEGIN_TAG:
			return (tag == VAR_END_TAG);
		case WBR_BEGIN_TAG:
		    return true;
		case XMP_BEGIN_TAG:
			return (tag == XMP_END_TAG);
		}
		return false;
	}
	
	
	public static final class Tag implements Serializable {
		
		private static final long serialVersionUID = 4972844083854737278L;
		
		// Begin index of the tag in the input document
		public final int beginIndex;
		// End index of the tag in the input document
		public final int endIndex;
		// What tag it is, e.g., SCRIPT_BEGIN_TAG, P_BEGIN_TAG?
		public final short type;
		// Is this tag a begin tag, end tag, or begin-end tag?
		public final short kind;
		// Begin index of the attributes part of the tag in the input document
		public final int attrBeginIndex;
		// End index of the attributes part of the tag in the input document
		public final int attrEndIndex;
		
		public Tag(int beginIndex, int endIndex, short type, short kind, 
				int attrBeginIndex, int attrEndIndex) {
			this.beginIndex = beginIndex;
			this.endIndex = endIndex;
			this.type = type;
			this.kind = kind;
			this.attrBeginIndex = attrBeginIndex;
			this.attrEndIndex = attrEndIndex;
		}
		
	}
	
	
	public static final class Test {
		public static final void main(String args[]) throws Exception {
			Reader reader = new InputStreamReader(new FileInputStream("F:/test/v4802.htm"), "UTF-8");
			StringBuilder sb = new StringBuilder();

			char[] buf = new char[4000];
			int nchars = 0;
			while ((nchars = reader.read(buf, 0, buf.length)) >= 0)
				sb.append(buf, 0, nchars);
			reader.close();
			
			String input = sb.toString();
			System.out.println("Input document length: " + input.length());
			
			long begin = System.currentTimeMillis();
			int passes = 10000;
			for (int i = 0; i < passes; i++) {

//			Writer writer = new OutputStreamWriter(new FileOutputStream("F:/test/test4802.htm"), "UTF-8");
			Tag tag = null;
			int pos = 0;
			while ((tag = TagParser.nextTag(input, pos)) != null) {
//				if (pos < tag.beginIndex)
//					writer.write(input, pos, tag.beginIndex - pos);
				
//				writer.write("[========= ");
//				if (tag.attrBeginIndex < tag.attrEndIndex) {
					// There's attribute
//					writer.write(input, tag.beginIndex, tag.attrBeginIndex - tag.beginIndex);
//					writer.write("[");
//					writer.write(input, tag.attrBeginIndex, tag.attrEndIndex - tag.attrBeginIndex);
//					writer.write("]");
//					writer.write(input, tag.attrEndIndex, tag.endIndex - tag.attrEndIndex);
//				}
//				else 
//					writer.write(input, tag.beginIndex, tag.endIndex - tag.beginIndex);
//				writer.write(" =========]");
				
				pos = tag.endIndex;
			}
//			if (pos < input.length())
//				writer.write(input, pos, input.length() - pos);
//			writer.close();

			}
			long end = System.currentTimeMillis();
			System.out.println("" + passes + " passes run in " + (end - begin) + " ms.");
			
		}
	}

}
