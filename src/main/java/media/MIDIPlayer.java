package media;

import javax.sound.midi.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MIDIPlayer extends Player implements MetaEventListener{

    private long durationNano;

    private Sequencer sequencer;
    private Synthesizer synthesizer;
    private MidiChannel[] channels;


    @Override
    public void load(InputStream stream) {
        try {
            if (this.sequencer == null) {
                this.sequencer = MidiSystem.getSequencer(false);
                this.synthesizer = MidiSystem.getSynthesizer();
                this.sequencer.open();
                this.synthesizer.open();
                sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
                this.channels = this.synthesizer.getChannels();
                this.sequencer.addMetaEventListener(this);
            }
        } catch (MidiUnavailableException e) {
            if(sequencer != null) {
                sequencer.removeMetaEventListener(this);
                synthesizer.close();
            }
            if(synthesizer != null) {
                synthesizer.close();
            }
            e.printStackTrace();
        }
        try {
            this.sequencer.setSequence(stream);
            this.durationNano = this.sequencer.getMicrosecondLength() * 1000L;
        } catch (IOException | InvalidMidiDataException e) {
            this.synthesizer.close();
            this.sequencer.close();
            e.printStackTrace();
        }
    }


    @Override
    public void start() {
        if(sequencer != null) {
            if(sequencer.isOpen()) {
                sequencer.start();
            }
//            sequencer.setLoopCount(loopCount);
//            sequencer.start();
        }
    }

    @Override
    public void close() {
        if (sequencer != null) {
            sequencer.removeMetaEventListener(this);
            sequencer.close();
            sequencer = null;
        }
        synthesizer = null;
        channels = null;
    }

    @Override
    public void stop() {
        if (this.sequencer != null) {
            this.sequencer.stop();
        }
    }

    @Override
    public void setMediaPosition(long pos) {
        if (sequencer != null) {
            sequencer.setMicrosecondPosition(pos);
        }
    }

    @Override
    public void meta(MetaMessage meta) {

    }
}
