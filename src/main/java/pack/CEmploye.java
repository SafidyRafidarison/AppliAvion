package pack;

import java.sql.Connection;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CEmploye {

        @PostMapping("/employes/{nom}/{pwd}")
     public boolean login(@PathVariable String nom,@PathVariable String pwd) 
     {
         Employe[] all=null;
        try {
            Connection con=Connect.connectDB();
            all=Employe.getAll(con);
            for(int i=0;i<all.length;i++)
            {
                if(nom.equals(all[i].getNom()) && pwd.equals(all[i].getPwd()))
                {
                    con.close();
                    return true;
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }
}