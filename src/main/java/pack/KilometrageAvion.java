package pack;
import java.sql.*;
import java.util.Date;

public class KilometrageAvion {
    private int idKilometrage;
    private int idAvion;
    private Date depart;
    private Date arrive;
    private double kmDepart;
    private double kmFin;
    public int getIdKilometrage() {
        return idKilometrage;
    }

    public KilometrageAvion(int idKilometrage, int idAvion, Date depart, Date arrive, double kmDepart, double kmFin) {
        this.idKilometrage = idKilometrage;
        this.idAvion = idAvion;
        this.depart = depart;
        this.arrive = arrive;
        this.kmDepart = kmDepart;
        this.kmFin = kmFin;
    }

    public void setIdKilometrage(int idKilometrage) {
        this.idKilometrage = idKilometrage;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
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
    
    public static KilometrageAvion[] getAllByIdAvion(Connection con,int idavion) throws Exception
    {
        KilometrageAvion[] retour=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql="select * from kilometrage where idavion="+idavion;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from kilometrage where idavion="+idavion);
            rs.next();
            retour=new KilometrageAvion[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                System.out.println(rs.getString("datedepart"));
                retour[i]=new KilometrageAvion(rs.getInt("idKilometrage"), rs.getInt("idAvion"),new Date(rs.getString("datedepart").replace('-', '/')), new Date(rs.getString("datearrive").replace('-', '/')), rs.getDouble("kmdepart"), rs.getDouble("kmarrive"));
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

    public KilometrageAvion() {
    }
    
}