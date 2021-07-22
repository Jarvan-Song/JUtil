package util.jmx;

/**
 * Created by songpanfei on 2021-07-22.
 */
public interface HelloMBean {
    public String getName();
    public void setName(String name);
    public void printHello();
    public void printHello(String whoName);
}
