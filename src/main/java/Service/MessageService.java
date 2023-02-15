package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }
    
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message){
        return messageDAO.insertMessage(message);
    }

    public Message getMessageaByMessageID(int message_id){
        if(messageDAO.getMessageByMessageId(message_id) == null){
            return null;
        }
        return messageDAO.getMessageByMessageId(message_id);
    }

    public List<Message> getMessageByAccountID(int message_id){
        if(messageDAO.getMessageByAccountId(message_id)== null){
            return null;
        }
        return messageDAO.getMessageByAccountId(message_id);

    }
}
