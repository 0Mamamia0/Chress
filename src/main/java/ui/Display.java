package ui;

import animation.Animator;
import io.github.humbleui.skija.Canvas;
import media.Sound;
import scene.*;

import java.util.EmptyStackException;
import java.util.Stack;

public class Display implements MenuScene.OnMenuSelectCallBack {




    private static final Display instance = new Display();


    public static Display getDefaultDisplay() {
        return instance;
    }







    private Scene scene;
    private final Stack<Scene> sceneStack;



    private MenuScene scene_menu;




    private Display() {
        sceneStack = new Stack<>();


        ColorfulScene scene_colorful = new ColorfulScene();
        MusicSelectScene scene_music = new MusicSelectScene();
        PaintingScene scene_painting = new PaintingScene();
        scene_menu = new MenuScene();
        ScoreScene scene_setting = new ScoreScene();
        LogoScene logoScene = new LogoScene();

        GameScene scene_game = new GameScene();
        scene_colorful.whenComplete(() -> setScene(logoScene));
        logoScene.whenComplete(() -> setScene(scene_music));
        scene_music.whenComplete(() -> setScene(scene_painting));
        scene_painting.whenComplete(() -> pushScene(scene_menu));

        scene_menu.setOnSelectCallback(this);
        setScene(scene_colorful);
    }




    public void setScene(Scene scene) {
        Scene previous = this.scene;
        if(previous != null) {
            previous.hide();
            previous.detach(this);
        }
        this.scene = scene;

        if(scene != null) {
            scene.attach(this);
            scene.show();
        }
    }



    public void loadCanjv(int year, int month, int day) {
        GameScene gameScene = new GameScene();
        gameScene.loadCanjv(year, month, day);
        pushScene(gameScene);
    }

    public void pushScene(Scene scene) {
        this.sceneStack.push(scene);
        setScene(scene);
    }

    public void backScene() {
        try {
            setScene(this.sceneStack.pop());
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

    }

    public void toMenuScene() {
        if(scene_menu != null) {
            setScene(scene_menu);
        }
    }



    public void update(float delta) {
        scene.update();
    }


    public void destroy() {
        setScene(null);
        Animator.getInstance().dispose();
        Sound.closeAll();
    }


    public void draw(Canvas canvas) {
        canvas.clear(0xFF000000);
        if(scene != null) {
            scene.draw(canvas);
        }
    }


    public void keyPressed(int key) {
        if(scene != null) {
            scene.keyPressed(key);
        }
    }

    public void keyRelease(int key) {
        if(scene != null) {
            scene.keyRelease(key);
        }
    }

    public void keyRepeated(int key) {
        if(scene != null) {
            scene.keyRepeated(key);
        }
    }

    public void pointerPressed(int x, int y) {
        if(scene != null) scene.pointerPressed(x, y);
    }

    public void pointerReleased(int x, int y) {
        if(scene != null) scene.pointerReleased(x, y);
    }

    public void pointerDragged(int x, int y) {
        if(scene != null) scene.pointerDragged(x, y);
    }


    @Override
    public void onSelect(int index) {
        switch (index) {
            case 0:
                ChessRemnantsSelectScene scene = new ChessRemnantsSelectScene();
                setScene(scene);
                break;
            case 1:
                CalendarScene calendarScene = new CalendarScene();
                setScene(calendarScene);
                break;
            case 2:
            case 3:
            case 4:
                setScene(new ScoreScene());
            case 5:
                SettingScene settingScene = new SettingScene();
                setScene(settingScene);
                break;
            case 10:
                destroy();
                System.exit(0);
                break;

        }
    }


}
