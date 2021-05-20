/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.json.JsonValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Comp
 */
@Entity
@XmlRootElement

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

   public int getWallet() {
        return wallet;
    }
    public String getWalletStr() {
        Double dWallet = (double)this.wallet/100;
        return dWallet.toString();
    }
    public double getWalletDouble() {
        return (double)this.wallet/100;
    }
    

    public void setWallet(String wallet) throws NumberFormatException {
        wallet = wallet.trim().replaceAll(",", ".");
        try {
            double dWallet = Double.parseDouble(wallet);
            this.wallet = (int)(dWallet * 100);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неправильный формат числа");
        }
    }
    public void setWallet(double wallet) {
        this.wallet = (int)(wallet * 100);
    }
}
   