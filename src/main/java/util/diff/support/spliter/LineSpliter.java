package util.diff.support.spliter;



import util.html.support.HtmlParser;
import util.html.support.HtmlParser.*;
import util.html.support.TagParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by songpanfei on 2018/11/19.
 * 将文本分割成段落，即以p标签为分割点
 */
public class LineSpliter implements ITextSpliter {
	
	private static final long serialVersionUID = -7351916057187547813L;
	@Override
	public String[] split(String text) {
		if (text == null) text = "";
		Element document = HtmlParser.parse(text);
		if (document == null) {
			return new String[]{""};
		}
		List<String> lines = new ArrayList<String>();
		Collection<Node> children = document.getChildren();
		if (children == null) {
			return new String[]{""};
		}

		Iterator<Node> iter = children.iterator();
		while (iter.hasNext()) {
			Node node = iter.next();
			if (node instanceof Element) {
				Element elem = (Element) node;
				switch(elem.tag){
					case TagParser.P_BEGIN_TAG:
					case TagParser.UL_BEGIN_TAG: //目录特殊标记符合
						convertParagraph(elem, lines, text, elem.tag);
						break;
					default:
						addNodeAsWholeBlock(elem, lines, text);
				}
			} else if (node instanceof PlainText) {
				addNodeAsPlainText(node, lines, text);
			}
		}
		return lines.toArray(new String[]{});
	}
	
	private void convertParagraph(Element doc, List<String> lines, String text, int tagType) {
		Collection<Node> children = doc.getChildren();
		if (children == null) {
			return;
		}
		switch(tagType){
			case TagParser.P_BEGIN_TAG: lines.add("<p>"); break;
			case TagParser.UL_BEGIN_TAG: lines.add("<ul>"); break;
		}
		Iterator<Node> iter = children.iterator();
		while (iter.hasNext()) {
			Node node = iter.next();
			if (node instanceof Element) {
				Element elem = (Element) node;
				addNodeAsWholeBlock(elem, lines, text);
			} else if (node instanceof PlainText) {
				addNodeAsPlainText(node, lines, text);
			}
		}
		switch(tagType){
			case TagParser.P_BEGIN_TAG: lines.add("</p>"); break;
			case TagParser.UL_BEGIN_TAG: lines.add("</ul>"); break;
		}
	}

	private void addNodeAsPlainText(Node node, List<String> ls, String text){
		String plainText = null;
		try{
			plainText = text.substring(node.beginIndex, node.endIndex);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(plainText == null) 
			return;
		char[] results = plainText.toCharArray();
		for(int i = 0; i < results.length; i++)
			ls.add(String.valueOf(results[i]));
	}
	private void addNodeAsWholeBlock(Node node, List<String> ls, String text){
		String htmlText = null;
		try{ 
			htmlText = text.substring(node.beginIndex, node.endIndex);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(htmlText != null)
			ls.add(htmlText);
	}

	public static void main(String[] args){
		String[] argNew = new LineSpliter().split("<p><a>123sdfsfsfdsdfsdfs。</a></p>weirwjfkdsfjk,sdfasdf<a>sfds</a>");
		for(String str: argNew){
			System.out.println(str);
		}
	}
}
