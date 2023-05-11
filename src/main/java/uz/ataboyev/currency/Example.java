package uz.ataboyev.currency;

import static uz.ataboyev.currency.Demo.getCurrencyRate;

public class Example {
    public static void main(String[] args) {

        String currencyRate = getCurrencyRate();

        System.out.println(currencyRate);
    }
}
