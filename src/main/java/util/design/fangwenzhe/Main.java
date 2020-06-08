package util.design.fangwenzhe;

/**
 * Created by songpanfei on 2020-05-12.
 */
public class Main {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
