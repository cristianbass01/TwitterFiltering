package upf.edu.filter;


import upf.edu.parser.SimplifiedTweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class FileLanguageFilter implements LanguageFilter{
    final String inputFile;
    final String outputFile;


    public FileLanguageFilter(String inputFile, String outputFile) throws IOException {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        FileReader reader = new FileReader(inputFile);
        BufferedReader bReader = new BufferedReader(reader);

        StringBuilder tweetJson = new StringBuilder();
        while(bReader.ready()){
            tweetJson.append(bReader.readLine());
        }

        Optional<SimplifiedTweet> sempTweet = SimplifiedTweet.fromJson(tweetJson.toString());
        System.out.println(sempTweet);
    }

    @Override
    public void filterLanguage(String language) throws Exception {

    }
}
