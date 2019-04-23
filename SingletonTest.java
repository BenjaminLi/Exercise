


public class SingletonTest extends Thread
{

    private static final int NUMBER_OF_THREADS = 10;

    public static void main(String[] args)
    {
        try
        {
            System.out.println("SingletonTest.main() started");
            SingletonTest[] threads = new SingletonTest[NUMBER_OF_THREADS];

            for (int i = 0; i < NUMBER_OF_THREADS; ++i)
            {
                threads[i] = new SingletonTest();
            }
            System.out.println("SingletonTest.main() thread objects created");

            for (int i = 0; i < NUMBER_OF_THREADS; ++i)
            {
                threads[i].start();
            }
            System.out.println("SingletonTest.main() all threads started");

            // Wait for all the threads to start
            Thread.sleep(5 * 1000);
            System.out.println("SingletonTest.main() checking thread readiness");

            for (int i = 0; i < NUMBER_OF_THREADS; ++i)
            {
                System.out.println("Checking thread readiness: " + i);
                while (threads[i].singleton == null)
                {
                    Thread.sleep(1000);
                }
            }

            boolean identical = true;
            System.out.println("Checking if threads are identical ");
            for (int i = 1; i < NUMBER_OF_THREADS; ++i)
            {
                System.out.println("SingletonTest.main() checking identical");
                if (threads[i - 1].singleton != threads[i].singleton)
                {
                    identical = false;
                    System.err.printf("Error: different Singleton instances. thread [%d]: %s, thread [%d]: %s\n", (i - 1), threads[i - 1].singleton, i, threads[i].singleton);
                }
            }

            System.out.printf("All instances are identical?: %b\n", identical);
            System.out.println("Done");
        }
        catch (Exception e)
        {
            System.out.println("SingletonTest.main(): Caught exception: " + e.getMessage());
            e.printStackTrace();
        }
    }







    private Singleton singleton = null;

    public void run()
    {
        try {
            singleton = Singleton.getInstance();
            System.out.println("SingletonTest.run() thread started: " + this.getId());

            Thread.sleep(1000);
            System.out.printf("SingletonTest.run() thread id = %d, singletom instance = %s\n ", this.getId(), singleton);
            singleton.service();
        }
        catch (Exception e)
        {
            System.out.println("SingletonTest.run(): Caught exception: " + e.getMessage());
            e.printStackTrace();

        }
    }
}
