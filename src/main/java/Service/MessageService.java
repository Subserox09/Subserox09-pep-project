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

    public Message getMessageByMessageID(int message_id){
       
        return messageDAO.getMessageByMessageId(message_id);
    }

    public List<Message> getMessageByAccountID(int message_id){
        return messageDAO.getMessageByAccountId(message_id);

    }

    public Message deleteMessageByID(int messageID){
        return messageDAO.deleteMessageByMessageId(messageID);
    }

    public Message updateMessagebyID(int id, Message message){

        return messageDAO.updateMessageByMessageId(id, message);
    }
}
