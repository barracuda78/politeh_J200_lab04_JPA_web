/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msg;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ENVY
 */
@Stateless
public class NumbersFacade extends AbstractFacade<Numbers> implements NumbersFacadeLocal {

    @PersistenceContext(unitName = "j200JPAwebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NumbersFacade() {
        super(Numbers.class);
    }
    
    @Override
    public boolean writeInteger(Integer number) {
        if(number == null)
            return false;
        Numbers n = new Numbers(number);
        if(em.contains(n)){
            return false;
        }
        Numbers tmp = em.find(Numbers.class, n.getNumber());
        if(tmp == null){
            em.persist(n);
            em.merge(n);
            return true;
        }
        return false;
    }
    
    @Override
    public ArrayList<Integer> getNumbers(){
//        TypedQuery<Numbers> typedQueryNumbers = em.createNamedQuery("Numbers.findAll", Numbers.class);
//        List<Numbers> list = typedQueryNumbers.getResultList();
//        ArrayList<Integer> l = new ArrayList<>(list.size());
//        for(Numbers n : list){
//            l.add(n.getNumber());
//        }
//        return l;

          List<Numbers> list = findAll();
          ArrayList<Integer> l = new ArrayList<>(list.size());
          for(Numbers n : list){
               l.add(n.getNumber());
          }
          return l;
    }
    
    @Override
    public int getTotal(){
        ArrayList<Integer> list = getNumbers();
        //return list.stream().mapToInt(x -> x).sum();
        int sum = 0;
        for(Integer i : list){
            sum += i;
        }
        return sum;
    }
    
//    @Override
//    public int cleanNumbers() {
//        Query q = em.createNamedQuery("Messages.cleanNumbers");
//        return q.executeUpdate();                        //--------------------> Может быть тут ошибка?
//    }
}
