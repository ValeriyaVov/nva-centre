package vov.nvacentre.bankcards;

import java.math.BigDecimal;
import java.util.Map;

public class DebitCardWithAccumulation extends DebitCard{
    public static final String ACCUMULATION = "Accumulation";
    private static final BigDecimal accumulationPercent = new BigDecimal("0.005");
    private BigDecimal accumulation;

    public DebitCardWithAccumulation(BigDecimal balance, BigDecimal accumulation) {
        super(balance);
        this.accumulation = accumulation;
    }

    @Override
    public void replenish(BigDecimal sum) {
        accumulation = sum.multiply(accumulationPercent);
        super.replenish(sum);
    }

    public void transferAccumulation() {
        addToBalance(accumulation);
        accumulation = BigDecimal.ZERO;
    }

    @Override
    public Map<String, BigDecimal> getFullBalanceInfo() {
        return Map.of(BALANCE_KEY, getBalance(), ACCUMULATION, accumulation);
    }
}
