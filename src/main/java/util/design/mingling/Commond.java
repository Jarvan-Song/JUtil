package util.design.mingling;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class Commond {
    private Receiver receiver;
    public Commond(){}
    public Commond(Receiver receiver){this.receiver=receiver;}
    public void exec(){
        receiver.action();
    }
}
