import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class ExerciceDATASET {
    public static void main(String[] args) {
           RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
           rootLogger.setLevel(Level.ERROR);
           Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
           Logger.getLogger("org.spark-project").setLevel(Level.WARN);

        SparkSession ss=SparkSession.builder().
                appName("TP SPARK SQL").
                master("local[*]").getOrCreate();
        // Create a DataFrame from a CSV file
        Dataset<Row> dataset= ss.read() // that returns a DataFrameReader object
                .option("header",true) // that allows to read the header
                .option("inferSchema",true) //
                .csv("./employees.csv"); // path to csv file
        Dataset<Row> df = dataset.select(col("id").cast("int")// cast the id column to long
                ,col("name")// select the name column
                ,col("age").cast("int") // cast the age column to int
                ,col("salary").cast("double") // cast the salary column to double
                ,col("department") // select the department column
        ); // that returns a DataFrame object casted to Row
        // dataframe to javaRDD of Employe
        JavaRDD<Employe> employes = df.as(Encoders.bean(Employe.class)).javaRDD();
        // employes with age > 30 and < 35
        employes.collect().stream().filter(e -> e.getAge() >= 30 && e.getAge() <= 35).forEach(e -> System.out.println(e.getName()));
        // average salary by department
        df.groupBy("department").avg("salary").show();

    }
}