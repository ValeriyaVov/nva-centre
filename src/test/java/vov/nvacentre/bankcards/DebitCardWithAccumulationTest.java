package vov.nvacentre.bankcards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class DebitCardWithAccumulationTest {

    @Test
    void replenish() {
        DebitCardWithAccumulation debitCardWithAccumulation = new DebitCardWithAccumulation(new BigDecimal(150), new BigDecimal("0.005"));
        debitCardWithAccumulation.replenish(new BigDecimal(6000));
        Assertions.assertEquals(new BigDecimal("30.000"), debitCardWithAccumulation.getFullBalanceInfo().get(DebitCardWithAccumulation.ACCUMULATION));
    }

    @Test
    void transferAccumulation() {
        DebitCardWithAccumulation debitCardWithAccumulation = new DebitCardWithAccumulation(new BigDecimal(150), new BigDecimal("0.005"));
        debitCardWithAccumulation.replenish(new BigDecimal(6000));
        debitCardWithAccumulation.transferAccumulation();
        Assertions.assertEquals(new BigDecimal("6180.000"), debitCardWithAccumulation.getBalance());
        Assertions.assertEquals(BigDecimal.ZERO, debitCardWithAccumulation.getFullBalanceInfo().get(DebitCardWithAccumulation.ACCUMULATION));
    }
}