package util;

public class Timer {


    public static final int INIT = 0;
    public static final int STARTED = 1;
    public static final int STOP = 2;

    private long startTime;
    private long lastExecTime;
    private long nextExecTime;


    private long delay;
    private long period;

    private int state;

    private final Task task;



    public Timer(long timeout, Task task) {
        this(timeout, -1, task);
    }

    public Timer(long timeout,long period, Task task) {
        setDelay(timeout);
        setPeriod(period);
        this.task = task;
    }

    private void setPeriod(long period) {
        if(period < -1) {
            throw new IllegalArgumentException();
        }
        this.period = period;
    }


    private void setDelay(long delay) {
        if(delay < 0) {
            throw new IllegalArgumentException();
        }
        this.delay = delay;
    }



    public boolean start() {
        if(state == INIT && task != null) {
            setState(STARTED);
            this.startTime = Time.now();
            this.nextExecTime = startTime + delay;
            return true;
        }
        return false;
    }



    public boolean act() {
        if(state == STARTED) {
            long now = Time.now();
            if(now > nextExecTime) {
                boolean finish = task.exec();
                if(period == -1 || finish) {
                    stop();
                    return true;
                }
                long next = nextExecTime + period;
                now = Time.now();

                nextExecTime = Math.max(now, next);
            }
            return false;
        }
        return true;
    }



    public void stop() {
        if(state == STARTED) setState(STOP);

    }

    private void setState(int state) {
        this.state = state;
    }





}
