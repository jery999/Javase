package thread.api;

/**
 * @Author: fujing
 * @Date: 2021/11/23
 * @Description:
 * @Version: 1.0
 */
public class JoinTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "====" + i);
        }
    }


    public static void main(String[] args) {
        JoinTest joinTest = new JoinTest();
        Thread thread = new Thread(joinTest);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "====" + i);
            if (i == 3) {
                try {
                    //在i==3的时候，主线程会先被阻塞,而调用join方法的线程thread-0会开始执行，thread-0执行完毕后，主线程才会继续执行
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
