package exercicio1;

public class Main {
    /**
     * Faça um programa em Java que consulte periodicamente o
     * estado de um conjunto de extras.threads.
     *
     * @autor: Gabriel Choptian
     */
    public static void main(String[] args) {

        // Grupo criado para monitorar quantas Threads ativas
        ThreadGroup group = new ThreadGroup("monitor");

        // Cria um vetor de Threads
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            // Instancia cada Thread e atrinui ao grupo
            threads[i] = new Thread(group, () -> {
                try {
                    System.out.println("Inicializando Thread");
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // Inicialza a Thread
            threads[i].start();
        }
        // Instancia a Thread de monitoramento de estados
        Thread t = new MonitoraListaThreads(threads);
        t.start();

        // Caso não aja mais Threads ativas para monitorar finaliza a thread de monitoramento
        while (true) {
            try {
                Thread.sleep(200);
                if (group.activeCount() == 0) {
                    t.interrupt();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

