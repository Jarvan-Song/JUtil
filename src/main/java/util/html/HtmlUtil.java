package util.html;

import util.html.support.AttributeParser;
import util.html.support.AttributeParser.*;
import util.html.support.HtmlParser;
import util.html.support.HtmlParser.*;
import util.html.support.TagParser;
import util.html.support.VideoElementChanger;


import java.util.*;
import java.util.regex.Pattern;

import static util.html.support.TagParser.*;
import static util.html.support.AttributeParser.APOSTROPHE_DELIMITER;
import static util.html.support.AttributeParser.QUOTATION_DELIMITER;


public class HtmlUtil {

	/**
	 * Interface for HTML document element selector, which selects from an input
	 * HTML document the element we want to operate on (e.g., replaced).
	 *
	 *
	 */
	public interface ElementSelector {
		/**
		 * Whether this element is the target we want to operate on?
		 *
		 * @param doc
		 * @param element
		 * @return
		 */
		boolean isTargetElement(String doc, Element element);
	}

	/**
	 * Retrieve document element specified by the selector.
	 *
	 * @param doc
	 * @param selector
	 * @return String
	 */
	public static String extractDocumentElement(String doc,
												ElementSelector selector)
	{
		if (doc == null || doc.length() == 0 || selector == null)
			return doc;
		Element document = HtmlParser.parse(doc);
		if (document == null)
			return null;

		/**
		 * Search for the HTML element. This is a Depth First Search algorithm.
		 */
		Stack<Node> hierarchy = new Stack<Node>();
		hierarchy.push(document);
		int position = 0;
		while (!hierarchy.empty()) {
			Node node = hierarchy.peek();

			if (node instanceof Element) {
				Element e = (Element) node;

				if (e.beginTagBeginIndex >= position) {
					// The first time we traverse this element
					position = e.beginTagEndIndex;

					if (selector.isTargetElement(doc, e)) {
						return doc.substring(e.beginTagBeginIndex, e.endIndex);
					}
				}
				// Check this element's children
				Collection<Node> children = e.getChildren();
				if (children == null || children.isEmpty()) {
					// back trace
					position = e.endIndex;
					hierarchy.pop();
				} else {
					Iterator<Node> iter = children.iterator();
					boolean done = true;
					while (iter.hasNext()) {
						Node cur = iter.next();
						if (cur.beginIndex >= position) {
							hierarchy.push(cur);
							done = false;
							break;
						}
					}
					if (done) {
						// back trace
						position = e.endIndex;
						hierarchy.pop();
					}
				}
			} else {
				// Ignore other HTML element
				position = node.endIndex;
				hierarchy.pop();
			}
		}

		return null;
	}

	/**
	 * Retrieve document element specified by the selector.
	 *
	 * @param doc
	 * @param selector
	 * @return String
	 */
	public static Element extractElement(String doc,
										 ElementSelector selector)
	{
		if (doc == null || doc.length() == 0 || selector == null)
			return null;
		Element document = HtmlParser.parse(doc);
		if (document == null)
			return null;

		/**
		 * Search for the HTML element. This is a Depth First Search algorithm.
		 */
		Stack<Node> hierarchy = new Stack<Node>();
		hierarchy.push(document);
		int position = 0;
		while (!hierarchy.empty()) {
			Node node = hierarchy.peek();

			if (node instanceof Element) {
				Element e = (Element) node;

				if (e.beginTagBeginIndex >= position) {
					// The first time we traverse this element
					position = e.beginTagEndIndex;

					if (selector.isTargetElement(doc, e)) {
						return e;
					}
				}
				// Check this element's children
				Collection<Node> children = e.getChildren();
				if (children == null || children.isEmpty()) {
					// back trace
					position = e.endIndex;
					hierarchy.pop();
				} else {
					Iterator<Node> iter = children.iterator();
					boolean done = true;
					while (iter.hasNext()) {
						Node cur = iter.next();
						if (cur.beginIndex >= position) {
							hierarchy.push(cur);
							done = false;
							break;
						}
					}
					if (done) {
						// back trace
						position = e.endIndex;
						hierarchy.pop();
					}
				}
			} else {
				// Ignore other HTML element
				position = node.endIndex;
				hierarchy.pop();
			}
		}

		return null;
	}

	private static final String HTTP_URL_PREFIX = "http://";
	private static final String WENWEN_URL_PREFIX = "http://wenwen.soso.com/";
	private static final String WENWEN_PIC_URL_PREFIX = "http://pic.wenwen.soso.com/";
	public static final Pattern WEIBO_PIC_URL_PATTERN = Pattern.compile("^http://.*\\.qpic\\.cn/.*$");
	private static final Pattern SOGOU_CDN_PATTERN = Pattern.compile("^http://.*\\.sogoucdn\\.com/.*$");

