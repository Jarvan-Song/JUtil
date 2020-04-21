package util.design.guanchazhe;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by songpanfei on 2020-04-21.
 */
public class Subject {
    private static Set<Observer> set = new HashSet<>();

    public boolean addObserver(Observer observer){
        return set.add(observer);
    }

    public boolean remObserver(Observer observer){
        return set.remove(observer);
    }

    public void notifyObserver(){
        if(set.size() > 0){
            for(Observer observer: set){
                observer.update();
            }
        }
    }
}
