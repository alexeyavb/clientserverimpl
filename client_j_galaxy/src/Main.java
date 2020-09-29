import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // запустим пул нитей в которых колличество возможных нитей ограничено -
        // 10-ю.
        ExecutorService exec = Executors.newFixedThreadPool(10);
        int j = 0;

        // стартуем цикл в котором с паузой в 10 милисекунд стартуем Runnable
        // клиентов,
        // которые пишут какое-то количество сообщений
        while (j < 30) {
            j++;
            exec.execute(new TestRunnableClientTester());
            //Thread.sleep(12);
        }

        // закрываем фабрику
        exec.shutdown();
    }
}

