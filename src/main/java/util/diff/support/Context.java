package util.diff.support;

import java.util.ArrayList;

public class Context<T> extends ArrayList<T>{
    private String text;

	public Context(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}