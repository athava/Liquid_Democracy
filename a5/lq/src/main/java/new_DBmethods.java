
import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lanag
 */
public class new_DBmethods {

    public void expiration() throws ClassNotFoundException {
        List<Initiative> pis = InitiativeDB.getInitiativesWithStatus(1);
        Initiative pi;

        int i = 0;
        while (i < pis.size()) {
            pi = pis.get(i);
            //if pi expired -> changes status to expired
            // for all pis that are active (status=1)
            if (pi.getExpires().before(new Date())) {
                pi.setStatus(2);
                InitiativeDB.updateInitiative(pi);
            }
            i++;
        }
    }

}
