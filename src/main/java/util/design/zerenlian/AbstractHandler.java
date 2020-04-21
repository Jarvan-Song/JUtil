package util.design.zerenlian;

/**
 * Created by songpanfei on 2020-04-21.
 */
public abstract class AbstractHandler implements Handler{
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
