package upf.edu.filter;


import upf.edu.parser.SimplifiedTweet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileLanguageFilter implements LanguageFilter{
    final String inputFile;
    final String outputFile;

    List<Optional<SimplifiedTweet> > tweetsList = new ArrayList<>();

    public FileLanguageFilter(String inputFile, String outputFile) throws IOException {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        FileReader reader = new FileReader(inputFile);
        BufferedReader bReader = new BufferedReader(reader);

        while(bReader.ready()) {
            String jsonTweet = bReader.readLine();
            if(! jsonTweet.equals("")) {
                tweetsList.add(SimplifiedTweet.fromJson(jsonTweet));
            }
        }
        System.out.println("        Tweets processed: "+ tweetsList.size());
        bReader.close();
    }

    @Override
    public void filterLanguage(String language) throws Exception {
        FileWriter writer = new FileWriter(this.outputFile);
        BufferedWriter bWriter = new BufferedWriter(writer);

        int counter = 0;
        StringBuilder buffer = new StringBuilder();
        for(Optional<SimplifiedTweet> tweet : tweetsList){
            if(tweet.isPresent()){
                if(tweet.map(SimplifiedTweet::getLanguage).equals(Optional.ofNullable(language))){
                    buffer.append(tweet.map(SimplifiedTweet::toString).get()).append("\n");
                    //bWriter.write(tweet.map(SimplifiedTweet::toString).get() + "\n");
                    counter++;
                }
            }
        }
        bWriter.write(buffer.toString());
        System.out.println("        Tweets found: " + counter);
        bWriter.close();
    }
}
