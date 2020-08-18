package util.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songpanfei on 2020-08-06.
 *     //ABC三个线程顺序输出 10遍为止

 */
public class ThreadOrder {
    static int num = 0;
    static Object lock = new Object();
    static java.lang.Thread a1 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 29){
                try {
                    synchronized (lock){
                        if(num%3==0){
                            System.out.println("a");
                            num++;
                            lock.notifyAll();
                        }else {
                            lock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    static java.lang.Thread a2 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 29){
                try {
                    synchronized (lock){
                        if(num%3==1){
                            System.out.println("b");
                            num++;
                            lock.notifyAll();
                        }else {
                            lock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    static java.lang.Thread a3 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 29){
                try {
                    synchronized (lock){
                        if(num%3==2){
                            System.out.println("c");
                            num++;
                            lock.notifyAll();
                        }else {
                            lock.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    static ReentrantLock lock2 = new ReentrantLock();
    static Condition condition = lock2.newCondition();
    static java.lang.Thread b1 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <=29){
                try {
                    lock2.lock();
                    if(num%3==0){
                        System.out.println("a"+num);
                        num++;
                        condition.signalAll();
                    }else {
                        condition.await();
                    }
                }catch (Exception e){
                }finally {
                    lock2.unlock();
                }
            }
        }
    });
    static java.lang.Thread b2 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 29){
                try {
                    lock2.lock();
                    if(num%3==1){
                        System.out.println("b"+num);
                        num++;
                        condition.signalAll();
                    }else {
                        condition.await();
                    }
                }catch (Exception e){
                }finally {
                    lock2.unlock();
                }
            }
        }
    });
    static java.lang.Thread b3 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 29){
                try {
                    lock2.lock();
                    if(num%3==2){
                        System.out.println("c"+num);
                        num++;
                        condition.signalAll();
                    }else {
                        condition.await();
                    }
                }catch (Exception e){
                }finally {
                    lock2.unlock();
                }
            }
        }
    });
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);
    static Semaphore s3 = new Semaphore(0);
    static java.lang.Thread c1 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 30){
                try {
                    s1.acquire();
                    System.out.println("a");
                    num++;
                    s2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    static java.lang.Thread c2 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 30){
                try {
                    s2.acquire();
                    System.out.println("b");
                    num++;
                    s3.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    static java.lang.Thread c3 = new java.lang.Thread(new Runnable() {
        @Override
        public void run() {
            while (num <= 30){
                try {
                    s3.acquire();
                    System.out.println("c");
                    num++;
                    s1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    public static void main(String[] args){
        b1.start();
        b2.start();
        b3.start();
    }
}
