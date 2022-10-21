package exercise;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;

import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1, String file2, String fileOut)
            throws ExecutionException, InterruptedException {

        CompletableFuture<String> getTextFromFile1 = CompletableFuture.supplyAsync(() -> {
            String text = "";
            try {
                text = readString(Paths.get(file1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return text;
        });
        CompletableFuture<String> getTextFromFile2 = CompletableFuture.supplyAsync(() -> {
            String text = "";
            try {
                text = readString(Paths.get(file2));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return text;
        });
        CompletableFuture<String> putUnionText = getTextFromFile1.thenCombine(getTextFromFile2, (text1, text2) -> {
            String text = text1 + " " + text2;
            try {
                writeString(Paths.get(fileOut), text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return text;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
       return putUnionText;
    }

    public static CompletableFuture<Long> getDirectorySize(String dir) {
        CompletableFuture<Long> countDirectory = CompletableFuture.supplyAsync(() -> {
            Long result = (long) 0;
            File[] files = new File(dir).listFiles();
            for(File file : files) {
                if(!file.isDirectory()) {
                    result += file.length();
                }
            }
            return result;
        });
        return countDirectory;
    }

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt", "src/main/resources/fileOut.txt");
        result.get();

        CompletableFuture<Long> size = getDirectorySize("src/main/resources");
        System.out.println(size.get());
        // END
    }
}

