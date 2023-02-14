package Service;

import DAO.AccountDAO;
import Model.Account;
//import java.util.List;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account) {
        /*if(accountDAO.getAccountById(account.getAccount_id()) != null){
            return null;
        }*/
        return accountDAO.insertAccount(account);
    }

    public Account getAccount(Account account){
       /*  if(accountDAO.getAccountDetails(account.getUsername(), account.getPassword()) == null){
            return null;
        }*/
        return accountDAO.getAccountDetails(account.getUsername(), account.getPassword());
    }
}


