# Twitter Analysis using Apache Spark on AWS EMR

## Project Overview
This project consists of three Spark-based applications designed to process and analyze tweets using Apache Spark on AWS ElasticMapReduce (EMR). The applications leverage Spark's distributed processing capabilities to filter, extract, and analyze Twitter data efficiently.

## Directory Structure
- **BiGramApp**: Identifies the top 10 most popular bi-grams (word pairs) for a given language from tweets.
- **MostRetweetedApp**: Finds the most retweeted tweet for the top 10 most retweeted users.
- **TwitterLanguageFilterApp**: Filters tweets based on language and stores the results in AWS S3.

## Applications

### 1. TwitterLanguageFilterApp
This application filters tweets by language and stores the filtered results in S3. It is an improved version of the Lab 1 language filter but now utilizes Apache Spark for scalable processing.

**Usage:**
```bash
spark-submit --master <YOUR MASTER> --class edu.upf.TwitterLanguageFilterApp your.jar <language> <output> <inputFile/Folder>
```
**Example:**
```bash
spark-submit --master local --class edu.upf.TwitterLanguageFilterApp twitter_analysis.jar en output/english_tweets input/tweets.json
```

### 2. BiGramApp
This application processes tweets to extract and count the most frequent bi-grams (two-word sequences) in tweets of a specific language. The results are stored in descending order based on frequency.

**Usage:**
```bash
spark-submit --master <YOUR MASTER> --class edu.upf.BiGramApp your.jar <language> <output> <inputFile/Folder>
```
**Example:**
```bash
spark-submit --master local --class edu.upf.BiGramApp twitter_analysis.jar en output/bigrams input/tweets.json
```

### 3. MostRetweetedApp
Finds the most retweeted tweet for each of the top 10 most retweeted users.

**Usage:**
```bash
spark-submit --master <YOUR MASTER> --class edu.upf.MostRetweetedApp your.jar <output> <inputFile/Folder>
```
**Example:**
```bash
spark-submit --master local --class edu.upf.MostRetweetedApp twitter_analysis.jar output/most_retweeted input/tweets.json
```

## Dependencies
- Apache Spark  
- AWS S3  
- Java (or Scala)  
- Maven  

## How to Run on AWS EMR
1. Upload input data to S3.
2. Create an EMR cluster with Spark support.
3. Upload the JAR file with dependencies to S3.
4. Submit the Spark job using `spark-submit` on the EMR cluster.