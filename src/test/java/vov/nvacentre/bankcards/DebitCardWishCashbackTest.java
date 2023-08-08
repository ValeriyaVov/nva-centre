package vov.nvacentre.bankcards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class DebitCardWishCashbackTest {

    @Test
    void payWhenSumIsMoreThanMinimumCashbackSum() {
        DebitCardWithCashback debitCardWithCashback = new DebitCardWithCashback(new BigDecimal(15000), new BigDecimal(4));
        debitCardWithCashback.pay(new BigDecimal(10000));
        Assertions.assertEquals(new BigDecimal(400), debitCardWithCashback.getFullBalanceInfo().get(DebitCardWithCashback.CASHBACK));
    }

    @Test
    void payWhenSumIsLessThanMinimumCashbackSum() {
        DebitCardWithCashback debitCardWithCashback = new DebitCardWithCashback(new BigDecimal(15000), new BigDecimal(4));
        debitCardWithCashback.pay(new BigDecimal(4500));
        Assertions.assertEquals(BigDecimal.ZERO, debitCardWithCashback.getFullBalanceInfo().get(DebitCardWithCashback.CASHBACK));
    }

    @Test
    void payWhenPaymentFailed() {
        DebitCardWithCashback debitCardWithCashback = new DebitCardWithCashback(new BigDecimal(5000), new BigDecimal(4));
        Assertions.assertFalse(debitCardWithCashback.pay(new BigDecimal(7000)));
        Assertions.assertEquals(BigDecimal.ZERO, debitCardWithCashback.getFullBalanceInfo().get(DebitCardWithCashback.CASHBACK));
    }

    @Test
    void transferCashback() {
        DebitCardWithCashback debitCardWithCashback = new DebitCardWithCashback(new BigDecimal(15000), new BigDecimal(4));
        debitCardWithCashback.pay(new BigDecimal(6000));
        debitCardWithCashback.transferCashback();
        Assertions.assertEquals(new BigDecimal(9240), debitCardWithCashback.getBalance());
        Assertions.assertEquals(BigDecimal.ZERO, debitCardWithCashback.getFullBalanceInfo().get(DebitCardWithCashback.CASHBACK));
    }
}