	/**
	 * Retrieve source of images in the input HTML document. If an image's url is relative, or a
	 * wenwen picture url (http://wenwen.soso.com/p/... or http://pic.wenwen.soso.com/p...), we
	 * extract the file name part only. Otherwise we keep the original url untouched.
	 *
	 * @param document
	 * @return
	 */
	public static List<String> retrieveImageSources(String document) {
		List<String> result = null;
		TagParser.Tag tag = null;
		int position = 0;
		while ((tag = TagParser.nextTag(document, position)) != null) {
			if (tag.type == IMG_BEGIN_TAG) {
				String attrs = document.substring(tag.attrBeginIndex, tag.attrEndIndex);
				Attribute attr = null;
				int apos = 0;
				while ((attr = AttributeParser.nextAttribute(attrs, apos)) != null) {
					String name = attrs.substring(attr.nameBeginIndex, attr.nameEndIndex);
					if ("src".equalsIgnoreCase(name)) {
						String path = attrs.substring(attr.valueBeginIndex, attr.valueEndIndex);
						if (!path.startsWith(HTTP_URL_PREFIX) ||
								path.startsWith(WENWEN_URL_PREFIX) ||
								path.startsWith(WENWEN_PIC_URL_PREFIX)) {
							// This is not an absolute url, or it's an Wenwen picture url
							// extract the image file name part
							int idx = path.lastIndexOf('/');
							if (idx >= 0)
								path = path.substring(idx + 1);
							// skip the non-digit prefix, if any
							for (idx = 0; idx < path.length(); idx++)
								if (Character.isDigit(path.charAt(idx)))
									break;
							if (idx > 0)
								path = path.substring(idx);
						}

						if (result == null)
							result = new ArrayList<String>(4);
						result.add(path);
						break;
					}
					apos = attr.endIndex;
				}
			}
			position = tag.endIndex;
		}

		return result;
	}

	/**
	 * Document element attributes transformer. It returns the changed
	 * attributes part. If an element is not changed, it should return the
	 * original attributes part of the element.
	 *
	 *
	 */
	public interface AttributesTransformer {
		public String transformAttributes(String doc, Element element);
	}

	/**
	 * Transform elements' attributes using specified attributes transformer.
	 *
	 * @param doc
	 * @param transformer
	 * @return
	 */
	public static String transformElementAttributes(String doc,
													AttributesTransformer transformer)
	{
		if (doc == null || doc.length() == 0 || transformer == null)
			return doc;
		Element document = HtmlParser.parse(doc);
		if (document == null)
			return doc;

		/**
		 * Search for the HTML element we want to replace and replace it. This
		 * is a Depth First Search algorithm.
		 */
		StringBuilder buf = new StringBuilder();
		Stack<Node> hierarchy = new Stack<Node>();
		hierarchy.push(document);
		int position = 0;
		while (!hierarchy.empty()) {
			Node node = hierarchy.peek();

			if (node instanceof Element) {
				Element e = (Element) node;

				if (e.beginTagBeginIndex >= position) {
					// The first time we traverse this element
					position = e.beginTagEndIndex;

					if (e != document)
						buf.append(doc.substring(e.beginTagBeginIndex,
								e.attributeBeginIndex));

					buf.append(transformer.transformAttributes(doc, e));

					if (e != document)
						buf.append(doc.substring(e.attributeEndIndex,
								e.beginTagEndIndex));
				}
				// Check this element's children
				Collection<Node> children = e.getChildren();
				if (children == null || children.isEmpty()) {
					if (e != document && e.contentEndIndex < e.endIndex) {
						// This element has an end tag
						buf.append(doc.substring(e.contentEndIndex,
								e.endIndex));
					}
					// back trace
					position = e.endIndex;
					hierarchy.pop();
				} else {
					Iterator<Node> iter = children.iterator();
					boolean done = true;
					while (iter.hasNext()) {
						Node cur = iter.next();
						if (cur.beginIndex >= position) {
							hierarchy.push(cur);
							done = false;
							break;
						}
					}
					if (done) {
						if (e != document && e.contentEndIndex < e.endIndex) {
							// This element has an end tag
							buf.append(doc.substring(e.contentEndIndex,
									e.endIndex));
						}
						position = e.endIndex;
						hierarchy.pop();
					}
				}
			} else {
				// Output other HTML elements directly
				buf.append(doc.substring(node.beginIndex, node.endIndex));
				position = node.endIndex;
				hierarchy.pop();
			}
		}

		return buf.toString();
	}

	/**
	 * Interface for HTML document element changer, which changes elements of an
	 * input HTML document. The replacement string for the input element is
	 * returned.
	 *
	 *
	 */
	public interface ElementChanger {
		/**
		 * Return the replacement string for the element. If the input element
		 * is not an element you want to replace, simply returns null. If you
		 * want to remove this element from output HTML document, simply returns
		 * an empty string ("").
		 *
		 * @param doc
		 * @param element
		 * @return
		 */
		String replaceElement(String doc, Element element);
	}

	public static final short REPLACED = 1;

