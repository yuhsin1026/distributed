package MDB;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//@MessageDriven(name = "MessageNoticeEJB")
@MessageDriven(mappedName = "jms/javaee7/Topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")})
/*@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/javaee7/Topic")
        ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})

 */
public class MessageNoticeBean implements MessageListener {
    public MessageNoticeBean() {
    }

    @Override
    public void onMessage(Message message) {
      //  System.out.println("I am listening.......................");
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println(msg.getText());
        } catch (JMSException ex) {
           // Logger.getLogger(MessageNoticeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
