package util.design.zerenlian;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class MyHandler3 extends AbstractHandler{

    @Override
    public void operation() {
        System.out.println("MyHandler3 handle");
        if(getHandler() != null){
            getHandler().operation();
        }
    }
}
