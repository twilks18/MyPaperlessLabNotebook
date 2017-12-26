package org.tlw.MyPaperless.models;

import java.util.ArrayList;

public class NewUser {
    public static ArrayList<User> user = new ArrayList<>();


    //add user
    public static void addUser(User newUser){
        user.add(newUser);
    }

    //get all users
    public static ArrayList<User> getAllUsers(){
        return user;
    }

    //remove user
    public static void removeUser(int id){
        User userToRemove = getUserById(id);
        user.remove(userToRemove);
    }

    //getById (userId)

    public static User getUserById(int id) {

        User userid = null;

        for (User theUser : user) {
            if (theUser.getUid() == id) {
                userid = theUser;
            }
        }
        return userid;
    }

    //getByName (name)

    public static User getUserByName( String name){

        User username = null;

        for (User userName : user){
            if ( userName.getName() == name){
                username = userName;
            }
        }
        return username;
    }
}
