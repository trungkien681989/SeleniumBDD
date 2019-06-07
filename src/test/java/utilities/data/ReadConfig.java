package utilities.data;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	private static Properties pro;
    
    private static Properties initFile() {

        File src = new File("./src/test/resources/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
		return pro;
        
    }
    
    public static String getApplicationURL() {
        String url = initFile().getProperty("baseURL");
        return url;
    }
    
    public static String getUsername() {
    	String username = initFile().getProperty("username");
	    return username;
    }
    
    public static String getPassword() {
    	String password = initFile().getProperty("password");
	    return password;
    }
    
}