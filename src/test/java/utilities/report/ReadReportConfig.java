package utilities.report;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadReportConfig {

	private static Properties pro;
    
    private static Properties initFile() {

        File src = new File("./src/test/resources/extent.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
		return pro;
        
    }
    
    public static String getReportDir() {
        String report_dir = initFile().getProperty("report_dir");
        return report_dir;
    }
    
}