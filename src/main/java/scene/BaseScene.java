package scene;

import animation.Animator;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;
import ui.Display;
import ui.ImageLoader;
import ui.Sprite;
import ui.Texture;
import util.Timer;
import util.Task;


import java.util.*;

public class BaseScene extends Scene{





    boolean menuShow;
    Sprite l_soft;
    Sprite r_soft;

    protected Display display;
    private final Animator animator;
    private Callback callback;
    private List<Timer> timers;







    public interface Callback {
        void complete();
    }


    public BaseScene() {
        this.animator = Animator.getInstance();
    }


    private void initMenu() {
        if(!menuShow) {

            l_soft = new Sprite();
            r_soft = new Sprite();



            l_soft.setOnClickCallBack(l -> onLeftSoftButton());
            r_soft.setOnClickCallBack(r -> onRightSoftButton());

            addNode(l_soft);
            addNode(r_soft);
        }

    }


    public void showCustomMenu(SoftMenu.Type l_soft, SoftMenu.Type r_soft) {

    }


    public void showCustomMenu() {
        initMenu();
        Texture[] item = new Texture("yn.png").spiltV(2);
        l_soft.setTexture(item[0]);
        r_soft.setTexture(item[1]);

        l_soft.setPosition(5, 320 - item[0].getHeight());
        r_soft.setPosition(240 - item[1].getWidth() - 5, 320 - item[1].getHeight());

    }

    protected void onRightSoftButton() {

    }


    protected void onLeftSoftButton() {

    }

    protected void loadResource() {



    }







    public void addTask(long delay, Task task) {
        addTask(delay, -1, task);
    }


    public void addTask(long delay, long period, Task task) {
        Timer timer = new Timer(delay, period,task);
        if(timer.start()) {
            if(timers == null) {
                timers = new ArrayList<>();
            }
            timers.add(timer);
        }
    }



    private void execTask() {


    }


    private void execTasks() {
        if(timers != null && !timers.isEmpty()) {
            timers.removeIf(Timer::act);
        }
    }


    @Override
    public void update() {
        animator.update();
        super.update();
        execTasks();
    }




    public Callback getCallback() {
        return callback;
    }


    public void finish() {
        if(callback != null) {
            callback.complete();
        }
    }

    public void whenComplete(Callback callback) {
        this.callback = callback;
    }


    @Override
    public void attach(Display display) {
        this.display = display;
    }

    @Override
    public void detach(Display display) {
        this.display = null;
    }
}
