package vov.nvacentre.bankcards;

import java.math.BigDecimal;
import java.util.Map;

public class CreditCard extends BankCard {
    public static final String CREDIT_LIMIT = "CreditLimit";
    public static final String CREDIT_MAX = "CreditMax";

    private BigDecimal balance;

    protected BigDecimal creditLimit;
    protected final BigDecimal creditMax;


    public CreditCard(BigDecimal balance, BigDecimal creditMax) {
        this.creditLimit = creditMax;
        this.creditMax = creditMax;
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void replenish(BigDecimal sum) {
        final BigDecimal debt = creditMax.subtract(creditLimit);
        // выбираем минимальное значение - либо долг, либо сумму пополнения, чтобы погасить долг на эту величину
        final BigDecimal amountToReplenishCredit = sum.compareTo(debt) < 0 ? sum : debt;
        creditLimit = creditLimit.add(amountToReplenishCredit);
        // пополняем баланс на остаток после погашения задолженности
        sum = sum.subtract(amountToReplenishCredit);
        balance = balance.add(sum);
    }

    @Override
    public boolean pay(BigDecimal sum) {
        if (sum.compareTo(balance) <= 0) {
            balance = balance.subtract(sum);
        } else {
            final BigDecimal amountToPayFromBalance = balance;
            final BigDecimal remainingSum = sum.subtract(balance);
            balance = BigDecimal.ZERO;

            if (creditLimit.compareTo(remainingSum) >= 0) {
                creditLimit = creditLimit.subtract(remainingSum);
            } else {
                System.out.println("Недостаточно средств");
                balance = amountToPayFromBalance; // откатываем списание баланса
                return false;
            }
        }

        return true;
    }

    @Override
    public Map<String, BigDecimal> getFullBalanceInfo() {
        return Map.of(BALANCE_KEY, balance, CREDIT_LIMIT, creditLimit, CREDIT_MAX, creditMax);
    }

    @Override
    public void addToBalance(BigDecimal sum) {
        balance = balance.add(sum);
    }
}
