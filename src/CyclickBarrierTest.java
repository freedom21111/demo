import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclickBarrierTest {
    public static void main(String[] args) {
        int childSize = 10;
        int mainSize = 20;
        int circleSize = 50;
        CyclicBarrier cbChild = new CyclicBarrier(childSize);
        CyclicBarrier cbMain = new CyclicBarrier(mainSize);
        ExecutorService esChild = Executors.newCachedThreadPool();
        ExecutorService esMain = Executors.newCachedThreadPool();
        for (int i = 0; i < circleSize; i++) {
            for (int j = 0; j < childSize; j++) {
                try {
                    System.out.println("This is "+i+"st cycle, Thread cbChild" + j+" begin .... ");
                    esChild.execute(()->{try {
                        cbChild.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }});
                    System.out.println("This is "+i+"st cycle, Thread cbChild" + j+" end .... ");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("cbChild.getParties()--------------------------" + cbChild.getParties());

            for (int j = 0; j < mainSize; j++) {
                try {
                    System.out.println("This is "+i+"st cycle, Thread cbMain" + j+" end .... ");
                    esMain.execute(()->{try {
                        cbMain.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }});
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("This is "+i+"st cycle, Thread cbMain" + j+" end .... ");
            }
            System.out.println("cbMain.getParties()--------------------------" + cbMain.getParties());

            System.out.println("=================================================================");
        }

        esChild.shutdown();
        esMain.shutdown();

    }
}
