package util.design.zerenlian;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class Main {
    public static void main(String[] args){
        MyHandler1 handler1 = new MyHandler1();
        MyHandler2 handler2 = new MyHandler2();
        MyHandler3 handler3 = new MyHandler3();
        handler1.setHandler(handler2);
        handler2.setHandler(handler3);
        handler1.operation();
    }
}
