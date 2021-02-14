/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Comp
 */
@Entity
public class Furniture implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private String size;
    private Integer quantity;
    private Integer price;
    
    

    public Furniture() {
    }

    public Furniture(String name, String color, String size, Integer quantity, Integer price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }
    public Furniture(String name, String color, String size, String quantity, String price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.setQuantity(quantity);
        this.setPrice(price);
    }
    public Furniture(String name, String color, String size, Double quantity, Double price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.setQuantity(quantity);
        this.setPrice(price);
    }
    public String getPriceToStr(){
        double dPrice = this.price/100;
        return String.format("%.2f", dPrice);
    }

    private void setPrice(Double price) {
        this.price = (int)(price*100);
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public String getQuantityToStr(){
        double dQuantity = this.quantity/100;
        return String.format("%.2f", dQuantity);
    }

    private void setQuantity(Double quantity) {
        this.quantity = (int)(price*100);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Integer getPrice() {
//        return price;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
    

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.color);
        hash = 41 * hash + Objects.hashCode(this.size);
        hash = 41 * hash + Objects.hashCode(this.quantity);
        hash = 41 * hash + Objects.hashCode(this.price);
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

    public void setPrice(String price) {
        try {
            int priceInt = Integer.parseInt(price);
            this.price = priceInt;
            System.out.println("Строка "+price+" успешно преобразована в число.");
        } catch (Exception e) {
            System.out.println("Введены не цифры. Поле не изменено");
        }
    }

    
}
    

    
    

