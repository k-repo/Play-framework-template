package controllers;

import models.Category;
import models.Product;
import models.Supplier;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.With;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 10/01/14
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */

@With(Secure.class)
public class Products extends Controller {

    public static void index(){

        int PAGES_MAX = 5;
        Long PAGES_COUNT = Long.parseLong(Product.findAll().size()+"");


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

        List<Object> items = Product.all().fetch(page,PAGES_MAX);

        render(items,previousPage,nextPage,numberOfPages);

    }

    public static void add(){

        Product product = new Product();
        List<Supplier> suppliers =Supplier.all().fetch();
        List<Category> categories =Category.all().fetch();

        render(product,suppliers,categories);

    }

    public static void delete(Long id){
        Product product = Product.findById(id);
        product.delete();
        flash.success("Product %s deleted", product.name);
        index();
    }

    public static void show(Long id){

        Product product = Product.findById(id);
        Supplier supplier = Supplier.findById(product.supplierId);
        Category category = Category.findById(product.category);
        render(product,supplier,category);


    }

    public static void save(@Required String name,@Required String description,@Required Long price, @Required Long category,  Long supplierId ){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            add();
        }

        Product product = new Product();
        product.name = name;
        product.description = description;
        product.price = price;
        product.category = category;
        product.supplierId = supplierId;

        flash.success("You added the new category %s", product.name);
        product.save()  ;
        index();

    }

    public static void update(Long id, @Required String name,@Required String description,@Required Long price){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            add();
        }
        Product product = Product.findById(id);
        product.name = name;
        product.description = description;
        product.price = price;

        flash.success("You added the new category %s", product.name);
        product.save()  ;
        index();

    }



}
