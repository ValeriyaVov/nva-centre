package vov.nvacentre.bankcards;

import java.math.BigDecimal;
import java.util.Map;

public abstract class BankCard {

    public static final String BALANCE_KEY = "Balance";

    public abstract BigDecimal getBalance();

    public abstract void replenish(BigDecimal sum);

    public abstract boolean pay(BigDecimal sum);

    public abstract Map<String, BigDecimal> getFullBalanceInfo();

    public abstract void addToBalance(BigDecimal sum);
}
