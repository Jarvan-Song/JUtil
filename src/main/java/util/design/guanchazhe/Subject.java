package util.design.guanchazhe;

/**
 * Created by songpanfei on 2020-04-21.
 */
public interface Subject {
    boolean addObserver(Observer observer);

    boolean remObserver(Observer observer);

    void notifyObserver();
}
