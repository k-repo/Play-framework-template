package controllers;

import models.Role;
import models.User;
import play.data.validation.*;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.With;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/12/13
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */


@With(Secure.class)
@Table(name = "user_accounts")
public class Users extends Controller {

    public static void index(){


        int PAGES_MAX = 5;
        Long PAGES_COUNT = Long.parseLong(User.findAll().size()+"");


        Long numberOfPages =  PAGES_COUNT/PAGES_MAX;
        Long numberOfPagesModulo =  PAGES_COUNT%PAGES_MAX;
        if(numberOfPagesModulo>0){
            numberOfPages++;
        }
        int page = 1;
        if(params.get("page") != null){
            page = Integer.parseInt(params.get("page"));
        }
        int previousPage=0;
        int nextPage=0;

        if(page == 1){
            previousPage = 1;
        } else if (page > 1){
            previousPage = page -1;
        }

        if(page == numberOfPages){
            nextPage = Integer.parseInt(numberOfPages+"");
        } else{
            nextPage = page +1;
        }

        List<Object> items = User.all().fetch(page,PAGES_MAX);

        render(items,previousPage,nextPage,numberOfPages);




    }


    public static void add(){

        User user = new User();

        List<Object> items = Role.all().fetch();
        render(user,items);

    }


    public static void delete(Long id){

        User user = User.findById(id);
        user.delete();
        flash.success("User %s deleted", user.firstname);
        index();

    }

    public static void show(Long id){

        User user = User.findById(id);

        Role role = Role.findById(user.roleId);

        System.out.println("######## "+user.firstname);
        render(user,role);

    }


    public static void save( @Required String firstname, @Required String lastname, @Required @Email String email, @Required String password, @Required String confirmpass ,  Long roleId, String isAdmin){


        if (validation.hasErrors() || !password.equals(confirmpass) ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            add();
        }

        User user = new User();
        user.firstname = firstname;
        user.lastname = lastname;
        user.fullname = firstname +" "+ lastname;
        user.email = email;
        user.password = password;
        user.roleId = roleId;

        flash.success("Thanks adding the new user %s", user.firstname);
        user.save();

        index();
    }

    public static void update( Long id, @Required String firstname, @Required String lastname, @Required @Email String email, @Required String password, @Required String confirmpass, Long roleId, String isAdmin){

        if (validation.hasErrors() || !password.equals(confirmpass) ) {
            flash.error(Messages.get("scaffold.validation"));
            show(id);
        }

        User user = User.findById(id);
        System.out.println("!!!! "+user.firstname);

        user.firstname = firstname;
        user.lastname = lastname;
        user.fullname = firstname +" "+ lastname;
        user.email = email;
        user.password = password;
        user.roleId = roleId;
        user.save();

        System.out.println("#### USER "+user.firstname);

        flash.success("User %s updated", user.firstname);


        index();
    }



}
