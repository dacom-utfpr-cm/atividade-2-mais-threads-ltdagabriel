package exercicio5;

import java.util.Arrays;

/**
 * Código do merge sort obtido em: https://www.baeldung.com/java-merge-sort escrito por: baeldung
 * editado para fins da atividade e algumas alteraçoes depreciadas
 *
 * Faça um programa multithreaded em Java que ordene um vetor usando o Merge Sort recursivo.
 * Faça com que a thread principal dispare duas threads para classificar cada metade do vetor.
 *
 * @autor: Gabriel Choptian
 */
public class Main {
    /*
     * We'll write a mergeSort function which takes in the input array and its length
     */
    static void mergeSort(int[] a, int n) {
        /* The base condition checks if the array length is 1 and it will just return */
        if (n < 2) {
            return;
        }
        /*
         * For the recursive case, we get the middle index and create two temporary arrays l[] and r[].
         */
        int mid = n / 2;
        int[] l = new int[n / 2];
        int[] r = new int[n - (n / 2)];
        System.arraycopy(a, 0, l, 0, l.length);
        System.arraycopy(a, mid, r, 0, r.length);
        Thread threadl = new Thread(() -> mergeSort(l, l.length));
        Thread threadr = new Thread(() -> mergeSort(r, r.length));

        threadl.start();
        threadr.start();
        /*
         * We then call the merge function which takes in the input and both the sub-arrays and the starting
         * and end indices of both the sub arrays.
         */
        try {
            /* Aguarda as duas threads terminarem para chamar a função merge*/
            threadl.join();
            threadr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        merge(a, l, r, l.length, r.length);
    }

    /*
     * We then call the merge function which takes in the input and both the sub-arrays and the starting and
     * end indices of both the sub arrays.
     *
     * */
    private static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        /* The merge function compares the elements of both sub-arrays one by one and places the smaller element
         * into the input array.
         */
        while (i < left && j < right) {
            a[k++] = (l[i] <= r[j]) ? l[i++] : r[j++];
        }
        while (i < left) a[k++] = l[i++];

        while (j < right) a[k++] = r[j++];

    }


    public static void main(String[] args) {
        int[] actual = {5, 1, 6, 2, 3, 4, 52, 1, 4, -5};

        mergeSort(actual, actual.length);
        System.out.println(Arrays.toString(actual));
    }
}
