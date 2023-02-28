package spark.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.Optional;

public class ExtendedSimplifiedTweet implements Serializable {
    private final long tweetId; // the id of the tweet (’id’)
    private final String text; // the content of the tweet (’text’)
    private final Long userId; // the user id (’user->id’)
    private final String userName; // the user name (’user’->’name’)
    private final long followersCount; // the number of followers (’user’->’followers_count’)
    private final String language; // the language of a tweet (’lang’)
    private final boolean isRetweeted; // is it a retweet? (the object ’retweeted_status’ exists?)

    private final Long retweetedUserId; // [if retweeted] (’retweeted_status’->’user’->’id’)
    private final Long retweetedTweetId; // [if retweeted] (’retweeted_status’->’id’)
    private final long timestampMs; // seconds from epoch (’timestamp_ms’)

    public ExtendedSimplifiedTweet(long tweetId, String text, long userId, String userName,
                                   long followersCount, String language, boolean isRetweeted,
                                   Long retweetedUserId, Long retweetedTweetId, long timestampMs) {
        this.tweetId = tweetId;
        this.text = text;
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
        this.language = language;
        this.isRetweeted = isRetweeted;
        this.retweetedUserId = retweetedUserId;
        this.retweetedTweetId = retweetedTweetId;
        this.timestampMs = timestampMs;
    }

    /**
     * Returns a {@link ExtendedSimplifiedTweet} from a JSON String.
     * If parsing fails, for any reason, return an {@link Optional#empty()}
     *
     * @param jsonStr
     * @return an {@link Optional} of a {@link ExtendedSimplifiedTweet}
     */
    public static Optional<ExtendedSimplifiedTweet> fromJson(String jsonStr) {
        long tweetId = 0;              // the id of the tweet ('id')
        String text = "";              // the content of the tweet ('text')
        long userId = 0;              // the user id ('user->id')
        String userName = "";          // the user name ('user'->'name')
        String language = "";          // the language of a tweet ('lang')
        long timestampMs = 0;          // seconduserIds from epoch ('timestamp_ms')
        long followersCount = 0; // the number of followers (’user’->’followers_count’)

        boolean isRetweeted = false; // is it a retweet? (the object ’retweeted_status’ exists?)
        Long retweetedUserId = 0L; // [if retweeted] (’retweeted_status’->’user’->’id’)
        Long retweetedTweetId = 0L; // [if retweeted] (’retweeted_status’->’id’)

        JsonElement je = new JsonParser().parse(jsonStr);
        JsonObject jo = je.getAsJsonObject();

        if (jo.has("retweeted_status")) {
            isRetweeted = true;
            JsonObject retObj = jo.get("retweeted_status").getAsJsonObject();

            if (retObj.has("id")) {
                retweetedTweetId = retObj.get("id")
                        .getAsLong();

            }

            if (jo.has("user")) {
                JsonObject userObj = jo.get("user").getAsJsonObject();

                if (userObj.has("id")) {
                    retweetedUserId = userObj.get("id")
                            .getAsLong();

                }
            }
        }

        if (jo.has("id"))
            tweetId = jo.get("id")
                    .getAsLong();


        if (jo.has("text"))
            text = jo.get("text")
                    .getAsString();



        if (jo.has("user")) {
            JsonObject userObj = jo.get("user").getAsJsonObject();


            if (userObj.has("id")) {
                userId = userObj.get("id")
                        .getAsLong();

            }

            if (userObj.has("name"))
            {
                userName = userObj.get("name")
                        .getAsString();

            }

            if (userObj.has("followers_count"))
            {
                followersCount = userObj.get("followers_count")
                        .getAsLong();

            }
        }
        if (jo.has("lang"))
            language = jo.get("lang")
                    .getAsString();

        if(jo.has("timestamp_ms"))
            timestampMs = jo.get("timestamp_ms")
                    .getAsLong();

        return Optional.of(new ExtendedSimplifiedTweet(tweetId, text, userId, userName, followersCount, language, isRetweeted, retweetedUserId, retweetedTweetId, timestampMs));
    }

    @Override
    public String toString() {
        // Overriding how SimplifiedTweets are printed in console or the output file
        // The following line produces valid JSON as output
        return new Gson().toJson(this);
    }

    public String getLanguage() {
        return language;
    }

    public boolean isRetweeted(){
        return isRetweeted;
    }

    public String getText(){
        return text;
    }

    public Long getRetweetedUserId() {
        return retweetedUserId;
    }

    public Long getRetweetedTweetId() {
        return retweetedTweetId;
    }

    public Long getUserId() {
        return userId;
    }

    public long getTweetId() {
        return tweetId;
    }
}