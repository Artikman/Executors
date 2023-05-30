import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorApp {

    /**
     * Существует три интерфейса, которая помогают не создавать заново новые потоки, а работать и
     * переиспользовать уже существующие, чтобы сэкономить ресурсы Java-приложения и драгоценное время
     * при разработке : Executor, ExecutorService, ScheduledExecutorService.
     * Executor - добавляет функциональность для управления ЖЦ потоков, содержит метод execute()
     * для запуска задачи, запускаемым объектом Runnable
     * ExecutorService - содержит дополнительный метод схожий на execute(), но более универсальный submit()
     * Этот метод может принимать как объект Runnable, так и объект Callable, если что-то нужно вернуть
     * ScheduledExecutorService - планирование выполнения задач в коде
     * shutdown() либо shutdownNow()
     */

//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int i = 1; i <= 5; i++) {
//            Runnable worker = new MyRunnable("Task" + i);
//            executorService.execute(worker);
//        }
//        executorService.shutdown();
//    }
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        for (int i = 1; i <= 5; i++) {
//            Runnable worker = new MyRunnable("Task" + i);
//            executorService.execute(worker);
//        }
//        executorService.shutdown();
//    }
//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        for (int i = 1; i <= 5; i++) {
//            Runnable worker = new MyRunnable("Task" + i);
//            executor.execute(worker);
//        }
//        executor.shutdown();
//    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Current Time = " + new Date());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 1; i <= 3; i++) {
            Thread.sleep(2000);
            RunnableTask task = new RunnableTask("Task" + i);
            scheduledExecutorService.schedule(task, 3, TimeUnit.SECONDS);
        }
        Thread.sleep(6000);
        scheduledExecutorService.shutdown();
        System.out.println("Completed all threads");
    }
}

class MyRunnable implements Runnable {

    /**
     * будет создан только один новый поток и одновременно будет выполняться только одна задача.
     * Остальные четыре задачи находятся в очереди ожидания.
     * Метод shutdown() ожидает завершения выполнения задач, в настоящий момент переданных
     * исполнителю, чтобы завершить его работу
     */

    /**
     * метод позволяет создать пул с фиксированным количеством потоков. Таким образом, когда мы отправим
     * пять задач, в коде будет создано три новых потока и будут выполнены три задачи.
     * Остальные две задачи находятся в очереди ожидания
     */

    /**
     * создаст пять новых потоков и обработает пять задач. Никакой очереди ожидания здесь не будет.
     * Если поток остается в бездействии более минуты, метод устраняет его
     */

    /**
     * Метод schedule принимает три аргумента: задачу, задержку и промежуток времени задержки.
     * Метод schedule() используется для планирования задачи после фиксированной задержки.
     * Метод scheduleAtFixedRate() используется для планирования задачи после фиксированной задержки
     * и последующего периодического выполнения этой задачи.
     * Метод scheduleWithFixedDelay() используется для планирования задачи после начальной задержки,
     * а затем выполнения задач с фиксированной задержкой после завершения предыдущей задачи.
     */

    private final String task;

    MyRunnable(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Executing " + task + " with " + Thread.currentThread().getName());
        }
        System.out.println();
    }
}

class RunnableTask implements Runnable {

    private String task;

    public RunnableTask(String s) {
        this.task = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start Time for " + task + " " + new Date());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End Time for " + task + " " + new Date());
    }

    @Override
    public String toString() {
        return this.task;
    }
}