package util;

public class Score {

    private static int level = 0;
    private static int score = 0;
    private static int level_max = 0;

    public static void setScore(int score) {
        if (score < 100) {
            level_max = 100;
            level = 0;
        } else if (score < 300) {
            level = 1;
            level_max = 300;
        } else if (score < 600) {
            level = 2;
            level_max = 600;
        } else if (score < 950) {
            level = 3;
            level_max = 950;
        } else if (score < 1350) {
            level = 4;
            level_max = 1350;
        } else if (score < 1800) {
            level = 5;
            level_max = 1800;
        } else if (score < 2300) {
            level = 6;
            level_max = 2300;
        } else if (score < 2800) {
            level = 7;
            level_max = 2800;
        } else if (score < 3400) {
            level = 8;
            level_max = 3400;
        } else if (score < 4000) {
            level = 9;
            level_max = 4000;
        } else if (score < 4700) {
            level = 10;
            level_max = 4700;
        } else if (score < 5500) {
            level = 11;
            level_max = 5500;
        } else if (score < 7000) {
            level = 12;
            level_max = 7000;
        } else {
            level = 13;
            level_max = 9999;
        }
        Score.score = Math.max(0, Math.min(score, 9999));

    }


    public static int getLevel() {
        return level;
    }

    public static int getScore() {
        return score;
    }

    public static int getMaxInLevel() {
        return level_max;
    }
}
