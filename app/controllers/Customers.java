package controllers;

import models.Category;
import models.Customer;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/01/14
 * Time: 04:15
 * To change this template use File | Settings | File Templates.
 */
public class Customers extends Controller {

    public static void index(){

        int PAGES_MAX = 5;
        Long PAGES_COUNT = Long.parseLong(Customer.findAll().size()+"");


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

        List<Object> items = Customer.all().fetch(page,PAGES_MAX);

        render(items,previousPage,nextPage,numberOfPages);


    }

    public static void add(){
        Customer customer = new Customer();
        customer.date = new Date();
        render(customer);
    }

    public static void delete(Long id){
        Customer customer = Customer.findById(id);
        customer.delete();
        flash.success("Customer %s deleted", customer.companyName);
        index();
    }

    public static void show(Long id){
        Customer customer = Customer.findById(id);
        render(customer);

    }

    public static void save(@Required String email, @Required String firstname, @Required String lastname, @Required String companyName, @Required String phone, String fax, @Required String adress, String datepick){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            add();
        }

        Customer customer = new Customer();
        customer.firstname = firstname;
        customer.lastname = lastname;
        customer.companyName = companyName;
        customer.phone = phone;
        customer.fax = fax;
        customer.adress = adress;
        customer.email = email;
        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        try {
            customer.date = formatter.parse(datepick);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        customer.save();
        flash.success("You added the new customer %s", customer.companyName);
        index();


    }

    public static void update(Long id,@Required String email, @Required String firstname, @Required String lastname, @Required String companyName, @Required String phone, String fax, @Required String adress,String datepick){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            show(id);
        }


        Customer customer = Customer.findById(id);
        customer.firstname = firstname;
        customer.lastname = lastname;
        customer.companyName = companyName;
        customer.phone = phone;
        customer.fax = fax;
        customer.email = email;
        customer.adress = adress;

        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        try {
            customer.date = formatter.parse(datepick);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        customer.save();
        flash.success("You updated the customer %s", customer.companyName);
        index();

    }

}
