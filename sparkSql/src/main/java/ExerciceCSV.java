import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.max;

public class ExerciceCSV {
    public static void main(String[] args) {
        RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.spark-project").setLevel(Level.WARN);

        SparkSession ss=SparkSession.builder().
                appName("TP SPARK SQL").
                master("local[*]").getOrCreate();

        // Dataset from csv file
        Dataset<Row> dataset= ss.read() // that returns a DataFrameReader object
                .option("header",true) // that allows to read the header
                .option("inferSchema",true) //
                .csv("./employees.csv"); // path to csv file
        Dataset<Row> df = dataset.select(col("id").cast("long")// cast the id column to long
                ,col("name")// select the name column
                ,col("salary").cast("double") // cast the salary column to double
                ,col("department") // select the department column
        ); // that returns a DataFrame object casted to Row

        //dataset.select(col("name"),col("salary")).show();
        // employees with age > 30 and < 35
        System.out.println("employees with age > 30 and < 35");
        df.filter(col("age").between(30,35)).show();
        // average salary by department
        System.out.println("average salary by department");
        df.groupBy("department").avg("salary").show();
        // count employees by department
        System.out.println("count employees by department");
        df.groupBy("department").count().show();
        // max salary by department
        System.out.println("max salary by department");
        df.groupBy("department").max("salary").show();
        // max salary in all departments
        System.out.println("max salary in all departments");
        df.select(max("salary")).show();
    }
}