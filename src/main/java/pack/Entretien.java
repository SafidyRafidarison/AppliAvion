package pack;
import java.sql.*;
import java.util.Date;

public class Entretien {
	private int idEntretien;
	private int idAvion;
	private Date dateEntretien;
	private int idElementEntretien;
	private String type;
	private String description;

    public int getIdEntretien() {
        return idEntretien;
    }

    public void setIdEntretien(int idEntretien) {
        this.idEntretien = idEntretien;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Date getDateEntretien() {
        return dateEntretien;
    }

    public void setDateEntretien(Date dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    public int getIdElementEntretien() {
        return idElementEntretien;
    }

    public void setIdElementEntretien(int idElementEntretien) {
        this.idElementEntretien = idElementEntretien;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Entretien(int idEntretien, int idAvion, Date dateEntretien, int idElementEntretien, String type, String description) {
        this.idEntretien = idEntretien;
        this.idAvion = idAvion;
        this.dateEntretien = dateEntretien;
        this.idElementEntretien = idElementEntretien;
        this.type = type;
        this.description = description;
    }
    
    public static Entretien[] getAllByIdAvion(Connection con,int idavion) throws Exception
    {
        Entretien[] retour=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql="select * from ventretien where idavion="+idavion;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from entretien where idavion="+idavion);
            rs.next();
            retour=new Entretien[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                retour[i]=new Entretien(rs.getInt("idEntretien"), rs.getInt("idAvion"),new Date(rs.getString("dateEntretien").replace('-', '/')),rs.getInt("idElementEntretien"),rs.getString("type"),rs.getString("description"));
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