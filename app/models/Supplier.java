package models;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/12/13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */


@Entity
public class Supplier extends Model {

    @Email
    @Required
    public String email;

    public String companyName;
    @Required
    public String firstname;
    @Required
    public String lastname;

    @Required
    public String phone;

    @Required
    public String adress;





}
