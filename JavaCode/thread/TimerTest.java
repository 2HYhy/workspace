public class TimerTest {

    Timer timer = new Timer();

    public TimerTest(int seconds) {
        timer.schedule(new MyTimerTask(), seconds*1000);
    }

    public static void main(String [] args) {
        System.out.println("before Timer scheduled!");
        new TimerTest(5);
        System.out.println("Timer scheduled!");
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("This is specific task in Timer");
            timer.cancel();
        }
    }
}
/**
 * 运行结果是先输出before Timer scheduled! \n Timer scheduled!
 * 5s后输出This is specific task in Timer
 */