package pack;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CAvion {
	 @GetMapping("/avion")
     public ResponseEntity getAll() 
     {
         Avion[] all=null;
        JSONArray liste=null;
        try {
            Connection con=Connect.connectDB();
            all=Avion.getAll(con);
             liste=new JSONArray();
            for(int i=0;i<all.length;i++)
            {
            JSONObject obj=new JSONObject();    
            obj.put("idAvion",all[i].getIdAvion());    
            obj.put("type",all[i].getType());    
            obj.put("photo",all[i].getPhoto()); 
            obj.put("numeroserie",all[i].getNumeroserie());     
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

     @GetMapping("/avion/{ida}")
     public String getone(@PathVariable int ida) 
     {
        Avion v=null;
        JSONObject obj=new JSONObject();    
        try {
            Connection con=Connect.connectDB();
            v=Avion.getone(con,ida);
            obj.put("idAvion",v.getIdAvion());    
            obj.put("type",v.getType());    
            obj.put("photo",v.getPhoto()); 
            obj.put("numeroserie",v.getNumeroserie());    
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return obj.toJSONString();
     }
}