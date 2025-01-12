/*
 * Filename     : Player.java
 * Programmer   : Nuarinun
 * Date         : 2024-05-25
 * Email        : nurainunlubis24@gmail.com
 * Website      : https://nurainunlubis.github.io/
 * Deskripsi    : kelas Player yaitu pemain
 */

package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
// mengakses konstanta
import static viewmodel.Constants.gameOption.*;

public class Player extends GameObject{
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean inAir = true;

    private float playerSpeed = 5.0f;
    private float jumpStrength = 15.0f; 
    private float airSpeed = 0;
    private float gravity = 0.4f;
    private float xSpeed = 0;

    private int TotalScore = 0;
    private int UpScore = 0;
    private int DownScore = 0;
    private int count = 0;
    private int tempY = 0;
    private Image posR;
    private Image posL;

    private boolean facingR;

    public Player(int x, int y) {
        // konstruktor, set properti parent
        super(x, y, 50, 50);
        posR = new ImageIcon(getClass().getResource("/aseets/PR.png")).getImage();
        posL = new ImageIcon(getClass().getResource("/aseets/PL.png")).getImage();

    }

    public void update(ArrayList<Obstacle> ob){
        // mengupdate posisi obstable
        updatePos(ob);
        updateCollisionBox();
    }

    @Override
    public void render(Graphics g){
        // mengoverride method parent

        if (facingR) {
            g.drawImage(posR, (int) x, (int) y, width, height, null);
        }else {
            g.drawImage(posL, (int) x, (int) y, width, height, null);
        }

        // score, up score dan down score
        g.setFont(new java.awt.Font("Segoe UI", 1, 13));
        g.setColor(Color.WHITE);
        g.fillRoundRect(15, 185, 100, 25, 15, 15);
        g.fillRoundRect(15, 215, 100, 25, 15, 15);
        g.fillRoundRect(15, 245, 100, 25, 15,15);
        g.setColor(Color.decode("#3A1070"));
        g.drawString("Score : " + Integer.toString(this.TotalScore), 20, 200);
        g.drawString("Up : " + Integer.toString(this.UpScore), 20, 230);
        g.drawString("Down : " + Integer.toString(this.DownScore), 20, 260);
    }

    public void updatePos(ArrayList<Obstacle> AOb){
        // mengupdate kondisi obstacle dan player
        if(left && right || !left && !right) {
            // jika klik kanan dan kiri atau tidak klik kanan dan kiri
            // kecepatan player biasa (mengikuti game)
            xSpeed = GAME_SPEED;
        } else if(left) {
            // jika klik kiri
            // kecepatan player berkurang
            xSpeed -= playerSpeed;
            facingR = false;
        } else if(right) {
            // jika klik kanan
            // kecepatan player bertambah
            xSpeed += playerSpeed + 1;
            facingR = true;
        }

        if(xSpeed > 3) {
            // kecepatan maksimal 3
            xSpeed = 3;
        } else if(xSpeed < -6) {
            // kecepatan minimal -6
            xSpeed = -6;
        }

        // lompat
        if(up && !inAir){
            inAir = true;
            airSpeed -= jumpStrength;
        }

        // di Obstacle Bawah
        if(!inAir && !isOnFloor(AOb)){
            inAir = true;
        }

        // di udara
        if(inAir){
            airSpeed += gravity;
        }

        for(Obstacle ob : AOb){
            // untuk setiap obstacle
            if(getBoundBottom().intersects(ob.getCollisionBox())){
                // jika bound bawah player beririsan dengan bound collisionBox
                inAir = false; // berarti tidak di udara
                airSpeed = 0; // kecepatan udara 0
                y = ob.getCollisionBox().y - height; // y tempat collision

                if(UpScore == 0 && count == 0) {
                    // jika game baru dimulai
                    tempY = (int) y;
                    count++;
                }

                if(tempY != y) {
                    // jika y player bertabrakan sebelumnya
                    // tidak sama dengan y sekarang
                    if(y > 300) {
                        // jika y 300 berarti collision dgn lantai
                        UpScore++;
                        TotalScore += ob.getScoreValue();
                    }
                    if(y < 300) {
                        // jika y < 300 berarti collision dgn pilar
                        DownScore++;
                        TotalScore+= ob.getScoreValue();
                    }
                    tempY = (int) y;
                }
            }

            // jika player nabrak
            // kembalikan ke speed normal
            if(getBoundRight().intersects(ob.getCollisionBox())){
                // jika nabrak kanan
                xSpeed = GAME_SPEED;
                x = ob.getCollisionBox().x - width - 1;
            }
            if(getBoundLeft().intersects(ob.getCollisionBox())){
                // jika nabrak kiri
                xSpeed = GAME_SPEED;
                x = ob.getCollisionBox().x + ob.getCollisionBox().width + 1;
            }
            if(getBoundRight().x > GAME_WIDTH){
                // jika nabrak atas
               x = GAME_WIDTH - width -1;
            }
        }
        x += xSpeed;
        y += airSpeed;
    }

    public boolean isOnFloor(ArrayList<Obstacle> AOb){
        // method mengecek apakah di lantai
        for(Obstacle ob : AOb){
            // jika batas bawah player beririsan dgn collision box
            if(getBoundBottom().intersects(ob.getCollisionBox())) return true;
        }
        return false;
    }

    public Rectangle getBoundBottom(){
        // membuat batas bawah
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2);
    }

    public Rectangle getBoundTop(){
        // membuat batas atas
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y), width/2, height/2);
    }

    public Rectangle getBoundRight(){
        // membuat batas kanan
        return new Rectangle((int) x+width-5, (int)y + 5, 5, height-10);
    }

    public Rectangle getBoundLeft(){
        // membuat batas kiri
        return new Rectangle((int) x, (int)y + 5, 5, height-10);
    }

    public void setLeft(boolean left) {
        // set player ke kiri
        this.left = left;
    }

    public void setUp(boolean up) {
        // set player ke atas
        this.up = up;
    }

    public void setRight(boolean right) {
        // set player ke kanan
        this.right = right;
    }

    public void setDown(boolean down) {
        // set player ke bawah
        this.down = down;
    }

    public int getUpScore() {
        // mendapatkan skor upScore
        return this.UpScore;
    }

    public int getDownScore() {
        // mendapatkan skor downScore
        return this.DownScore;
    }

    public int getTotalScore() {
        // mendapatkan total score
        return this.TotalScore;
    }

    public void setTotalScore(int totalScore) {
        this.TotalScore = totalScore;
    }

    public void setUpScore(int UpScore) {
        this.UpScore = UpScore;
    }

    public void setDownScore(int DownScore)
    {
        this.DownScore = DownScore;
    }
}
