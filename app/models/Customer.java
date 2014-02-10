package models;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/12/13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */


@Entity
public class Customer extends Model {

    @Email
    @Required
    public String email;

    @Required
    public String firstname;
    @Required
    public String lastname;

    public String companyName;

    @Required
    public String phone;

    public String fax;

    @Required
    public String adress;

    public Date date;



}
