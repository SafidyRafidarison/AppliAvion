/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package token;
import java.sql.*;
/**
 *
 * @author iitu
 */
public class Employer {
    int idEmployer;
    String nom;
    String pwd;

    public Employer(int idEmployer, String nom, String pwd) {
        this.idEmployer = idEmployer;
        this.nom = nom;
        this.pwd = pwd;
    }
    public Employer(){}

    public int getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public Employer getEmployerById(Connection con){
        Employer retour = new Employer();
        PreparedStatement pstm = null;
        ResultSet res = null;
        try{
            pstm = con.prepareStatement("SELECT * FROM Employer WHERE idEmployer = ?");
            pstm.setInt(1, this.getIdEmployer());
            res = pstm.executeQuery();
//            System.out.println(pstm.toString());D
            while(res.next()){
                retour = new Employer(
                    res.getInt("idemployer"),
                    res.getString("nom"),
                    res.getString("pwd")
                );
            }
            return retour;
        }
        catch(Exception e){
            System.out.println("Erreur");
        }
        return retour;
    }
    
    public Employer login (Connection con){
        Employer retour = new Employer();
        PreparedStatement pstm = null;
        ResultSet res = null;
        try{
            pstm = con.prepareStatement("SELECT * FROM Employer WHERE nom = ? and pwd = ?");
            pstm.setString(1, this.getNom());
            pstm.setString(2, this.getPwd());
            res = pstm.executeQuery();
//            System.out.println(pstm.toString());D
            while(res.next()){
                retour = new Employer(
                    res.getInt("idemployer"),
                    res.getString("nom"),
                    res.getString("pwd")
                );
            }
            return retour;
        }
        catch(Exception e){
            System.out.println("Erreur");
        }
        return retour;
    }
}
