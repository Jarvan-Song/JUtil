package util.diff;


import util.diff.support.Context;
import util.diff.support.ITextSpliter;
import util.html.support.HtmlParser;
import util.html.support.HtmlParser.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 将文本分割成句子，即以逗号和句号进行分割，主要用于中文diff
 */
public class SentenceSpliter implements ITextSpliter {

	private static final String[] EMPTY = new String[]{""};
	
	@Override
	public String[] split(String text) {
		if (text == null) return EMPTY;
		Element document = HtmlParser.parse(text);
		if (document == null) return EMPTY;
		Collection<Node> children = document.getChildren();
		if (children == null) return EMPTY;
		Iterator<Node> iter = children.iterator();
		Context<String> context = new Context(text);
		accept(context, iter);
		return context.toArray(new String[]{});
	}

	private void accept(Context context, Iterator<Node> iter){
		if (iter == null)
			return;
		while (iter.hasNext()){
			Node node = iter.next();
			if (node instanceof Element){
				accept(context, (Element)node);
			}else{
				accept(context, node);
			}
		}
	}

	private void accept(Context context, Node node){
		String plainText = null;
		try{
			plainText = context.getText().substring(node.beginIndex, node.endIndex);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(plainText == null) 
			return;
		
		StringTokenizer st = new StringTokenizer(plainText, "。，", true);
		while (st.hasMoreTokens()){
			String tk = st.nextToken().trim();
			if (tk != null && !tk.isEmpty())
				context.add(tk);
		}
		
	}

	private void accept(Context context , Element e){
		String beginTag = context.getText().substring(e.beginTagBeginIndex, e.beginTagEndIndex);
		context.add(beginTag);
		
		Collection<Node> children = e.getChildren();
		if (children != null){
			Iterator<Node> iter = children.iterator();
			accept(context, iter);
		}
		String endTag = context.getText().substring(e.contentEndIndex, e.endIndex);
		if (!(endTag == null || endTag.isEmpty()))
			context.add(endTag);
	}

	public static void main(String[] args){
		String[] argOld = new SentenceSpliter().split("<p>123fffffffffffff</p><a></a>");
		String[] argNew = new SentenceSpliter().split("<p><a>123sdfsfsfdsdfsdfs。sfsfs</a></p>weirwjfkdsfjk。sdfasdf<a>sfds</a>");
		for(String str: argOld){
			System.out.println(str);
		}
		for(String str: argNew){

			System.out.println(str);
		}
	}
	
}
