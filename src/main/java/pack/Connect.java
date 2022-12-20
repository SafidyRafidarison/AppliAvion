package pack;

import java.sql.*;
import java.util.HashMap;

public class Connect {
    static String dbname = "uieluscg";
    static String user = "uieluscg";
    static String password = "O8A3SyG1_y0TNykr5k4G5lFajxjtfS_C";
    HashMap connection;
    public static Connection connectDB() throws Exception {
        Connection retour = null;
        try {
            Class.forName("org.postgresql.Driver");
            retour = DriverManager.getConnection("jdbc:postgresql://mel.db.elephantsql.com:5432/" + dbname, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur de connexion ");
        } finally {
            return retour;
        }
    }
}
