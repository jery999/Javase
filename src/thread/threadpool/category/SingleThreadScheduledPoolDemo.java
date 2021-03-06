package thread.threadpool.category;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: fujing
 * @Date: 2021/12/11
 * @Description:
 * @Version: 1.0
 */
public class SingleThreadScheduledPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        System.out.println("begin--" + System.currentTimeMillis());//第一个线程开始前输出

/*        //延迟一段时间执行
        for (int i = 0; i < 20; i++) {
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程任务提交后，延迟3秒执行====");
                    System.out.println("run end:" + System.currentTimeMillis());//每个线程执行结束输出
                }
            }, 3000, TimeUnit.MILLISECONDS);
        }*/


        /**
         * 定期执行线程任务：
         */
        for (int i = 0; i < 3; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("定期执行线程任务：第一次延迟1秒执行，之后每3秒执行一次。。。");
                    System.out.println(Thread.currentThread().getName() +"--run end---" + System.currentTimeMillis());//每个线程执行结束输出
                }
            }, 1, 3, TimeUnit.SECONDS);
        }

//        //定期执行期间，如果任务没执行结束，线程池暂时不能关闭
//        scheduledExecutorService.shutdown();
    }
}
