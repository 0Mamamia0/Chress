package animation;

import util.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animator {


    public static Animator animator = new Animator();


    public List<Animation> animations;



    public static Animator getInstance() {
        return animator;
    }


    public Animator() {
        this.animations = new ArrayList<>();
    }




    public void addAnimation(Animation animation) {
        if(animations == null) animations = new ArrayList<>();
        if(!animations.contains(animation)) {
            animations.add(animation);
        }
    }


    public void update() {
        processAnimation();
    }

    private void processAnimation() {
        if(animations != null && !animations.isEmpty()) {
            long time = Time.now();

            Animation[] tmp = this.animations.toArray(new Animation[0]);
            for (int i = 0; i < tmp.length; i++) {
                if(!tmp[i].update(time)) {
                    tmp[i] = null;
                }
            }

            for (Animation animation : tmp) {
                if (animation != null) {
                    animations.remove(animation);
                }
            }

        }
    }


    public void dispose() {
        animations = null;
    }
}
