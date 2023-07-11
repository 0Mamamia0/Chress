package media;

import java.util.HashMap;

public class Sound {


    private static boolean mute;
    private static int volumeLevel = 0;

    private static HashMap<String, Player> player;


    public static void play(String name) {
        if(mute) return;
        if(player == null) {
            player = new HashMap<>();
        }
        Player p = player.computeIfAbsent(name, Player::create);
        p.setMediaPosition(0);
        p.start();
    }


    public static void stop(String name) {
        if(player == null) return;
        Player p = player.get(name);
        if(p != null) {
            p.stop();
        }
    }
    public static void close(String name) {
        if(player == null) return;
        Player p = player.get(name);
        if(p != null) {
            p.close();
        }
    }


    public static void closeAll() {
        if(player == null) return;
        for (Player p : player.values()) {
            p.close();
        }
    }

    public static void setMute(boolean mute) {
        Sound.mute = mute;
    }


    public static void setVolumeLevel(int level) {
        if(level < 0) level = 0;
        if(level > 5) level = 5;
        Sound.volumeLevel = level;
    }


    public static int getVolumeLevel() {
        return volumeLevel;
    }

    public static void setVolume(int volume) {

    }
}
