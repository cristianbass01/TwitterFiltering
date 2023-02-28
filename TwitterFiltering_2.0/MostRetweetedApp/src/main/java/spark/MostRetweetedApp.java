package spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Array;
import scala.Tuple2;
import spark.model.ExtendedSimplifiedTweet;

import java.util.*;

public class MostRetweetedApp {

    public static void main(String[] args) {
        String input = args[1];
        String output = args[0];

        //Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("MostRetweetedApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        // Load input
        JavaRDD<String> lines = sparkContext.textFile(input);

        // Map lines to simplified tweets
        JavaRDD<Optional<ExtendedSimplifiedTweet> > tweets = lines
                .filter(tweet -> !tweet.equals(""))
                .map(ExtendedSimplifiedTweet::fromJson);

        // Filter tweets to have retweeted tweets only
        JavaRDD<Optional<ExtendedSimplifiedTweet> > retweetedTweets = tweets
                .filter(tweet -> tweet.map(ExtendedSimplifiedTweet::isRetweeted).get());

        // count the number of retweets per user
        JavaPairRDD<Long, Integer> mostRetweetedUsers = retweetedTweets
                .mapToPair(tweet -> new Tuple2<>(tweet.get().getRetweetedUserId(), 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .mapToPair(Tuple2::swap);

        // count the number of retweets per tweet
        JavaPairRDD<Long, Integer> mostRetweetedTweets = retweetedTweets // user, (tweet, countTweet)
                /*
                .mapToPair(tweet -> new Tuple2<>(new Tuple2<>(tweet.get().getRetweetedTweetId(), tweet.get().getRetweetedUserId()), 1))
                .reduceByKey(Integer::sum)
                .mapToPair(t -> new Tuple2<>(t._1._2, new Tuple2<>(t._1._1, t._2)))
                .reduceByKey((a,b) -> {
                    if(a._2 >= b._2) return new Tuple2<>(a._1, a._2);
                    else return new Tuple2<>(b._1, b._2);
                })
                .mapToPair(t-> new Tuple2<>(new Tuple2<>(t._2._1, t._1), t._2._2))

                 */
                .mapToPair(t -> new Tuple2<>(t.get().getRetweetedTweetId(), 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .mapToPair(Tuple2::swap);
                //.mapToPair(t -> new Tuple2<>(t._1._2, new Tuple2<>(t._1._1, t._2)));

        /*
        System.out.println( mostRetweetedUsers  // user, (countUser, (tweet, countTweet))
                .join(mostRetweetedTweets)
                .mapToPair(t -> new Tuple2<>(t._2._1, new Tuple2<>(t._1, t._2._2)))
                .sortByKey(false)
                .mapToPair(t -> new Tuple2<>(t._2._1, new Tuple2<>(t._1, t._2._2)))
                .reduceByKey((a,b) -> {
                    if(a._1 > b._1) return new Tuple2<>(a._1, a._2);
                    else if(a._1.equals(b._1)) {
                        if (a._2._2 >= b._2._2) return new Tuple2<>(a._1, a._2);
                        else return new Tuple2<>(b._1, b._2);
                    }
                    else return new Tuple2<>(b._1, b._2) ;
                })
                //.filter(t-> t._2._1 > 1 && t._2._2._2>1)
                .mapToPair(t-> new Tuple2<>(t._2._1, new Tuple2<>(t._1, t._2._2)))
                .sortByKey(false)
                .mapToPair(t-> new Tuple2<>(t._2._2._2, new Tuple2<>(t._1, new Tuple2<>(t._2._2._1, t._2._1))))
                .sortByKey(false)
                .collect());

         */

        /*
        JavaPairRDD<Long, Tuple2<Integer, Tuple2<Long, Integer>>> mostEverything = mostRetweetedUsers  // user, (countUser, (tweet, countTweet))
                .join(mostRetweetedTweets)
                .mapToPair(t -> new Tuple2<>(t._2._1, new Tuple2<>(t._1, t._2._2)))
                .sortByKey(false)
                .mapToPair(t -> new Tuple2<>(t._2._1, new Tuple2<>(t._1, t._2._2)));


         */

        //List<Tuple2<Long, Tuple2<Integer, Tuple2<Long, Integer>>>> results = mostEverything.take(10);
        //System.out.println(results);

        // ordered list of users with most retweeted
        List<Tuple2<Long, Integer>> topUsers = mostRetweetedUsers.collect();
        List<String> results = new ArrayList<>();

        // Find the most retweeted tweet for each user
        int count = 0;
        for(int i = 0; count < 10 && i < topUsers.size(); i++){
            Long userId = topUsers.get(i)._1;
            int userRetweets = topUsers.get(i)._2;

            // tweets of the user
            JavaRDD<Optional<ExtendedSimplifiedTweet> > userTweets= tweets
                    .filter(t -> t.get().getUserId().equals(userId));

            Map<Long, Integer> map = mostRetweetedTweets.collectAsMap();

            // if we have some tweets about the user we insert him to the top10
            if(userTweets.count() > 0) {
                JavaPairRDD<Optional<ExtendedSimplifiedTweet>, Integer> prova = userTweets
                        .mapToPair(t -> new Tuple2<>(t, map.get(t.get().getTweetId())))
                        .filter(t -> t._2 != null ); // (tweetId, number of retweet)

                if(prova.count() > 0) {
                    Tuple2<ExtendedSimplifiedTweet, Integer> mostRetweetedTweet = prova
                            .mapToPair(t -> new Tuple2<>(t._2, t._1.get()))
                            .sortByKey(false)
                            .take(1).get(0).swap();

                    results.add((count+1) + "-> User ID: " + userId + ", Retweet Count: " + userRetweets + ", Most Retweeted Tweet(" + mostRetweetedTweet._2 + " times): " + mostRetweetedTweet._1);
                    count++;
                }
            }
        }

        for(int i = 0; i< results.size(); i++){
            System.out.println(results.get(i));
        }
    }
}
