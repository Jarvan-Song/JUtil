package util.leetcode;

/**
 * Created by songpanfei on 2020-05-13.
 */
public class OptimisticLock {
    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            new OptimThread(String.valueOf(i), 1, "c++").start();
        }
    }

    //数据版本号
    static int version = 1;
    //真实数据
    static String data = "java";
    public static int getVersion(){
        return version;
    }
    public static void updateVersion(){
        version = version + 1;
    }

    static class OptimThread extends Thread {
        public int version;
        public String data;
        public OptimThread(String name, int version, String data){
            super(name);
            this.version = version;
            this.data =data;
        }
        public void run() {
            // 1.读数据
            String text = OptimisticLock.data;
            System.out.println("线程"+ getName() + "，获得的数据版本号为：" + OptimisticLock.getVersion());
            System.out.println("线程"+ getName() + "，预期的数据版本号为：" + version);
            System.out.println("线程"+ getName() + "读数据完成=========data = " + text);
            // 2.写数据：预期的版本号和数据版本号一致，那就更新
            if(OptimisticLock.getVersion() == version){
                System.out.println("线程" + getName() + "，版本号为：" + version + "，正在操作数据");
                synchronized(OptimThread.class){
                    if(OptimisticLock.getVersion() == version){
                        OptimisticLock.data = this.data;
                        OptimisticLock.updateVersion();
                        System.out.println("线程" + getName() + "写数据完成=========data = " + this.data);
                        return ;
                    }
                }
            }else{
                // 3. 版本号不正确的线程，需要重新读取，重新执行
                System.out.println("线程"+ getName() + "，获得的数据版本号为：" + OptimisticLock.getVersion());
                System.out.println("线程"+ getName() + "，预期的版本号为：" + version);
                System.err.println("线程"+ getName() + "，需要重新执行。==============");
            }
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


}
