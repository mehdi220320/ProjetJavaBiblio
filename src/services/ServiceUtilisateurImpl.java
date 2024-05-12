package services;

import entities.User;
import services.ServiceUtilisateur;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {
    private static List<User> listeUser=new ArrayList<>();

    public List<User> getListeUser() {
        return listeUser;
    }

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
        for(User us:this.listeUser){
            System.out.println(us.toString());
        }
    }
    @Override
    public User getByEmail(String email){
        for(User us:listeUser){
            if(us.getEmail().equals(email)) {
                return us;
            }
        }
        return null;
    }
    @Override
    public boolean login(String email,String password) {
        User user=getByEmail(email);
        if(user!=null )
        {
            if( user.getPassword().equals(password)){
            return true;
            }
        }
        return false;
    }

}
