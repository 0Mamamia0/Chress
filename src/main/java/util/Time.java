package util;

public class Time {
    public static long now() {
        return System.currentTimeMillis();
    }

    public static int minute(long i) {
        if(i < 0) return 0;
        return (int)(i / 1000 / 60) % 10;
    }

    public static int ten_second(long i) {
        if(i < 0) return 0;
        return (int)(i / 1000) % 60 / 10;
    }

    public static int second(long i) {
        if (i < 0) return 0;
        return (int)(i / 1000) % 10;
    }


}
