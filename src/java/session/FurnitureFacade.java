/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Furniture;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Comp
 */
@Stateless
public class FurnitureFacade extends AbstractFacade<Furniture> {

    @PersistenceContext(unitName = "SPTVR19WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FurnitureFacade() {
        super(Furniture.class);
    }

    public List<Furniture> findNotDiscountFurniture() {
        try {
           return em.createQuery("SELECT furniture FROM Furniture furniture WHERE furniture.discount <= 0")
                   .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
