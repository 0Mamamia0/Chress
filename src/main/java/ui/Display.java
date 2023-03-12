package ui;

import io.github.humbleui.skija.Canvas;
import scene.ColorfulScene;
import scene.Scene;

public class Display {




    private static Display instance = new Display();
    private Scene scene = new ColorfulScene();





    public static Display getDefaultDisplay() {
        return instance;
    }



    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void update() {

        scene.update();

    }

    public void draw(Canvas canvas) {
        canvas.clear(0xFF00FF00);

        if(scene != null) {
            scene.draw(canvas);
        }
    }



}
