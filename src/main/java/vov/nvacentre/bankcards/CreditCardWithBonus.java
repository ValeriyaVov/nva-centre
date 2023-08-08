package vov.nvacentre.bankcards;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CreditCardWithBonus extends CreditCard{
    public static final String BONUS = "Bonus";
    private BigDecimal bonus;
    private final BigDecimal bonusPercent;


    public CreditCardWithBonus(BigDecimal balance, BigDecimal creditMax, BigDecimal bonusPercent) {
        super(balance, creditMax);
        this.bonusPercent = bonusPercent;
        this.bonus = BigDecimal.ZERO;
    }

    @Override
    public boolean pay(BigDecimal sum) {
        boolean isPaymentSuccessful = super.pay(sum);
        if (isPaymentSuccessful) {
            bonus = sum.divide(new BigDecimal(100), RoundingMode.FLOOR).multiply(bonusPercent);
        }
        return isPaymentSuccessful;
    }

    @Override
    public Map<String, BigDecimal> getFullBalanceInfo() {
        return Map.of(BALANCE_KEY, getBalance(), CREDIT_LIMIT, creditLimit, CREDIT_MAX, creditMax, BONUS, bonus);
    }
}