	/**
	 * Replace document element with specified replacement. The elements are
	 * selected by the specified element selector.
	 *
	 * @param doc
	 * @param doc
	 * @param changer
	 * @return
	 */
	public static String replaceDocumentElement(String doc,
												ElementChanger changer)
	{
		if (doc == null || doc.length() == 0 || changer == null)
			return doc;
		Element document = HtmlParser.parse(doc);
		if (document == null)
			return doc;

		/**
		 * Search for the HTML element we want to replace and replace it. This
		 * is a Depth First Search algorithm.
		 */
		StringBuilder buf = new StringBuilder();
		Stack<Node> hierarchy = new Stack<Node>();
		hierarchy.push(document);
		int position = 0;
		while (!hierarchy.empty()) {
			Node node = hierarchy.peek();

			if (node instanceof Element) {
				Element e = (Element) node;

				if (e.beginTagBeginIndex >= position) {
					// The first time we traverse this element
					position = e.beginTagEndIndex;

					String replacement = changer.replaceElement(doc, e);
					if (replacement != null) {
						buf.append(replacement);
						e.flags = REPLACED;
						position = e.endIndex;
					} else if (e != document) {
						buf.append(doc.substring(e.beginTagBeginIndex,
								e.beginTagEndIndex));
					}
				}
				// Check this element's children
				Collection<Node> children = e.getChildren();
				if (children == null || children.isEmpty()) {
					if (e != document && e.contentEndIndex < e.endIndex
							&& e.flags != REPLACED) {
						// This element has an end tag
						buf.append(doc.substring(e.contentEndIndex,
								e.endIndex));
					}
					// back trace
					position = e.endIndex;
					hierarchy.pop();
				} else {
					Iterator<Node> iter = children.iterator();
					boolean done = true;
					while (iter.hasNext()) {
						Node cur = iter.next();
						if (cur.beginIndex >= position) {
							hierarchy.push(cur);
							done = false;
							break;
						}
					}
					if (done) {
						if (e != document && e.contentEndIndex < e.endIndex
								&& e.flags != REPLACED) {
							// This element has an end tag
							buf.append(doc.substring(e.contentEndIndex,
									e.endIndex));
						}
						position = e.endIndex;
						hierarchy.pop();
					}
				}
			} else {
				// Output other HTML elements directly
				buf.append(doc.substring(node.beginIndex, node.endIndex));
				position = node.endIndex;
				hierarchy.pop();
			}
		}

		return buf.toString();
	}

	/**
	 * Count images and characters (excluding whitespace) in the document. The
	 * first element of the returned array is the number of images, and the
	 * second is the number of characters.
	 *
	 * @param document
	 * @return
	 */
	public static int[] countImagesAndCharacters(String document) {
		if (document == null || document.length() == 0)
			return new int[]{0, 0};

		int imageCount = 0;
		int charCount = 0;

		TagParser.Tag tag = null;
		int position = 0;
		while ((tag = TagParser.nextTag(document, position)) != null) {
			for (int i = position; i < tag.beginIndex; i++) {
				char c = document.charAt(i);
				if (!Character.isWhitespace(c) && c != '　')
					charCount++;
			}
			if (tag.type == IMG_BEGIN_TAG)
				imageCount++;
			position = tag.endIndex;
		}
		for (int i = position; i < document.length(); i++) {
			char c = document.charAt(i);
			if (!Character.isWhitespace(c) && c != '　')
				charCount++;
		}
		return new int[]{imageCount, charCount};
	}

	/**
	 * Retrieve the value of specified attribute of an element.
	 *
	 * @param doc
	 * @param element
	 * @param attributeName
	 * @return
	 */
	public static String getAttributeValue(String doc, Element element,
										   String attributeName)
	{
		String attributes = doc.substring(element.attributeBeginIndex,
				element.attributeEndIndex);
		if (attributes.length() == 0)
			return null;

		Attribute attr = null;
		String name, value;
		int pos = 0;
		while ((attr = AttributeParser.nextAttribute(attributes, pos)) != null)
		{
			pos = attr.endIndex;

			name = attributes.substring(attr.nameBeginIndex,
					attr.nameEndIndex);
			if (name.equalsIgnoreCase(attributeName)) {
				value = attributes.substring(attr.valueBeginIndex,
						attr.valueEndIndex);

				if ((attr.delimiter.equals(QUOTATION_DELIMITER) ||
						attr.delimiter.equals(APOSTROPHE_DELIMITER)) &&
						value.indexOf('\\') >= 0) {

					boolean escaping = false;

					StringBuilder buf = new StringBuilder();
					for (int i = 0; i < value.length(); i++) {
						char c = value.charAt(i);
						if (escaping) {
							buf.append(c);
							escaping = false;
						}
						else if (c == '\\')
							escaping = true;
						else
							buf.append(c);
					}
					return buf.toString();
				}
				else
					return value;
			}
		}

		return null;
	}

	/**
	 * Determine whether the input string is an HTML document.
	 *
	 * @param input
	 * @return
	 */
	public static boolean isHtmlDocument(String input) {
		if (input == null || input.length() == 0)
			return false;

		return TagParser.nextTag(input, 0) != null;
	}

	/**
	 * Remove all markups in the input document. This is a utility method.
	 *
	 * @param input
	 * @return
	 */
	public static String removeMarkups(String input) {
		if (input == null || input.length() == 0)
			return input;

		StringBuilder buf = new StringBuilder();
		TagParser.Tag tag = null;
		int pos = 0;
		while ((tag = TagParser.nextTag(input, pos)) != null) {
			if (pos < tag.beginIndex)
				buf.append(input, pos, tag.beginIndex);
			pos = tag.endIndex;
		}
		if (pos < input.length())
			buf.append(input, pos, input.length());

		return buf.toString();
	}

	/**
	 * Replace all markups in the input document with whitespace and convert the
	 * input string into plain text.
	 *
	 * @param input
	 * @return
	 */
	public static String replaceMarkupWithWhitespace(String input) {
		if (input == null || input.length() == 0)
			return input;

		StringBuilder buf = new StringBuilder();
		TagParser.Tag tag = null;
		int pos = 0;
		while ((tag = TagParser.nextTag(input, pos)) != null) {
			if (pos < tag.beginIndex)
				buf.append(input, pos, tag.beginIndex);
			buf.append(' ');
			pos = tag.endIndex;
		}
		if (pos < input.length())
			buf.append(input, pos, input.length());

		return compactWhitespaces(escapeFromHtml(buf.toString()));
	}

