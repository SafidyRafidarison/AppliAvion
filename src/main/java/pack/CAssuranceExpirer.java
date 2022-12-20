package pack;
import java.sql.Connection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

@CrossOrigin
@RestController
public class CAssuranceExpirer {
    @GetMapping("/assuranceExpirer/1")
    public ResponseEntity assuranceExpirerInOneMonth() {
        AssuranceExpirer[] retour = null;
        try {
            Connection connexion = Connect.connectDB();
            retour = (AssuranceExpirer[]) AssuranceExpirer.cast(new AssuranceExpirer().select(connexion,"V_AssuranceExpirerInOneMonth"));
            return ResponseEntity.accepted().body(retour);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/assuranceExpirer/3")
    public ResponseEntity assuranceExpirerInThreeMonth() {
        AssuranceExpirer[] retour = null;
        try {
            Connection connexion = Connect.connectDB();
            retour = (AssuranceExpirer[]) AssuranceExpirer.cast(new AssuranceExpirer().select(connexion,"V_AssuranceExpirerInThreeMonth"));
            return ResponseEntity.accepted().body(retour);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
