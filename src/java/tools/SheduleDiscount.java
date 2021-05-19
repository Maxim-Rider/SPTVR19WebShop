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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 *
 * @author jvm
 */
public class SheduleDiscount {
    public Furniture setDiscount(Furniture furniture, int discount, Date date, int duration, String durationType){
        furniture.setDiscount(discount);
        furniture.setDiscountDuration(duration);
        Date startDate = DateUtils.getStartOfDay(date); // приведение даты к началу дня
        Date endDate;
        Calendar c = new GregorianCalendar();
        switch (durationType) {
            case "MINUTE":
                furniture.setDiscountDate(c.getTime());
                endDate = DateUtils.plusMinutesToDate(c.getTime(), duration);
                break;
            case "HOUR":
                furniture.setDiscountDate(c.getTime());
                endDate = DateUtils.plusHoursToDate(c.getTime(), duration);
                break;
            case "DAY":
                furniture.setDiscountDate(startDate);
                endDate = DateUtils.plusMinutesToDate(startDate, duration);
                break;
            default:
                furniture.setDiscountDate(startDate);
                endDate = DateUtils.plusMinutesToDate(startDate, duration);
        }
        Timer timer = new Timer();
        DiscountTimerTask discountTimerTask = new DiscountTimerTask(furniture);
        timer.schedule(discountTimerTask, endDate);
        return furniture;
    }

}