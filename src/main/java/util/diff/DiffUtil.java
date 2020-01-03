package util.diff;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import util.diff.support.*;
import util.string.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by songpanfei on 2018/11/19.
 */
public class DiffUtil {

    public static TextDiffResult diffText(String oldText, String newText, Algorithm algorithm){
        ITextSpliter spliter;
        switch (algorithm){
            case splitOnWord:
                spliter = new WordSpliter();
                break;
            case splitOnLine:
                spliter = new LineSpliter();
                break;
            case splitOnSentence:
                spliter = new SentenceSpliter();
                break;
            default:
                spliter = new WordSpliter();
        }
        String[] argOld = spliter.split(oldText);
        String[] argNew = spliter.split(newText);
        List<String> leftList  = Arrays.asList(argOld);
        List<String> rightList = Arrays.asList(argNew);
        System.out.println(leftList);
        System.out.println(rightList);
        Patch patch = DiffUtils.diff(leftList, rightList);
        List<Delta> deltas=  patch.getDeltas();
        for(Delta delta: deltas){
            Chunk left   = delta.getOriginal();
            Chunk right  = delta.getRevised();
            System.out.println(delta.getType() + "  "+left);
            System.out.println(delta.getType() + "  "+right);
            addExtentTag(left,  delta.getType(), leftList);
            addExtentTag(right, delta.getType(), rightList);
        }
        StringBuilder left  = new StringBuilder();
        StringBuilder right = new StringBuilder();
        for(int i=0;i < leftList.size();i++){
            if(StringUtil.isNotEmpty(leftList.get(i))){
                left.append(leftList.get(i));
            }
        }
        for(int i=0;i < rightList.size();i++){
            if(StringUtil.isNotEmpty(rightList.get(i))){
                right.append(rightList.get(i));
            }
        }
        TextDiffResult textDiffResult = new TextDiffResult();
        textDiffResult.setOldText(left.toString());
        textDiffResult.setNewText(right.toString());
        return textDiffResult;
    }

    public static List<String> addExtentTag(Chunk text, Delta.TYPE type, List<String> rightList){
        if(text.size() > 0){
            StringBuilder rightText = new StringBuilder();
            switch (type){
                case CHANGE:
                    rightText.append("<span class=\"diff_modify\">");
                    break;
                case DELETE:
                    rightText.append("<span class=\"diff_del\">");
                    break;
                case INSERT:
                    rightText.append("<span class=\"diff_add\">");
                    break;
            }
            for(int i = text.getPosition();i < text.getPosition() + text.size();i++){
                rightText.append(rightList.get(i));
                rightList.set(i, "");
            }
            rightText.append("</span>");
            rightList.set(text.getPosition(), rightText.toString());
        }
        return rightList;
    }

    public static void main(String[] args){
        TextDiffResult textDiffResult  = diffText("<p>在中国<span>我是</span></p>fefsfadfsdffsfs<a></a>", "<p>美国嗖嗖嗖</p>weirwjfkdsfjksdfasdf<a>sfds</a>",Algorithm.splitOnWord);
//        TextDiffResult textDiffResult  = diffText("我们以前完全不担心吃糖果有可能会蛀牙。", "我们以前完全不火烧吃糖果有可能会蛀牙", Algorithm.splitOnWord);
        System.out.println(textDiffResult.getOldText());
        System.out.println(textDiffResult.getNewText());
    }
}
