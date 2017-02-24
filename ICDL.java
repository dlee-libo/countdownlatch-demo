
public class ICDL {
    private int count;
    private Object cond = new Object();

    public ICDL(int n) {
        count = n;
    }

    public void countDown() {
        synchronized (cond) {
            if (--count == 0) {
                cond.notifyAll();
            }
        }
    }

    public void await() throws InterruptedException {
        synchronized (cond) {
            while (count != 0) {
                cond.wait();
            }
        }
    }
}

