package thread;

public class ThreadState {
    public static void main(String[] args) {
        /*
        Thread first = new Thread(
                () -> {}
        );
        System.out.println(first.getState());
        first.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        System.out.println(first.getState());

         */
        Thread one = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        one.start();
        Thread two = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        two.start();
        System.out.println(Thread.currentThread().getState());
    }
}
