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
public class Voiture {

    int idVoiture;
    String matricule;
    String marque;
    int kilometrageinitiale;
    int idMarque;

  

    public Voiture() {
    }

    public Voiture(int idVoiture, String matricule, String marque, int idMarque, int kilometrageinitiale) {
        this.idVoiture = idVoiture;
        this.matricule = matricule;
        this.marque = marque;
        this.idMarque = idMarque;
        this.kilometrageinitiale = kilometrageinitiale;
    }
    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public int getIdMarque() {
        return this.idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getKilometrageinitiale() {
        return kilometrageinitiale;
    }

    public void setKilometrageinitiale(int kilometrageinitiale) {
        this.kilometrageinitiale = kilometrageinitiale;
    }
    
    
    public void insert(Connection con) throws Exception
    {
        String sql="insert into voiture (matricule,idMarque,Kilometrageinitiale) values('"+matricule+"',"+idMarque+","+kilometrageinitiale+")"; 
       try {
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
       } catch (Exception e) {
        e.printStackTrace();
        throw e;
       } 
    }
    
    public static Voiture[] getAll(Connection con) throws Exception
    {
        String sql="select * from v_voiture";
        Statement stmt=null;
        ResultSet rs=null;
        Voiture[] retour=null;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from v_voiture");
            rs.next();
            retour=new Voiture[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                retour[i]=new Voiture(rs.getInt("idvoiture"),rs.getString("matricule"),rs.getString("marque"),rs.getInt("idmarque"),rs.getInt("kilometrageinitiale"));
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

    public static Voiture getOne(int id,Connection con) throws Exception
    {
        String sql="select * from v_voiture where idVoiture="+id;
        Statement stmt=null;
        ResultSet rs=null;
        Voiture retour=null;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                retour=new Voiture(rs.getInt("idvoiture"),rs.getString("matricule"),rs.getString("marque"),rs.getInt("idmarque"),rs.getInt("kilometrageinitiale"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw e;
        }
        return retour;
    }

    public void update(Connection con) throws Exception
    {
        String sql="update voiture set matricule='"+matricule+"',idmarque="+idMarque+",kilometrageinitiale="+kilometrageinitiale+"  where idvoiture="+idVoiture;
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
           throw e;
        }
    }

    public static void delete(int id,Connection con) throws Exception
    {
        String sql="delete from voiture where idVoiture="+id;
        try {
            Statement stmt=con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
          throw e;
        }
    }

}
