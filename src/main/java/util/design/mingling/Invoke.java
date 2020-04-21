package util.design.mingling;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class Invoke {
    private Commond commond;

    public Invoke(){}
    public Invoke(Commond commond){this.commond=commond;}

    public void commond(){
        System.out.println("命令下达");
        commond.exec();
    }
}
