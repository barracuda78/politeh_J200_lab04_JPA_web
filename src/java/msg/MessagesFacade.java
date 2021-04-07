package msg;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Stateless
public class MessagesFacade extends AbstractFacade<Messages> implements MessagesFacadeLocal {

    @PersistenceContext(unitName = "j200JPAwebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessagesFacade() {
        super(Messages.class);
    }
    
    //-----------------------------------------
        @Override
    public ArrayList<String> getMessageList(){
        //StringBuilder sb = new StringBuilder();
        TypedQuery<Messages> typedQuery = em.createNamedQuery("Messages.findAll", Messages.class);
        List<Messages> list = typedQuery.getResultList();
        ArrayList<String> l = new ArrayList<>(list.size());
        //sb.append("<ul>\n");
        for(Messages m : list){
            l.add(m.getMessage());
        }
        //sb.append("</ul>\n");
        //return sb.toString();
        return l;
    }
    

    
    @Override
    public boolean writeMessage(String message) {
        if(message == null)
            return false;
        Messages m = new Messages(message);
        if(em.contains(m)){
            return false;
        }
        Messages tmp = em.find(Messages.class, m.getMessage());
        if(tmp == null){
            em.persist(m);
            em.merge(m);
            return true;
        }
        return false;
    }
    

    
    @Override
    public int cleanMessages(){
        //Query q = em.createNamedQuery("Messages.cleanMessages");
        //em.createNamedQuery("Messages.cleanMessages", Messages.class);
        //return q.executeUpdate();                       //--------------------> Может быть тут ошибка?
        
        List<Messages> list = findAll();
        for(Messages m : list){
            remove(m);
        }
        return 1;
    }
    
    //===================
}
