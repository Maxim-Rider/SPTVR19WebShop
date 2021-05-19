/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Comp
 */
import entity.Furniture;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.FurnitureFacade;


public class DiscountTimerTask extends TimerTask{
    private Furniture discountFurniture;
    private FurnitureFacade furnitureFacade;

    public DiscountTimerTask(Furniture discountFurniture) {
        Context ctx;
        try {
            ctx = new InitialContext();
            this.furnitureFacade = (FurnitureFacade)ctx.lookup("java:global/SPTVR19WebShop/FurnitureFacade");
        } catch (NamingException ex) {
            Logger.getLogger(DiscountTimerTask.class.getName()).log(Level.SEVERE, "Не найден FurnitureFacade", ex);
        }
        this.discountFurniture = discountFurniture;
    }

    @Override
    public void run() {
        discountFurniture.setDiscount(0);
        discountFurniture.setDiscountDate(null);
        discountFurniture.setDiscountDuration(0);
        furnitureFacade.edit(discountFurniture);
    }

}