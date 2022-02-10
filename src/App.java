import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {// поток, временно выделяющий и освобождающий ~100 Mb памяти
            while (true) {
                List<byte[]> bytes = new LinkedList<>();
                for (int i = 0; i < 100; i++) {
                    bytes.add(new byte[1024 * 1024]);
                }
            }
        });


        Thread t2 = new Thread(() -> { // поток, распечатывающий время, прошедшее за время секундной паузы
            while (true) {
                long start = System.currentTimeMillis();
                sleep(1000);// остановка потока
                System.out.println(System.currentTimeMillis() - start);// отобразим время с момента предыдущего сохранения времени
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}