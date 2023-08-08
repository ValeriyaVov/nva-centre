package vov.nvacentre.bankcards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class DebitCardTest {

    @Test
    void replenish() {
        DebitCard debitCard = new DebitCard(new BigDecimal(7000));
        debitCard.replenish(new BigDecimal(3000));
        Assertions.assertEquals(new BigDecimal(10000), debitCard.getBalance());
    }

    @Test
    void pay() {
        DebitCard debitCard = new DebitCard(new BigDecimal(7000));
        debitCard.pay(new BigDecimal(2000));
        Assertions.assertEquals(new BigDecimal(5000), debitCard.getBalance());
    }

    @Test
    void failedPay() {
        DebitCard debitCard = new DebitCard(new BigDecimal(7000));
        Assertions.assertFalse(debitCard.pay(new BigDecimal(7500)));
        Assertions.assertEquals(new BigDecimal(7000), debitCard.getBalance());
    }

    @Test
    void getFullBalanceInfo() {
        DebitCard debitCard = new DebitCard(new BigDecimal(1000));
        BigDecimal balance = debitCard.getFullBalanceInfo().get(CreditCard.BALANCE_KEY);
        Assertions.assertEquals(new BigDecimal(1000), balance);
    }
}