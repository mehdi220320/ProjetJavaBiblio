package services;

import entities.User;
import services.ServiceUtilisateur;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {
    List<User> listeUser=new ArrayList<>();


    @Override
    public void addUtilisateur(User user) {
        listeUser.add(user);
    }

    @Override
    public void deleteUtilisateur(int id) {
        for(User us:listeUser){
            if(us.getId()==id){
                listeUser.remove(us);
            }
        }
    }

}
