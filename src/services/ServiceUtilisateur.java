package services;

import entities.User;

import java.util.List;

public interface ServiceUtilisateur {
    public boolean addUtilisateur(User user);
    public void deleteUtilisateur(int id);
    public List<User> getUsers();
    public void AfficheUsers();
    public boolean login(String email,String password);
    public User getByEmail(String email);

    }
