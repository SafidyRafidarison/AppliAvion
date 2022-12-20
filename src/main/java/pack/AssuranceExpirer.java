package pack;
import util.*;
import java.util.*;
public class AssuranceExpirer extends ObjetBDD{
    int idvoiture = -1234567;
    Date dateExpiration;
    String description;
    String matricule;
    double kilometrageInitiale = -1234567;
    String marque;

    public AssuranceExpirer() {
    }

    public int getIdvoiture() {
        return idvoiture;
    }
    public void setIdvoiture(int idvoiture) {
        this.idvoiture = idvoiture;
    }
    public Date getDateExpiration() {
        return dateExpiration;
    }
    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public double getKilometrageInitiale() {
        return kilometrageInitiale;
    }
    public void setKilometrageInitiale(double kilometrageInitiale) {
        this.kilometrageInitiale = kilometrageInitiale;
    }
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }

    public static AssuranceExpirer[] cast(Object[] objet){
        AssuranceExpirer[] retour = new AssuranceExpirer[objet.length];
        for (int indice = 0;indice<objet.length;indice++) {
            retour[indice] = (AssuranceExpirer) objet[indice];
        }
        return retour;
    }
}
