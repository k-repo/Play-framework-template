package models;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 10/01/14
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Product extends Model{

    @Required
    public String name;

    @Required
    public String description;

    @Required
    public Long price;

    @Required
    public Long category;

    public Long supplierId;

    public Long qte;

    public Blob attachment;

    public Product() {
        this.qte = 0L;
    }
}
