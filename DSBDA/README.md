# Data Science and Big Data Analytics (DSBDA) Assignments

This repository contains practical assignments for the Data Science and Big Data Analytics (DSBDA) course. Each directory represents a different practical assignment focusing on various aspects of data science, from basic data manipulation to advanced machine learning techniques.

## Requirements

To run these programs, you need Python 3.8+ and the following packages:

- pandas (for data manipulation)
- numpy (for numerical operations)
- matplotlib (for data visualization)
- seaborn (for advanced data visualization)
- scikit-learn (for machine learning algorithms)
- nltk (for natural language processing)

Install the required packages using:

```bash
pip install -r requirements.txt
```

For NLTK, you may need to download additional resources:

```python
import nltk
nltk.download('punkt')
nltk.download('stopwords')
nltk.download('averaged_perceptron_tagger')
```

## Assignments

### Assignment 1: Data Wrangling and Manipulation
- **File**: `1/1.ipynb`
- **Dataset**: `1/data.csv`
- **Description**: Basic data manipulation operations using pandas, including data type conversion, handling missing values, sorting, filtering, and reshaping data.
- **Key Operations**: Data loading, exploration, transformation, and basic statistics.

### Assignment 2: Data Cleaning and Preprocessing
- **File**: `2/2.ipynb`
- **Dataset**: `2/acdemic_data.csv`
- **Description**: Techniques for identifying and handling missing values in datasets, including various methods for imputation.
- **Key Operations**: Detecting missing values, removing or filling missing values, and data cleaning.

### Assignment 3: Statistical Methods in Data Analysis
- **Part 1 File**: `3/part 1/3.ipynb`
- **Part 2 File**: `3/part 2/3.ipynb`
- **Datasets**: `3/part 1/loan.csv`, `3/part 2/Iris.csv`
- **Description**: Application of statistical methods to analyze datasets, including calculating measures of central tendency and dispersion.
- **Key Operations**: Mean, median, mode, standard deviation, variance, and statistical summaries.

### Assignment 4: Linear Regression
- **File**: `4/4.ipynb`
- **Dataset**: `4/boston_housing.csv`
- **Description**: Implementation of linear regression to predict housing prices, including model training and evaluation.
- **Key Operations**: Data splitting, model training, prediction, and performance evaluation.

### Assignment 5: Logistic Regression
- **File**: `5/5.ipynb`
- **Dataset**: `5/social_data.csv`
- **Description**: Implementation of logistic regression for binary classification on social network advertisement data.
- **Key Operations**: Model training, prediction, visualization of decision boundaries, and confusion matrix analysis.

### Assignment 6: Naive Bayes Classification
- **File**: `6.ipynb`
- **Dataset**: Uses the Iris dataset loaded from a URL
- **Description**: Implementation of Naive Bayes algorithm for multi-class classification on the Iris dataset.
- **Key Operations**: Data preprocessing, model training, confusion matrix, and accuracy calculation.

### Assignment 7: Natural Language Processing
- **File**: `7.ipynb`
- **Description**: Text processing techniques including tokenization, stopword removal, stemming, and part-of-speech tagging.
- **Key Operations**: Text preprocessing, NLTK library usage, and text analysis.

### Assignment 8: Data Visualization with Seaborn
- **File**: `8.ipynb`
- **Dataset**: Titanic dataset from seaborn
- **Description**: Various visualization techniques using the Seaborn library.
- **Key Operations**: Distribution plots, joint plots, pair plots, and rug plots.

### Assignment 9: Advanced Data Visualization
- **File**: `9/9.ipynb`
- **Dataset**: `9/titanic_Dataset.csv`
- **Description**: Creating complex visualizations to understand data relationships.
- **Key Operations**: Count plots, pie charts, histograms, distribution plots, and scatter plots.

### Assignment 10: Clustering and Dimensionality Reduction
- **File**: `10/10.ipynb`
- **Dataset**: `10/Iris.csv`
- **Description**: Visualization techniques for high-dimensional data analysis.
- **Key Operations**: Histograms, boxplots, and various statistical visualizations.

### Group B: Big Data Processing with Hadoop and Spark
- **Files**: Various Java and Scala files in `Group_B/`
- **Description**: Big data processing using Hadoop MapReduce and Apache Spark.
- **Programs**: Word Count, Log Processor, Weather Analysis, and Spark Word Count.

## How to Run the Programs

1. **Jupyter Notebooks**:
   - Install Jupyter: `pip install jupyter`
   - Navigate to the assignment directory
   - Run: `jupyter notebook`
   - Open the desired .ipynb file and run the cells sequentially

2. **Group B Programs**:
   - Follow the compilation and execution instructions in the respective files
   - Ensure Hadoop/Spark is properly configured if running in a distributed environment
   - For local testing, run in standalone mode

## Troubleshooting

- **Missing NLTK Data**: If you encounter errors related to NLTK resources, make sure to download them using the appropriate `nltk.download()` commands.
- **Package Versions**: If you experience compatibility issues, check the versions of your installed packages.
- **File Paths**: Ensure that the data files are located in the correct directories relative to the notebooks.
- **Java/Scala Programs**: For Group B assignments, verify that your Hadoop/Spark environment is properly set up.

## Additional Notes

- Most assignments include data exploration, preprocessing, analysis, and visualization steps.
- Comments in the notebooks explain the purpose of each code segment.
- The assignments build upon each other, with later assignments incorporating techniques from earlier ones.
