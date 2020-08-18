package util.html.support;



import java.util.*;

import static util.html.support.TagParser.*;


public class HtmlParser {
	/**
	 * Base class for Html elements, plain text and comment.
	 *
	 *
	 */
	public static class Node {
		public int beginIndex;
		public int endIndex;

		public Node() {}
		public Node(int beginIndex, int endIndex) {
			this.beginIndex = beginIndex;
			this.endIndex = endIndex;
		}

		public void addChild(Node child) {};
		public Collection<Node> getChildren() { return null; }
	}

	/**
	 * Html element closure, i.e., begin tag, content, and end tag.
	 *
	 *
	 */
	public static class Element extends Node {
		public int beginTagBeginIndex;
		public int beginTagEndIndex;
		public short tag;
		public short flags;				// Unused by the parser
		public int attributeBeginIndex;
		public int attributeEndIndex;
		public int contentBeginIndex;
		public int contentEndIndex;

		public List<Node> children;

		public Element(int beginIndex, int endIndex) {
			super(beginIndex, endIndex);
		}

		@Override
		public void addChild(Node child) {
			if (child != null) {
				if (children == null)
					children = new LinkedList<Node>();
				children.add(child);
			}
		}

		@Override
		public Collection<Node> getChildren() {
			if (children == null)
				return null;

			return Collections.unmodifiableList(children);
		}
	}

	/**
	 * Plain text.
	 *
	 */
	public static class PlainText extends Node {
		public PlainText(int beginIndex, int endIndex) {
			super(beginIndex, endIndex);
		}
	}

	/**
	 * Comment.
	 *
	 *
	 */
	public static class Comment extends Node {
		public Comment(int beginIndex, int endIndex) {
			super(beginIndex, endIndex);
		}
	}

