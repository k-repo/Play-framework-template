package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/01/14
 * Time: 03:31
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class PurchaseProduct extends Model {

    Long productId;
    Long factureId;
    Integer qte;

}
