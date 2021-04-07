/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msg;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author ENVY
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/StringIntegerQ")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class IntegerConsumer implements MessageListener {
    
    @EJB
    private NumbersFacadeLocal numbersFacade;
    
    public IntegerConsumer() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage om = (ObjectMessage)message;
            try {
                Integer num = (Integer)om.getObject();
                numbersFacade.writeInteger(num);
            } catch (JMSException ex) {
                System.out.println("Ошибка извлечения числа из сообщения");
            }
        }
    }
    
}
