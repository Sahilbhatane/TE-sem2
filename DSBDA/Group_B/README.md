# Group B - Big Data Analytics Solutions

This directory contains solutions for the Group B assignments focused on Big Data Analytics using Java/Scala.

## Requirements

To run these programs, you need:

1. Hadoop distribution (3.x recommended)
2. Apache Spark (3.x recommended)
3. Java 8 or higher
4. Scala 2.12 or higher

## Programs Included

### 1. WordCount.java
A simple word counting application using Hadoop MapReduce that counts occurrences of each word in a given input set.

**Compile:**
```bash
hadoop com.sun.tools.javac.Main WordCount.java
jar cf wc.jar WordCount*.class
```

**Run:**
```bash
hadoop jar wc.jar WordCount /input/path /output/path
```

### 2. LogProcessor.java
A distributed application that processes log files to extract information like HTTP status codes and error rates.

**Compile:**
```bash
hadoop com.sun.tools.javac.Main LogProcessor.java
jar cf lp.jar LogProcessor*.class
```

**Run:**
```bash
hadoop jar lp.jar LogProcessor /input/path /output/path
```

### 3. WeatherAnalysis.java
A program that processes weather data to calculate average temperature, dew point, and wind speed.

**Compile:**
```bash
hadoop com.sun.tools.javac.Main WeatherAnalysis.java
jar cf wa.jar WeatherAnalysis*.class
```

**Run:**
```bash
hadoop jar wa.jar WeatherAnalysis /input/path /output/path
```

### 4. WordCountSpark.scala
A simple word counting program implemented using Apache Spark and Scala.

**Compile:**
```bash
scalac -classpath "$(spark-submit --verbose 2>&1 | grep "Spark assembly has been built" | cut -d' ' -f 7)" WordCountSpark.scala
jar cf wcs.jar *.class
```

**Run:**
```bash
spark-submit --class WordCountSpark wcs.jar /input/path /output/path
```

## Local Testing Setup

If you don't have a full Hadoop cluster, you can run these programs in local (standalone) mode:

1. Set HADOOP_HOME to your Hadoop installation directory
2. Configure Hadoop to run in local mode by setting appropriate properties in core-site.xml
3. Ensure that input directories exist and output directories do not exist before running

## Sample Data

For testing these applications, you can use:
- Text files for WordCount: Any text files
- Log files for LogProcessor: Apache/Nginx web server logs
- Weather data for WeatherAnalysis: CSV files with columns for station_id, date, temperature, dew_point, wind_speed

## Troubleshooting

If you encounter errors:
1. Check that all required libraries are in your classpath
2. Ensure Hadoop and Spark are properly configured
3. Make sure input paths exist and output paths do not exist
4. For standalone mode, you might need to set hadoop.tmp.dir to a writable location 