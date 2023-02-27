package spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;
import spark.model.ExtendedSimplifiedTweet;

public class MostRetweetedApp {

    public static void main(String[] args) {
        String input = args[0];

        //Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("MostRetweetedApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        // Load input
        JavaRDD<String> lines = sparkContext.textFile(input);

        // Map lines to simplified tweets
        JavaRDD<ExtendedSimplifiedTweet> tweets = lines
                .map(line -> ExtendedSimplifiedTweet.fromJson(line));

        // Find the top 10 most retweeted users
        JavaPairRDD<Long, Integer> userRetweets = tweets
                .filter(tweet -> tweet.getRetweetedUserId() != null)
                .mapToPair(tweet -> new Tuple2<>(tweet.getRetweetedUserId(), 1))
                .reduceByKey((a, b) -> a + b)
                .sortBy(tuple -> tuple._2, false);

        List<Tuple2<Long, Integer>> topUsers = userRetweets.take(10);

        // Find the most retweeted tweet for each user
        for (Tuple2<Long, Integer> user : topUsers) {
            Long userId = user._1();
            JavaRDD<ExtendedSimplifiedTweet> userTweets = tweets.filter(tweet -> tweet.getUserId().equals(userId));

            SimplifiedTweet mostRetweeted = userTweets.max(Comparator.comparing(SimplifiedTweet::getRetweetCount));

            System.out.println("User ID: " + userId + ", Retweet Count: " + user._2() + ", Most Retweeted Tweet: " + mostRetweeted.toJson());
        }
    }
}
