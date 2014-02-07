package controllers;


import models.User;
import play.libs.Crypto;
import play.mvc.Scope;


public class Security extends Secure.Security {

    static void onAuthenticated() {

        String user = Security.connected();
        session.put("username", user);

        User connectedUser = User.find("fullName", user).first();

        //if(User.find("userName",user).fetch().size() != 0){
        if(connectedUser!=null)
        {
            //User connectedUser = User.find("userName",user).first();
            System.out.println("Connected user name "+connectedUser.fullname);
            System.out.println(connectedUser.toString());

            session.put("email", connectedUser.email);
            session.put("userId", connectedUser.id);

            System.out.println(" User mail " + connectedUser.email);
        }


    }


    static boolean authenticate(String username, String password) {
        if(User.find("byFullNameAndPassword", username, Crypto.passwordHash(password)).fetch().size() > 0){
            User user = User.find("byFullNameAndPassword", username, Crypto.passwordHash(password)).first();

            if(user == null){
                System.out.println("User == null 1");
                return false;
            }

            session.put("username", user.fullname);
            session.put("userId", user.id);
            System.out.println("Authenticate session content "+session.get("username"));
            return user != null && user.password.equals(Crypto.passwordHash(password));

        }
        else
        {
            System.out.println("User == null 2");
            return false;
        }
    }

    /************************************ SECURITY MODEL **********************************************/





}
