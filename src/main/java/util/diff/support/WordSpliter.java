package util.diff.support;



import util.diff.support.Context;
import util.diff.support.ITextSpliter;
import util.html.support.HtmlParser;
import util.html.support.HtmlParser.*;

import java.util.*;



/**
 * Created by songpanfei on 2018/11/19.
 * 把代表词条的纯 HTML 划分成原子单元：字。
 * 在此基础上进行diff操作。
 */
public class WordSpliter implements ITextSpliter {

	private static final String[] EMPTY = new String[]{""};
	
	@Override
	public String[] split(String text) {
		if (text == null) return EMPTY;
		HtmlParser.Element document = HtmlParser.parse(text);
		if (document == null) return EMPTY;
		Collection<Node> children = document.getChildren();
		if (children == null) return EMPTY;
		Iterator<Node> iter = children.iterator();
		Context<String> context = new Context(text);
		accept(context, iter);
		return context.toArray(new String[]{});
	}

	private void accept(Context<String> context, Iterator<Node> iter){
		if (iter == null) return;
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
		if(plainText == null) return;
		char[] results = plainText.toCharArray();
		for(int i = 0; i < results.length; i++)
			context.add(String.valueOf(results[i]));
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
}