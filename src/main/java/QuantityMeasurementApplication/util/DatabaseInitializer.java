package QuantityMeasurementApplication.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

	public static void initialize() {

		try (Connection conn = ConnectionPool.getConnection(); Statement stmt = conn.createStatement()) {

			String sql = """
					    CREATE TABLE IF NOT EXISTS quantity_measurement (
					        id INT AUTO_INCREMENT PRIMARY KEY,
					        measurement_value DOUBLE,
					        unit VARCHAR(50),
					        type VARCHAR(50),
					        operation VARCHAR(50),
					        created_at TIMESTAMP
					    )
					""";

			stmt.execute(sql);

			System.out.println("✅ Table created or already exists");

		} catch (Exception e) {
			throw new RuntimeException("Error creating table", e);
		}
	}
}