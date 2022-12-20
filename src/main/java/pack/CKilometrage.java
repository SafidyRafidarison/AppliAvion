package pack;

import org.springframework.web.bind.annotation.*;
import org.json.simple.*;    
import java.util.Date;
import java.sql.*;
@RestController
public class CKilometrage {

    @PostMapping("/kilometrages")
    public boolean insert(@RequestParam(value="idvoiture")int idvoiture,@RequestParam(value="idemploye")int idemploye,@RequestParam(value="depart")String depart,@RequestParam(value="arrive")String arrive,@RequestParam(value="kmDepart")double kmDepart,@RequestParam(value="kmFin")double kmFin)
    {
        Kilometrage km=new Kilometrage();
        try{
            km.setArrive(new Date(arrive.replace('-', '/')) );
            km.setDepart(new Date(depart.replace('-', '/')));
            km.setIdVoiture(idvoiture);
            km.setIdEmploye(idemploye);
            km.setKmDepart(kmDepart);
            km.setKmFin(kmFin);
            km.insert(Connexion.connectDB());
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/kilometrages")
     public String getAll()
    {
        Kilometrage[] all=null;
        JSONArray liste=null;
        try {
            Connection con=Connexion.connectDB();
            all=Kilometrage.getAll(con);
             liste=new JSONArray();
            for(int i=0;i<all.length;i++)
            {
            JSONObject obj=new JSONObject();    
            obj.put("idKilometrage",all[i].getIdKilometrage());    
            obj.put("idVoiture",all[i].getIdVoiture());    
            obj.put("idEmploye",all[i].getIdEmploye());    
            obj.put("depart",all[i].getDepart());    
            obj.put("arrive",all[i].getArrive());    
            obj.put("kmDepart",all[i].getKmDepart());    
            obj.put("kmFin",all[i].getKmFin());    
            liste.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return JSONObject.toString("liste", liste);
    }
    
    public String getJSon()
     {
        JSONObject obj=new JSONObject();    
        obj.put("name","sonoo");    
        obj.put("age",new Integer(27));    
        obj.put("salary",new Double(600000));    
        return obj.toJSONString();
     }
//getKilometrage/vehicule/id
     
}