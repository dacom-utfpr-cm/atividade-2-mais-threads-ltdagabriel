package exercicio3;

import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Faça um programa em Java com threads que exiba os números primos entre 0 e 100000.
 *
 * @autor: Gabriel Choptian
 */
public class Main {
    private static boolean ehPrimo(int value) {
        for (int j = 2; j <= value; j++) {
            if (((value % j) == 0) && (j != value)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Grupo criado para verificar Threads ativas
        ThreadGroup group = new ThreadGroup("primos");
        ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
        for (int i = 1; i < 100000; i++) {
            list.add(i);
        }

        // Numero de Threads ativas simultaneas, (funciona?)
        int actives = 10;
        for (int i = 0; i < actives; i++) {
            new Thread(() -> {
                while (!list.isEmpty()) {
                    int value = list.poll();
                    if (ehPrimo(value)) {
                        System.out.println(value);
                    }
                }
            }).start();
        }

    }
}

