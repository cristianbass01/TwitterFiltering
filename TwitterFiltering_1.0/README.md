# TwitterFiltering

Create a small Java command called TwitterFilter that, given a collection of files containing tweets, copies
all tweets in a given language into a new file, and uploads it to S3.

The application receives the following parameters:
- a 2 character string, which represents a language following the ISO 639-1 standard.
- the name of a local output file (where the output will be temporarily stored)
- the name for a bucket on S3 where to upload the resulting file
- a variable number of strings representing the paths of the files to be processed by the application (one
or more files). Each file contains tweets in JSON format, one tweet per line.

The command produces as output a local file containing all tweets in that language and then uploads it
to Amazon S3 in a given bucket name with a given key.

A call with the following parameters:
```
mvn package
java -cp target/TwitterFiltering-1.0-SNAPSHOT.jar upf.edu.TwitterFilter es es lsds2022.lab1.output.<userID> ./src/main/resources/Eurovision3.json ./src/main/resources/Eurovision4.json ./src/main/resources/Eurovision5.json ./src/main/resources/Eurovision6.json ./src/main/resources/Eurovision7.json ./src/main/resources/Eurovision8.json ./src/main/resources/Eurovision9.json ./src/main/resources/Eurovision10.json
```
will read 7 files (Eurovisions), and generate a new output file es contain-
ing all the tweets in the Spanish (es) language, then upload such file in an S3 bucket named lsds2022.lab1.output.<userID>.

## Benchmark
1. Destination buckets: 
   - lsds2022.lab1.output.u210426
   - lsds2022.lab1.output.u210653
2. Statistics are as follows:
Total number of tweets processed: 1291668
### Tweets in Spanish (es):
    Elapsed time in milliseconds in total: 89 seconds

### Tweets in English (en):
    Elapsed time in milliseconds in total: 90 seconds

### Tweets in Catalan (ca):
    Elapsed time in milliseconds in total: 74 seconds


3.  I'm using the following runtime environment:
~~~
openjdk version "19.0.1" 2022-10-18
~~~
   Time unit:
~~~
seconds (s)
~~~

More in Benchmark.md

## Extensions

Describe of the unit tests:
1. toStringTest(): to test if the method toString of the class SimplifiedTweet is correct
2. getLanguageTest(): to test if the getLanguage method of the SimplifiedTweet class is correct
3. parsingRight(): to test the parsing from a Json string to a simplifiedTweet object (if the json is correct)
4. parsingWrong(): to test the parsing from a Json string to a simplifiedTweet object (if the json is wrong and should return an exception)
5. parsingMissing(): to test the parsing from a Json string to a simplifiedTweet object (if the json is missing variable lang = language. So the filter is taking into account this tweet only if we give an empty string)
6. fileNotFoundTest(): to test if it gives an exception if we give it a wrong input file
7. rightFilter(): to test if the filter gives the correct tweets
8. noFilterTweet(): to test if the filter can also filter all the tweets without errors

