package util.design.mingling;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class Main {
    public static void main(String[] args){
        Receiver receiver = new Receiver();
        Commond commond  = new Commond(receiver);
        Invoke invoke = new Invoke(commond);
        invoke.commond();
    }
}
