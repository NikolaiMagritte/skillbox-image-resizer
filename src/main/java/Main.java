import java.io.File;

public class Main {
    public static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:/Users/trprp/Desktop/src";
        String dstFolder = "C:/Users/trprp/Desktop/dst";

        Runtime runtime = Runtime.getRuntime();
        int processorsCount = runtime.availableProcessors();

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int step = files.length / processorsCount;

        if (step < 1) {
            step = 1;
            processorsCount = files.length;
        }
        for (int i = 0; i <= processorsCount - 1; i++) {
            if (i == processorsCount - 1) {
                File[] imageFiles = new File[files.length - step * i];
                System.arraycopy(files, step * i, imageFiles, 0, imageFiles.length);
                new Thread(new ImageResizer(imageFiles, newWidth, dstFolder, start)).start();
                break;
            }
            File[] imageFiles = new File[step];
            System.arraycopy(files, step * i, imageFiles, 0, imageFiles.length);
            new Thread(new ImageResizer(imageFiles, newWidth, dstFolder, start)).start();
        }
    }
}
