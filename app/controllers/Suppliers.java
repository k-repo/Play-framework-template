package controllers;

import models.Supplier;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/01/14
 * Time: 04:16
 * To change this template use File | Settings | File Templates.
 */
public class Suppliers extends Controller {



    public static void index(){

        int PAGES_MAX = 5;
        Long PAGES_COUNT = Long.parseLong(Supplier.findAll().size()+"");


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

        List<Object> items = Supplier.all().fetch(page,PAGES_MAX);

        render(items,previousPage,nextPage,numberOfPages);


    }

    public static void add(){
        Supplier supplier = new Supplier();
        render(supplier);
    }

    public static void delete(Long id){
        Supplier supplier = Supplier.findById(id);
        supplier.delete();
        flash.success("Supplier %s deleted", supplier.companyName);
        index();
    }

    public static void show(Long id){
        Supplier supplier = Supplier.findById(id);
        render(supplier);

    }

    public static void save(@Required String email, @Required String firstname, @Required String lastname, @Required String companyName, @Required String phone, String fax, @Required String adress){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            add();
        }

        Supplier supplier = new Supplier();
        supplier.firstname = firstname;
        supplier.lastname = lastname;
        supplier.companyName = companyName;
        supplier.phone = phone;
        supplier.fax = fax;
        supplier.adress = adress;
        supplier.email = email;
        supplier.save();
        flash.success("You added the new Supplier %s", supplier.companyName);
        index();


    }

    public static void update(Long id,@Required String email, @Required String firstname, @Required String lastname, @Required String companyName, @Required String phone, String fax, @Required String adress){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            show(id);
        }


        Supplier supplier = Supplier.findById(id);
        supplier.firstname = firstname;
        supplier.lastname = lastname;
        supplier.companyName = companyName;
        supplier.phone = phone;
        supplier.fax = fax;
        supplier.adress = adress;
        supplier.email = email;
        supplier.save();
        flash.success("You updated the Supplier %s", supplier.companyName);
        index();

    }



}
