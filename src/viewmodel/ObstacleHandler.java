
package viewmodel;

import java.util.Random;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
// mengakses konstanta
import static viewmodel.Constants.gameOption.GAME_HEIGHT;
import static viewmodel.Constants.gameOption.GAME_WIDTH;
// mengakses model
import model.Obstacle;


public class ObstacleHandler {
    private final int MIN_Y = GAME_HEIGHT / 2; // posisi Y Min
    private final int MAX_Y = GAME_HEIGHT; // posisi y Max (batasan bawah)
    private final int MAX_X = GAME_WIDTH; // posisi batasan kanan
    private final Random rand = new Random(); // inisialisasi library random

    private final int MAX_OBSTACLE = 15; // jumlah maks obstacle dlm 1 frame
    private final int GAP = 100; // lebar gap minimum antar obstacle
    private final int OBS_WIDTH = 50; // lebar obstacle
    private int obstacleNumber = 0; // jumlah obstacle
    private final ArrayList<Obstacle> obstacles = new ArrayList<>(); // list obstacle

    public ObstacleHandler() {
        // Konstruktor kosong
    }

    public synchronized void updateObstacle() {
        // mengupdate kondisi obstacle
        Iterator<Obstacle> itr = obstacles.iterator(); // iterator untuk setiap obstacle
        while (itr.hasNext()) {
            // selama obstacle ada
            Obstacle ob = itr.next();
            if (ob.getX() < -50) {
                // jika posisi x obstacle melebihi batas x frame
                itr.remove(); // hilangkan obstacle
                obstacleNumber--; // decrement jumlah obstacle
            } else {
                // jika tidak, update posisi obstacle
                ob.update();
            }
        }
    }

    public void renderObstacle(Graphics g) {
        // merender obstacle
        for (Obstacle ob : obstacles) {
            // untuk setiap obstacle
            ob.render(g); // gambar objeknya
        }
    }

    public synchronized void addObstacle() {
        // menambah jumlah obstacle
        if (obstacleNumber < MAX_OBSTACLE) {
            // jika jumlah obstacle dalam frame
            // masih kurang dari batas maks obstacle

            float x = (MAX_X / 2) - OBS_WIDTH; // posisi x di tengah layar
            float y = rand.nextInt((MAX_Y - 100) - (MIN_Y + 50)) + (MIN_Y + 50); // posisi y sesuai batas
            if (obstacleNumber >= 1) {
                // jika jumlah obstacle lebih dari 1
                // maka ambil obstacle, simpan data x obstacle
                x = obstacles.get(obstacles.size() - 1).getX() + GAP;
            }
            // buat obstacle baru
            Obstacle obstacle = new Obstacle(x, y, OBS_WIDTH, (int) (MAX_Y - y), 0);
            obstacles.add(obstacle); // tambahkan ke list
            obstacleNumber++; // increment jumlah obstacle

            // obstacle atas
            float xUp = obstacles.get(obstacles.size() - 1).getX() + GAP;
            int minY = (int) (MIN_Y - 50);
            float yUp = rand.nextInt(minY - 55) + 55;
            int heightUp = 10; // Ukuran tinggi melayang
            Obstacle obstacleUp = new Obstacle(xUp, yUp, OBS_WIDTH, heightUp, 1);
            
            obstacles.add(obstacleUp);
            obstacleNumber++;
        }
    }

    public synchronized ArrayList<Obstacle> getObstacles() {
        // mengambil obstacle
        return obstacles;
    }
}
