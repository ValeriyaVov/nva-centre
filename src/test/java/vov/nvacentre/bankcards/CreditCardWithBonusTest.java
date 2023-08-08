package vov.nvacentre.bankcards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CreditCardWithBonusTest {

    @Test
    void pay() {
        CreditCardWithBonus creditCardWithBonus = new CreditCardWithBonus(BigDecimal.ZERO, new BigDecimal(30000), new BigDecimal(3));
        creditCardWithBonus.pay(new BigDecimal(10000));
        Assertions.assertEquals(new BigDecimal(300), creditCardWithBonus.getFullBalanceInfo().get(CreditCardWithBonus.BONUS));
    }

    @Test
    void failedPay() {
        CreditCardWithBonus creditCardWithBonus = new CreditCardWithBonus(BigDecimal.ZERO, new BigDecimal(30000), new BigDecimal(3));
        creditCardWithBonus.pay(new BigDecimal(50000));
        Assertions.assertEquals(BigDecimal.ZERO, creditCardWithBonus.getFullBalanceInfo().get(CreditCardWithBonus.BONUS));

    }
}