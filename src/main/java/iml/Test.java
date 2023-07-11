package iml;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        SpriteLoaderImpl impl = new SpriteLoaderImpl();
        Unkonw m = new Unkonw(impl.loadSprite("/game.anu"), 0, 0);
        ColofulGame game = m.getColofulGame();
        short[] position = game.sprite.position;

        System.out.println(position.length);
        System.out.println(position.length/4);
        System.out.println(Arrays.toString(position));


        for (int i = 0; i < position.length/4; i++) {
            //System.out.println(position[i * 4 + 3]);
            int index = position[i * 4];
            int x = position[i * 4 + 1];
            int y = position[i * 4 + 2];
            if(position[i * 4] + 3 == 0) {
                throw new RuntimeException();
            };
            if(index == 0) {
                System.out.println( index + "  pos [ " + x + ", " + y + "]");
            }
        }

//        while (!m.finished()) {
//            System.out.println("paint");
//            m.paint();
//        }

    }
}
