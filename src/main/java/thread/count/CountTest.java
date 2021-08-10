package thread.count;

public class CountTest {
    private class ThreadCount extends Thread {
        private final Count count;

        public ThreadCount (final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }

    }
}
