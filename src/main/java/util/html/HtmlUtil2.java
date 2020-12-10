package util.html;


import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.HtmlCleanerException;
import org.htmlcleaner.TagNode;
import util.html.support.HtmlParser;
import util.html.support.TagParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static util.html.support.TagParser.*;
import static util.html.support.HtmlParser.Element;
import static util.html.support.HtmlParser.Node;
import static util.html.support.HtmlParser.PlainText;


public class HtmlUtil2 {

	private static final short	MONOLITHIC_BLOCK	= 0x1;

	/**
	 * 用HtmlCleaner过滤字符串
	 * @param input
	 * @return
	 */
	public static String cleanText(String input) {
		if(input==null || input.length()==0)
			return input;

		HtmlCleaner cleaner = new HtmlCleaner();
		cleaner.getProperties().setOmitXmlDeclaration(true);
		cleaner.getProperties().setUseEmptyElementTags(false);
		TagNode root = null;
		try {
			root = cleaner.clean(input);
		} catch (HtmlCleanerException e) {
			return input;
		}
		return cleaner.getInnerHtml((TagNode)root.getChildren().get(1));
	}


	public static boolean isMonolithic(Element element){
		switch (element.tag) {
			case DL_BEGIN_TAG:
			case H1_BEGIN_TAG:
			case H2_BEGIN_TAG:
			case H3_BEGIN_TAG:
			case H4_BEGIN_TAG:
			case H5_BEGIN_TAG:
			case H6_BEGIN_TAG:
			case OL_BEGIN_TAG:
			case TABLE_BEGIN_TAG:
			case UL_BEGIN_TAG:
				return true;
		}
		return false;
	}

	private static boolean isParagraphStartedBy(Element element) {
		switch (element.tag) {
			case P_BEGIN_TAG:
				return true;
		}
		return false;
	}

	private static boolean isParagraphEndedBy(Element element) {
		switch (element.tag) {
			case BR_BEGIN_TAG:
			case P_BEGIN_TAG:
				return true;
		}
		return false;
	}

	/**
	 * 这个函数会把段落划分成以 br 结尾的段
	 * @deprecated
	 * @param doc
	 * @return
	 */
	public static List<String> splitDocumentAsParagraphs(String doc) {
		if (doc == null || doc.length() == 0)
			return null;
		doc = removeAllParagraphTags(doc);
		Element document = getDocumentBody(doc);
		if (document == null)
			return null;

		List<String> result = new ArrayList<String>();
		Stack<Node> hierarchy = new Stack<Node>();
		hierarchy.push(document);
		int position = document.contentBeginIndex;
		int lastParagraphEnd = position;
		while (!hierarchy.empty()) {
			Node node = hierarchy.peek();

			if (node instanceof Element) {
				Element e = (Element) node;

				if (e.beginTagBeginIndex >= position) {
					// The first time we traverse this element
					position = e.beginTagEndIndex;

					if (isMonolithic(e)) {
						// A paragraph ends before a monolithic block
						String paragraph = doc.substring(lastParagraphEnd, e.beginTagBeginIndex);
						if (paragraph.trim().length() > 0)
							result.add(paragraph);
						// The monolithic block is a paragraph by itself
						paragraph = doc.substring(e.beginTagBeginIndex, e.endIndex);
						result.add(paragraph);
						// Update the paragraph end mark
						lastParagraphEnd = e.endIndex;
						position = e.endIndex;
						e.flags = MONOLITHIC_BLOCK;
					} else if (isParagraphStartedBy(e)) {
						// A new paragraph begin
						String paragraph = doc.substring(lastParagraphEnd, e.beginTagBeginIndex);
						if (paragraph.trim().length() > 0)
							result.add(paragraph);
						// Update the paragraph end mark
						lastParagraphEnd = e.beginTagBeginIndex;
					}
				}
				// Check this element's children
				Collection<Node> children = e.getChildren();
				if (children == null || children.isEmpty()) {
					// back trace
					position = e.endIndex;
					hierarchy.pop();

					if (e.flags == MONOLITHIC_BLOCK) {
						// We need not do anything here, since we have processed
						// a monolithic block at its begin tag
					} else if (isParagraphEndedBy(e)) {
						// A paragraph ended
						String paragraph = doc.substring(lastParagraphEnd, e.endIndex);
						if (paragraph.trim().length() > 0)
							result.add(paragraph);
						// Update the paragraph end mark
						lastParagraphEnd = e.endIndex;
					}

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
						position = e.endIndex;
						hierarchy.pop();

						if (e.flags == MONOLITHIC_BLOCK) {
							// We need not do anything here, since we have
							// processed a monolithic block at its begin tag
						} else if (isParagraphEndedBy(e)) {
							// A paragraph ended
							String paragraph = doc.substring(lastParagraphEnd, e.endIndex);
							if (paragraph.trim().length() > 0)
								result.add(paragraph);
							// Update the paragraph end mark
							lastParagraphEnd = e.endIndex;
						}

					}
				}
			} else {
				// Skip other HTML element (character data, comment, etc.)
				position = node.endIndex;
				hierarchy.pop();
			}
		}
		if (lastParagraphEnd < document.contentEndIndex) {
			String paragraph = doc.substring(lastParagraphEnd, document.contentEndIndex);
			if (paragraph.trim().length() > 0)
				result.add(paragraph);
		}

