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

    public Message getSpecificMessage(int message_id){
        if(messageDAO.getMessageById(message_id) == null){
            return null;
        }
        return messageDAO.getMessageById(message_id);
    }
}
