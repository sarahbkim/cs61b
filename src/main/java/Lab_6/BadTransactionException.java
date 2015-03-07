package main.java.Lab_6;

/**
 * Created by sarahbkim on 3/6/15.
 */
public class BadTransactionException extends Exception {
    public int transactionAmount;
    public BadTransactionException(int badTransactionAmount) {
        super("Invalid transaction amount: " + badTransactionAmount);
        transactionAmount = badTransactionAmount;
    }
}
