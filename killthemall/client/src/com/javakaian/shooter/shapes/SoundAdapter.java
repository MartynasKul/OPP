package com.javakaian.shooter.shapes;

import de.ralleytn.simple.audio.AudioException;
import de.ralleytn.simple.audio.BufferedAudio;

public class SoundAdapter implements ISoundPlayer{

    public void play(String file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedAudio audio = new BufferedAudio(file);
                    audio.open();
                    audio.play();
                    
                    Thread.sleep(audio.getLength());
                    
                    audio.close();
                    audio = null;
                } catch (AudioException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
