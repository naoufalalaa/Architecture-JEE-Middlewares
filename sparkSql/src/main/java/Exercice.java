import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class Exercice {
    public static void main(String[] args) {
        RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.spark-project").setLevel(Level.WARN);

        SparkSession ss=SparkSession.builder().
                appName("TP SPARK SQL").
                master("local[*]").getOrCreate();

        Dataset<Row> dataset= ss.read()
                .option("multiline",true) //pour avoir les lignes multiples
                .json("./employees.json"); // on lit le fichier json

        // employees with age > 30 and < 35
        dataset.filter(col("age").between(30,35)).show();
        // average salary by department
        dataset.groupBy("department").avg("salary").show();
        // count employees by department
        dataset.groupBy("department").count().show();
        // max salary by department
        dataset.groupBy("department").max("salary").show();
        // max salary in all departments
        dataset.select(max("salary")).show();
    }
}