public class TimerTaskTest extends TimerTask {

    @Override
    public void run() {
        System.out.println("timer task start at " + new Date());
        //执行一个任务要20s，但调度器是每隔10s调度一次，所以一次任务执行完立马执行下一次任务
        executeTask();
        System.out.println("timer task end at " + new Date());
    }

    //时间调度器具体执行的任务
    public void executeTask() {
        try {
            //休眠20s
            Thread.sleep(20000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TimerTaskTest task = new TimerTaskTest();
        //置为守护进程
        Timer timer = new Timer(true);
        //延迟0s后调度task,调度完后，等待10s后再开始调度
        timer.scheduleAtFixedRate(task, 0, 10*1000);
        System.out.println("调用时间调度器");
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2分钟后删除调度器
        timer.cancel();
        System.out.println("删除时间调度器");
        //让最后一次任务能够执行完打印出来
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//schedule(TimerTask task, long delay, long period)和scheduleAtFixedRate(TimerTask task, long delay, long period)的区别:
/**
 * schedule在计算下一次执行的时间时,是通过实际运行的时间点+时间片
 * eg:时间片是5s，理论后面的调度时间依次是10，15，20等，假如第一次调度因为某原因延迟到了第8s，那么下次调度的时间就是第13s，保证隔5s。
 * scheduleAtFixedRate计算下一次执行的时间时，是通过理论运行的时间点+时间片
 * eg:时间片是5s，理论后面的调度时间依次是10，15，20等，假如第一次调度因为某原因延迟到了第8s，那么下次调度的时间还是第10s，只隔了2s。
 */
 // Timer是一个定时任务调度器, TimerTask是一个实现了run方法的抽象类,在run中可以定义具体的定时任务。
