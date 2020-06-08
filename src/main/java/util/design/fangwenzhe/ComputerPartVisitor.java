package util.design.fangwenzhe;

/**
 * Created by songpanfei on 2020-05-12.
 */
public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}
