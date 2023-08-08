package vov.nvacentre.bankcards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CreditCardTest {

    @Test
    void replenish() {
        CreditCard creditCard = new CreditCard(new BigDecimal(50000), new BigDecimal(30000));
        creditCard.replenish(new BigDecimal(2000));
        Assertions.assertEquals(new BigDecimal(52000), creditCard.getBalance());
    }

    @Test
    void replenishCreditAccount() {
        CreditCard creditCard = new CreditCard(BigDecimal.ZERO, new BigDecimal(30000));
        creditCard.pay(new BigDecimal(10000));
        creditCard.replenish(new BigDecimal(2000));
        Assertions.assertEquals(new BigDecimal(22000), getCreditLimit(creditCard));
        Assertions.assertEquals(BigDecimal.ZERO, creditCard.getBalance());
    }

    @Test
    void replenishCreditAndBalance(){
        CreditCard creditCard = new CreditCard(BigDecimal.ZERO, new BigDecimal(30000));
        creditCard.pay(new BigDecimal(10000));
        creditCard.replenish(new BigDecimal(33000));
        Assertions.assertEquals(new BigDecimal(30000), getCreditLimit(creditCard));
        Assertions.assertEquals(new BigDecimal(23000), creditCard.getBalance());
    }

    @Test
    void pay() {
        CreditCard creditCard = new CreditCard(new BigDecimal(50000), new BigDecimal(30000));
        creditCard.pay(new BigDecimal(2000));
        Assertions.assertEquals(new BigDecimal(48000), creditCard.getBalance());
    }

    @Test
    void payWithCreditAccount(){
        CreditCard creditCard = new CreditCard(BigDecimal.ZERO, new BigDecimal(30000));
        creditCard.pay(new BigDecimal(16000));
        Assertions.assertEquals(new BigDecimal(14000), getCreditLimit(creditCard));
        Assertions.assertEquals(BigDecimal.ZERO, creditCard.getBalance());
    }

    @Test
    void payWithCreditAndBalance(){
        CreditCard creditCard = new CreditCard(new BigDecimal(4000), new BigDecimal(30000));
        creditCard.pay(new BigDecimal(25000));
        Assertions.assertEquals(new BigDecimal(9000), getCreditLimit(creditCard));
        Assertions.assertEquals(BigDecimal.ZERO, creditCard.getBalance());
    }

    @Test
    void failedPaymentAttempt(){
        CreditCard creditCard = new CreditCard(new BigDecimal(1000), new BigDecimal(30000));
        Assertions.assertFalse(creditCard.pay(new BigDecimal(32000)));
        Assertions.assertEquals(new BigDecimal(30000), getCreditLimit(creditCard));
        Assertions.assertEquals(new BigDecimal(1000), creditCard.getBalance());
    }

    @Test
    void getFullBalanceInfo(){
        CreditCard creditCard = new CreditCard(new BigDecimal(1000), new BigDecimal(30000));
        BigDecimal balance = creditCard.getFullBalanceInfo().get(CreditCard.BALANCE_KEY);
        Assertions.assertEquals(new BigDecimal(1000), balance);
    }
   private static BigDecimal getCreditLimit(CreditCard creditCard) {
       return creditCard.getFullBalanceInfo().get(CreditCard.CREDIT_LIMIT);
   }
}