package controllers;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 24/02/14
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */


@With(Secure.class)
public class Services extends Controller {



    public static void geocode(){
        String address="";
        params.get("loc");
        if(params.get("loc") == null || params.get("loc").isEmpty()){
        address = "casablanca maroc";
        }else{
            address = params.get("loc");
        }

        System.out.println("### "+address);

        WS.HttpResponse res = WS.url("https://michele-zonca-google-geocoding.p.mashape.com/geocode/json?address="+address+"&sensor=false&bounds=%3Cbounds%3E&language=fr&region=%3Cregion%3E").headers(Collections.singletonMap("X-Mashape-Authorization","Pye4FcJo1dxtr43p8AjsIvQ8kV2DUV2m")).get();
        JsonElement json = res.getJson();

        render(json);

    }

    public static void googleSearch(String q){



    //      WS.HttpResponse res = WS.url("https://www.googleapis.com/customsearch/v1?&num=10&start=1&key=AIzaSyBQ25rF8Zt6L48wjHhRf9OueflFnwwClqE&cx=005529555936134972311:lnblfzns-qy&q="+q).get();
        WS.HttpResponse res = WS.url("https://www.googleapis.com/customsearch/v1?&num=10&start=1&key=AIzaSyCy0eKOkilT_Ln8gH_gO02RT3RVsFxQ-pI&cx=005558272405387467247:l08thvzzhbe&q="+q).get();

        JsonElement json = res.getJson();


        Object[] j = json.getAsJsonObject().entrySet().toArray();

        System.out.println("------------ TABLE SIZE "+j.length);

        for(int i = 0; i<j.length ; i++){
            System.out.println("##### "+i+" : "+j[i]);
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");
        }

        // System.out.println("### json "+json.isJsonObject());

        //if(json.isJsonObject()){

        //    System.out.println("######################" +json.getAsJsonObject().entrySet().size());
        //    System.out.println("######################" +json.getAsJsonObject().entrySet().iterator());

        //}

        render(json);
        //renderJSON(json);
    }

    //


    public static void location(){
        render();
    }



}
