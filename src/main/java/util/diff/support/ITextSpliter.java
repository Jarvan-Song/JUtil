package util.diff.support;

import java.io.Serializable;

public interface ITextSpliter extends Serializable{
	String[] split(String text);
}