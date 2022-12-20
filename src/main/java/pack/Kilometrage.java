/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class Kilometrage {
    int idKilometrage;
    int idVoiture;
    int idEmploye;
    Date depart;
    Date arrive;
    double kmDepart;
    double kmFin;

    public Kilometrage(int idKilometrage, int idVoiture, int idEmploye, Date depart, Date arrive, double kmDepart, double kmFin) {
        this.idKilometrage = idKilometrage;
        this.idVoiture = idVoiture;
        this.idEmploye = idEmploye;
        this.depart = depart;
        this.arrive = arrive;
        this.kmDepart = kmDepart;
        this.kmFin = kmFin;
    }

    public Kilometrage() {
    }

    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public int getIdKilometrage() {
        return this.idKilometrage;
    }

    public void setIdKilometrage(int idKilometrage) {
        this.idKilometrage = idKilometrage;
    }
    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Date getArrive() {
        return arrive;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    public double getKmDepart() {
        return kmDepart;
    }

    public void setKmDepart(double kmDepart) {
        this.kmDepart = kmDepart;
    }

    public double getKmFin() {
        return kmFin;
    }

    public void setKmFin(double kmFin) {
        this.kmFin = kmFin;
    }

    
    public void insert(Connection con) throws Exception 
    {
        String sql="insert into kilometrage (idEmployer,idVoiture,DateDepart,DateArrive,kmdepart,kmarrive) values("+this.idEmploye+","+idVoiture+",'"+this.depart+"','"+this.arrive+"',"+this.kmDepart+","+this.kmFin+")";
        try {
            Statement stmt=con.createStatement();
            System.out.println(sql);
            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public  static Kilometrage[] getAll(Connection con) throws Exception
    {
        Kilometrage[] retour=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql="select * from kilometrage";
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from kilometrage");
            rs.next();
            retour=new Kilometrage[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                System.out.println(rs.getString("datedepart"));
                retour[i]=new Kilometrage(rs.getInt("idKilometrage"), rs.getInt("idVoiture"), rs.getInt("idEmployer"),new Date(rs.getString("datedepart").split(" ")[0].replace('-', '/')), new Date(rs.getString("datearrive").split(" ")[0].replace('-', '/')), rs.getDouble("kmdepart"), rs.getDouble("kmarrive"));
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
}
