package util.design.danli;

/**
 * Created by songpanfei on 2020-04-07.
 */
public class Main {
    public static void main(String[] args){
    }
    private static class S1{
        private static S1 s1 = new S1();
        private S1(){}
        public static S1 getMain(){
            return s1;
        }
    }

    private static class S2{
        private static S2 s2 = null;
        private S2(){}
        public synchronized static S2 getS2(){
            if(s2 == null){
                s2 = new S2();
            }
            return s2;
        }
    }

    private static class S3{
        //如果去掉第一个if则变成S2模式，如果去掉第二个if则存在线程安全，比如两个线程竞争s3锁，1竞争到了进入synchronize，2竞争失败挂起，如果没有第二个if则当1完成初始化退出时，2会进入synchronize进行二次初始化。
        private static volatile S3 s3 = null;
        private S3(){}
        public static S3 getS3(){
            if(s3 == null){
                synchronized (s3){
                    if(s3 == null){
                        s3 = new S3();
                    }
                }
            }
            return s3;
        }
    }

    private static class S4{
        private S4(){}
        private static class S5{
            private static S4 s4 = new S4();
        }
        public static S4 getS4(){
            return S5.s4;
        }
    }

}
