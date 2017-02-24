public class Demo {

    public static void main(String argv[]) {
        ICDL doneSignal = new ICDL(3);
        for (int i = 0; i < 3; i++) {
            Worker x = new Worker(i+1, doneSignal);
            x.start();
        }
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("OK. All finished");
    }

    private static class Worker extends Thread {
        private int n;
        private ICDL doneSignal;

        public Worker(int n, ICDL doneSignal) {
            this.n = n;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(n*1000);
                System.out.println("done my work, " + n);
                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

