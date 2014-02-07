package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/12/13
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Role extends Model {

    @Required
    public String roleName;

    @Lob
    @Required
    public String roleDescription;

    public Long parentRole;



}
