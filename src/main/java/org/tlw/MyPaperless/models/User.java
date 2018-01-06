package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User extends AbstractModel {

    @NotNull
    @Size(min = 5, max = 20, message = "Please add a username that is between 5 and 20 characters")
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min= 5 , max= 10, message = "Please add a password")
    @Column(name = "password")
    private String password;


    public User(String name, String password) {

        this.name = name;
        this.password = password;
    }

    public User() { }

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
