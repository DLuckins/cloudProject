package org.tastydrinkysquish.drink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tds_user")
public class DrinkUser {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    private List<String> drinks;


    public DrinkUser() {
    }


    public DrinkUser(Long userID, String userName, String userPassword, List<String> drinks) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.drinks = drinks;
    }
    public DrinkUser(Long userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }
    public DrinkUser(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
    public DrinkUser(String userName, String userPassword,List<String> drinks) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.drinks=drinks;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<String> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<String> collections) {
        this.drinks = collections;
    }

}
