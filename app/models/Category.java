package models;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 10/01/14
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Category extends Model{

    @Required
    public String name;

    @Required
    public String description;

    public Blob attachment;

}
