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
public class Buyer implements Serializable, EntityInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private Integer wallet;
    
    

    public Buyer() {
    }

    public Buyer(String firstname, String lastname, String phone, Integer wallet) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.wallet = wallet;
    }
        public Buyer(String firstname, String lastname, String phone, String wallet) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        setWallet(wallet);
    }

    
   
    
    

    
    

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }
    public void setWallet(String wallet) {
        try {
            int walletInt = Integer.parseInt(wallet);
            this.wallet = walletInt;
            System.out.println("Строка "+wallet+" успешно преобразована в число.");
        } catch (Exception e) {
            System.out.println("Введены не цифры. Поле не изменено");
        }
        
    }

    @Override
    public String toString() {
        return "Buyer{" 
                + "firstname=" + firstname 
                + ", lastname=" + lastname 
                + ", phone=" + phone
                + ", wallet=" + wallet
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.firstname);
        hash = 53 * hash + Objects.hashCode(this.lastname);
        hash = 53 * hash + Objects.hashCode(this.phone);
        hash = 53 * hash + Objects.hashCode(this.wallet);
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
        final Buyer other = (Buyer) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.wallet, other.wallet)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    
}
