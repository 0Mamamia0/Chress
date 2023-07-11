package media;

import java.io.IOException;
import java.io.InputStream;

public class Player {



    public static Player create(String name) {
        Player player = null;
        if(name.endsWith(".wav")) {
            player = new WavPlayer();
            player.load(name);
        } else if(name.endsWith(".mid")) {
            player = new MIDIPlayer();
            player.load(name);
        }
        return player;
    }


    public void load(String name) {
        try(InputStream stream = Player.class.getClassLoader().getResourceAsStream("sound/" +  name)) {
            load(stream);
        } catch (IOException ignored){}
    }


    public void load(InputStream stream) {

    }


    public void setMediaPosition(long pos) {

    }



    public void start() {

    }


    public void stop() {

    }


    public void close() {

    }


}
