package pack;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CVoiture {

    @PutMapping("/voitures/{idv}")
    public boolean update(@PathVariable int idv,@RequestParam(value="idmarque")int idMarque,@RequestParam(value="matricule")String matricule,@RequestParam(value="kilometrageinitiale")int kilometrageinitiale)
    {
        Voiture v=null;
        try {
            v=new Voiture();
            v.setIdVoiture(idv);
            v.setIdMarque(idMarque);
            v.setKilometrageinitiale(kilometrageinitiale);
            v.setMatricule(matricule);
            Connection con=Connexion.connectDB();
            v.update(con);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/voitures")
    public boolean insert(@RequestParam(value="marque")int marque,@RequestParam(value="matricule")String matricule,@RequestParam(value="kilometrageinitiale")int kilometrageinitiale)
    {
        Voiture v=new Voiture();
        v.setKilometrageinitiale(kilometrageinitiale);   
        v.idMarque=marque;
        v.setMatricule(matricule);
        try{
            Connection con=Connexion.connectDB();
            v.insert(con);
            con.close();
            return true;
        }
        catch(Exception e){}
        return false;
    }

    @GetMapping("/voitures")
     public ResponseEntity getAll() 
     {
         Voiture[] all=null;
        JSONArray liste=null;
        try {
            Connection con=Connexion.connectDB();
            all=Voiture.getAll(con);
             liste=new JSONArray();
            for(int i=0;i<all.length;i++)
            {
            JSONObject obj=new JSONObject();    
            obj.put("idVoiture",all[i].getIdVoiture());    
            obj.put("matricule",all[i].getMatricule());    
            obj.put("marque",all[i].getMarque());    
            obj.put("idMarque",all[i].getIdMarque());    
            obj.put("kilometrageinitiale",all[i].getKilometrageinitiale());    
            liste.add(obj);
            }
            return ResponseEntity.accepted().body(all);
        } catch (Exception e) {
            e.printStackTrace();
           // return e.getMessage();
        }
        return null;
        //return "{"+JSONObject.toString("liste", liste)+"}";
     }

     @GetMapping("/voitures/{idv}")
     public String getOne(@PathVariable int idv)
     {
        Voiture v=null;
        JSONObject obj=new JSONObject();    
        try {
            Connection con=Connexion.connectDB();
            v=Voiture.getOne(idv,con);
            obj.put("idVoiture",v.getIdVoiture());    
            obj.put("matricule",v.getMatricule());    
            obj.put("marque",v.getMarque());    
            obj.put("idMarque",v.getIdMarque());    
            obj.put("kilometrageinitiale",v.getKilometrageinitiale());    
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return obj.toJSONString();
     }

     @DeleteMapping("/voitures/{idv}")
     public boolean delete(@PathVariable int idv)
     {
        try {
            Connection con=Connexion.connectDB();
            Voiture.delete(idv,con);
            return true;
        }
        catch(Exception ex){ex.printStackTrace();}
        return false;
     }
}