	public static Element parse(String doc) {
		if (doc == null){
			return null;
		}
		Stack<Element> hierarchy = new Stack<>();
		Element document = new Element(0, doc.length());
		document.contentBeginIndex = 0;
		document.contentEndIndex = doc.length();
		hierarchy.push(document);

		TagParser.Tag tag;
		int pos = 0;
		while ((tag = TagParser.nextTag(doc, pos)) != null) {
			Element node = hierarchy.peek();
			if (pos < tag.beginIndex) {
				PlainText text = new PlainText(pos, tag.beginIndex);
				node.addChild(text);
			}
			switch (tag.kind) {
				case IS_START_TAG:
					while (TagParser.isBeginTagClosedBy(node.tag, tag.type)
							&& node != document)
					{
						node.endIndex = tag.beginIndex;
						node.contentBeginIndex = node.beginTagEndIndex;
						node.contentEndIndex = tag.beginIndex;
						hierarchy.pop();
						node = hierarchy.peek();
					}

					Element element = new Element(tag.beginIndex, tag.endIndex);
					element.beginTagBeginIndex = tag.beginIndex;
					element.beginTagEndIndex = tag.endIndex;
					element.attributeBeginIndex = tag.attrBeginIndex;
					element.attributeEndIndex = tag.attrEndIndex;
					element.tag = tag.type;
					node.addChild(element);

					if (TagParser.isStandaloneBeginTag(element.tag)) {
						// look ahead
						tag = TagParser.nextTag(doc, tag.endIndex);
						if (tag != null &&
								TagParser.isBeginEndTagPair(element.tag, tag.type)) {
							element.endIndex = tag.endIndex;
							element.contentBeginIndex = element.beginTagEndIndex;
							element.contentEndIndex = tag.beginIndex;
						}
						else {
							element.endIndex = element.beginTagEndIndex;
							element.contentBeginIndex = element.beginTagEndIndex;
							element.contentEndIndex = element.beginTagEndIndex;
						}
						// Move forward
						pos = element.endIndex;
						continue;
					}

					hierarchy.push(element);
					break;

				case IS_END_TAG:
					boolean closing = false;
					ListIterator<Element> reverse = hierarchy.listIterator(hierarchy.size());
					while (reverse.hasPrevious()) {
						Element openning = reverse.previous();
						if (openning == document)
							break;
						if (TagParser.isBeginEndTagPair(openning.tag, tag.type)) {
							closing = true;
							break;
						}
					}
					if (closing) {
						while (node != document) {
							if (TagParser.isBeginEndTagPair(node.tag, tag.type)) {
								node.endIndex = tag.endIndex;
								node.contentBeginIndex = node.beginTagEndIndex;
								node.contentEndIndex = tag.beginIndex;
								hierarchy.pop();
								break;
							}
							else {
								node.endIndex = tag.beginIndex;
								node.contentBeginIndex = node.beginTagEndIndex;
								node.contentEndIndex = tag.beginIndex;
								hierarchy.pop();
								node = hierarchy.peek();
							}
						}
					}
					else
						// end tag without previous begin tag
						// keep it but treat it as if it's a piece of normal text
						node.addChild(new PlainText(tag.beginIndex, tag.endIndex));
					break;

				case IS_EMPTY_ELEMENT_TAG:
					Element elem = new Element(tag.beginIndex, tag.endIndex);
					elem.beginTagBeginIndex = tag.beginIndex;
					elem.beginTagEndIndex = tag.endIndex;
					elem.contentBeginIndex = tag.endIndex;
					elem.contentEndIndex = tag.endIndex;
					elem.attributeBeginIndex = tag.attrBeginIndex;
					elem.attributeEndIndex = tag.attrEndIndex;
					elem.tag = tag.type;
					node.addChild(elem);
					break;

				case IS_COMMENT:
					node.addChild(new Comment(tag.beginIndex, tag.endIndex));
					break;

				case IS_UNKNOWN_TAG:
					//node.addChild(new PlainText(tag.beginIndex, tag.endIndex));
					break;

				default:
					node.addChild(new PlainText(tag.beginIndex, tag.endIndex));
					break;
			}

			// Move forward
			pos = tag.endIndex;
		}
		if (pos < doc.length()) {
			Element node = hierarchy.peek();
			node.addChild(new PlainText(pos, doc.length()));
		}
		// Close all open tags
		Element node = hierarchy.peek();
		while (node != document) {
			node.endIndex = doc.length();
			node.contentBeginIndex = node.beginTagEndIndex;
			node.contentEndIndex = doc.length();
			hierarchy.pop();
			node = hierarchy.peek();
		}

		hierarchy.pop();
		return document;
	}

/*
	public static final class ThreadOrder {

		public static final void dumpElement(String input, Element root,
				String indent, int level, Writer writer) throws IOException
		{
			writer.write(indent + level);
			writer.write(input.substring(
					root.beginTagBeginIndex,
					root.beginTagEndIndex));
			writer.write("\n");

			Collection<Node> elements = root.getChildren();
			if (elements != null) {
				for (Iterator<Node> iter = elements.iterator(); iter.hasNext();)
				{
					Node node = iter.next();
					if (node instanceof PlainText) {
						PlainText text = (PlainText) node;
						writer.write(input.substring(text.beginIndex,
								text.endIndex));
					}
					else if (node instanceof Comment) {
						Comment comment = (Comment) node;
						writer.write(input.substring(comment.beginIndex,
								comment.endIndex));
					}
					else if (node instanceof Element) {
						Element element = (Element) node;
						dumpElement(input, element, indent + "===", level + 1,
							writer);
					}
				}
			}
		}

		public static final void main(String args[]) throws Exception {
			Reader reader = new InputStreamReader(new FileInputStream(
				"F:/test/93357.htm"), "GB2312");
			StringBuilder sb = new StringBuilder();

			char[] buf = new char[4000];
			int nchars = 0;
			while ((nchars = reader.read(buf, 0, buf.length)) >= 0)
				sb.append(buf, 0, nchars);
			reader.close();

			String input = sb.toString();
			System.out.println("Input document length: " + input.length());


			Writer writer = new OutputStreamWriter(new FileOutputStream(
				"F:/test/parsed93357.htm"), "GB2312");

			long beginTime = System.currentTimeMillis();
			int total = 10000;
			for (int i = 0; i < total; i++) {
				Element document = parse(input);
//				dumpElement(input, document, "|", 0, writer);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Runs " + total + " in " +
				(endTime - beginTime) + " ms");
			writer.close();
		}
	}
*/

}
