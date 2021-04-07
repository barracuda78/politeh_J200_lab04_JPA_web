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
public interface NumbersFacadeLocal {

    void create(Numbers numbers);

    void edit(Numbers numbers);

    void remove(Numbers numbers);

    Numbers find(Object id);

    List<Numbers> findAll();

    List<Numbers> findRange(int[] range);

    int count();
    
        
    boolean writeInteger(Integer number);
    ArrayList<Integer> getNumbers();
    int getTotal();
    int cleanNumbers();
}
