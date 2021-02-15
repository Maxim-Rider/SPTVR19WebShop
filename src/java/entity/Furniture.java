/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Comp
 */
@Entity
public class Furniture implements Serializable, EntityInterface{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private String size;
    private Integer quantity;
    @OneToOne
    private Cover cover;
    @Basic(fetch = FetchType.LAZY)
    private String text;
    private Integer price;

    public Furniture() {
    }

    public Furniture(String name, String color, String size, Integer quantity, String text, Integer price, Cover cover) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.text = text;
        this.price = price;
        this.cover = cover;
    }
    public Furniture(String name, String color, String size, String quantity, String text, String price, Cover cover) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.text = text;
        this.setQuantity(quantity);
        this.setPrice(price);
        this.cover = cover;
    }
    public Furniture(String name, String color, String size, Double quantity, String text, Double price, Cover cover) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.text = text;
        this.setQuantity(quantity);
        this.setPrice(price);
        this.cover = cover;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
   
    
    
    
    public void setPrice(String price) {
       if(price.matches(",")){
           price = price.replaceAll(",", ".");
       }
        try {
            Double d = Double.parseDouble(price);
            this.price = (int)(d * 100);
        } catch (Exception e) {
            throw new NumberFormatException(price);
        }

    }
    
    public String getPriceToStr(){
        double dPrice = this.price/100;
        return String.format("%.2f", dPrice);
    }

    private void setPrice(Double price) {
        this.price = (int)(price*100);
    }
    
    public String getQuantityToStr(){
        double dQuantity = this.quantity/100;
        return String.format("%.2f", dQuantity);
    }

    private void setQuantity(Double quantity) {
        this.quantity = (int)(quantity*100);
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
   
    
    
  

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

 
    
    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.color);
        hash = 41 * hash + Objects.hashCode(this.size);
        hash = 41 * hash + Objects.hashCode(this.quantity);
        hash = 41 * hash + Objects.hashCode(this.price);
        hash = 41 * hash + Objects.hashCode(this.cover);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Furniture other = (Furniture) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.cover, other.cover)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Furniture{" 
                + ", name=" + name
                + ", color=" + color
                + ", size=" + size
                + ", quantity=" + quantity
                + ", price=" + price
                + '}';
    }

    public void setQuantity(String quantity) {
        try {
            int quantityInt = Integer.parseInt(quantity);
            this.quantity = quantityInt;
            System.out.println("Строка "+quantity+" успешно преобразована в число.");
        } catch (Exception e) {
            System.out.println("Введены не цифры. Поле не изменено");
        }
    }

//    public void setPrice(String price) {
//        try {
//            int priceInt = Integer.parseInt(price);
//            this.price = priceInt;
//            System.out.println("Строка "+price+" успешно преобразована в число.");
//        } catch (Exception e) {
//            System.out.println("Введены не цифры. Поле не изменено");
//        }
//    }

    
}
    

    
    

