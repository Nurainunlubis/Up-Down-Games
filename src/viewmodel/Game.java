/*
 * Filename     : Game.java
 * Programmer   : Nurainun
 * Date         : 2024-05-25
 * Email        : Nurainunlubis24@gmail.com
 * Website      : https://nurainunlubis.github.io/
 * Deskripsi    : kelas Game untuk membuat game baru
 */

package viewmodel;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.Random;
import javax.sound.sampled.Clip;
// mengakses model
import model.Player;
import model.Score;
import model.TableScore;
// mengakses konstanta
import static viewmodel.Constants.gameOption.GAME_HEIGHT;
import static viewmodel.Constants.gameOption.GAME_WIDTH;
// mengakses view
import view.Window;
import view.Menu;

/**
 *
 * @author Nurainun
 */
public class Game extends JPanel implements Runnable {
    // mengimplementasikan Runnable java untuk membuat thread

    // properti thread
    private Thread gameThread;
    private boolean running = false; // deteksi game berjalan

    private Window window; // window game
    private Clip audio; // backsound

    // properti objek dlm game
    private final Player player; // player
    private final ObstacleHandler obs_handler; // obstacle
    private String username; // username
    private  int score;  // Total Score
    private int UpScore; // skor Up
    private int DownSCore; // skor Down

    private Image backgroundImage;

    public enum STATE{
        Game,
        GameOver
    }

    public Game(){
        // konstruktor

        backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/aseets/background.jpg"));
        //membuat player dgn posisi di tiang pertama
        this.player =  new Player(GAME_WIDTH/2,  150);

        // membuat obstacle
        this.obs_handler = new ObstacleHandler();

        // membuat backsound
        Sound bgm = new Sound();
        audio = bgm.playSound(this.audio, "02_Main_BGM.wav");
    }

    // mengeset STATE game
    public STATE gameState = STATE.Game;

    public synchronized void StartGame(Window gw){
        // memulai menjalankan game
        this.window = gw; // buat window
        gameThread = new Thread(this); // buat thread baru
        gameThread.start(); // thread dijalankan
        running = true; // set running
    }

    @Override
    public void paint(Graphics g){
        // membuat Component
        super.paint(g); // method parent
       // gambar background
        if (backgroundImage != null){
            g.drawImage(backgroundImage, 0,0, GAME_WIDTH, GAME_HEIGHT, null);
        }else {
            System.out.println("Background image is null");
        }

        player.render(g);
        obs_handler.renderObstacle(g);
    }


    @Override
    public void run() {
        // meng override method run dari parent Runnable
        while(true){
            // selama true (game loop)
            try {
                updateGame(); // update objek game
                repaint(); // membuat ulang Component (update paint())
                Thread.sleep(1000L/60L); // pause thread
                this.score = player.getTotalScore(); // mengambil total score
                this.UpScore = player.getUpScore(); // mengambil skor Up
                this.DownSCore = player.getDownScore(); // mengambil skor down
                if(this.player.getBoundLeft().x < 0) {
                    // jika posisi tabrakan player > 1000
                    // maka player tergerus zaman (keluar frame dari kanan)
                    this.gameState = STATE.GameOver;
                }
                if(gameState == STATE.GameOver) {
                    // jika state saat ini GameOver
                    Sound bgm = new Sound();
                    bgm.stopSound(this.audio); // Stop Sound
                    saveScore(); // Menyimpan Score
                    close(); // Menutup window
                    Menu.main(new String[0]); // menampilkan menu
                    stopGame(); // stop game
                }
            } catch (InterruptedException ex) {
                // log error
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateGame(){
        // update objek dalam game
        obs_handler.addObstacle(); // menambah obstacle
        obs_handler.updateObstacle(); // mengupdate kondisi obstacle
        player.update(obs_handler.getObstacles()); // mengupdate kondisi player
    }

    public synchronized void stopGame() {
        // menghentikan game
        try{
            gameThread.join(); // menghentikan thread
            running = false; // set tidak berjalan
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void close() {
        // menutup window
        window.CloseWindow();
    }

    public Player getPlayer(){
        // mengambil player
        return this.player;
    }

    public void setUsername(String username) {
        // mengeset username game
        this.username = username;
    }

    public void setScore(int score, int upScore, int downSCore) {
        // mengeset skor player di awal game
        this.player.setTotalScore(score);
        this.player.setUpScore(upScore);
        this.player.setDownScore(downSCore);
    }

    public void saveScore() {
        // menyimpan skor saat game over
        // mainkan backsound game over
        Sound gobgm = new Sound();
        audio = gobgm.playSound(this.audio, "03_Game_Over.wav");

        try {
            // menambah atau update data
            TableScore tScore = new TableScore();
            tScore.insertData(this.username, this.score, this.UpScore, this.DownSCore);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // menampilan panel game over
        JOptionPane.showMessageDialog(null, "Username : " + this.username + "\nScore : " + this.score + "\nUp : " + this.UpScore+ "\nDown : " + this.DownSCore, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        // berhentikan sound saat panel game over hilang
        gobgm.stopSound(this.audio);
    }

}
