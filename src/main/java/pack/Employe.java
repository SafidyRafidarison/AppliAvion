/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class Employe {

    int idEmploye;
    String nom;
    String pwd;

  

    public Employe() {
    }

    public Employe(int idEmploye, String nom, String pwd) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.pwd = pwd;
    }
    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    // public void insert(Connection con) throws Exception
    // {
    //     String sql="insert into employer (nom,pwd) values('"+this.nom+"','"+this.pwd+"')"; 
    //    try {
    //     Statement stmt=con.createStatement();
    //     stmt.execute(sql);
    //     stmt.close();
    //    } catch (Exception e) {
    //     e.printStackTrace();
    //     throw e;
    //    } 
    // }
    
    public static Employe[] getAll(Connection con) throws Exception
    {
        String sql="select * from login";
        Statement stmt=null;
        ResultSet rs=null;
        Employe[] retour=null;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from login");
            rs.next();
            retour=new Employe[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                retour[i]=new Employe(rs.getInt("idlogin"),rs.getString("nom"),rs.getString("motdepasse"));
                i++;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retour;
    }

    // public static Employe getOne(int id,Connection con) throws Exception
    // {
    //     String sql="select * from Employer where idEmployer="+id;
    //     Statement stmt=null;
    //     ResultSet rs=null;
    //     Employe retour=null;
    //     try {
    //         stmt=con.createStatement();
    //         rs=stmt.executeQuery(sql);
    //         while(rs.next())
    //         {
    //             retour=new Employe(rs.getInt("idEmployer"),rs.getString("nom"),rs.getString("pwd"));
    //         }
    //         rs.close();
    //         stmt.close();
    //     } catch (Exception e) {
    //         throw e;
    //     }
    //     return retour;
    // }

    // public void update(Connection con) throws Exception
    // {
    //     String sql="update Employer set pwd='"+getPwd()+"',nom='"+getNom()+"';";
    //     try {
    //         Statement stmt=con.createStatement();
    //         stmt.executeUpdate(sql);
    //         stmt.close();
    //     } catch (Exception e) {
    //        throw e;
    //     }
    // }

    // public static void delete(int id,Connection con) throws Exception
    // {
    //     String sql="delete from Employer where idEmployer="+id;
    //     try {
    //         Statement stmt=con.createStatement();
    //         stmt.executeUpdate(sql);
    //         stmt.close();
    //     } catch (Exception e) {
    //       throw e;
    //     }
    // }

}