	/**
	 * Escape special characters in input string, including '&', '<', '>' and
	 * '"'.
	 *
	 * @param input
	 * @return
	 */
	public static String escapeToHtml(String input) {
		if (input == null || input.length() == 0)
			return input;

		int extra = 0;
		char c;
		int i;
		for (i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '<' || c == '>')
				extra += 3;
			else if (c == '"')
				extra += 5;
			else if (c == '&')
				extra += 4;
		}

		if (extra == 0)
			// No special character in the input string
			return input;

		StringBuilder buf = new StringBuilder(input.length() + extra);
		for (i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '<')
				buf.append("&lt;");
			else if (c == '>')
				buf.append("&gt;");
			else if (c == '"')
				buf.append("&quot;");
			else if (c == '&')
				buf.append("&amp;");
			else
				buf.append(c);
		}
		return buf.toString();
	}

	/**
	 * Convert HTML escape characters (including "&amp;", "&lt;", "&gt;",
	 * "&nbsp;", and "&quot;") in input string.
	 *
	 * @param input
	 * @return
	 */
	public static String escapeFromHtml(String input) {
		if (input == null || input.length() == 0)
			return input;

		int extra = 0;
		char c;
		int i;
		for (i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '&') {
				if (i + 5 < input.length() &&
						((input.charAt(i + 1) == 'n' &&
								input.charAt(i + 2) == 'b' &&
								input.charAt(i + 3) == 's' &&
								input.charAt(i + 4) == 'p') ||
								(input.charAt(i + 1) == 'q' &&
										input.charAt(i + 2) == 'u' &&
										input.charAt(i + 3) == 'o' &&
										input.charAt(i + 4) == 't')) &&
						input.charAt(i + 5) == ';') {
					extra += 5;
					i += 5;
				}
				else if (i + 4 < input.length() &&
						input.charAt(i + 1) == 'a' &&
						input.charAt(i + 2) == 'm' &&
						input.charAt(i + 3) == 'p' &&
						input.charAt(i + 4) == ';') {
					extra += 4;
					i += 4;
				}
				else if (i + 3 < input.length() &&
						(input.charAt(i + 1) == 'l' ||
								input.charAt(i + 1) == 'g') &&
						input.charAt(i + 2) == 't' &&
						input.charAt(i + 3) == ';') {
					extra += 3;
					i += 3;
				}
			}
		}
		if (extra == 0)
			// no escaped characters in input string
			return input;

		StringBuilder buf = new StringBuilder(input.length() - extra);
		for (i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '&') {
				if (i + 5 < input.length() &&
						((input.charAt(i + 1) == 'n' &&
								input.charAt(i + 2) == 'b' &&
								input.charAt(i + 3) == 's' &&
								input.charAt(i + 4) == 'p') ||
								(input.charAt(i + 1) == 'q' &&
										input.charAt(i + 2) == 'u' &&
										input.charAt(i + 3) == 'o' &&
										input.charAt(i + 4) == 't')) &&
						input.charAt(i + 5) == ';') {
					buf.append(input.charAt(i + 1) == 'n' ? ' ' : '"');
					i += 5;
				}
				else if (i + 4 < input.length() &&
						input.charAt(i + 1) == 'a' &&
						input.charAt(i + 2) == 'm' &&
						input.charAt(i + 3) == 'p' &&
						input.charAt(i + 4) == ';') {
					buf.append('&');
					i += 4;
				}
				else if (i + 3 < input.length() &&
						(input.charAt(i + 1) == 'l' ||
								input.charAt(i + 1) == 'g') &&
						input.charAt(i + 2) == 't' &&
						input.charAt(i + 3) == ';') {
					buf.append(input.charAt(i + 1) == 'l' ? '<' : '>');
					i += 3;
				}
				else
					buf.append(c);
			}
			else
				buf.append(c);
		}

		return buf.toString();
	}

	/**
	 * Compact whitespaces in the input string. Leading and trailing whitespaces are removed.
	 * Consecutive whitespaces are replaced by a space character.
	 *
	 * @param input
	 * @return
	 */
	public static String compactWhitespaces(String input) {
		if (input == null || input.length() == 0)
			return input;

		// Fast path
		boolean hasConsecutiveWhitespaces = false;
		int whitespaceBeginIndex = -1;
		for (int i = 0; i < input.length(); ++i) {
			if (Character.isWhitespace(input.charAt(i))) {
				if (whitespaceBeginIndex < 0)
					whitespaceBeginIndex = i;
				else {
					hasConsecutiveWhitespaces = true;
					break;
				}
			}
			else
			if (whitespaceBeginIndex >= 0)
				// reset beginIndex
				whitespaceBeginIndex = -1;
		}
		if (!hasConsecutiveWhitespaces)
			return input;

		// Slow path
		// Ignore leading white spaces
		int i = 0;
		while (i < input.length() && Character.isWhitespace(input.charAt(i)))
			++i;
		if (i == input.length())
			return "";

		StringBuilder buf = new StringBuilder(input.length());
		int nonWhitespaceBeginIndex = whitespaceBeginIndex = -1;
		for (; i < input.length(); ++i) {
			if (Character.isWhitespace(input.charAt(i))) {
				if (whitespaceBeginIndex < 0)
					whitespaceBeginIndex = i;
				if (nonWhitespaceBeginIndex >= 0) {
					buf.append(input, nonWhitespaceBeginIndex, i);
					nonWhitespaceBeginIndex = -1;
				}
			}
			else {
				if (whitespaceBeginIndex >= 0) {
					whitespaceBeginIndex = -1;
					buf.append(' ');
				}
				if (nonWhitespaceBeginIndex < 0)
					nonWhitespaceBeginIndex = i;
			}
		}
		if (nonWhitespaceBeginIndex >= 0)
			buf.append(input, nonWhitespaceBeginIndex, input.length());
		return buf.toString();
	}

	/**
	 * Convert an HTML document to plain text, removing all mark-ups. The <br>,
	 * <br/>, <p> and </p> markups are replaced by a new line character. HTML
	 * character entities (&amp;, &lt;, &gt;, &nbsp; and &quot;) are converted
	 * to normal characters.
	 *
	 * @param input
	 * @return
	 */
	public static String toPlainText(String input) {
		input = compactWhitespaces(input);
		if (input == null || input.length() == 0)
			return input;

		StringBuilder buf = new StringBuilder();
		TagParser.Tag tag = null;
		int pos = 0;
		while ((tag = TagParser.nextTag(input, pos)) != null) {
			if (pos < tag.beginIndex)
				buf.append(escapeFromHtml(input.substring(pos, tag.beginIndex)));
			if (tag.type == BR_BEGIN_TAG || tag.type == P_BEGIN_TAG)
				buf.append('\n');
			pos = tag.endIndex;
		}
		if (pos < input.length())
			buf.append(escapeFromHtml(input.substring(pos)));

		return buf.toString().trim();
	}

	/**
	 * Convert a plain text string to HTML document. The newline character
	 * '\n' is replaced by a "<br/>" tag. The HTML character entities ('&', '<',
	 * '>', ' ', and '"') are escaped.
	 *
	 * @param input
	 * @return
	 */
	public static String fromPlainText(String input) {
		if (input == null || input.length() == 0)
			return input;

		int extra = 0;
		char c;
		int i;
		for (i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '<' || c == '>')
				extra += 3;
			else if (c == '"')
				extra += 5;
			else if (c == '&')
				extra += 4;
			else if (c == '\n')
				extra += 4;
			else if (c == ' ')
				extra += 5;
		}

		if (extra == 0)
			// No special character in the input string
			return input;

		StringBuilder buf = new StringBuilder(input.length() + extra);
		for (i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '<')
				buf.append("&lt;");
			else if (c == '>')
				buf.append("&gt;");
			else if (c == '"')
				buf.append("&quot;");
			else if (c == '&')
				buf.append("&amp;");
			else if (c == '\n')
				buf.append("<br/>");
			else if (c == ' ')
				buf.append("&nbsp;");
			else
				buf.append(c);
		}
		return buf.toString();
	}

	/**
	 * Whether does the input HTML document contain <img> or <object> elements?
	 *
	 * @param input
	 * @return true if the document contains <img> or <object> elements
	 */
	public static boolean containsImagesOrObjects(String input) {
		if (input == null || input.isEmpty())
			return false;

		TagParser.Tag tag = null;
		int pos = 0;
		while ((tag = TagParser.nextTag(input, pos)) != null) {
			if (tag.type == IMG_BEGIN_TAG || tag.type == OBJECT_BEGIN_TAG)
				return true;
			pos = tag.endIndex;
		}
		return false;
	}

	// Parsed html element flags
	public static final short TAG_SHOULD_BE_REMOVED_MASK	= 0x0003;
	public static final short TAG_SHOULD_BE_REMOVED_TRUE	= 0x0001;
	public static final short TAG_SHOULD_BE_REMOVED_FALSE	= 0x0002;

	/**
	 * Whether a markup should be removed? Note that only the markup is removed,
	 * its content is not removed.
	 *
	 * @param document
	 * @return
	 */
	private static boolean shouldRemoveMarkup(String document, Element element) {
		if ((element.flags & TAG_SHOULD_BE_REMOVED_MASK) != 0)
			// We already checked it
			return ((element.flags & TAG_SHOULD_BE_REMOVED_TRUE) != 0);

		switch (element.tag) {
			case FONT_BEGIN_TAG:
			case INPUT_BEGIN_TAG:
			case PRE_BEGIN_TAG:
			case U_BEGIN_TAG:
			case DIV_BEGIN_TAG:
			case SPAN_BEGIN_TAG:
				// Remove <div> and <span> tags, since they normally don't have
				// effect on the semantics of a document
			case A_BEGIN_TAG:
				// Remove <a> tags
				element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
				return true;

			case IMG_BEGIN_TAG:
				// Keep class="ed_video_img"
				if (VideoElementChanger.hasEdVideoImgClass(document, element)) break;

				// Keep images from "soso.com" domain.
				String src = getAttributeValue(document, element, "src");
				if (src != null) {
					src = src.trim();
					if(!src.startsWith("http://")){
						// Remove these images since they cannot be displayed
						// correctly
						element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
						return true;
					}
					if (!src.startsWith(WENWEN_URL_PREFIX) &&
							!src.startsWith(WENWEN_PIC_URL_PREFIX) &&
							!WEIBO_PIC_URL_PATTERN.matcher(src).matches() &&
							!SOGOU_CDN_PATTERN.matcher(src).matches()) {
						element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
						return true;
					}
				}

				try{
					String dynsrc = getAttributeValue(document, element, "dynsrc");
					if (dynsrc != null) {
						dynsrc = dynsrc.trim();
						if(!dynsrc.startsWith("http://")){
							// Remove these images since they cannot be displayed
							// correctly
							element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
							return true;
						}
						if (!dynsrc.startsWith(WENWEN_URL_PREFIX) &&
								!dynsrc.startsWith(WENWEN_PIC_URL_PREFIX) &&
								!WEIBO_PIC_URL_PATTERN.matcher(dynsrc).matches()) {
							element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
							return true;
						}
					}

					String lowsrc = getAttributeValue(document, element, "lowsrc");
					if (lowsrc != null) {
						lowsrc = lowsrc.trim();
						if(!lowsrc.startsWith("http://")){
							// Remove these images since they cannot be displayed
							// correctly
							element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
							return true;
						}
						if (!lowsrc.startsWith(WENWEN_URL_PREFIX) &&
								!lowsrc.startsWith(WENWEN_PIC_URL_PREFIX) &&
								!WEIBO_PIC_URL_PATTERN.matcher(lowsrc).matches()) {
							element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
							return true;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}


				break;

			// OPTIONAL TAGS:
			// To enable a tag, comment out the corresponding case clause
			case UNKNOWN_BEGIN_TAG:
			case BLOCKQUOTE_BEGIN_TAG:
			case ABBR_BEGIN_TAG:
			case ACRONYM_BEGIN_TAG:
			case ADDRESS_BEGIN_TAG:
			case APPLET_BEGIN_TAG:
			case AREA_BEGIN_TAG:
//			case B_BEGIN_TAG:
			case BASE_BEGIN_TAG:
			case BASEFONT_BEGIN_TAG:
			case BDO_BEGIN_TAG:
			case BGSOUND_BEGIN_TAG:
			case BIG_BEGIN_TAG:
			case BLINK_BEGIN_TAG:
			case BODY_BEGIN_TAG:
			case BUTTON_BEGIN_TAG:
//			case CAPTION_BEGIN_TAG:
			case CENTER_BEGIN_TAG:
			case CITE_BEGIN_TAG:
			case CODE_BEGIN_TAG:
			case COL_BEGIN_TAG:
			case COLGROUP_BEGIN_TAG:
//			case DD_BEGIN_TAG:
			case DEL_BEGIN_TAG:
			case DFN_BEGIN_TAG:
			case DIR_BEGIN_TAG:
//			case DL_BEGIN_TAG:
//			case DT_BEGIN_TAG:
			case EM_BEGIN_TAG:
			case EMBED_BEGIN_TAG:
			case FIELDSET_BEGIN_TAG:
			case FORM_BEGIN_TAG:
			case FRAME_BEGIN_TAG:
			case FRAMESET_BEGIN_TAG:
			case H1_BEGIN_TAG:
			case H2_BEGIN_TAG:
			case H3_BEGIN_TAG:
			case H4_BEGIN_TAG:
			case H5_BEGIN_TAG:
			case H6_BEGIN_TAG:
			case HEAD_BEGIN_TAG:
			case HR_BEGIN_TAG:
			case HTML_BEGIN_TAG:
			case I_BEGIN_TAG:
			case IFRAME_BEGIN_TAG:
			case ILAYER_BEGIN_TAG:
			case INS_BEGIN_TAG:
			case ISINDEX_BEGIN_TAG:
			case KBD_BEGIN_TAG:
			case LABEL_BEGIN_TAG:
			case LEGEND_BEGIN_TAG:
//			case LI_BEGIN_TAG:
			case LINK_BEGIN_TAG:
			case LISTING_BEGIN_TAG:
			case MAP_BEGIN_TAG:
			case MARQUEE_BEGIN_TAG:
			case MENU_BEGIN_TAG:
			case META_BEGIN_TAG:
			case MULTICOL_BEGIN_TAG:
			case NEXTID_BEGIN_TAG:
			case NOBR_BEGIN_TAG:
			case NOEMBED_BEGIN_TAG:
			case NOFRAMES_BEGIN_TAG:
			case NOSCRIPT_BEGIN_TAG:
			case OBJECT_BEGIN_TAG:
//			case OL_BEGIN_TAG:
			case OPTGROUP_BEGIN_TAG:
			case OPTION_BEGIN_TAG:
			case PARAM_BEGIN_TAG:
			case PLAINTEXT_BEGIN_TAG:
			case Q_BEGIN_TAG:
			case S_BEGIN_TAG:
			case SAMP_BEGIN_TAG:
			case SCRIPT_BEGIN_TAG:
			case SELECT_BEGIN_TAG:
			case SERVER_BEGIN_TAG:
			case SMALL_BEGIN_TAG:
			case SPACER_BEGIN_TAG:
			case STRIKE_BEGIN_TAG:
//			case STRONG_BEGIN_TAG:
			case STYLE_BEGIN_TAG:
//			case SUB_BEGIN_TAG:
//			case SUP_BEGIN_TAG:
//			case TABLE_BEGIN_TAG:
//			case TBODY_BEGIN_TAG:
//			case TD_BEGIN_TAG:
			case TEXTAREA_BEGIN_TAG:
//			case TFOOT_BEGIN_TAG:
//			case TH_BEGIN_TAG:
//			case THEAD_BEGIN_TAG:
			case TITLE_BEGIN_TAG:
//			case TR_BEGIN_TAG:
			case TT_BEGIN_TAG:
//			case UL_BEGIN_TAG:
			case VAR_BEGIN_TAG:
			case WBR_BEGIN_TAG:
			case XMP_BEGIN_TAG:

				element.flags |= TAG_SHOULD_BE_REMOVED_TRUE;
				return true;

		}
		// Keep others
		element.flags |= TAG_SHOULD_BE_REMOVED_FALSE;
		return false;
	}

	/**
	 * Determine whether an HTML element attribute should be removed.
	 *
	 * @param doc
	 * @return
	 */
	private static boolean shouldRemoveAttribute(String doc, Element element,
												 String attributeName, String attributeValue)
	{
		int attribute = AttributeParser.getAttributeType(attributeName);
		switch (attribute) {

			// All event attributes should be removed
			case AttributeParser.ON_ABORT:
			case AttributeParser.ON_BLUR:
			case AttributeParser.ON_CHANGE:
			case AttributeParser.ON_CLICK:
			case AttributeParser.ON_DBL_CLICK:
			case AttributeParser.ON_ERROR:
			case AttributeParser.ON_FOCUS:
			case AttributeParser.ON_KEY_DOWN:
			case AttributeParser.ON_KEY_PRESS:
			case AttributeParser.ON_KEY_RELEASE:
			case AttributeParser.ON_KEY_UP:
			case AttributeParser.ON_LOAD:
			case AttributeParser.ON_MOUSE_DOWN:
			case AttributeParser.ON_MOUSE_MOVE:
			case AttributeParser.ON_MOUSE_OUT:
			case AttributeParser.ON_MOUSE_OVER:
			case AttributeParser.ON_MOUSE_UP:
			case AttributeParser.ON_RESET:
			case AttributeParser.ON_SELECT:
			case AttributeParser.ON_SUBMIT:
			case AttributeParser.ON_UNLOAD:

				// Some other attributes should be removed
			case AttributeParser.BGCOLOR:
			case AttributeParser.BACKGROUND:
			case AttributeParser.HREF:
			case AttributeParser.HAS_DBL_EVENT:
			case AttributeParser.ID:
			case AttributeParser.NAME:
				return true;

			case AttributeParser.CLASS:
				// Keep "ed_*" class attribute
				if (attributeValue != null && attributeValue.startsWith("ed_"))
					return false;
				return true;
			case AttributeParser.STYLE:
				// if there has class="ed_video_img", than keep this style, else remove
				if (VideoElementChanger.hasEdVideoImgClass(doc, element)) return false;
				return true;
		}
		// Keep other attributes
		return false;
	}

	/**
	 * Filter out all unwanted markups and attributes. This is a Depth-First
	 * Search algorithm.
	 *
	 * @param doc
	 * @return
	 */
	public static String preFilterDocument(String doc) {
		// Parse the input HTML document into a tree
		Element root = HtmlParser.parse(doc);
		if (root == null)
			return null;

		StringBuilder buf = new StringBuilder();
		// Stack used by the depth-first search procedure
		Stack<Node> hierarchy = new Stack<Node>();
		hierarchy.push(root);
		// Current position in the original input HTML document
		// This position moves when we traverse the tree
		int position = 0;
		while (!hierarchy.empty()) {
			Node node = hierarchy.peek();
			if (node instanceof Element) {
				Element e = (Element) node;

				switch (e.tag) {
					case APPLET_BEGIN_TAG:
					case LINK_BEGIN_TAG:
					case META_BEGIN_TAG:
					case SCRIPT_BEGIN_TAG:
					case STYLE_BEGIN_TAG:
					case FORM_BEGIN_TAG:
					case IFRAME_BEGIN_TAG:
					case OBJECT_BEGIN_TAG:
					case EMBED_BEGIN_TAG:

						// Skip this element (and its content)
						position = e.endIndex;
						hierarchy.pop();
						break;

					default:

						// Go further
						if (e.beginTagBeginIndex >= position) {
							// The first time we process the tag
							position = e.beginTagEndIndex;
							if (e != root && !shouldRemoveMarkup(doc, e)) {
								// print the begin tag of this element
								buf.append('<');
								buf.append(TagParser.getTagName(e.tag));
								if (e.attributeBeginIndex < e.attributeEndIndex) {
									// There're attributes
									// Filter the attributes
									String attrs = doc.substring(
											e.attributeBeginIndex,
											e.attributeEndIndex);
									Attribute attr = null;
									int attrPosition = 0;
									while ((attr = AttributeParser.nextAttribute(
											attrs, attrPosition)) != null)
									{
										attrPosition = attr.endIndex;
										String name = attrs.substring(
												attr.nameBeginIndex,
												attr.nameEndIndex);
										String value = attrs.substring(
												attr.valueBeginIndex,
												attr.valueEndIndex);
										if (shouldRemoveAttribute(doc, e, name, value))
											continue;

										buf.append(' ');
										buf.append(name);
										buf.append('=');
										buf.append(attr.delimiter);
										buf.append(value);
										buf.append(attr.delimiter);
									}
								}
								if (e.attributeEndIndex == e.beginTagEndIndex - 2)
									buf.append("/>");
								else
									buf.append('>');
							}
						}
						// Check current element's children
						Collection<Node> children = e.getChildren();
						if (children == null || children.isEmpty()) {
							// This node is a leaf
							if (e != root && !shouldRemoveMarkup(doc, e)) {
								if (e.contentEndIndex < e.endIndex ||
										TagParser.isEndTagNeverOmitted(e.tag)) {
									// If there's end tag for this element in the
									// original document, or the end tag should
									// never be omitted, we print the end tag
									buf.append("</");
									buf.append(TagParser.getTagName(e.tag));
									buf.append('>');
								}
							}
							position = e.endIndex;
							hierarchy.pop();
						} else {
							// Check its next child
							Iterator<Node> iter = children.iterator();
							boolean done = true;
							while (iter.hasNext()) {
								Node cur = iter.next();
								if (cur.beginIndex >= position) {
									hierarchy.push(cur);
									done = false;
									break;
								}
							}
							if (done) {
								// All children are checked
								if (e != root && !shouldRemoveMarkup(doc, e)) {
									if (e.contentEndIndex < e.endIndex ||
											TagParser.isEndTagNeverOmitted(e.tag)) {
										// If there's end tag for this element in
										// the original document, or the end tag
										// should never be omitted, we print the
										// end tag
										buf.append("</");
										buf.append(TagParser.getTagName(e.tag));
										buf.append('>');
									}
								}
								position = e.endIndex;
								hierarchy.pop();
							}
						}
						break;

				}

			} else if (node instanceof PlainText) {
				buf.append(doc.substring(node.beginIndex, node.endIndex));
				position = node.endIndex;
				hierarchy.pop();
			} else if (node instanceof Comment) {
				// Skip comment
				position = node.endIndex;
				hierarchy.pop();
			} else {
				// Skip unknown node
				position = node.endIndex;
				hierarchy.pop();
			}
		}
		return buf.toString();
	}

	/**
	 * Filter out all unwanted markups and attributes. This is a Depth-First
	 * Search algorithm.
	 *
	 * @param doc
	 * @return
	 */
	public static String filterDocument(String doc) {
		return HtmlUtil.replaceDocumentElement(preFilterDocument(doc), new VideoElementChanger());
	}

	public static String makeImageSourceAbsolute(String document) {
		boolean allImagesSourcesAbsolute = true;

		// Fast path, if all images' sources are absolute URL
		Tag tag = null;
		int position = 0;
		while ((tag = TagParser.nextTag(document, position)) != null) {
			if (tag.type == IMG_BEGIN_TAG) {
				String attrs = document.substring(tag.attrBeginIndex, tag.attrEndIndex);
				Attribute attr = null;
				int apos = 0;
				while ((attr = AttributeParser.nextAttribute(attrs, apos)) != null) {
					String name = attrs.substring(attr.nameBeginIndex, attr.nameEndIndex);
					if ("src".equalsIgnoreCase(name)) {
						String path = attrs.substring(attr.valueBeginIndex, attr.valueEndIndex);
						if (!path.startsWith(HTTP_URL_PREFIX))
							allImagesSourcesAbsolute = false;
						break;
					}
					apos = attr.endIndex;
				}
				if (!allImagesSourcesAbsolute)
					break;
			}
			position = tag.endIndex;
		}
		if (allImagesSourcesAbsolute)
			return document;

		// Slow path, if there's relative image source URL
		AttributesTransformer transformer = new AttributesTransformer() {

			/**
			 * Change relative image source URL to absolute Wenwen picture URL.
			 */
			@Override
			public String transformAttributes(String doc, Element element)
			{
				String attributes = doc.substring(element.attributeBeginIndex,
						element.attributeEndIndex);
				if (element.tag != IMG_BEGIN_TAG)
					return attributes;

				StringBuilder buf = new StringBuilder();

				// Replace its "src" attribute
				Attribute attr = null;
				String name, value;
				int pos = 0;
				while ((attr = AttributeParser.nextAttribute(attributes, pos)) != null) {
					if (pos < attr.nameBeginIndex)
						buf.append(attributes.substring(pos, attr.nameBeginIndex));
					pos = attr.endIndex;

					buf.append(attributes.substring(attr.nameBeginIndex,
							attr.valueBeginIndex));

					name = attributes.substring(attr.nameBeginIndex,
							attr.nameEndIndex);
					int type = AttributeParser.getAttributeType(name);
					if (type == AttributeParser.SRC) {
						value = attributes.substring(attr.valueBeginIndex,
								attr.valueEndIndex);
						if (!value.toLowerCase().startsWith("http:")) {
							// This is not an absolute url
							// extract the image file name part
							int idx = value.lastIndexOf('/');
							if (idx >= 0)
								value = value.substring(idx + 1);
							String filename = value;

							// skip the non-digit prefix, if any
							for (idx = 0; idx < value.length(); idx++)
								if (Character.isDigit(value.charAt(idx)))
									break;
							if (idx > 0)
								value = value.substring(idx);

							buf.append(WENWEN_PIC_URL_PREFIX);
							buf.append("p/");
							if (value.length() > 8) {
								buf.append(value.substring(0, 8));
								buf.append('/');
							}
							buf.append(filename);
							buf.append(attributes.substring(attr.valueEndIndex, attr.endIndex));
						}
						else
							buf.append(attributes.substring(
									attr.valueBeginIndex,
									attr.endIndex));
					}
					else
						buf.append(attributes.substring(attr.valueBeginIndex,
								attr.endIndex));
				}
				if (pos < attributes.length())
					buf.append(attributes.substring(pos));

				return buf.toString();
			}

		};
		return transformElementAttributes(document, transformer);
	}
}
