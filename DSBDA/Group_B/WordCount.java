import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Problem 1: Word Count Application using Hadoop MapReduce
 * This program counts the occurrence of each word in the input files
 * Run with: hadoop jar WordCount.jar WordCount <input_path> <output_path>
 */
public class WordCount {
    
    /**
     * Mapper class that tokenizes each line and emits (word, 1) pairs
     */
    public static class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            // Convert line to string and tokenize it
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            
            // For each word in the line, emit (word, 1)
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken().toLowerCase());
                context.write(word, one);
            }
        }
    }
    
    /**
     * Reducer class that sums up all values for each key (word)
     */
    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        
        private IntWritable result = new IntWritable();
        
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            // Sum up all counts for the current word
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }
    
    public static void main(String[] args) throws Exception {
        // Check arguments
        if (args.length != 2) {
            System.err.println("Usage: WordCount <input path> <output path>");
            System.exit(-1);
        }
        
        // Create configuration and job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        
        // Set jar by class
        job.setJarByClass(WordCount.class);
        
        // Set mapper and reducer classes
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);  // Combiner for efficiency
        job.setReducerClass(IntSumReducer.class);
        
        // Define output types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        // Set input and output formats
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        
        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        // Exit when job completes
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
} 