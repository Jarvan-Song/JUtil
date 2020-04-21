package util.design.zerenlian;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class MyHandler1 extends AbstractHandler{

    @Override
    public void operation() {
        System.out.println("MyHandler1 handle");
        if(getHandler() != null){
            getHandler().operation();
        }
    }
}
