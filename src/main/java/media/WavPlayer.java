package media;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WavPlayer extends Player implements LineListener{

    private Clip clip;

    @Override
    public void load(InputStream stream) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(stream);
            AudioInputStream decodedStream = null;
            AudioFormat format = audioStream.getFormat();
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                AudioFormat decodedFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        16,
                        format.getChannels(),
                        format.getChannels() * 2,
                        format.getSampleRate(),
                        false
                );
                decodedStream = AudioSystem.getAudioInputStream(decodedFormat, audioStream);
                int frameLength = (int) decodedStream.getFrameLength();
                int frameSize = decodedFormat.getFrameSize();
                DataLine.Info info = new DataLine.Info(Clip.class, decodedFormat, frameLength * frameSize);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(decodedStream);
            } else {
                DataLine.Info info2 = new DataLine.Info(Clip.class, format, AudioSystem.NOT_SPECIFIED);
                clip = (Clip) AudioSystem.getLine(info2);
                clip.open(audioStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    @Override
    public void start() {
        if (clip != null) {
            clip.addLineListener(this);
            clip.start();
        }
    }


    @Override
    public void setMediaPosition(long pos) {
        if(clip != null) {
            clip.setMicrosecondPosition(pos);
        }
    }

    @Override
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }


    @Override
    public void close() {
        if (clip != null) {
            clip.removeLineListener(this);
            clip.close();
        }
    }

    @Override
    public void update(LineEvent event) {

    }
}
