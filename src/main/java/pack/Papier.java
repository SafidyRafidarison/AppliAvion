package pack;
import java.sql.*;
import java.util.Date;

public class Papier {
	private int idPapier;
	private int idAvion;
	private Date dateExpiration;
        
        public Papier(int idPapier, int idAvion, Date dateExpiration) {
        this.idPapier = idPapier;
        this.idAvion = idAvion;
        this.dateExpiration = dateExpiration;
    }

    public int getIdPapier() {
        return idPapier;
    }

    public void setIdPapier(int idPapier) {
        this.idPapier = idPapier;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    
    public static Papier[] getAllByIdAvion(Connection con,int idavion) throws Exception
    {
        Papier[] retour=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql="select * from papier where idavion="+idavion;
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select count(*) as taille from papier where idavion="+idavion);
            rs.next();
            retour=new Papier[rs.getInt("taille")];
            rs.close();
            rs=stmt.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                retour[i]=new Papier(rs.getInt("idPapier"), rs.getInt("idAvion"),new Date(rs.getString("dateExpiration").replace('-', '/')));
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