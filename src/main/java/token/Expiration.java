package token;

import java.sql.*;
import java.awt.event.*;
import java.lang.Object.*;

public class Expiration{
	// private Employe;
	private int idexpiration;
	private int idemploye;
	private String token;
	private Date connection;
	private Date deconnection;

	public int getidexpiration(){return idexpiration;}
	public int getidemploye(){return idemploye;}
	public String gettoken(){return token;}
	public Date getconnection(){return connection;}
	public Date getdeconnection(){return deconnection;}

	public Expiration(){}

	public Expiration(int emp,Connection c)throws Exception{
			Connection co=null;
            PreparedStatement m=null;
            ResultSet rs=null;
            try{
            	co=c;
                String req="select * from expiration where idemployer = '"+new Integer(emp).toString()+"' and idexpiration = (select max(idexpiration) from expiration)";
                m=co.prepareStatement(req);
                rs=m.executeQuery();
                rs.next();
                idexpiration=rs.getInt("idExpiration");
                idemploye=rs.getInt("idEmployer");
                token=rs.getString("token");
                connection=rs.getDate("datyConnexion");
                deconnection=rs.getDate("datyDeconnexion");
              }
              catch(Exception ex){
                  throw ex;
              }
            finally{
                if(rs!=null){
                    rs.close();
                }
                if (m!=null) {
                    m.close();
                }
            }
	}

	public static void insert_expiration(int emp,Connection c)throws Exception{
			PreparedStatement m=null;
            Connection co=null;
            Employer em=new Employer();
            em.setIdEmployer(emp);
            Employer e=em.getEmployerById(c);
            String token=Expiration.criptage(c,e.getNom()+e.getPwd());
            try{
            	co=c;
                m=co.prepareStatement("insert into Expiration(idEmployer,token) values(?,?)");
                m.setInt(1,emp);
                m.setString(2,token);
                m.executeUpdate();
            }
            catch(Exception ex){
              throw ex;
            }
            finally{
                if (m!=null) {
                    m.close();
                }
            }
	}

	public boolean permission(Connection c)throws Exception{
			Connection co=null;
            PreparedStatement m=null;
            ResultSet rs=null;
            boolean retour;
            try{
            	co=c;
                String req="select * from expiration where idexpiration = '"+new Integer(getidexpiration()).toString()+"' and datydeconnexion <= now()";
                m=co.prepareStatement(req);
                rs=m.executeQuery();
                retour=rs.next();
              }
              catch(Exception ex){
                  throw ex;
              }
            finally{
                if(rs!=null){
                    rs.close();
                }
                if (m!=null) {
                    m.close();
                }
            }
            return retour;
	}

	public static String criptage(Connection c,String s)throws Exception{
		Connection con=null;
        PreparedStatement m=null;
        ResultSet rs=null;
        String retour="";
        try{
            String req="select md5('"+s+"') ";
            con=c;
            m=con.prepareStatement(req);
            rs=m.executeQuery();
            rs.next();
            retour=rs.getString("md5");
        }
        catch(Exception ex){
                  throw ex;
              }
            finally{
            	// if(con!=null){
             //        con.close();
             //    }
                if(rs!=null){
                    rs.close();
                }
                if (m!=null) {
                    m.close();
                }
            }
        return retour;
	}

	public static void main(String[] args)throws Exception {
		Expiration.insert_expiration(1,Connexion.getCo());
	}
}