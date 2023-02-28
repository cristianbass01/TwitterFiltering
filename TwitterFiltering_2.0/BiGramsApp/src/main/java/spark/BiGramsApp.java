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

        JavaPairRDD<Tuple2<String, String> , Integer > bigrams = sentences
                .filter(tweet -> !tweet.equals(""))
                .map(ExtendedSimplifiedTweet::fromJson)
                .filter(tweet -> tweet.map(ExtendedSimplifiedTweet::getLanguage).equals(Optional.ofNullable(language)) && !tweet.map(ExtendedSimplifiedTweet::isRetweeted).get() )
                .map(tweet -> normalise(tweet.map(ExtendedSimplifiedTweet::getText).get()))
                .flatMapToPair(word -> {
                    String[] w = word.split("[+( )]");
                    List<Tuple2<String, String>> pairs = new ArrayList<>();
                    for (int count = 0; count < w.length - 1; count++) {
                        pairs.add(new Tuple2<>(w[count], w[count+1]));
                    }
                    return pairs.iterator();
                })
                .filter(o -> !o._1.equals("") && !o._2.equals(""))
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(o -> new Tuple2<>(o._1._1 + " " + o._1._2, o._2))
                .sortByKey()
                .mapToPair(t -> new Tuple2<>(t._2, t._1))
                .sortByKey(false)
                //.mapToPair(t -> new Tuple2<>(t._2, t._1));
                .mapToPair(s -> new Tuple2<>(new Tuple2<>(s._2.split("[ ]")[0], s._2.split("[ ]")[1]),s._1));
        List<Tuple2<Tuple2<String, String>, Integer > > topBigrams = bigrams.take(10);
        System.out.println("Top 10 most used bigrams in original tweets: ");
        for(int y = 0; y < topBigrams.size(); y++){
            System.out.println(y + ": (" + topBigrams.get(y)._1._1 + ") - (" + topBigrams.get(y)._1._2 + ") repeated " + topBigrams.get(y)._2 + " times");
        }
        bigrams.saveAsTextFile(outputDir);
    }

    private static String normalise(String word) {
        return word.trim().toLowerCase().replaceAll("[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Cf}\\p{Cs}]","");
    }
}
