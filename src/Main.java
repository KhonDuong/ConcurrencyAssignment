import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = generateRandomArrayOfLength(200000000);
        try {
            multiThreadSum(array);
        } catch (InterruptedException e) {
            System.out.println("I don't really know what this complaint is");
        }
        System.out.println(" ");
        singleThreadSum(array);
    }

    public static int[] generateRandomArrayOfLength(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIntBetweenOneAndTen = random.nextInt(10) + 1; // Normally gives between 0 and 9, but + 1 increases from 1 to 10
            array[i] = randomIntBetweenOneAndTen;
        }
        return array;
    }

    public static void singleThreadSum(int[] array) {
        int sum = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < 200000000; i++) {
            sum = sum + array[i];
        }

        long endTime = System.nanoTime();
        System.out.println("The sum for 200 million random numbers between 1 and 10 using a single thread is: " + sum);
        System.out.println("It took this many nanoseconds to complete the task: " + (endTime - startTime));
    }

    public static void multiThreadSum(int[] array) throws InterruptedException {
        int sum = 0;
        long startTime = System.nanoTime();
        // Assume 8 threads
        // 200,000,000 / 8 = 25,000,000 Numbers Assigned to Each
        MultiThread m1 = new MultiThread(Arrays.copyOfRange(array, 0, 25000000));
        MultiThread m2 = new MultiThread(Arrays.copyOfRange(array, 25000000, 50000000));
        MultiThread m3 = new MultiThread(Arrays.copyOfRange(array, 50000000, 75000000));
        MultiThread m4 = new MultiThread(Arrays.copyOfRange(array, 75000000, 100000000));
        MultiThread m5 = new MultiThread(Arrays.copyOfRange(array, 100000000, 125000000));
        MultiThread m6 = new MultiThread(Arrays.copyOfRange(array, 125000000, 150000000));
        MultiThread m7 = new MultiThread(Arrays.copyOfRange(array, 150000000, 175000000));
        MultiThread m8 = new MultiThread(Arrays.copyOfRange(array, 175000000, 200000000));

        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
        m6.start();
        m7.start();
        m8.start();

        m1.join();
        m2.join();
        m3.join();
        m4.join();
        m5.join();
        m6.join();
        m7.join();
        m8.join();

        sum = m1.getSum() + m2.getSum() + m3.getSum() + m4.getSum() + m5.getSum() + m6.getSum() + m7.getSum() + m8.getSum();
        long endTime = System.nanoTime();

        System.out.println("The sum for 200 million random numbers between 1 and 10 using multithreading is: " + sum);
        System.out.println("It took this many nanoseconds to complete the task: " + (endTime - startTime));
    }
}