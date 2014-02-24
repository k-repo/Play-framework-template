package controllers;
import models.User;
import play.libs.OpenID;
import play.mvc.Before;
import play.mvc.Controller;


public class OpenIdService extends Controller{

    @Before(unless={"login", "authenticate"})
    static void checkAuthenticated()  throws Throwable {

        //System.out.println("checkAuthenticated");
        //System.out.println("Session user "+session.get("username"));
        //System.out.println("Session email "+session.get("email"));
        //System.out.println("Session role "+session.get("userRole"));
        if (session.get("username") == null || session.get("username").equals("")) {
            //System.out.println("session username null or empty");
            Secure.login();
        }

    }

    public static void index() {
        Application.index();
    }

    public static void login() throws Throwable {
        //System.out.println("login");
        Secure.login();
    }

    public static void authenticate(String user) throws Throwable {

        //System.out.println("authenticate");
        //System.out.println("user authenticate openId: " + user);

        if (OpenID.isAuthenticationResponse()) {

            OpenID.UserInfo verifiedUser = OpenID.getVerifiedID();

            //System.out.println("verifiedUser: " + verifiedUser.id);

            if (verifiedUser == null) {
                //System.out.println("Error login");
                flash.put("error", "Authentication has failed");
                //System.out.println("openid verified user null");
                Secure.login();
            }




            String userEmail = verifiedUser.extensions.get("email");
            //System.out.println("##### Email user "+userEmail);
            String first = verifiedUser.extensions.get("first");
            //System.out.println("##### firstname user "+first);
            String last = verifiedUser.extensions.get("last");
            //System.out.println("##### lastname user "+last);




            if (userEmail == null) {
                flash.error("Your email address is required, please authorize our site to access it");
                //System.out.println("openid user mail null");
                Secure.login();
            }else{
                User connectedUser = User.find("byEmail", userEmail.toLowerCase()).first();
                if(connectedUser != null && User.find("byEmail", userEmail.toLowerCase()).fetch().size() > 0 && connectedUser.email.toLowerCase().equals(userEmail.toLowerCase())){
                    session.put("userId",connectedUser.id);
                    session.put("username", first+" "+last);
                    session.put("userRole", connectedUser.roleId);
                    session.put("email", userEmail);
                    flash.put("Welcome", session.get("username"));

                }else{
                    //System.out.println("Error login");
                    flash.put("error", "Authentication has failed");
                    //System.out.println("openid connected user not found");
                    Secure.login();
                }
            }





            index();

        }else {
            // Verify the id
            if (!OpenID.id("https://www.google.com/accounts/o8/id")
                    .required("email", "http://axschema.org/contact/email")
                    .required("first", "http://axschema.org/namePerson/first")
                    .required("last", "http://axschema.org/namePerson/last")
                    .verify()) {
                flash.put("error", "Cannot contact google");
                index();
            }

        }

    }


}
