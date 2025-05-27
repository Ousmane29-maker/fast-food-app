package modele;

import vues.Observateur;

import java.util.ArrayList;

public class SujetObserve {
    ArrayList<Observateur> observateurs ;
    public SujetObserve(){
        observateurs = new ArrayList<>() ;
    }

    public void notifierObservateur(){
        for(Observateur o : observateurs){
            o.reagir() ;
        }
    }

    public void ajouterObservateur(Observateur obs){
        observateurs.add(obs);
    }


}
