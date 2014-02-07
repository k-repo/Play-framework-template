package models;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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



    public String fullname;
    @Required
    public String firstname;
    @Required
    public String lastname;

    public boolean isAdmin;

    public Long roleId;

    @Required
    public String password;

    public void setPassword(String password) {
        this.password = Crypto.passwordHash(password);
    }
}
