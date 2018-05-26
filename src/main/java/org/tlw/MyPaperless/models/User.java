package org.tlw.MyPaperless.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int uid;

    @NotNull
    @Column(unique = true )
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastname;

    @Column
    private String hashedPassword;

    private static final BCryptPasswordEncoder encodedPassword = new BCryptPasswordEncoder();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_uid")
    private List<Intro> intro = new ArrayList<>();

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hashedPassword = hashIt(this.password);

    }

    public User() { }

    public static boolean isValidName(String name){

        Pattern isAValidName = Pattern.compile("[A-Z][A-Za-z'\\-\\s]{1,19}");
        Matcher matcher = isAValidName.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidUserName(String username){

        Pattern isAValidUserName = Pattern.compile("[A-Z][A-Za-z0-9_-]{4,8}");
        Matcher matcher = isAValidUserName.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password){

        Pattern isAValidPassword = Pattern.compile("[A-Z][$@!&A-Za-z0-9_-]{6,10}");
        Matcher matcher = isAValidPassword.matcher(password);
        return matcher.matches();
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    private static String hashIt(String password) {
        return encodedPassword.encode(password);
    }

    public boolean isCorrectPassword(String password){

        return encodedPassword.matches(password,hashedPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public List<Intro> getIntro() {
        return intro;
    }

    public void setIntro(List<Intro> intro) {
        this.intro = intro;
    }
}
