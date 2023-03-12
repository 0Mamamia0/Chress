package ui;

import io.github.humbleui.skija.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    private static byte[] readAllBytes(String name) throws IOException {
        try(InputStream in = TextureLoader.class.getResourceAsStream(name)) {
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
            byte[] data = readAllBytes(name);
            return Image.makeFromEncoded(data);
        } catch (IOException e) {
            return null;
        }
    }

}
