package util.jmx;

/**
 * Created by songpanfei on 2021-07-22.
 */
public class Hello implements HelloMBean {
    private String name;

    public Hello(){}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printHello() {
        System.out.println("Hello world, "+ name);
    }

    @Override
    public void printHello(String whoName) {
        System.out.println("Hello, "+whoName);
    }
}
