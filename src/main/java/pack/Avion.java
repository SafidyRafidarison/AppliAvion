package pack;
import java.sql.*;

public class Avion {
	private int idAvion;
	private String type;
	private String photo;
        private String numeroserie;

    public String getNumeroserie() {
        return numeroserie;
    }

    public void setNumeroserie(String numeroserie) {
        this.numeroserie = numeroserie;
    }

    public Avion() {
    }

	private KilometrageAvion[] kilometrages;
	private Papier[] papiers;
	private Entretien[] entretiens;
        
    
    public Avion(int idAvion, String type, String photo,String numeroserie) {
        this.idAvion = idAvion;
        this.type = type;
        this.photo = photo;
                this.numeroserie = numeroserie;

    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public KilometrageAvion[] getKilometrages() {
        
        return kilometrages;
    }

    public void setKilometrages(KilometrageAvion[] kilometrages) {
        this.kilometrages = kilometrages;
    }

    public Papier[] getPapiers() {
        return papiers;
    }

    public void setPapiers(Papier[] papiers) {
        this.papiers = papiers;
    }

    public Entretien[] getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(Entretien[] entretiens) {
        this.entretiens = entretiens;
    }

    public static Avion[] getAll(Connection con) throws Exception
    {
        String sql="select * from vavion";
        Statement stmt=null;
        ResultSet rs=null;
        Avion[] retour=null;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from vavion");
            rs.next();
            retour=new Avion[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                retour[i]=new Avion(rs.getInt("idAvion"),rs.getString("type"),rs.getString("photo"),rs.getString("numeroserie"));
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

    public static Avion getone(Connection con,int ida) throws Exception
    {
        String sql="select * from vavion where idavion="+ida;
        Statement stmt=null;
        ResultSet rs=null;
        Avion retour=null;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                retour=new Avion(rs.getInt("idAvion"),rs.getString("type"),rs.getString("photo"),rs.getString("numeroserie"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw e;
        }
        return retour;
    }

}