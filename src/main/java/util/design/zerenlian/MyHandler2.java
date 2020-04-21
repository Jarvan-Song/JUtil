package util.design.zerenlian;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class MyHandler2 extends AbstractHandler{

    @Override
    public void operation() {
        System.out.println("MyHandler2 handle");
        if(getHandler() != null){
            getHandler().operation();
        }
    }
}
