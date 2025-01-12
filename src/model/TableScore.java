package model;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class TableScore extends DB {
    private String tableName; // nama tabel

    public TableScore() throws Exception, SQLException {
        // constructor
        super();
        this.tableName = "tscore";
    }

    public void getTscore() {
        // mengeksekusi query untuk mengambil semua data pada tabel pengguna
        try {
            String query = "SELECT * FROM " + this.tableName;
            createQuery(query);
        } catch (Exception e) {
            // menampilkan error
            System.out.println(e.toString());
        }
    }

    public void getDataTscore(String username) {
        // mengeksekusi query untuk mengambil 1 record data berdasarkan username
        try {
            String query = "SELECT * FROM " + this.tableName + " WHERE username='" + username + "'";
            createQuery(query);
        } catch (Exception e) {
            // menampilkan error
            System.err.println(e.toString());
        }
    }

    public void insertData(String username, int score, int up, int down) {
        // Cek apakah harus ada update
        boolean update = false;
        try {
            TableScore temp = new TableScore();
            temp.getDataTscore(username);
            // cek apakah username sudah ada dalam database
            if (temp.getResult().next()) {
                update = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Untuk insert
        if (!update) {
            try {
                String query = "INSERT INTO " + this.tableName + " (username, score, up, down) VALUES('" + username + "', " + score + ", " + up + ", " + down + ")";
                createUpdate(query);
            } catch (Exception e) {
                // menampilkan error
                System.out.println(e.toString());
            }
        }
        // Untuk update
        else {
            try {
                String query = "UPDATE " + this.tableName + " SET score=" + score + ", up=" + up + ", down=" + down + " WHERE username='" + username + "'";
                createUpdate(query);
            } catch (Exception e) {
                // menampilkan error
                System.out.println(e.getMessage());
            }
        }
    }

    // Membuat datatable
    public DefaultTableModel setTable() {
        DefaultTableModel dataTable = null;
        try {
            // membuat header tabel
            Object[] column = {"Username", "Score", "Up", "Down"};
            dataTable = new DefaultTableModel(null, column);

            // query data untuk ditampilkan di tabel
            String query = "SELECT * FROM " + this.tableName + " ORDER BY score DESC";
            this.createQuery(query);

            // mengambil data per baris
            while (this.getResult().next()) {
                Object[] row = new Object[4];
                // mengambil per kolom
                row[0] = this.getResult().getString("username");
                row[1] = this.getResult().getInt("score");
                row[2] = this.getResult().getInt("up");
                row[3] = this.getResult().getInt("down");
                dataTable.addRow(row);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // mengembalikan data yang sudah diambil
        return dataTable;
    }
}
