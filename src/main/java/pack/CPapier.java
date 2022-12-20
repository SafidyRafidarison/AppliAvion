package pack;

import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CPapier {
	 @GetMapping("/papier/{ida}")
     public ResponseEntity getAll(@PathVariable int ida) 
     {
     	 Papier[] all=null;
        JSONArray liste=null;
        try {
            Connection con=Connect.connectDB();
            all=Papier.getAllByIdAvion(con,ida);
             liste=new JSONArray();
            for(int i=0;i<all.length;i++)
            {
            JSONObject obj=new JSONObject();    
            obj.put("idpapiere",all[i].getIdPapier());    
            obj.put("dateexpiration",all[i].getDateExpiration());    
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
        //return "{
     }
 }