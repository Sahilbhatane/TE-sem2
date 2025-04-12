import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Problem 3: Weather Data Analysis using MapReduce
 * This program processes weather data to calculate average temperature, 
 * dew point, and wind speed from weather station readings
 * Run with: hadoop jar WeatherAnalysis.jar WeatherAnalysis <input_path> <output_path>
 */
public class WeatherAnalysis {
    
    /**
     * Mapper for processing weather data
     * Assumes CSV format with columns for station_id, date, temperature, dew_point, wind_speed
     */
    public static class WeatherMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
        
        private Text outputKey = new Text();
        private DoubleWritable outputValue = new DoubleWritable();
        
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            // Skip header line
            if (key.get() == 0 && value.toString().contains("station_id")) {
                return;
            }
            
            String line = value.toString();
            String[] fields = line.split(",");
            
            // Check if we have enough fields
            if (fields.length < 5) {
                return;
            }
            
            try {
                // Extract data - fields order: station_id, date, temperature, dew_point, wind_speed
                String stationId = fields[0].trim();
                double temperature = Double.parseDouble(fields[2].trim());
                double dewPoint = Double.parseDouble(fields[3].trim());
                double windSpeed = Double.parseDouble(fields[4].trim());
                
                // Emit temperature readings
                outputKey.set(stationId + "-TEMP");
                outputValue.set(temperature);
                context.write(outputKey, outputValue);
                
                // Emit dew point readings
                outputKey.set(stationId + "-DEW");
                outputValue.set(dewPoint);
                context.write(outputKey, outputValue);
                
                // Emit wind speed readings
                outputKey.set(stationId + "-WIND");
                outputValue.set(windSpeed);
                context.write(outputKey, outputValue);
                
            } catch (NumberFormatException e) {
                // Skip lines with invalid data
            }
        }
    }
    
    /**
     * Reducer to calculate averages for each weather metric by station
     */
    public static class WeatherReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        
        private DoubleWritable result = new DoubleWritable();
        private MultipleOutputs<Text, DoubleWritable> multipleOutputs;
        
        @Override
        protected void setup(Context context) {
            multipleOutputs = new MultipleOutputs<>(context);
        }
        
        public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
                throws IOException, InterruptedException {
            
            double sum = 0;
            int count = 0;
            
            // Calculate sum and count
            for (DoubleWritable val : values) {
                sum += val.get();
                count++;
            }
            
            // Calculate average
            double average = count > 0 ? sum / count : 0;
            result.set(average);
            
            // Write result to appropriate output file based on metric type
            String keyStr = key.toString();
            String stationId = keyStr.substring(0, keyStr.indexOf("-"));
            String metricType = keyStr.substring(keyStr.indexOf("-") + 1);
            
            Text outputKey = new Text(stationId);
            multipleOutputs.write(outputKey, result, metricType);
        }
        
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            multipleOutputs.close();
        }
    }
    
    public static void main(String[] args) throws Exception {
        // Check arguments
        if (args.length != 2) {
            System.err.println("Usage: WeatherAnalysis <input path> <output path>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "weather analysis");
        
        job.setJarByClass(WeatherAnalysis.class);
        job.setMapperClass(WeatherMapper.class);
        job.setReducerClass(WeatherReducer.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        
        // Set multiple outputs
        MultipleOutputs.addNamedOutput(job, "TEMP", TextOutputFormat.class, Text.class, DoubleWritable.class);
        MultipleOutputs.addNamedOutput(job, "DEW", TextOutputFormat.class, Text.class, DoubleWritable.class);
        MultipleOutputs.addNamedOutput(job, "WIND", TextOutputFormat.class, Text.class, DoubleWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
} 