package animation;

import util.Time;

public class Animation {


    public static final int STARTED = 1;

    private int state;
    private boolean started;
    private boolean canceled;
    private boolean ended;

    private int repeatCount = 1;
    private int repeated;

    protected long startTime;
    protected long duration;


    private AnimationCallback callback;





    public  void start() {
        this.startTime = -1;
    }


    public void setDuration(long duration) {
        this.duration = duration;
    }


    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount - 1;
        this.repeated = 0;
    }

    public boolean update(long time) {

        if(startTime == -1) {
            startTime = Time.now();
        }

        float normalizedTime;

        if(duration > 0) {
            normalizedTime = (float) (time - startTime) / (float)duration;
        } else {
            normalizedTime = time < startTime ? 0.0f : 1.0f;
        }

        final boolean expired = normalizedTime >= 1.0f || canceled;

//        if (!mFillEnabled) normalizedTime = Math.max(Math.min(normalizedTime, 1.0f), 0.0f);
        normalizedTime = Math.max(Math.min(normalizedTime, 1.0f), 0.0f);

        if(!started) {
            fireAnimationStart();
            started = true;
        }

        if(!canceled) apply(normalizedTime);

        if( repeated == repeatCount || canceled) {
            if(!ended) {
                ended = true;
                fireAnimationEnd();
            }
        } else if(normalizedTime >= 1.0f){

            if(repeatCount > 0) {
                repeated ++;
                if(repeated == repeatCount) {
                    ended = true;
                    fireAnimationEnd();
                    return true;
                }
            }
            startTime = -1;
            fireAnimationRepeat();
        }

        return ended;

    }

    private void fireAnimationRepeat() {
        if(callback != null) {
            callback.onAnimationRepeat(this);
        }
    }

    private void fireAnimationEnd() {
        if(callback != null) {
            callback.onAnimationEnd(this);
        }
    }

    private void fireAnimationStart() {
        if(callback != null) {
            callback.onAnimationStart(this);
        }
    }

    public void apply(float factor) {

    }


//    public void pause() {
//
//    }

    public void cancel() {
        this.canceled = true;
    }


    public void setCallback(AnimationCallback callback) {
        this.callback = callback;
    }



    public void reset() {
        started = false;
        ended = false;
        canceled = false;
        startTime = Long.MAX_VALUE;
        repeated = 0;
    }


//    public void stop() {
//
//    }


    public long getStartTime() {
        return startTime;
    }


    public long getDuration() {
        return duration;
    }

    public int getRepeatCount() {
        return repeatCount;
    }
}
