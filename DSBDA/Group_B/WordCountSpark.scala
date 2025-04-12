import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 * Problem 4: Simple Word Count using Apache Spark and Scala
 * 
 * This program demonstrates the use of Apache Spark to count words in text files.
 * It shows the basic RDD operations in Spark using Scala.
 * 
 * To run:
 * spark-submit --class WordCountSpark WordCountSpark.jar <input_path> <output_path>
 */
object WordCountSpark {
  def main(args: Array[String]) {
    
    // Check command line arguments
    if (args.length < 2) {
      println("Usage: WordCountSpark <input_path> <output_path>")
      System.exit(1)
    }
    
    // Get input and output paths
    val inputPath = args(0)
    val outputPath = args(1)
    
    // Create Spark configuration and context
    val conf = new SparkConf()
      .setAppName("Word Count Example")
      .setMaster("local[*]") // Use local mode with as many threads as cores
    
    val sc = new SparkContext(conf)
    
    // Load text files and perform word count
    val textFiles = sc.textFile(inputPath)
    
    // Split each line into words, flatten the result, and count each word
    val wordCounts = textFiles
      .flatMap(line => line.toLowerCase().split("\\W+")) // Split by non-word characters
      .filter(word => word.length > 0) // Remove empty words
      .map(word => (word, 1)) // Create (word, 1) pairs
      .reduceByKey(_ + _) // Sum up the counts for each word
      .sortBy(pair => pair._2, ascending = false) // Sort by count (descending)
    
    // Save the results
    wordCounts.saveAsTextFile(outputPath)
    
    // Print the top 10 most frequent words
    println("\nTop 10 most frequent words:")
    wordCounts.take(10).foreach(println)
    
    // Stop the Spark context
    sc.stop()
  }
} 