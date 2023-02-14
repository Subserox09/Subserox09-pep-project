package Controller;

import Model.Account;
//import Model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import Service.AccountService;
//import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;

    //MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        //this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.get("/messages", this::getAllMessagesHandler);
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
   /*  private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json("sample text");
    }*/

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
                ctx.status(200);           
        }else{
            ctx.status(401);
        }
    }


}