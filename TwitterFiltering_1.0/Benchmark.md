## Benchmark
1. Destination bucket:
    - lsds2022.lab1.output.u210426
2. Statistics are as follows:
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
    Total number of tweets: 1291668
    Total number of tweets found: 509435
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
    Total number of tweets: 1291668
    Total number of tweets found: 446603
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
    Total number of tweets: 1291668
    Total number of tweets found: 4583
    Elapsed time in milliseconds in total: 74750


3.  I'm using the following runtime environment:
~~~
openjdk version "19.0.1" 2022-10-18
OpenJDK Runtime Environment (build 19.0.1+10-Ubuntu-1ubuntu122.04) 
OpenJDK 64-Bit Server VM (build 19.0.1+10-Ubuntu-1ubuntu122.04, mixed mode, sharing)
~~~
Time unit:
~~~
milliseconds (ms)
~~~
4. Did you encounter any issue when performing the calculation?
   I cannot count that easily the total number of tweets processed and found because every file is processed in a different object.