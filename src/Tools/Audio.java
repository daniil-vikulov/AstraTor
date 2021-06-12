package Tools;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio implements Runnable{
    private String track;
    private Clip clip = null;
    private double wt;
    private FloatControl volumeC = null;
    private boolean musicStarted = false;

    public Audio(String track, double wt) {
        this.track = track;
        this.wt = wt;
    }

    @Override
    public void run() {
        File f = new File(track);
        AudioInputStream tr;
        try {
            tr = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(tr);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            setVolume((float) wt);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(float x) {
        if (x<0) x = 0;
        if (x>1) x = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max-min)*x+min);
    }
}