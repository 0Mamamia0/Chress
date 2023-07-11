package ui;

import io.github.humbleui.skija.Bitmap;
import io.github.humbleui.skija.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    private static byte[] readAllBytes(String name) throws IOException {
        try(InputStream in = ImageLoader.class.getResourceAsStream(name)) {
            if(in == null) throw new IOException("Res not found " + name);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int len;
            byte[] buf = new byte[2048];
            while ((len = in.read(buf, 0, buf.length)) > 0) {
                bos.write(buf, 0, len);
            }
            return bos.toByteArray();
        }
    }


    public static Image loadImage(String name) {
        try {
            byte[] data = readAllBytes("/images/" + name);
            return Image.makeFromEncoded(data);
        } catch (IOException e) {
            throw new RuntimeException("Image:loadImage(" + name+ ")", e);
        }
    }

    public static Sprite[] spilt(Image src, int w, int h) {
        if(src == null) return null;
        int width = src.getWidth();
        int height = src.getHeight();

        int col = width / w;
        int row = height / h;

        int size = col * row;
        int index = 0;
        Sprite[] result = new Sprite[size];
        Sprite sprite = null;

        for(int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                int sx = j * w;
                int sy = i * h;
                sprite = new Sprite(src, sx, sy, w, h);
                result[index] = sprite;
                index++;
            }
        }
        return result;
    }






}
