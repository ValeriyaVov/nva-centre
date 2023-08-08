package vov.nvacentre.bankcards;

import java.math.BigDecimal;
import java.util.Map;

public class DebitCard extends BankCard {

    private BigDecimal balance;

    public DebitCard(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void replenish(BigDecimal sum) {
        balance = balance.add(sum);
    }

    @Override
    public boolean pay(BigDecimal sum) {
        if (balance.compareTo(sum) < 0) {
            return false;
        }
        balance = balance.subtract(sum);
        return true;
    }

    public Map<String, BigDecimal> getFullBalanceInfo() {
        return Map.of(BALANCE_KEY, balance);
    }

    @Override
    public void addToBalance(BigDecimal sum) {
        balance = balance.add(sum);
    }

}
