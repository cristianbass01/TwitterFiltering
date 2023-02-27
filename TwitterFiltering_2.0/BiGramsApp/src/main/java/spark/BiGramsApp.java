package spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import spark.model.ExtendedSimplifiedTweet;

import java.util.*;

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

        JavaPairRDD<Tuple2<String, String>, Integer > words = sentences
                .filter(tweet -> !tweet.equals(""))
                .map(ExtendedSimplifiedTweet::fromJson)
                .filter(tweet -> tweet.map(ExtendedSimplifiedTweet::getLanguage).equals(Optional.ofNullable(language)) && !tweet.map(ExtendedSimplifiedTweet::isRetweeted).get() )
                .map(tweet -> tweet.map(ExtendedSimplifiedTweet::getText).get())
                .flatMapToPair(word -> {
                    String[] w = word.split("[ ]");
                    List<Tuple2<String, String>> pairs = new ArrayList<>();
                    for (int i = 0; i < w.length - 1; i++) {
                        pairs.add(new Tuple2<>(w[i], w[i+1]));
                    }
                    return pairs.iterator();
                })
                .mapValues(BiGramsApp::normalise)
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum)
                .sortByKey(Comparator.comparing(o -> (o._1 + " " + o._2)))
                .mapToPair(t -> new Tuple2<>(t._2, t._1))
                .sortByKey(false)
                .mapToPair(t -> new Tuple2<>(t._2, t._1));
        System.out.print(words.collect());


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
