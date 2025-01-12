
package model;

import java.awt.*;
import java.util.Random;
// mengakses viewmodel
import viewmodel.Constants;

import static viewmodel.Constants.gameOption.GAME_HEIGHT;
import static viewmodel.Constants.gameOption.GAME_SPEED;

/**
 *
 * @author Nurainun
 */
public class Obstacle extends GameObject{
    // properti obstacle
    private int ScoreValue;

    // inisialisasi library
    Random rand = new Random(); // fungsi random
    Image image;

    public Obstacle(float x, float y, int width, int height, int obsType) {
        // konstruktor
        super(x, y, width, height);
        this.ScoreValue = calculateScore(height, (int)y);
    }

    public void update() {
        // mengupdate posisi dan collisionBox
        updatePos();
        updateCollisionBox();
    }

    @Override
    public void render(Graphics g) {
        // mengoverride fungsi render di parent

        g.fillRect((int)x, (int)y, width, height); // membuat persegi panjang

        if (height == 10)
        {
            g.setColor(Color.decode("#703A10"));
            g.drawString(Integer.toString(getScoreValue()), (int) x + 15, 28);

        }else {
            // menambahkan image Obstacle Down
            image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/aseets/floor.png"));
            g.drawImage(image, (int)x-5, (int)y-4, width+8, height+5, null);

            //Score untuk setiap pilar
            g.setColor(Color.white);
            g.setColor(Color.decode("#3A1070"));
            g.drawString(Integer.toString(getScoreValue()), (int)x +18,GAME_HEIGHT - 50 );
        }
    }

    private void updatePos(){
        // mengupdate posisi
            x += GAME_SPEED;
    }

    public float getX(){
        // mengambil nilai x si obstacle
        return x;
    }

    private  int calculateScore(int height, int y){
        if (height == 10)
        {
            //jika tanah melayang
            return 1000/y +12;
        }
        //jika pilar bawah
        return 1000/height + 8;
    }

    public int getScoreValue() {
        return ScoreValue;
    }
}
