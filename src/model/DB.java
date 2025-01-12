/*
 * Filename     : DB.java
 * Programmer   : Nurainun
 * Date         : 2024-06-10
 * Email        : Nurainunlubis@gmail.com
 * Website      : https://Nurainunlubis.github.io/
 * Deskripsi    : kelas DB untuk mengakses Basis Data
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sekar
 */
public class DB {
    // konfigurasi koneksi
    private String connAddress = "jdbc:mysql://localhost/db_updown_tmd?user=root&password=";
    private Statement stmt = null; // koneksi query
    private Connection conn = null; // koneksi mysql dan basis data
    private ResultSet rs = null; // hasil query

    public DB() throws Exception, SQLException {
        /*
         * Method DB
         * Konstruktor melakukan koneksi ke mysql dan basis data
         * Menerima masukan berupa string alamat koneksi ke mysql dan basis data
         */
        try {
            // membuat driver mysql
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // membuat koneksi ke mysql dan basis data
            conn = DriverManager.getConnection(connAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException e) {
            // error jika koneksi gagal
            throw e;
        }
    }

    public void createQuery(String Query) throws Exception, SQLException {
        /*
         * Method createQuery
         * Mengeksekusi query tanpa mengubah isi data
         * Menerima masukan berupa string query
         */
        try {
            stmt = conn.createStatement();
            // eksekusi query
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)) {
                // ambil hasil query
                rs = stmt.getResultSet();
            }
        } catch (SQLException e) {
            // error jika query gagal di eksekusi
            throw e;
        }
    }

    public void createUpdate(String Query)throws Exception, SQLException {
        /*
         * Method createUpdate
         * Mengeksekusi query yang mengubah isi data (update, insert, delete)
         * Menerima masukan berupa string query
         */
        try {
            stmt = conn.createStatement();
            // eksekusi query
            int res = stmt.executeUpdate(Query);
        } catch (SQLException e) {
            // eksekusi jika query gagal di eksekusi
            throw e;
        }
    }

    public ResultSet getResult() throws Exception {
        /*
         * Method getResult
         * Memberikan hasil query
         */
        ResultSet Temp = null;
        try {
            return rs;
        } catch (Exception e) {
            // eksepsi jika hasil tidak dapat dikembalikan
            return Temp;
        }
    }

    public void closeResult() throws Exception, SQLException {
        /*
         * Method closeResult
         * Menutup hubungan dari eksekusi query
         */
        if(rs != null) {
            try {
                rs.close();
            }
            catch(SQLException e){
                rs = null;
                throw e;
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                stmt = null;
                throw e;
            }
        }
    }

    public void closeConnection() throws Exception, SQLException {
        /*
         * Method closeConnection
         * Menutup hubungan dengan mysql dan basis data
         */
        if(conn != null) {
            try {
                conn.close();
            }
            catch(SQLException e){
                conn = null;
            }
        }
    }
}
