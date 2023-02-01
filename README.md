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
java -cp target/TwitterFiltering-1.0-SNAPSHOT.jar upf.edu.TwitterFilter ca ca lsds2022.lab1.output.<userID> ./src/main/resources/Eurovision3.json ./src/main/resources/Eurovision4.json ./src/main/resources/Eurovision5.json ./src/main/resources/Eurovision6.json ./src/main/resources/Eurovision7.json ./src/main/resources/Eurovision8.json ./src/main/resources/Eurovision9.json ./src/main/resources/Eurovision10.json
```
will read 3 files (f1.json f2.json f3.json), and generate a new output file /tmp/output-es.txt contain-
ing all the tweets in the Spanish (es) language, then upload such file in an S3 bucket named test-bucket.

## Statistics (Benchmarking)
1. Destination bucket: lsds2022.lab1.output.u210426
2. 
### Tweets in Spanish (es):
    Processing: ./src/main/resources/Eurovision3.json
        Tweets processed: 65746
        Tweets found: 23848
        Elapsed time in milliseconds per file: 5580
    Processing: ./src/main/resources/Eurovision4.json
        Tweets processed: 240477
        Tweets found: 78433
        Elapsed time in milliseconds per file: 12024
    Processing: ./src/main/resources/Eurovision5.json
        Tweets processed: 129425
        Tweets found: 45800
        Elapsed time in milliseconds per file: 6966
    Processing: ./src/main/resources/Eurovision6.json
        Tweets processed: 185256
        Tweets found: 71677
        Elapsed time in milliseconds per file: 10909
    Processing: ./src/main/resources/Eurovision7.json
        Tweets processed: 131369
        Tweets found: 54969
        Elapsed time in milliseconds per file: 7384
    Processing: ./src/main/resources/Eurovision8.json
        Tweets processed: 113880
        Tweets found: 38805
        Elapsed time in milliseconds per file: 6030
    Processing: ./src/main/resources/Eurovision9.json
        Tweets processed: 63228
        Tweets found: 26244
        Elapsed time in milliseconds per file: 4432
    Processing: ./src/main/resources/Eurovision10.json
        Tweets processed: 362287
        Tweets found: 169659
        Elapsed time in milliseconds per file: 29349
    Elapsed time in milliseconds in total: 89952

### Tweets in English (en):
    Processing: ./src/main/resources/Eurovision3.json
        Tweets processed: 65746
        Tweets found: 24346
        Elapsed time in milliseconds per file: 5516
    Processing: ./src/main/resources/Eurovision4.json
        Tweets processed: 240477
        Tweets found: 96430
        Elapsed time in milliseconds per file: 12231
    Processing: ./src/main/resources/Eurovision5.json
        Tweets processed: 129425
        Tweets found: 50545
        Elapsed time in milliseconds per file: 6935
    Processing: ./src/main/resources/Eurovision6.json
        Tweets processed: 185256
        Tweets found: 66596
        Elapsed time in milliseconds per file: 10599
    Processing: ./src/main/resources/Eurovision7.json
        Tweets processed: 131369
        Tweets found: 39794
        Elapsed time in milliseconds per file: 7811
    Processing: ./src/main/resources/Eurovision8.json
        Tweets processed: 113880
        Tweets found: 35569
        Elapsed time in milliseconds per file: 6376
    Processing: ./src/main/resources/Eurovision9.json
        Tweets processed: 63228
        Tweets found: 18048
        Elapsed time in milliseconds per file: 4660
    Processing: ./src/main/resources/Eurovision10.json
        Tweets processed: 362287
        Tweets found: 115275
        Elapsed time in milliseconds per file: 28202
    Elapsed time in milliseconds in total: 89993

### Tweets in Catalan (ca):
    Processing: ./src/main/resources/Eurovision3.json
        Tweets processed: 65746
        Tweets found: 242
        Elapsed time in milliseconds per file: 4930
    Processing: ./src/main/resources/Eurovision4.json
        Tweets processed: 240477
        Tweets found: 983
        Elapsed time in milliseconds per file: 11044
    Processing: ./src/main/resources/Eurovision5.json
        Tweets processed: 129425
        Tweets found: 581
        Elapsed time in milliseconds per file: 6279
    Processing: ./src/main/resources/Eurovision6.json
        Tweets processed: 185256
        Tweets found: 717
        Elapsed time in milliseconds per file: 10255
    Processing: ./src/main/resources/Eurovision7.json
        Tweets processed: 131369
        Tweets found: 398
        Elapsed time in milliseconds per file: 6728
    Processing: ./src/main/resources/Eurovision8.json
        Tweets processed: 113880
        Tweets found: 404
        Elapsed time in milliseconds per file: 5266
    Processing: ./src/main/resources/Eurovision9.json
        Tweets processed: 63228
        Tweets found: 193
        Elapsed time in milliseconds per file: 3915
    Processing: ./src/main/resources/Eurovision10.json
        Tweets processed: 362287
        Tweets found: 1065
        Elapsed time in milliseconds per file: 24337
    Elapsed time in milliseconds in total: 74750


3. 
4. 

## Extensions

Describe unit tests

