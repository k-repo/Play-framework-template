package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 10/01/14
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Sale extends Model{

    public String reference;
    public Date date;
    public Long userId;
    public Long supplierId;




}
