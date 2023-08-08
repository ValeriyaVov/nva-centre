package vov.nvacentre.bankcards;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class DebitCardWithCashback extends DebitCard{
    public static final String CASHBACK = "CashBack";
    private static final BigDecimal minimumSumCashback = new BigDecimal(5000);
    private BigDecimal cashback = BigDecimal.ZERO;
    private final BigDecimal cashbackPercent;
    private BigDecimal expense = BigDecimal.ZERO;

    public DebitCardWithCashback(BigDecimal balance, BigDecimal cashbackPercent) {
        super(balance);
        this.cashbackPercent = cashbackPercent;
    }

    @Override
    public boolean pay(BigDecimal sum) {
        if (!super.pay(sum)) {
            return false;
        }
        expense = sum.add(expense);
        if (expense.compareTo(minimumSumCashback) > 0) {
            cashback = sum.divide(new BigDecimal(100), RoundingMode.FLOOR).multiply(cashbackPercent);
        }
        return true;
    }

    public void transferCashback() {
        addToBalance(cashback);
        cashback = BigDecimal.ZERO;
        expense = BigDecimal.ZERO;
    }

    @Override
    public Map<String, BigDecimal> getFullBalanceInfo() {
        return Map.of(BALANCE_KEY, getBalance(), CASHBACK, cashback);
    }
}
