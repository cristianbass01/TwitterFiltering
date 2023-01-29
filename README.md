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

Example: a call with the following parameters:
```
TwitterFilter es /tmp/output-es.txt test-bucket f1.json f2.json f3.json
```
will read 3 files (f1.json f2.json f3.json), and generate a new output file /tmp/output-es.txt contain-
ing all the tweets in the Spanish (es) language, then upload such file in an S3 bucket named test-bucket.
