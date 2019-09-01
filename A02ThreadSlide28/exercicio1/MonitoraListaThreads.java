package exercicio1;

class MonitoraListaThreads extends Thread {
    private Thread[] threads;

    MonitoraListaThreads(Thread[] threads) {
        this.threads = threads;
    }

    @Override
    public void run() {

        try {
            while (true) {
                for (Thread t : threads) {
                    System.out.println(t.getState().toString());
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Finalizado por Interrupção");
        }
    }
}
