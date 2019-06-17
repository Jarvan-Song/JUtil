package util.txt;

import java.io.Serializable;

public class Pv implements Serializable {

    private long lemmaId;
    private int date;
    private int pvPc;
    private int pvMb;

    private String lemmaTitle; //额外字段

    public String getLemmaTitle() {
        return lemmaTitle;
    }

    public void setLemmaTitle(String lemmaTitle) {
        this.lemmaTitle = lemmaTitle;
    }

    public long getLemmaId() {
        return lemmaId;
    }

    public void setLemmaId(long lemmaId) {
        this.lemmaId = lemmaId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getPvPc() {
        return pvPc;
    }

    public void setPvPc(int pvPc) {
        this.pvPc = pvPc;
    }

    public int getPvMb() {
        return pvMb;
    }

    public void setPvMb(int pvMb) {
        this.pvMb = pvMb;
    }

    public String getFormat(){
        return  lemmaId + "\t"+
                lemmaTitle  +"\t"+
                pvPc +"\t"+
                pvMb +"\t"+
                date;
    }

    @Override
    public String toString() {
        return "Pv{" +
                "lemmaId=" + lemmaId +
                ", date=" + date +
                ", pvPc=" + pvPc +
                ", pvMb=" + pvMb +
                ", lemmaTitle='" + lemmaTitle + '\'' +
                '}';
    }
}
