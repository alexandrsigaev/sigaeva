package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class Billing {
    private Map<User, List<Account>> clients = new TreeMap<>();

    public void addUser(User user) {
        clients.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        clients.remove(user);
    }

    public void addAccountToUser(String passport, Account account) throws NotFoundUserException {
        User user = this.getUserByPassport(passport);
        if (user.equals(null)) {
            throw new NotFoundUserException("User not found");
        } else {
            this.clients.get(user).add(account);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) throws NotFoundUserException {
        User user = this.getUserByPassport(passport);
        if (user.equals(null)) {
            throw new NotFoundUserException("User not found");
        } else {
            this.clients.get(user).remove(account);
        }
    }

    public List<Account> getUserAccounts(String passport) throws NotFoundUserException {
        List<Account> result = new ArrayList<>();
        User user = this.getUserByPassport(passport);
        if (user.equals(null)) {
            throw new NotFoundUserException("User not found");
        } else {
            result = clients.get(user);
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        try {
            Account srcAccount = getAccountByRequsites(srcRequisite, getUserAccounts(srcPassport));
            Account dstAccount = getAccountByRequsites(dstRequisite, getUserAccounts(dstPassport));
            if (srcAccount.getValue() < amount) {
                throw new InsufficientFundsException("insufficient funds");
            } else {
                srcAccount.setValue(srcAccount.getValue() - amount);
                dstAccount.setValue(dstAccount.getValue() + amount);
                result = true;
            }

        } catch (NotFoundAccountException e) {
            e.printStackTrace();
        } catch (NotFoundUserException e) {
            e.printStackTrace();
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUserByPassport(String passport) {
        User user = null;
        for (User client : this.clients.keySet()) {
            if (passport.equals(client.getPassport())) {
                user = client;
                break;
            }
        }
        return user;
    }

    private Account getAccountByRequsites(String requisite, List<Account> accounts) throws NotFoundAccountException {
        Account result = null;

        for (Account account : accounts) {
            if (account.getRequsites() == requisite) {
                result = account;
                break;
            }
        }
        if (result.equals(null)) {
            throw new NotFoundAccountException("Account not found");
        }
        return result;
    }

}
