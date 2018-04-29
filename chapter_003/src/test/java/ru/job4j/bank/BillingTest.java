package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class BillingTest {

    @Test
    public void whenAddUserWithAccountThenGetUserAccounts() {
        Billing billing = new Billing();
        User user001 = new User();
        user001.setName("Ben");
        user001.setPassport("1234567890a");
        Account account = new Account();
        account.setRequsites("78ue7896");
        account.setValue(200);
        billing.addUser(user001);
        try {
            billing.addAccountToUser(user001.getPassport(), account);
            List<Account> result = new ArrayList<>();
            result.add(account);
            assertThat(billing.getUserAccounts(user001.getPassport()), is(result));
        } catch (NotFoundUserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDelUserAccountThenGetUserWithoutThisAccounts() {
        Billing billing = new Billing();
        User user001 = new User();
        user001.setName("Ben");
        user001.setPassport("1234567890a");
        Account account01 = new Account();
        account01.setRequsites("78ue7896");
        account01.setValue(200);
        billing.addUser(user001);
        Account account02 = new Account();
        account02.setRequsites("78ue7835");
        account02.setValue(1100);
        billing.addUser(user001);
        try {
            billing.addAccountToUser(user001.getPassport(), account01);
            billing.addAccountToUser(user001.getPassport(), account02);
            billing.deleteAccountFromUser(user001.getPassport(), account01);
            List<Account> result = new ArrayList<>();
            result.add(account02);
            assertThat(billing.getUserAccounts(user001.getPassport()), is(result));
        } catch (NotFoundUserException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void whenTransferMoney() {
        Billing billing = new Billing();
        User user001 = new User();
        user001.setName("Ben");
        user001.setPassport("1234567890a");
        User user002 = new User();
        user002.setName("Ivan");
        user002.setPassport("12345678563");
        Account account01 = new Account();
        account01.setRequsites("78ue7896");
        account01.setValue(200);
        Account account02 = new Account();
        account02.setRequsites("78ue7835");
        account02.setValue(1100);
        billing.addUser(user001);
        billing.addUser(user002);
        try {
            billing.addAccountToUser(user001.getPassport(), account01);
            billing.addAccountToUser(user002.getPassport(), account02);

            assertThat(billing.transferMoney(user001.getPassport(), account01.getRequsites(),
                    user002.getPassport(), account02.getRequsites(), 10), is(true));
        } catch (NotFoundUserException e) {
            e.printStackTrace();
        }

    }

}
