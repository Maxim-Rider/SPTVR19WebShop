/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoncovertors;

import entity.Buyer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Comp
 */
public class JsonBuyerBuilder {
    public JsonObject createJsonBuyer(Buyer buyer){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", buyer.getId())
                .add("firstname", buyer.getFirstname())
                .add("lastname", buyer.getLastname())
                .add("phone", buyer.getPhone())
                .add("wallet", buyer.getWallet());
        return job.build();
    }
}
