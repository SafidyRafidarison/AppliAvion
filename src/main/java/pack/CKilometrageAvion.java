package pack;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CKilometrageAvion {
	 @GetMapping("/kilomavion/{ida}")
     public ResponseEntity getAll(@PathVariable int ida) 
     {
         KilometrageAvion[] all=null;
        JSONArray liste=null;
        try {
            Connection con=Connect.connectDB();
            all=KilometrageAvion.getAllByIdAvion(con,ida);
             liste=new JSONArray();
            for(int i=0;i<all.length;i++)
            {
            JSONObject obj=new JSONObject();    
            obj.put("idKilometrage",all[i].getIdKilometrage());    
            obj.put("datedepart",all[i].getDepart());    
            obj.put("datearrive",all[i].getArrive()); 
            obj.put("kmdepart",all[i].getKmDepart()); 
            obj.put("kmarrive",all[i].getKmFin());     
            obj.put("idavion",all[i].getIdAvion());     

            liste.add(obj);
            }
            con.close();
            return ResponseEntity.accepted().body(all);
        } catch (Exception e) {
            e.printStackTrace();
           // return e.getMessage();
        }
        return null;
        //return "{"+JSONObject.toString("liste", liste)+"}";
     }
}