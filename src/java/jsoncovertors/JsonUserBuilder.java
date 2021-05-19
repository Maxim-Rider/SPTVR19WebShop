/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoncovertors;

import entity.User;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Comp
 */
public class JsonUserBuilder {
    public JsonObject createJsonUser(User user){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", user.getId())
                .add("login", user.getLogin())
                .add("buyer", new JsonBuyerBuilder().createJsonBuyer(user.getBuyer()));
        return job.build();
    }
}
