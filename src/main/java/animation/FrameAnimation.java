package animation;

import util.Time;



public class FrameAnimation extends Animation {


    private long[] time_delay;
    private int[] frame;


    private int currentFrame = -1;

    public FrameAnimation(long[] time, int[] frame) {
        this.time_delay = time;
        this.frame = frame;

        long duration = 0;
        for (long t : time) {
            duration += t;
        }
        setDuration(duration);
    }


    @Override
    public boolean update(long time) {

        if(startTime == -1) {
            startTime = Time.now();
        }

        long duration  = this.duration;
        long startTime = this.startTime;

        if(duration > 0) {
            long pass = time - startTime;
            pass %= duration;
            int frame;
            if (pass < 0) {
                frame = -1;
            } else if (pass > duration) {
                frame = -2;
            }else {
                int i = 0;
                while ((pass  -= time_delay[i]) > 0) {
                    i ++;
                }
                frame = i;
            }
            if(currentFrame != frame) {
                currentFrame = frame;
                updateValue(currentFrame);
            }
        }

        return false;
    }



    public void updateValue(int frame) {

    }
}
