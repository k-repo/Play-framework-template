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
public class User extends Model {

    @Email
    @Required
    public String email;

    @Required
    public String password;

    public String fullname;

    public String firstname;

    public String lastname;

    public boolean isAdmin;






}
