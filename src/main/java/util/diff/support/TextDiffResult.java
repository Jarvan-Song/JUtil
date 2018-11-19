package util.diff.support;

import java.io.Serializable;

/**
 * Created by songpanfei on 2018/11/19.
 */
public class TextDiffResult implements Serializable{
    private String oldText;
    private String newText;

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }
}
