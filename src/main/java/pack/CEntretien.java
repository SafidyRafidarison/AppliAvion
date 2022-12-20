package pack;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CEntretien {
	 @GetMapping("/entretien/{ida}")
     public ResponseEntity getAll(@PathVariable int ida) 
     {
     	 Entretien[] all=null;
        JSONArray liste=null;
        try {
            Connection con=Connect.connectDB();
            all=Entretien.getAllByIdAvion(con,ida);
             liste=new JSONArray();
            for(int i=0;i<all.length;i++)
            {
            JSONObject obj=new JSONObject();    
            obj.put("identretien",all[i].getIdEntretien());    
            obj.put("dateentretien",all[i].getDateEntretien());    
            obj.put("type",all[i].getType());
            obj.put("description",all[i].getDescription());
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
     }
 }