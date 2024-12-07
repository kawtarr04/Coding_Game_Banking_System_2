package bank;

public class BusinessAccount extends BankAccount {
    private static final double TRANSACTION_FEE = 3.65;

    public BusinessAccount(String accountHolderName, double initialBalance) {
        super(accountHolderName, initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        double totalAmount = amount + TRANSACTION_FEE;
        if (totalAmount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal. transaction fees are included.");
        }
        balance -= totalAmount;
        System.out.println("New balance after withdrawal: " + amount + " Transaction fee: " + TRANSACTION_FEE);
    }
}