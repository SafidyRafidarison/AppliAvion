package token;

import java.sql.*;

public class Connexion{

	public Connexion(){}
	public static Connection getCo(){
		Connection c=null;
		try{
 			Class.forName("org.postgresql.Driver");
 			c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/gp15","postgres","1234");
 			// System.out.println("mety");
 		}
 		catch(Exception e){
 			System.out.println("problem");
 			e.printStackTrace();
 		}
		return c;
	}
}