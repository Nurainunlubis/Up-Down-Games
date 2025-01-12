/*
 * Filename     : Menu.java
 * Programmer   : Nurainun
 * Date         : 2024-05-25
 * Email        : Nurainunlubis24@gmail.com
 * Website      : https://Nurainunlubis.github.io/
 * Deskripsi    : kelas Menu untuk membuat tampilan menu
 */

package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.TableScore;
import model.TableScore;
import viewmodel.Game;
import viewmodel.Sound;

public class Menu extends javax.swing.JFrame {
    // properti
    public Game game; // objek game
    private TableScore tScore; // data tabel
    public Clip audio; // backsound
    private String username; // username

    public Menu() {
        // konstruktor
        initComponents(); // load component
        this.username = ""; // inisialisasi username
        try {
            // membuat tabel baru
            this.tScore = new TableScore();
            // diisi dgn data dari database
            TableExperiences.setModel(tScore.setTable());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // memutar suara di menu awal
        Sound bgm = new Sound();
        audio = bgm.playSound(this.audio, "01_Game_Start.wav");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        lblMenuTitle = new javax.swing.JLabel();
        lblCopy = new javax.swing.JLabel();
        btnQuit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableExperiences = new javax.swing.JTable();
        lblNewUsername = new javax.swing.JLabel();
        TFUsername = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Up Down | Menu");
        setBackground(new java.awt.Color(234, 121, 197));
        setName("Main Menu");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(234, 121, 197));


        btnPlay.setBackground(new java.awt.Color(217, 217, 130));
        btnPlay.setFont(new java.awt.Font("Ubuntu", 1, 14));
        btnPlay.setForeground(new java.awt.Color(255, 255, 255));
        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        lblMenuTitle.setFont(new java.awt.Font("Bunny Funny", 1, 36));
        lblMenuTitle.setForeground(java.awt.Color.white);
        lblMenuTitle.setText("UP DOWN");
        lblMenuTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblMenuTitle.setAlignmentY(0.0F);
        lblMenuTitle.setIconTextGap(0);

        lblCopy.setFont(new java.awt.Font("Ubuntu", 0, 12));
        lblCopy.setForeground(new java.awt.Color(236, 245, 255));
        lblCopy.setText("2202046 - Nur Ainun");

        btnQuit.setBackground(new java.awt.Color(255, 102, 102));
        btnQuit.setFont(new java.awt.Font("Ubuntu", 1, 14));
        btnQuit.setForeground(new java.awt.Color(255, 255, 255));
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        TableExperiences.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Username", "Score", "Up", "Down"
                }
        ));
        TableExperiences.setPreferredSize(new java.awt.Dimension(225, 200));
        TableExperiences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableExperiencesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableExperiences);

        lblNewUsername.setFont(new java.awt.Font("Ubuntu", 0, 12));
        lblNewUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblNewUsername.setText("Masukan Username atau Pilih Dalam Tabel");

        TFUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFUsername.setToolTipText("Username...");
        TFUsername.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCopy)
                                .addGap(16, 16, 16))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(36, 36, 36)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(TFUsername)
                                                                        .addComponent(lblMenuTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(98, 98, 98)
                                                .addComponent(lblNewUsername)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(lblMenuTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lblNewUsername)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TFUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lblCopy)
                                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        System.exit(0);
    }

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.username.isEmpty() && !TFUsername.getText().isEmpty()) {
            this.username = TFUsername.getText();
        }
        if (TFUsername.getText().isEmpty() && this.username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username tidak boleh kosong!\nPilih user atau masukkan username baru.");
        } else {
            Sound bgm = new Sound();
            bgm.stopSound(this.audio);

            game = new Game();
            Window gw = new Window(game);
            game.setUsername(this.username);
            try {
                TableScore temp = new TableScore();
                temp.getDataTscore(this.username);
                int c = 0, tup = 0, tdown = 0, tscore = 0;
                while (temp.getResult().next()) {
                    tup = Integer.parseInt(temp.getResult().getString(3));
                    tdown = Integer.parseInt(temp.getResult().getString(4));
                    tscore = Integer.parseInt(temp.getResult().getString(5));
                    c++;
                }
                if (c == 0) {
                    game.setScore(0, 0, 0);
                } else {
                    game.setScore(tup, tdown, tscore);
                }
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.StartGame(gw);
            this.setVisible(false);
            this.dispose();
        }
    }

    private void TableExperiencesMouseClicked(java.awt.event.MouseEvent evt) {
        int row = TableExperiences.getSelectedRow();
        this.username = TableExperiences.getModel().getValueAt(row, 0).toString();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }

    private javax.swing.JTextField TFUsername;
    private javax.swing.JTable TableExperiences;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnQuit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCopy;
    private javax.swing.JLabel lblMenuTitle;
    private javax.swing.JLabel lblNewUsername;
}
