package services;

import entities.User;
import services.ServiceUtilisateur;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {
    List<User> listeUser=new ArrayList<>();

    public boolean UserExist(User user){
        for(User us:listeUser){
            if(us.equals(user)){
                return  true;
            }
        }return false;
    }
    @Override
    public boolean addUtilisateur(User user) {
        if(!UserExist(user))
        {
            user.setId(listeUser.size()+1);
            listeUser.add(user);
            return true;
        }
        System.out.println("Account already Exist");
        return false;
    }

    @Override
    public void deleteUtilisateur(int id) {
        for(User us:listeUser){
            if(us.getId()==id){
                listeUser.remove(us);
            }
        }
    }

    @Override
    public List<User> getUsers() {
        return this.listeUser;
    }

    @Override
    public void AfficheUsers() {
        for(User us:listeUser){
            System.out.println(us.toString());
        }
    }

}
