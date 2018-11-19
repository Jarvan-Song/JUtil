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
        TextDiffResult textDiffResult = new TextDiffResult();
        textDiffResult.setOldText(left.toString());
        textDiffResult.setNewText(right.toString());
        return textDiffResult;
    }

    public static void main(String[] args){
        TextDiffResult textDiffResult  = diffText("<p>在中国</p>fefsfadfsdffsfs<a></a>", "<p>美国嗖嗖嗖</p>weirwjfkdsfjksdfasdf<a>sfds</a>",Algorithm.splitOnWord);
        System.out.println(textDiffResult.getOldText());
        System.out.println(textDiffResult.getNewText());
    }
}
