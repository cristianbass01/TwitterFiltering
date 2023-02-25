package edu.upf;

import edu.upf.model.SimplifiedTweet;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Optional;

public class TwitterLanguageFilterApp {

    public static void main(String[] args){
        String language = args[0];
        String input = args[2];
        String outputDir = args[1];

        //Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("TwitterLanguageFilterApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(input);

        /*
        JavaPairRDD<String, Integer> counts = sentences
            .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
            .map(TwitterLanguageFilterApp::normalise)
            .mapToPair(word -> new Tuple2<>(word, 1))
            .reduceByKey(Integer::sum);

        System.out.println("Total words: " + counts.count());
        counts.saveAsTextFile(outputDir);
         */
        JavaRDD<String > filter = sentences
                .filter(tweet -> !tweet.equals(""))
                .map(SimplifiedTweet::fromJson)
                .filter(tweet -> tweet.map(SimplifiedTweet::getLanguage).equals(Optional.ofNullable(language)))
                .map(tweet -> tweet.map(SimplifiedTweet::toString).get());

        System.out.println("Total tweets: " + filter.count());
        filter.coalesce(1).saveAsTextFile(outputDir + "/" + language);
    }
}
