package entities;

import java.time.LocalDateTime;

public class emplivre {

    private int id;
    private int id_livre;
    private int id_user ;
    private LocalDateTime date_emp;
    private LocalDateTime date_expiration;
    private int page_arr;

    public emplivre(int id_livre, int id_user, LocalDateTime date_emp, LocalDateTime date_expiration) {
        this.id_livre = id_livre;
        this.id_user = id_user;
        this.date_emp = date_emp;
        this.date_expiration = date_expiration;
        this.page_arr =0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public LocalDateTime getDate_emp() {
        return date_emp;
    }

    public void setDate_emp(LocalDateTime date_emp) {
        this.date_emp = date_emp;
    }

    public LocalDateTime getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDateTime date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getPage_arr() {
        return page_arr;
    }

    public void setPage_arr(int page_arr) {
        this.page_arr = page_arr;
    }
}
