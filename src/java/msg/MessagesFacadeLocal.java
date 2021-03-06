/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msg;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ENVY
 */
@Local
public interface MessagesFacadeLocal {

    void create(Messages messages);

    void edit(Messages messages);

    void remove(Messages messages);

    Messages find(Object id);

    List<Messages> findAll();

    List<Messages> findRange(int[] range);

    int count();
    
    //-----------------------------------
    int cleanMessages();
    boolean writeMessage(String message);
    ArrayList<String> getMessageList();
    

    
}
