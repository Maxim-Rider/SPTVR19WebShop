/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.json;

/**
 *
 * @author Comp
 */
import java.io.Closeable;

public interface JsonBuyer extends Closeable {

    public JsonStructure read();

    public JsonObject readObject();

    public JsonArray readArray();

    public void close();
}
