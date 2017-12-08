package org.tlw.MyPaperless.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min= 5 , max= 20, message = "Please add a username that is between 5 and 20 characters")
    private String name;

    @NotNull
    @Size(min= 5 , max= 10, message = "Please add a password")
    private String password;
    private int userID;
    private static int nextUserID = 1;

    public User(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }

    public User() {
        userID = nextUserID;
        nextUserID++;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
