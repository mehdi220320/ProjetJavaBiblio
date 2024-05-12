package services;

import entities.User;

import java.util.List;

public interface ServiceUtilisateur {
    public boolean addUtilisateur(User user);
    public void deleteUtilisateur(int id);
    public List<User> getUsers();
    public void AfficheUsers();

}
