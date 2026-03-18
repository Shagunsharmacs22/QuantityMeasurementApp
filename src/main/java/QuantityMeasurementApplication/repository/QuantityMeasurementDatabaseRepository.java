package QuantityMeasurementApplication.repository;

import QuantityMeasurementApplication.entity.QuantityMeasurementEntity;
import QuantityMeasurementApplication.exception.DatabaseException;
import QuantityMeasurementApplication.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

	@Override
	public void save(QuantityMeasurementEntity entity) {

		String sql = "INSERT INTO quantity_measurement (measurement_value, unit, type, operation, created_at) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setDouble(1, entity.getValue());
			ps.setString(2, entity.getUnit());
			ps.setString(3, entity.getType());
			ps.setString(4, entity.getOperation());
			ps.setTimestamp(5, Timestamp.valueOf(java.time.LocalDateTime.now()));

			ps.executeUpdate();

			System.out.println("✅ Data inserted successfully");

		} catch (Exception e) {
			throw new DatabaseException("Error saving measurement", e);
		}
	}

	// 🔥 COMMON MAPPER METHOD
	private QuantityMeasurementEntity mapRow(ResultSet rs) throws SQLException {
		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
		entity.setValue(rs.getDouble("measurement_value"));
		entity.setUnit(rs.getString("unit"));
		entity.setType(rs.getString("type"));
		entity.setOperation(rs.getString("operation"));
		return entity;
	}

	@Override
	public List<QuantityMeasurementEntity> getAllMeasurements() {

		String sql = "SELECT * FROM quantity_measurement";
		List<QuantityMeasurementEntity> list = new ArrayList<>();

		try (Connection conn = ConnectionPool.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(mapRow(rs));
			}

		} catch (Exception e) {
			throw new DatabaseException("Error fetching all measurements", e);
		}

		return list;
	}

	@Override
	public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {

		String sql = "SELECT * FROM quantity_measurement WHERE operation = ?";
		List<QuantityMeasurementEntity> list = new ArrayList<>();

		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, operation);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRow(rs));
				}
			}

		} catch (Exception e) {
			throw new DatabaseException("Error fetching by operation", e);
		}

		return list;
	}

	@Override
	public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {

		String sql = "SELECT * FROM quantity_measurement WHERE type = ?";
		List<QuantityMeasurementEntity> list = new ArrayList<>();

		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, type);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRow(rs));
				}
			}

		} catch (Exception e) {
			throw new DatabaseException("Error fetching by type", e);
		}

		return list;
	}

	@Override
	public int getTotalCount() {

		String sql = "SELECT COUNT(*) FROM quantity_measurement";

		try (Connection conn = ConnectionPool.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			throw new DatabaseException("Error getting count", e);
		}

		return 0;
	}

	@Override
	public void deleteAll() {

		String sql = "DELETE FROM quantity_measurement";

		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.executeUpdate();

			System.out.println("🗑️ All records deleted");

		} catch (Exception e) {
			throw new DatabaseException("Error deleting data", e);
		}
	}
}