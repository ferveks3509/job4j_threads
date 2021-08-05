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
        while (one.getState() != Thread.State.TERMINATED) {
            System.out.println(one.getState());
        }
        System.out.println(one.getState());
        Thread two = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        two.start();
        while (two.getState() != Thread.State.TERMINATED) {
            System.out.println(two.getState());
        }
        System.out.println(two.getState());
        System.out.println(Thread.currentThread().getState());

    }
}
