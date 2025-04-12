import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Problem 2: Log File Processing using MapReduce
 * This program processes log files to extract useful information such as 
 * HTTP status codes, URL access patterns, and error rates
 * Run with: hadoop jar LogProcessor.jar LogProcessor <input_path> <output_path>
 */
public class LogProcessor {
    
    /**
     * Mapper to process log lines and extract status codes
     * Expects Apache/Nginx style log format
     */
    public static class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        
        private final static IntWritable one = new IntWritable(1);
        private Text statusCode = new Text();
        
        // Regular expression to extract HTTP status code from log
        private static final Pattern LOG_PATTERN = Pattern.compile(
            ".*HTTP/\\d\\.\\d\\\" (\\d{3}) .*"
        );
        
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            String line = value.toString();
            Matcher matcher = LOG_PATTERN.matcher(line);
            
            if (matcher.matches()) {
                // Extract HTTP status code
                String code = matcher.group(1);
                statusCode.set(code);
                context.write(statusCode, one);
                
                // Also categorize errors vs success
                if (code.startsWith("5") || code.startsWith("4")) {
                    statusCode.set("ERROR");
                } else if (code.startsWith("2") || code.startsWith("3")) {
                    statusCode.set("SUCCESS");
                }
                context.write(statusCode, one);
            }
        }
    }
    
    /**
     * Reducer to count occurrences of each status code
     */
    public static class LogReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        
        private IntWritable result = new IntWritable();
        
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
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
            System.err.println("Usage: LogProcessor <input path> <output path>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "log processor");
        
        job.setJarByClass(LogProcessor.class);
        job.setMapperClass(LogMapper.class);
        job.setCombinerClass(LogReducer.class);
        job.setReducerClass(LogReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
} 