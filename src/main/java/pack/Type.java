package pack;
import java.sql.*;

public class Type {
	private int idType;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public Type(int idType, String type) {
        this.idType = idType;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }
	private String type;
        
        
}