import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songpanfei on 2019-11-22.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//        reentrantLock.newCondition();
//        Thread thread = new MyThread();
//        thread.start();
//        Thread.sleep(500);
//        System.out.println(thread.getState());
//        thread.interrupt();
//        Thread.sleep(1000);
//        System.out.println(thread.isInterrupted());
//        String url = "https://pic.baike.soso.com/ugc/baikepic2/0/20200717163941-1430022197_jpeg_500_500_43868.jpg/800";
//        String url2 = "https://pic.baike.soso.com/ugc/baikepic2/0/ori-20190909104631-854506882_jpg_738_501_52730.jpg/800";
//        String[] array = url2.split("/");
//        String fileName = array[array.length-2];
//        Pattern pattern = Pattern.compile("[0-9]{8}");
//        System.out.println(fileName);
//
//        Matcher matcher = pattern.matcher(fileName);
//        if(matcher.find()){
//            System.out.println(matcher.group(0));
//        }
//        HashMap<String ,String> a = new HashMap<>(5);
//        a.put(1+"",1+"");
//        a.put(2+"",2+"");
//        a.put(3+"",2+"");
//        a.put(4+"",1+"");
//        a.put(5+"",1+"");
//        a.put(6+"",1+"");
//        a.put(7+"",1+"");
//        a.put(8+"",1+"");
//        a.put(9+"",1+"");
//        a.put(10+"",1+"");
//        a.put(11+"",1+"");
//        a.put(12+"",1+"");
//        a.put(13+"",1+"");
//        a.put(14+"",1+"");
//        a.put(15+"",1+"");
//        a.put(16+"",1+"");
//        a.put(17+"",1+"");
//        HashMap<String ,String> b = new HashMap<>();

        ThreadLocal<String> a = new ThreadLocal<>();
        a.get();
        a.set("1");
        a.set("2");
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
