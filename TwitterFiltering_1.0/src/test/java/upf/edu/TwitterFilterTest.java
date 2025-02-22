package upf.edu;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import upf.edu.filter.FileLanguageFilter;
import upf.edu.parser.SimplifiedTweet;

import java.io.*;
import java.util.Optional;

/**
 * Unit test for simple App.
 */
public class TwitterFilterTest
{
    /**
     * Rigorous Test :-)
     */
    public String rightJson = "{\"created_at\":\"Sat May 12 15:58:53 +0000 2018\",\"id\":995332494974210048,\"id_str\":\"995332494974210048\",\"text\":\"RT @carloscarmo98: -Manel, algo que decir sobre tu actuaci\\u00f3n en Eurovision?\\n-Kikiriketediga https:\\/\\/t.co\\/yXGYtKmJoM\",\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/android\\\" rel=\\\"nofollow\\\"\\u003eTwitter for Android\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":492271155,\"id_str\":\"492271155\",\"name\":\"alba aguirre\",\"screen_name\":\"Alba137\",\"location\":\"en pleno akelarre\",\"url\":null,\"description\":\"no todo lo que brilla es oro, a veces es highlight \\u2728\\ud83d\\udc9c\",\"translator_type\":\"regular\",\"protected\":false,\"verified\":false,\"followers_count\":718,\"friends_count\":416,\"listed_count\":2,\"favourites_count\":24718,\"statuses_count\":21764,\"created_at\":\"Tue Feb 14 14:46:34 +0000 2012\",\"utc_offset\":10800,\"time_zone\":\"Athens\",\"geo_enabled\":true,\"lang\":\"es\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/623739463202684928\\/vjeMtHPV.jpg\",\"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/623739463202684928\\/vjeMtHPV.jpg\",\"profile_background_tile\":true,\"profile_link_color\":\"00B371\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/992694287451217920\\/v3dvIg9N_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/992694287451217920\\/v3dvIg9N_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/492271155\\/1516449879\",\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweeted_status\":{\"created_at\":\"Sat May 13 20:57:18 +0000 2017\",\"id\":863498411517108224,\"id_str\":\"863498411517108224\",\"text\":\"-Manel, algo que decir sobre tu actuaci\\u00f3n en Eurovision?\\n-Kikiriketediga https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_text_range\":[0,72],\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/android\\\" rel=\\\"nofollow\\\"\\u003eTwitter for Android\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":1651197529,\"id_str\":\"1651197529\",\"name\":\"Carlos Carmona\",\"screen_name\":\"carloscarmo98\",\"location\":\"Logro\\u00f1o, Espa\\u00f1a\",\"url\":null,\"description\":\"Estudiante de Geograf\\u00eda e Historia y seguidor del Valencia C.F. Nacido en Villanueva de la Serena, Extremadura, y viviendo en Logro\\u00f1o\",\"translator_type\":\"none\",\"protected\":false,\"verified\":false,\"followers_count\":155,\"friends_count\":108,\"listed_count\":0,\"favourites_count\":1100,\"statuses_count\":1857,\"created_at\":\"Tue Aug 06 20:28:56 +0000 2013\",\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"lang\":\"es\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/924184485771522048\\/MGfUXALI_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/924184485771522048\\/MGfUXALI_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/1651197529\\/1498818908\",\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"quote_count\":63,\"reply_count\":17,\"retweet_count\":2945,\"favorite_count\":2568,\"entities\":{\"hashtags\":[],\"urls\":[],\"user_mentions\":[],\"symbols\":[],\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[73,96],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}}}]},\"extended_entities\":{\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[73,96],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}}}]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"low\",\"lang\":\"es\"},\"is_quote_status\":false,\"quote_count\":0,\"reply_count\":0,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"urls\":[],\"user_mentions\":[{\"screen_name\":\"carloscarmo98\",\"name\":\"Carlos Carmona\",\"id\":1651197529,\"id_str\":\"1651197529\",\"indices\":[3,17]}],\"symbols\":[],\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[92,115],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}},\"source_status_id\":863498411517108224,\"source_status_id_str\":\"863498411517108224\",\"source_user_id\":1651197529,\"source_user_id_str\":\"1651197529\"}]},\"extended_entities\":{\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[92,115],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}},\"source_status_id\":863498411517108224,\"source_status_id_str\":\"863498411517108224\",\"source_user_id\":1651197529,\"source_user_id_str\":\"1651197529\"}]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"low\",\"lang\":\"es\",\"timestamp_ms\":\"1526140733842\"}";
    public String wrongJson = "{\"prova\": 1 , 2}";
    public String missingJson = "{\"created_at\":\"Sat May 12 15:58:53 +0000 2018\",\"id\":995332494974210048,\"id_str\":\"995332494974210048\",\"text\":\"RT @carloscarmo98: -Manel, algo que decir sobre tu actuaci\\u00f3n en Eurovision?\\n-Kikiriketediga https:\\/\\/t.co\\/yXGYtKmJoM\",\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/android\\\" rel=\\\"nofollow\\\"\\u003eTwitter for Android\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":492271155,\"id_str\":\"492271155\",\"name\":\"alba aguirre\",\"screen_name\":\"Alba137\",\"location\":\"en pleno akelarre\",\"url\":null,\"description\":\"no todo lo que brilla es oro, a veces es highlight \\u2728\\ud83d\\udc9c\",\"translator_type\":\"regular\",\"protected\":false,\"verified\":false,\"followers_count\":718,\"friends_count\":416,\"listed_count\":2,\"favourites_count\":24718,\"statuses_count\":21764,\"created_at\":\"Tue Feb 14 14:46:34 +0000 2012\",\"utc_offset\":10800,\"time_zone\":\"Athens\",\"geo_enabled\":true,\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/623739463202684928\\/vjeMtHPV.jpg\",\"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/623739463202684928\\/vjeMtHPV.jpg\",\"profile_background_tile\":true,\"profile_link_color\":\"00B371\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/992694287451217920\\/v3dvIg9N_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/992694287451217920\\/v3dvIg9N_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/492271155\\/1516449879\",\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweeted_status\":{\"created_at\":\"Sat May 13 20:57:18 +0000 2017\",\"id\":863498411517108224,\"id_str\":\"863498411517108224\",\"text\":\"-Manel, algo que decir sobre tu actuaci\\u00f3n en Eurovision?\\n-Kikiriketediga https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_text_range\":[0,72],\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/android\\\" rel=\\\"nofollow\\\"\\u003eTwitter for Android\\u003c\\/a\\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":1651197529,\"id_str\":\"1651197529\",\"name\":\"Carlos Carmona\",\"screen_name\":\"carloscarmo98\",\"location\":\"Logro\\u00f1o, Espa\\u00f1a\",\"url\":null,\"description\":\"Estudiante de Geograf\\u00eda e Historia y seguidor del Valencia C.F. Nacido en Villanueva de la Serena, Extremadura, y viviendo en Logro\\u00f1o\",\"translator_type\":\"none\",\"protected\":false,\"verified\":false,\"followers_count\":155,\"friends_count\":108,\"listed_count\":0,\"favourites_count\":1100,\"statuses_count\":1857,\"created_at\":\"Tue Aug 06 20:28:56 +0000 2013\",\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/924184485771522048\\/MGfUXALI_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/924184485771522048\\/MGfUXALI_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/1651197529\\/1498818908\",\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"quote_count\":63,\"reply_count\":17,\"retweet_count\":2945,\"favorite_count\":2568,\"entities\":{\"hashtags\":[],\"urls\":[],\"user_mentions\":[],\"symbols\":[],\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[73,96],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}}}]},\"extended_entities\":{\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[73,96],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}}}]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"low\"},\"is_quote_status\":false,\"quote_count\":0,\"reply_count\":0,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"urls\":[],\"user_mentions\":[{\"screen_name\":\"carloscarmo98\",\"name\":\"Carlos Carmona\",\"id\":1651197529,\"id_str\":\"1651197529\",\"indices\":[3,17]}],\"symbols\":[],\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[92,115],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}},\"source_status_id\":863498411517108224,\"source_status_id_str\":\"863498411517108224\",\"source_user_id\":1651197529,\"source_user_id_str\":\"1651197529\"}]},\"extended_entities\":{\"media\":[{\"id\":863498400314126342,\"id_str\":\"863498400314126342\",\"indices\":[92,115],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/C_vDOFbXcAYRKXK.jpg\",\"url\":\"https:\\/\\/t.co\\/yXGYtKmJoM\",\"display_url\":\"pic.twitter.com\\/yXGYtKmJoM\",\"expanded_url\":\"https:\\/\\/twitter.com\\/carloscarmo98\\/status\\/863498411517108224\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":680,\"h\":519,\"resize\":\"fit\"},\"medium\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"},\"large\":{\"w\":1078,\"h\":823,\"resize\":\"fit\"}},\"source_status_id\":863498411517108224,\"source_status_id_str\":\"863498411517108224\",\"source_user_id\":1651197529,\"source_user_id_str\":\"1651197529\"}]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"low\",\"timestamp_ms\":\"1526140733842\"}";
    public SimplifiedTweet rightTweet;
    public String rightString = "{\"tweetId\":995332494974210048,\"text\":\"RT @carloscarmo98: -Manel, algo que decir sobre tu actuación en Eurovision?\\n-Kikiriketediga https://t.co/yXGYtKmJoM\",\"userId\":492271155,\"userName\":\"alba aguirre\",\"language\":\"es\",\"timestampMs\":1526140733842}";

    @Before
    public void setUp(){
        long tweetId = 995332494974210048L;              // the id of the tweet ('id')
        String text = "RT @carloscarmo98: -Manel, algo que decir sobre tu actuación en Eurovision?\n-Kikiriketediga https://t.co/yXGYtKmJoM";              // the content of the tweet ('text')
        long userId = 492271155;              // the user id ('user->id')
        String userName = "alba aguirre";          // the user name ('user'->'name')
        String language = "es";          // the language of a tweet ('lang')
        long timestampMs = 1526140733842L;          // seconduserIds from epoch ('timestamp_ms')

        rightTweet = new SimplifiedTweet(tweetId, text, userId, userName, language, timestampMs);
    }

    @Test
    public void toStringTest(){
        assertEquals(rightString, rightTweet.toString());
    }
    @Test
    public void getLanguageTest(){
        assertEquals("es", rightTweet.getLanguage());
    }

    @Test
    public void parsingRight(){
        Optional<SimplifiedTweet> givenTweet = SimplifiedTweet.fromJson(rightJson);
        assertEquals(rightTweet.toString(), givenTweet.get().toString());
        }

    @Test
    public void parsingWrong(){
        try{
            Optional<SimplifiedTweet> givenTweet = SimplifiedTweet.fromJson(wrongJson);
        }catch (Exception m){
            assertTrue(true);
            return;
        }
        fail();
    }

    @Test
    public void parsingMissing(){
        Optional<SimplifiedTweet> givenTweet = SimplifiedTweet.fromJson(missingJson);
        assertNotEquals("es", givenTweet.get().getLanguage());
        assertNotEquals(rightTweet.getLanguage(), givenTweet.get().getLanguage());
        assertEquals("", givenTweet.get().getLanguage());
        assertNotEquals(rightTweet.toString(), givenTweet.get().toString());
    }

    @Test
    public void fileNotFoundTest(){
        try{
            String inputTest = "inputTest";
            String outputTest = "outputTest.txt";
            final FileLanguageFilter filter = new FileLanguageFilter(inputTest, outputTest);
        }catch (Exception e){
            assertTrue(true);
            return;
        }
        fail();
    }
    @Test
    public void rightFilter(){
        try{
            String inputTest = "inputTest.txt";
            FileWriter writer = new FileWriter(inputTest);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write(rightJson);
            bWriter.close();

            String outputTest = "outputTest.txt";
            final FileLanguageFilter filter = new FileLanguageFilter(inputTest, outputTest);
            String language="es";
            filter.filterLanguage(language);

            FileReader reader = new FileReader(outputTest);
            BufferedReader bReader = new BufferedReader(reader);
            String actualString = bReader.readLine();
            assertEquals(rightString, actualString);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void noFilterTweet(){
        try{
            String inputTest = "inputTest.txt";
            FileWriter writer = new FileWriter(inputTest);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write(rightJson);
            bWriter.close();

            String outputTest = "outputTest.txt";
            final FileLanguageFilter filter = new FileLanguageFilter(inputTest, outputTest);
            String language="en";
            filter.filterLanguage(language);

            FileReader reader = new FileReader(outputTest);
            BufferedReader bReader = new BufferedReader(reader);
            String actualString = bReader.readLine();
            assertNull(actualString);
        }catch (Exception e){
            fail();
        }
    }
}
