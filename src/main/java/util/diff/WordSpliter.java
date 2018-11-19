package util.diff;



import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import util.diff.support.Context;
import util.diff.support.ITextSpliter;
import util.html.support.HtmlParser;
import util.html.support.HtmlParser.*;
import util.string.StringUtil;

import java.util.*;



/**
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

	public static void main(String[] args){
		String[] argOld = new WordSpliter().split("<p>在中国</p>fefsfadfsdffsfs<a></a>");
		String[] argNew = new WordSpliter().split("<p>美国嗖嗖嗖</p>weirwjfkdsfjksdfasdf<a>sfds</a>");

		List<String> leftList = Arrays.asList(argOld);
		List<String> rightList = Arrays.asList(argNew);
		Patch patch = DiffUtils.diff(leftList, rightList);
		List<Delta> deltas=  patch.getDeltas();
		for(Delta delta: deltas){
			Chunk left   = delta.getOriginal();
			Chunk right  = delta.getRevised();
			System.out.println(delta.getType() + "  "+left +"  " + right);
			if(left.size() >  0) {
				StringBuilder leftText = new StringBuilder();
				switch (delta.getType()){
					case CHANGE:
						leftText.append("<span class=\"modify\">");
						break;
					case DELETE:
						leftText.append("<span class=\"del\">");
						break;
					case INSERT:
						leftText.append("<span class=\"add\">");
						break;
				}
				for(int i=left.getPosition();i<left.getPosition()+left.size();i++){
					leftText.append(leftList.get(i));
					leftList.set(i, "");
				}
				leftText.append("</span>");
				leftList.set(left.getPosition(), leftText.toString());
			}

			if(right.size() >  0){
				StringBuilder rightText = new StringBuilder();
				switch (delta.getType()){
					case CHANGE:
						rightText.append("<span class=\"modify\">");
						break;
					case DELETE:
						rightText.append("<span class=\"del\">");
						break;
					case INSERT:
						rightText.append("<span class=\"add\">");
						break;
				}
				for(int i=right.getPosition();i<right.getPosition()+right.size();i++){
					rightText.append(rightList.get(i));
					rightList.set(i, "");
				}
				rightText.append("</span>");
				rightList.set(right.getPosition(), rightText.toString());
			}
		}
		StringBuilder left = new StringBuilder();
		for(int i=0;i<leftList.size();i++){
			if(StringUtil.isNotEmpty(leftList.get(i))){
				left.append(leftList.get(i));
			}
		}

		StringBuilder right = new StringBuilder();
		for(int i=0;i<rightList.size();i++){
			if(StringUtil.isNotEmpty(rightList.get(i))){
				right.append(rightList.get(i));
			}
		}
		System.out.println(left.toString());
		System.out.println(right.toString());
	}
}