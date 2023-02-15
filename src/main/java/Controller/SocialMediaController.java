package Controller;

import Model.Account;
import Model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;

    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.get("/accounts/{first}/messages",this::getMessageByAccountIDHandler)
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{first}", this::getMessageByMessageIDHandler);
        app.post("/messages", this::postMessageHandler);
        

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    public void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage != null){
            if(addedMessage.getMessage_text().length() > 255){
                ctx.status(400);
            }else if(addedMessage.getMessage_text().length() <= 255 && !(addedMessage.getMessage_text().equals(""))){
                ctx.json(mapper.writeValueAsString(addedMessage));
                ctx.status(200);
            }else{
                ctx.status(400);
            }
         
        }else{
            ctx.status(400);
        }
    }

    public void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
        
    }

    public void getMessageByAccountIDHandler(Context context){
        String id = context.pathParam("first");
        int accountId = Integer.ParseInt(id);
        List<Message> messages = messageService.getMessageByAccountId(accountId);

        if(messages != null){
            context.json(messages);
            context.status(200);
        }else{
            context.json(messages);
            context.status(400);
            
        }
    }

    public void getMessageByMessageIDHandler(Context context){
        String id = context.pathParam("first");
        int messageId = Integer.parseInt(id);
        Message message = messageService.getMessageByMessageID(messageId);

        if(message != null){
            context.json(message);
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount!=null){
            if(!account.getUsername().equals("")){
                ctx.json(mapper.writeValueAsString(addedAccount));
                ctx.status(200);
            }else{
                ctx.status(400);

            }

            if(account.getPassword().length() < 4){
                ctx.status(400);
            }
            
        }else{
            ctx.status(400);
        }
    }

    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account searchForAccount = accountService.getAccount(account);
        if(searchForAccount!=null){
            ctx.json(mapper.writeValueAsString(searchForAccount));
                ctx.status(200);           
        }else{
            ctx.status(401);
        }
    }


}