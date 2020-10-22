package Session_Bean;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless(name = "MessagesenderEJB",mappedName = "MessagesenderEJB")
public class Messagesender {
    @Resource(mappedName = "jms/javaee7/Topic")
   // private Queue demoPublishDest;
    private Destination destination;
    //provide connection factory obj for the particular queue
    @Resource(mappedName = "jms/javaee7/ConnectionFactory")
   // private ConnectionFactory queue;
    private QueueConnectionFactory  connFactory;

     public Messagesender(){

       }

    public void ableToSend()
    {
        String text= " Message sender is constructed. and it is now able to send message";
        try {
            this.send(text);
        } catch (JMSException ex) {
         //   Logger.getLogger(Messagesender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String message) throws JMSException
    {

        Connection connection =connFactory.createConnection();
        Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer;
        messageProducer = (MessageProducer)session.createProducer( destination);

        TextMessage textMessage = session.createTextMessage();

        textMessage.setText(message);
//      textMessage.setStringProperty("ID","1");

        messageProducer.send(textMessage);

    }

}