		return result;
	}


	/**
	 * 抽取并过滤自然段，使之符合规范。
	 * @param doc
	 * @param beginIdx
	 * @param endIdx
	 * @param result
	 * @param doTrim
	 * @param doClean
	 */
	private static void extractAndFilterParagraph(String doc, int beginIdx, int endIdx, List<String> result, boolean doTrim, boolean doClean){

		String para = doc.substring(beginIdx, endIdx);
		if (para == null)
			return;

		if (doTrim){
			para = para.trim();
			if (para.isEmpty())
				return;
		}

		if (doClean){
			boolean regular = false;
			para = cleanText(para);
			Element elem = HtmlParser.parse(para);
			if (elem == null) return;
			Collection<Node> childrens = elem.getChildren();
			if (childrens == null || childrens.isEmpty()) return;
			Node firstChild = childrens.iterator().next();
			if (firstChild instanceof Element){
				if (((Element) firstChild).tag == P_BEGIN_TAG){
					// 规范的情况
					regular = true;
				}
			}

			// 非规范的情况
			if (!regular)
				para = "<p>" + para + "</p>";
		}

		// 正则化段落
		para = regularizeParagraph(para);

		result.add(para);

	}

	/**
	 * 正则化段落，使之总以被段落标签包围
	 * @return
	 */
	private static String regularizeParagraph(String para){
		Tag tag = TagParser.nextTag(para, 0);
		boolean apd_head = false;
		boolean apd_tail = false;
		if (tag == null || tag.type != TagParser.P_BEGIN_TAG){
			// 需要补一个 <p>
			apd_head = true;
		}
		if (!para.endsWith("</p>")){
			apd_tail = true;
		}
		if (apd_head || apd_tail){
			StringBuilder builder = new StringBuilder();
			if (apd_head)
				builder.append("<p>");
			builder.append(para);
			if (apd_tail)
				builder.append("</p>");
			return builder.toString();
		}else
			return para;
	}

	/**
	 * 本函数按以下方式进行：
	 * <ol>
	 * <li>清理标签，使所有标签闭合</li>
	 * <li>段落标签规范化为 p </li>
	 * </ol>
	 * 对于非规范的标签嵌套，本函数不作分析处理。
	 * @param doc
	 * @param doClean
	 * 	如果为真，在切分自然段前，做标签清理，并且对分段后，缺失段落标签的进行修补、转换。
	 * @return
	 * 	分段结果
	 */
	public static final List<String> splitDocumentAsParagraphs2(String doc, boolean doClean){
		List<String> result = new ArrayList<String>();
		/**
		 * 清理标签
		 */
		if (doClean) doc = cleanText(doc);
		/**
		 * 获取 body 元素
		 */
		Element document = getDocumentBody(doc);
		if (document == null) return result;
		Stack<Node> hierarchy = new Stack<>();
		hierarchy.push(document);
		// 当前处理的位置
		int position = document.beginTagEndIndex;
		// 指示是否需要深入标签子结构处理
		boolean indeep = true;
		// 假定 Body 开始时是一个段落，以后我们遇到 <p> 开标签时，会更新此值。
		// 如果遇到 <br/> 或者 </p> ，我们归结出一个段落，也会更新此值
		// 这有一个假定， <p> 标签不会嵌套。
		int paraBeginPos = document.beginTagEndIndex;
		while (!hierarchy.isEmpty()){
			// 为保证按序添加段落，
			Node node = hierarchy.pop();
			if (node instanceof Element){
				Element elem = (Element) node;
				// 是否第一次进行这个标签?
				if (elem.beginTagBeginIndex >= position){
					position = elem.beginTagEndIndex;
					if (isMonolithic(elem)){
						// 结束上一段落
						extractAndFilterParagraph(doc, paraBeginPos, elem.beginIndex, result, true, doClean);
						// Monolithic block 自作为一个段落
						// p 标签包围的作为一个自然段
						result.add(doc.substring(elem.beginIndex, elem.endIndex));
						// 更新段落开始位置
						paraBeginPos = elem.endIndex;
						position = elem.endIndex;
						indeep = false;
					} else if (isParagraphStartedBy(elem)){
						// 结束上一段落
						extractAndFilterParagraph(doc, paraBeginPos, elem.beginIndex, result, true, doClean);
						// 遗留数据中，p 标签内可能包含一些 br 标签，
						// 为兼容，不能武断地使之作为一个自然段
						paraBeginPos = elem.beginIndex;
						indeep = true;
					} else if (elem.tag == BR_BEGIN_TAG){
						// 段落结束标签，结束掉上一个段落
						extractAndFilterParagraph(doc, paraBeginPos, elem.beginIndex, result, true, doClean);
						paraBeginPos = elem.endIndex;
						indeep = false;
					}else{
						// other tags
						indeep = true;
					}
					if (indeep){
						// 本结点入栈，以作结束标签处理
						hierarchy.push(node);
						// 深度优先处理标签子结构
						Collection<Node> childrens = elem.getChildren();
						if (childrens == null) continue;
						LinkedList<Node> list = new LinkedList<Node>(childrens);
						Iterator<Node> iter = list.descendingIterator();
						while (iter.hasNext()){
							hierarchy.push(iter.next());
						}
					}
				} else {  // 非第一次访问标签
					position = elem.endIndex;
					// 遇到 p 的结束标签，记为一个段落
					if (elem.tag == P_BEGIN_TAG){
						extractAndFilterParagraph(doc, paraBeginPos, elem.endIndex, result, false, doClean);
						paraBeginPos = elem.endIndex;
					}
				}
			}else{
				// text node
				position = node.endIndex;
			}
		}
		if (paraBeginPos < document.endIndex){
			extractAndFilterParagraph(doc, paraBeginPos, document.endIndex, result, true, doClean);
		}
		return result;
	}

	private static String removeAllParagraphTags(String text) {
		Element document = HtmlParser.parse(text);
		if (document == null) {
			return "";
		}
		Collection<Node> children = document.getChildren();
		if (children == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Node> iter = children.iterator();
		while (iter.hasNext()) {
			Node node = iter.next();
			if (node instanceof Element) {
				Element elem = (Element) node;
				switch (elem.tag) {
					case TagParser.P_BEGIN_TAG:
						String content = text.substring(elem.contentBeginIndex, elem.contentEndIndex);
						content = content.replace("<br>", "<br/>");
						if (content.endsWith("<br/>"))
							sb.append(content);
						else
							sb.append(content + "<br/>");
					break;
					default:
						//sb.append(removeAllParagraphTags(text.substring(node.beginIndex, node.endIndex)));
						sb.append(text.substring(node.beginIndex, node.endIndex));
				}
			} else
				sb.append(text.substring(node.beginIndex, node.endIndex));
		}
		return sb.toString();
	}

	/**
	 * Document element attributes transformer. It returns the changed
	 * attributes part. If an element is not changed, it should return the
	 * original attributes part of the element.
	 *
	 * @author haiyunzhao
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
	public static String transformElementAttributes(String doc, AttributesTransformer transformer) {
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
						buf.append(doc.substring(e.beginTagBeginIndex, e.attributeBeginIndex));

					buf.append(transformer.transformAttributes(doc, e));

					if (e != document)
						buf.append(doc.substring(e.attributeEndIndex, e.beginTagEndIndex));
				}
				// Check this element's children
				Collection<Node> children = e.getChildren();
				if (children == null || children.isEmpty()) {
					if (e != document && e.contentEndIndex < e.endIndex) {
						// This element has an end tag
						buf.append(doc.substring(e.contentEndIndex, e.endIndex));
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
							buf.append(doc.substring(e.contentEndIndex, e.endIndex));
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
	 * @author haiyunzhao
	 *
	 */
	public static interface ElementChanger {
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


	/**
	 * 该接口与ElementChanger不同的是，除了替换Element, 还替换PlainTextNode
	 *
	 * @author simonlin
	 *
	 */
	public static interface NodeChanger extends ElementChanger{
		/**
		 * Return the replacement string for the element. If the input element
		 * is not an element you want to replace, simply returns null. If you
		 * want to remove this element from output HTML document, simply returns
		 * an empty string ("").
		 *
		 * @param doc
		 * @param node
		 * @return
		 */
		String replacePlainTextNode(String doc, HtmlParser.PlainText node, Stack<Node> path);
	}

	public static final short REPLACED = 1;

	/**
	 * Replace document node with specified replacement. The node are
	 * selected by the specified node selector.
	 *
	 * @param doc
	 * @param changer
	 * @return
	 */
	public static String replaceDocumentNode(String doc, NodeChanger changer) {
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
				// if is a PlainText node, then replace it, else Output other HTML elements directly
				String replacement = null;
				if(node instanceof PlainText)
					replacement = changer.replacePlainTextNode(doc, (PlainText)node, hierarchy);

				if(replacement==null)
					buf.append(doc.substring(node.beginIndex, node.endIndex));
				else
					buf.append(replacement);

				position = node.endIndex;
				hierarchy.pop();
			}
		}

		return buf.toString();
	}


	/**
	 * Replace document element with specified replacement. The elements are
	 * selected by the specified element selector.
	 *
	 * @param doc
	 * @param selector
	 * @param replacement
	 * @return
	 */
	public static String replaceDocumentElement(String doc, ElementChanger changer)
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

	public static boolean isSpaceChar(char ch) {
		return Character.isSpaceChar(ch) || Character.isWhitespace(ch);
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
			if (isSpaceChar(input.charAt(i))) {
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
		while (i < input.length() && isSpaceChar(input.charAt(i)))
			++i;
		if (i == input.length())
			return "";

		StringBuilder buf = new StringBuilder(input.length());
		int nonWhitespaceBeginIndex = whitespaceBeginIndex = -1;
		for (; i < input.length(); ++i) {
			if (isSpaceChar(input.charAt(i))) {
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
		Tag tag = null;
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

//	/**
//	 * Escape special characters in input string, including '&', '<', '>', ' ' and
//	 * '"'.
//	 *
//	 * @param input
//	 * @return
//	 */
//	public static String escapeToHtmlWithSpace(String input){
//		if (input == null || input.length() == 0)
//			return input;
//
//		int extra = 0;
//		char c;
//		int i;
//		for (i = 0; i < input.length(); i++) {
//			c = input.charAt(i);
//			if (c == '<' || c == '>')
//				extra += 3;
//			else if (c == '"' || c == ' ')
//				extra += 5;
//			else if (c == '&')
//				extra += 4;
//		}
//
//		if (extra == 0)
//			// No special character in the input string
//			return input;
//
//		StringBuilder buf = new StringBuilder(input.length() + extra);
//		for (i = 0; i < input.length(); i++) {
//			c = input.charAt(i);
//			if (c == '<')
//				buf.append("&lt;");
//			else if (c == '>')
//				buf.append("&gt;");
//			else if (c == '"')
//				buf.append("&quot;");
//			else if (c == ' ')
//				buf.append("&nbsp;");
//			else if (c == '&')
//				buf.append("&amp;");
//			else
//				buf.append(c);
//		}
//		return buf.toString();
//	}

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
     * Produce a string with XML-compatible characters. Escape those special
     * characters.
     *
     * @param string
     * @return
     */
    public static String xmlCleanInvalid(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }

        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len);

        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            if ((c < 0x9) || (c >= 0x0b && c <= 0x0c)
            		|| (c >= 0x0e && c <= 0x1f)
            		|| (c >= 0xd800 && c <= 0xdfff)
            		|| (c >= 0xfffe && c <= 0xfff)) {
            	// remove these forbidden character
            	;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Produce a string with XML-compatible characters. Escape those special
     * characters.
     *
     * @param string
     * @return
     */
    public static String xmlClean(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }

        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len);

        // FIXME: seek a better character escape schema
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            case '\'':
                sb.append("&apos;");
                break;
            default:
                if ((c < 0x9) || (c >= 0x0b && c <= 0x0c)
                		|| (c >= 0x0e && c <= 0x1f)
                		|| (c >= 0xd800 && c <= 0xdfff)
                		|| (c >= 0xfffe && c <= 0xfff)) {
                	// remove these forbidden character
                	;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Produce a string with XML-compatible characters. Escape those special
     * characters.
     *
     * @param string
     * @return
     */
    public static String xmlQuote(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }

        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len);

        // FIXME: seek a better character escape schema
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            case '\'':
                sb.append("&apos;");
                break;
            default:
                if ((c < 0x9) || (c >= 0x0b && c <= 0x0c)
                		|| (c >= 0x0e && c <= 0x1f)
                		|| (c >= 0xd800 && c <= 0xdfff)
                		|| (c >= 0xfffe && c <= 0xfff)) {
                	// replace these forbidden character with a question mark
                	sb.append('?');
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

	/**
	 * Get the body element of the input HTML document. If the document contains
	 * a <body> markup, it goes into the content of this tag. Otherwise, it
	 * returns the element representing the whole document.
	 *
	 * @param doc
	 * @return
	 */
	public static Element getDocumentBody(String doc) {
		Element document = HtmlParser.parse(doc);
		if (document == null)
			return null;

		boolean deeper = false;
		do {
			Collection<Node> children = document.getChildren();
			if (children == null)
				return null;

			deeper = false;
			Iterator<Node> iter = children.iterator();
			while (iter.hasNext()) {
				Node node = iter.next();
				if (node instanceof Element) {
					Element elem = (Element) node;
					switch (elem.tag) {
						case HTML_BEGIN_TAG:
						case BODY_BEGIN_TAG:
							document = elem;
							deeper = true;
						break;
					}
				}
				if (deeper)
					break;
			}

		} while (deeper);

		return document;
	}

    public static String subStringRichTextWithATag(String inputString, int max_len) {
		if(inputString == null || inputString.length() <= 0 || max_len <= 0 || inputString.length() < max_len) return inputString;

		Element document = HtmlParser.parse(inputString);
		if(document != null) {
			StringBuffer buffer = new StringBuffer();
			int begin = 0;
			int end = 0;
			String part = null;
			int less_len = max_len;
			for(int i = 0; i < document.children.size(); i++) {
				Node node = document.children.get(i);
				if(node != null) {
					begin = node.beginIndex;
					end = node.endIndex;
					if(begin >= 0 && begin < end && end <= inputString.length()) {
						part = inputString.substring(begin, end);
						if(part.startsWith("<a")) {
							// has a tag
							String inner = HtmlUtil.toPlainText(part);
							if(inner != null && inner.length() > 0) {
								if(inner.length() <= less_len) {
									buffer.append(part);
									less_len -= inner.length();
								} else {
									buffer.append("...");
									break;
								}
							}
						} else {
							// plain text
							if(part.length() <= less_len) {
								buffer.append(part);
								less_len -= part.length();
							} else {
								buffer.append(part.substring(0, less_len)).append("...");
								break;
							}
						}
					}
				}
			}
			String result = buffer.toString();
			if(buffer.length() > 0) return result;
		} 
		return inputString;
	}
    
	public static int[] countImagesAndCharacters(String document) {
		if (document == null || document.length() == 0)
			return new int[]{0, 0};
		
		int imageCount = 0;
		int charCount = 0;
		
		Tag tag = null;
		int position = 0;
		while ((tag = TagParser.nextTag(document, position)) != null) {
			for (int i = position; i < tag.beginIndex; i++) {
				char c = document.charAt(i);
				if (!isSpaceChar(c) && c != '　')
					charCount++;
			}
			if (tag.type == IMG_BEGIN_TAG)
				imageCount++;
			position = tag.endIndex;
		}
		for (int i = position; i < document.length(); i++) {
			char c = document.charAt(i);
			if (!isSpaceChar(c) && c != '　')
				charCount++;
		}
		return new int[]{imageCount, charCount};
	}


	public static void main(String[] args){
		String text = "<p><img data-id=\"1050732\" data-position=\"1\" data-total=\"2\" alt=\"刘德华早期电视剧剧照\" elem-type=\"album\" width=\"220\" height=\"165\" src=\"http://pic.baike.soso.com/ugc/baikepic2/9574/cut-20141203102839-775950066.jpg/0\" data-style=\"padding: 0px 158px 121px 0px; background-image: url(&quot;https://pic.baike.soso.com/ugc/baikepic2/9574/cut-20141203102839-775950066.jpg/0&quot;);\"/>1981年，时年20岁的刘德华顺利考进第10期无线电视艺员训练班。</p><p>1982年,，刘德华以甲级优异成绩毕业后任无线电视台演员。首次在“香港电台电视部”制作电视单元剧《香港香港》、《江湖再见》里担任主角并获美国电视节电视剧特别奖。后来，刘德华在周润发主演的《鳄鱼潭》里演一个杀手，并且获得周润发的鼓励。</p><p>1983年，刘德华受TVB力捧，与黄日华、梁朝伟、苗侨伟和汤镇业组成“无线五虎将”，出演了《神雕侠侣》、《鹿鼎记》等多部很受欢迎的TVB剧集。</p><p>1984年，刘德华拍完《神雕侠侣》后因不愿续5年的合约遭雪藏长达400天。</p><p><img data-id=\"1050734\" data-position=\"1\" data-total=\"2\" alt=\"刘德华古装剧剧照\" elem-type=\"album\" width=\"220\" height=\"165\" src=\"https://pic.baike.soso.com/ugc/baikepic2/9563/cut-20141203103057-1771269361.jpg/0\" data-style=\"padding: 0px 158px 121px 0px; background-image: url(&quot;https://pic.baike.soso.com/ugc/baikepic2/9563/cut-20141203103057-1771269361.jpg/0&quot;);\"/></p><p>1985年，刘德华开始向歌坛和影坛大力发展。</p><p>1986年，在邵逸夫主席的出面调解下，刘德华和无线双方摒弃前嫌，握手言和，签下新合约。</p><p>1988年，刘德华跟邵美琪出演《天狼劫》后正式全身投入影坛。</p>";
		text = "<div id=\"lemma_module_movie\" class=\"lemma_module\" datatype=\"movie\" contenteditable=\"false\"></div><p><i>注：部分时间为开机时间</i></p>";
		text = "123<p></p>";
		List<String> res = splitDocumentAsParagraphs2(text,false);
		for(String a: res){
			System.out.println(a);
		}
		System.out.println(cleanText("最小图片上传尺寸为&apos;"));
	}
}
