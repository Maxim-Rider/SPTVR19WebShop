/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoncovertors;

import entity.Furniture;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Comp
 */
public class JsonFurnitureBuilder {
    public JsonObject createJsonFurniture(Furniture furniture){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", furniture.getId())
                .add("name", furniture.getName())
                .add("color", furniture.getColor())
                .add("size", furniture.getSize())
                .add("price", furniture.getPrice())
                .add("quantity", furniture.getQuantity())
                .add("cover", new JsonCoverBuilder().createJsonCover(furniture.getCover()))
                .add("text", new JsonTextBuilder().createJsonText(furniture.getText()))
                .add("discount", furniture.getDiscount())
                .add("discountDuration", furniture.getDiscountDuration());
        if(furniture.getDiscountDate() == null){
            job.add("discountDate", "null");
        }else{
            job.add("discountDate", furniture.getDiscountDate().getTime());
        }
                
        return job.build();
    }
}
