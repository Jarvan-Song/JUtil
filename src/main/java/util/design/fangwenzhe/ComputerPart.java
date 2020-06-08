package util.design.fangwenzhe;

/**
 * Created by songpanfei on 2020-05-12.
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
