package controllers;

import models.Category;
import models.Role;
import models.User;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 10/01/14
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */

@With(Secure.class)
public class Categories extends Controller{

    public static void index(){


        int PAGES_MAX = 5;
        Long PAGES_COUNT = Long.parseLong(Category.findAll().size()+"");


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

        List<Object> items = Category.all().fetch(page,PAGES_MAX);

        render(items,previousPage,nextPage,numberOfPages);



    }

    public static void add(){

        Category category = new Category();
        render(category);

    }

    public static void delete(Long id){

        Category category = Category.findById(id);
        category.delete();
        flash.success(Messages.get("general.delete",category.name), category.name);
        index();

    }

    public static void show(Long id){

        Category category = Category.findById(id);
        render(category);


    }

    public static void save(@Required String name, @Required String description){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
            add();
        }

        Category category = new Category();
        category.name = name;
        category.description = description;
        flash.success("You added the new category %s", category.name);
        category.save()  ;
        index();


    }

    public static void update(Long id, @Required String name, @Required String description){
        if (validation.hasErrors() ) {
            flash.error(Messages.get("scaffold.validation"));
            validation.keep();
           show(id);
        }


        Category category = Category.findById(id);
        category.name = name;
        category.description = description;
        flash.success("You updated the category %s", category.name);
        category.save()  ;
        index();



    }


}
