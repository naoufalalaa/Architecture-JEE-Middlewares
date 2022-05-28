import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class Exercice2 {
    public static void main(String[] args) {
           RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
           rootLogger.setLevel(Level.ERROR);
           Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
           Logger.getLogger("org.spark-project").setLevel(Level.WARN);

        SparkSession ss=SparkSession.builder().
                appName("TP SPARK SQL").
                master("local[*]").getOrCreate();
        // JDBC connection
        Dataset<Row> dataset= ss.read().format("jdbc")
                .option("url","jdbc:mysql://localhost:3306/DB_CATALOGUE")
                .option("driver","com.mysql.cj.jdbc.Driver")
                //.option("dbtable","PRODUCTS")
                // QUERY
                .option("query","SELECT * FROM ORDER_ITEMS")
                .option("user","root")
                .option("password","")
                .load();
        // SCHEMA
        dataset.printSchema();
        // CATEGORIES OF PRODUCTS
        dataset.groupBy("PRODUCT_CATEGORY_ID").count().show();
    }
}