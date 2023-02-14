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
        if(accountDAO.getAccountById(account.getAccount_id()) != null){
            return null;
        }
        return accountDAO.insertAccount(account);
    }
}


