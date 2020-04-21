import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songpanfei on 2019-11-22.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.newCondition();
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(500);
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("i am waiting but facing interruptexception now " + Thread.currentThread().isInterrupted());
                }
            }
        }
    }
}
