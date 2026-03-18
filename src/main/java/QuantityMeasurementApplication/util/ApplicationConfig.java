package QuantityMeasurementApplication.util;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            properties.load(input);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to load application.properties");
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public int getPoolSize() {
        String size = properties.getProperty("db.pool.size");

        if (size == null || size.isEmpty()) {
            return 5; // default value
        }

        return Integer.parseInt(size);
    }
    

    public static String getRepositoryType() {
        return properties.getProperty("repository.type");
    }
}