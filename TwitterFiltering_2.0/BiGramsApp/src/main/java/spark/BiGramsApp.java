package spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;
import spark.model.ExtendedSimplifiedTweet;

import java.util.Arrays;
import java.util.Optional;

public class BiGramsApp {

    public static void main(String[] args) {
        String language = args[0];
        String input = args[2];
        String outputDir = args[1];

        //Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("BiGramsApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(input);

        JavaRDD<String > words = sentences
                .filter(tweet -> !tweet.equals(""))
                .map(ExtendedSimplifiedTweet::fromJson)
                .filter(tweet -> tweet.map(ExtendedSimplifiedTweet::getLanguage).equals(Optional.ofNullable(language)) && tweet.map(ExtendedSimplifiedTweet::isRetweeted).get() )
                .map(tweet -> tweet.map(ExtendedSimplifiedTweet::getText).get())
                .flatMap(s -> Arrays.asList(s.split("[ ]")).iterator())
                .map(BiGramsApp::normalise);
        System.out.print(words);
        
        /*
        JavaPairRDD<String, Integer> counts = sentences
            .flatMap(s -> Arrays.asList(s.split("[ ]")).iterator())
            .map(word -> normalise(word))
            .mapToPair(word -> new Tuple2<>(word, 1))
            .reduceByKey((a, b) -> a + b);
        System.out.println("Total words: " + counts.count());
        counts.saveAsTextFile(outputDir);
         */


    }

    private static String normalise(String word) {
        return word.trim().toLowerCase();
    }
}
