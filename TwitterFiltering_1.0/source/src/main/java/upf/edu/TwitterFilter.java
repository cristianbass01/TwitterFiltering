package upf.edu;

import upf.edu.filter.FileLanguageFilter;
import upf.edu.uploader.S3Uploader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TwitterFilter {
    public static void main( String[] args ) throws Exception {
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0);
        String outputFile = argsList.get(1);
        String bucket = argsList.get(2);
        System.out.println("Language: " + language + ".\nOutput file: " + outputFile + ".\nDestination bucket: " + bucket);

        long startTime = System.currentTimeMillis();
        for(String inputFile: argsList.subList(3, argsList.size())) {
            long startTimeFile = System.currentTimeMillis();
            System.out.println("    Processing: " + inputFile);
            final FileLanguageFilter filter = new FileLanguageFilter(inputFile, outputFile);
            filter.filterLanguage(language);
            long endTimeFile = System.currentTimeMillis();
            long elapsedTimeFile = endTimeFile - startTimeFile;

            System.out.println("        Elapsed time in milliseconds per file: " + elapsedTimeFile);
        }

        final S3Uploader uploader = new S3Uploader(bucket, "", "default");
        uploader.upload(Arrays.asList(outputFile));

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("    Elapsed time in milliseconds in total: " + elapsedTime);
    }
}