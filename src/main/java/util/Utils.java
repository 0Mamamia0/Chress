package util;

import eleeye.Position;

import java.io.InputStream;

public class Utils {

    public static void loadCanJv(Position pos, int year, int month, int day) {
        pos.clearBoard();
        int[] canju_f;
        int[] canju_b;
        if (month >= 10) {
            canju_f = Utils.loadCanJv_f_form_res("/canju/" + year + month + day + "f");
            canju_b = Utils.loadCanJv_b_form_res("/canju/" + year + month + day + "b" );
        } else {
            canju_f = Utils.loadCanJv_f_form_res("/canju/"+ year + "0" + month + day + "f");
            canju_b = Utils.loadCanJv_b_form_res("/canju/" + year + "0" + month + day+ "b");
        }
        for (int i = 0; i < 256; i++) {
            int i6 = canju_f[i];
            if (i6 > 0 ) {
                pos.addPiece(i, i6 - 1);
            }

        }
        pos.setIrrev();

    }


    private static String readFully(String str) {
        String result = "";

        byte[] buffer = new byte[1024];
        try {
            InputStream is = Utils.class.getResourceAsStream(str);
            is.read(buffer);
            is.close();
            result = new String(buffer).trim();
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return result;
    }


    public static int[] loadCanJv_b_form_res(String name) {
        return loadCanJv_b(readFully(name));
    }
    public static int[] loadCanJv_f_form_res(String name) {
        return loadCanJv_f(readFully(name));
    }


    public static int[] loadCanJv_b(String str) {
        int[] data = new int[str.length() / 2];
        int i = 0;
        for (int j = 0; j <= str.length(); j++) {
            if(j == i + 2) {
                try {
                    int num = Integer.valueOf(str.substring(i, j), 10).intValue();
                    data[(j / 2) - 1] = ( num % 10) + (((num / 10) + 3) << 4) + 2;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i = j;
            }
        }
        return data;
    }


    public static int[] loadCanJv_f(String str) {
        int[] data = new int[90];
        int i = 0;
        int cursor = 0;
        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);
            if (c == 44) {
                try {
                    data[cursor] = Integer.parseInt(str.substring(i, index).trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i = index + 1;
                cursor++;
            }
        }
        int[] position = new int[256];
        for (int index = 0; index < position.length; index++) {
            if ((index % 16) - 3 < 0 || (index % 16) - 3 >= 9 || index / 16 < 3 || index / 16 >= 13) {
                position[index] = 0;
            } else {
                position[index] = data[((index % 16) - 3) + (((index / 16) - 3) * 9)];
            }
        }
        return position;
    }

}
