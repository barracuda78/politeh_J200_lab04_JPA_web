package msg;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/StringStringQ")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class StringConsumer implements MessageListener {
    
//    @EJB
//    private DbMasterLocal dbMaster;
    
    @EJB
    private MessagesFacadeLocal jpaMaster;
    
    
    public StringConsumer() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                String mg = ((TextMessage) message).getText();
                System.out.println("bean StringConsumer: method onMessage: мы перехватили сообщение.");
                //dbMaster.writeMessage(mg);
                jpaMaster.writeMessage(mg);
            } catch (JMSException ex) {
                System.out.println("------Ошибка в методе onMessage бина StringConsumer");
            }
        }else{
            System.out.println("------StringConsumer получил левое сообщение");
        }
    }
    
}
