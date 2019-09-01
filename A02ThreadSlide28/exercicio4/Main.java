package exercicio4;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Faça um programa em Java que realize uma busca paralela em um vetor de inteiros.
 * Informe para o método: valor procurado, vetor de inteiros e o número de threads.
 *
 * @autor: Gabriel Choptian
 */
public class Main {
    static class Obj {
        private final int value;
        private final int index;

        Obj(int index, int value) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }
    }

    static int buscaParalela(int valor, int[] vetor, int threads) {
        int intervalo = vetor.length / threads;

//        AtomicInteger res = new AtomicInteger(-1);
        AtomicInteger res = new AtomicInteger(-1);
        ThreadGroup group = new ThreadGroup("busca");
        for (int i = 0; i < threads; i++) {
            int finalI1 = i;
            new Thread(group, () -> {
                try {
                    int finalI = finalI1;
                    while (finalI * intervalo < (finalI + 1) * intervalo && finalI < vetor.length) {
                        if (vetor[finalI] == valor) {
                            group.interrupt();
                            res.set(finalI);
                            throw new InterruptedException("Valor encontrado!");
                        }
                        finalI++;
                    }
                } catch (InterruptedException ignored) {
                }
            }).start();
        }
        while (group.activeCount() > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return res.get();
    }

    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 3, 5, 6, 7, 8, 9, 1, 23, 24};
        System.out.println(buscaParalela(6, values, 5));
    }
}


