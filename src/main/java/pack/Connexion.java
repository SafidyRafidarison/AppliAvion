/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author Meilnerga
 */
public class Connexion {
    static String dbname = "flotte";
    static String user = "s5";
    static String password = "s5";
    // static String user = "itu";
    // static String password = "0";
    HashMap connection;
    public static Connection connectDB() throws Exception {
        Connection retour = null;
        try {
            Class.forName("org.postgresql.Driver");
            retour = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur de connexion ");
        } finally {
            return retour;
        }
    }
}
