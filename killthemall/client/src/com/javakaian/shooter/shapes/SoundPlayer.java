package com.javakaian.shooter.shapes;

public class SoundPlayer {

    private ISoundPlayer player;

    public ISoundPlayer getPlayer(){
        return player;
    }

    public void setPlayer(ISoundPlayer player){
        this.player = player;
    }
    
    public void playSound(String file){

        if (player == null){
            return;
        }

        player.play(file);
    }
}
