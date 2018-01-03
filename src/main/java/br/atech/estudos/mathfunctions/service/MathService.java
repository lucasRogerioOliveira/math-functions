package br.atech.estudos.mathfunctions.service;

public class MathService {

    public static boolean isPrimeNumber(final long number) {
        if (number > 1) {
            for (long i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int calculateLenghtOfPrimeNumbers(final long from, final long to) {
        int primeLength = 0;
        for (long i = from; i <= to; i++) {
            primeLength += isPrimeNumber(i) ? 1 : 0;
        }
        return primeLength;
    }
}
