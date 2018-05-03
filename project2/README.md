# Project 2

Always view the [most updated version](https://github.com/usfcs245/Project2 "Project 2") if possible.

In this project you will be required to code insertion sort, quick sort, and bucket sort. You will also need to create your own array based list.

**MAKE SURE TO LOOK AT THE [DEADLINES](#deadlines)!**

## Description

In this project, you will code your own insertion sort, quick sort, and bucket sort. You will use these sorts to sort Amazon products based on reviews. The original data can be found [here](http://jmcauley.ucsd.edu/data/amazon/ "Amazon Review Data"). The original data is formatted with each line being a new review. For this project, we will be using a condensed version where each line is a different product.

The consensed file is formatted as `<ASIN>,"[ratings]"` per line. In the file it will look something like this:

```
8805002917,"[1]"
9792372326,"[5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 4, 5, 4, 4, 5, 5, 5]"
9868238854,"[5, 5]"
B0000000P3,"[5, 5]"
B0000000PB,"[5]"
```
The first String up to the comma in each line is the `ASIN` or *Amazon Standard Identification Number*. If you would like to, you can go [here](http://www.amazon-asin.com/ "ASIN Lookup") to look up what item each ASIN correlates to. Keep in mind that these ASINs may have been changed from when the data was recorded (May 1996 - July 2014). Next in the file is an array of integers in quotes. This array is the ratings. The file name will loook like `ratings_Musical_Instruments_short_condensed.csv`.

## Sorting

For simplicity, we will be sorting based on average rating from highest to lowest.

## Input

Input will be given through the command line. This will be the condensed csv file. Such as:

```
> java Driver ratings_Musical_Instruments_short_condensed.csv
```

## Output

You can choose one sort to sort the condensed file. The other sorts need to be functional as well though. After you have sorted the file write to \<condensed file name>_sorted.csv. The written file for `ratings_Musical_Instruments_short_condensed.csv` will look like `ratings_Musical_Instruments_short_condensed_sorted.csv`.

What you will write to the sorted file is the sorted list of ASIN separated by new lines. For example:

```
9868238854
B0000000P3
B0000000PB
9792372326
8805002917
```

## Template

There is a template provided for this project. You can download all the files by clicking the `Clone or download` button in the same row as the `Upload files` button. You can click `Download ZIP` and work on those files.

If you want to, you can generalize the parameters with java generics for the sorts. So the parameter would become `ArrayBasedList<E>`. This is not required though.

There is a test file to help test your sorts that is provided with the files. You are *not* expected to understand all of the code in the test file. If there are any issues with the test file, please let any of the instructors know as soon as possible. If you pass all the tests, it does not mean you are finished. These tests are not comprehensive and relatively basic. So your own testing needs to be done in addition to adding comments and etc.

The test requires the _result file as well so don't forget to put it in your project folder.

If you do not want to, you do not need to use the template. Please indicate in your comments that you are not using the template though. Also, the same requirements still apply with or without the template.

## Requirements

#### Input and Output
See [Input](#input) and [Output](#output) for input and output requirements. 

#### File parsing
You must parse the condensed file. You can assume that each ASIN is unique in the file. If you would like to, you can parse to original data [here](http://jmcauley.ucsd.edu/data/amazon/ "Amazon Review Data"). This will be more difficult though. If you choose to parse the original data, add a comment to your driver and tell an instructor.

#### Sorting Req
You must write functioning sorts for insertion, quick, and bucket sort.

You must write a description for each sort in the header of each sort class and write comments explaining your code.

You must sort based on the formula given in the [Sorting](#sorting) section. If you have a better way to sort the data based on average rating and amount of ratings, you must ask either in person or on Piazza before you use it.

You may **not** use Collections.sort.

If you want, you may implement the Comparable interface.

#### Array Based List
You must create an array based list without importing ArrayList. Your array based list can extend `AbstractList` though. It must have, at the least, these method: get(index), size(), add(Object), add(index, Object), remove(index), and set(index, Object).

This array based list should have a dynamic size change. Instead of starting with an array of Integer.MAX_VALUE size, the size should grow dynamically. One way is to use a default size and double the size whenever the array is filled (and copy the old data to the new one). You can use default size 100. 

You can use your array based list that you created in your project.

## Deadlines

**THIS PROJECT HAS 2 DEADLINES**

You must upload each part to GitHub **BEFORE** each deadline.

First Deadline|
--------------|
**DATE:** Monday, March 26th|
*TIME:* 11:59 pm|
For the first deadline, you **MUST** complete your array based list and insertion sort. These both **MUST** be functional before the deadline.  |

Second Deadline|
---------------|
**DATE:** Monday, April 2nd|
*TIME:* 11:59 pm|
For the second deadline, you **MUST** complete all the requirements in the first deadline **AND** your quick sort and bucket sort. You must also finish all the rest of the requirements listed in [Requirements](#requirements).|

## Things to think about

Take a look at the timing of the insertion versus quick sort. Is there a substantial difference in time? Or does it depend? 

How about quick versus bucket sort?

Keep in mind that the speed of bucket sort relies on even distribution between buckets.







