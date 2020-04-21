package util.design.guanchazhe;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class Main {
    public static void main(String[] args){
        Subject subject= new Subject();
        Observer observer1= new ObserverImp1();
        Observer observer2= new ObserverImp2();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notifyObserver();
        subject.remObserver(observer1);
        subject.notifyObserver();
        subject.remObserver(observer2);
        subject.notifyObserver();

    }
}
