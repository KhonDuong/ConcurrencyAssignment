public class MultiThread extends Thread {
    private int sum;
    private final int[] array;

    public MultiThread(int[] arrayChunk) {
        array = arrayChunk;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < array.length; i++) {
                sum = sum + array[i];
            }
        } catch (Exception e) {
            System.out.println("Thread has stopped");
        }
    }

    public int getSum() {
        return sum;
    }
}
