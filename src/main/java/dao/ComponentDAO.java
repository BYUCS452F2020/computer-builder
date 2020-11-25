package dao;

import models.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponentDAO {

    private final Connection connection;

    public ComponentDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Component component) throws DataAccessException {
        String sql = "INSERT INTO Components (component_id, component_name, component_type, " +
                "manufacturer, performance_rating, price, cpu_family, tdp) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, component.getComponentID());
            stmt.setString(2, component.getComponentName());
            stmt.setString(3, component.getComponentType());
            stmt.setString(4, component.getManufacturer());
            stmt.setInt(5, component.getPerformanceRating());
            stmt.setDouble(6, component.getPrice());
            stmt.setString(7, component.getCpuFamily());
            stmt.setInt(8, component.getTpd());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting into the Components table in database");
        }
    }

    public Component queryNoPreconditions(String componentID) throws DataAccessException {
        Component component;
        ResultSet rs = null;
        String sql = "SELECT * FROM Components WHERE component_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, componentID);
            rs = stmt.executeQuery();
            if(rs.next()) {
                component = new Component(rs.getString("component_id"),
                        rs.getString("component_name"), rs.getString("component_type"),
                        rs.getString("manufacturer"), rs.getInt("performance_rating"),
                        rs.getDouble("price"), rs.getString("cpu_family"),
                        rs.getInt("tdp"));
                return component;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while querying a component");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Component> queryComponentsWithConditions(String componentType, int maxPrice, int performanceRating,
                                                         String cpuFamily, int maxTDP) throws DataAccessException {
        List<Component> components = new ArrayList<>();
        boolean isPriced = false;
        boolean isPerformance = false;
        boolean isCPUFamily = false;
        boolean isPowerSupply = false;
        int index = 1;
        ResultSet rs = null;
        String sql = "SELECT * FROM Components WHERE component_type = ?";
        if (maxPrice != 0) {
            System.out.println("maxprice");
            isPriced = true;
            sql += " AND price <= ?";
        }
        if (performanceRating != 0) {
            System.out.println("prating");
            isPerformance = true;
            sql += " AND performance_rating = ?";
        }
        if (cpuFamily != null) {
            System.out.println("cpufam");
            isCPUFamily = true;
            sql += " AND cpu_family = ?";
        }
        if (componentType.equals("Power-Supply")) {
            System.out.println("powersupply");
            isPowerSupply = true;
            sql += " AND tdp = ?";
        }
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(index, componentType);
            if(isPriced) {
                index += 1;
                stmt.setInt(index, maxPrice);
            }
            if(isPerformance) {
                index += 1;
                stmt.setInt(index, performanceRating);
            }
            if(isCPUFamily) {
                index += 1;
                stmt.setString(index, cpuFamily);
            }
            if(isPowerSupply) {
                index += 1;
                stmt.setInt(index, maxTDP);
            }
            rs = stmt.executeQuery();
            while(rs.next()) {
                components.add(new Component(rs.getString("component_id"),
                        rs.getString("component_name"), rs.getString("component_type"),
                        rs.getString("manufacturer"), rs.getInt("performance_rating"),
                        rs.getDouble("price"), rs.getString("cpu_family"),
                        rs.getInt("tdp")));
            }
            return components;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying components with conditions: " + e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int deleteAll() throws DataAccessException {
        int count;
        String sql = "DELETE FROM Components";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting all components");
        }
    }

    public int deleteOne(String componentID) throws DataAccessException {
        int count;
        String sql = "DELETE FROM Components WHERE component_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, componentID);
            count = stmt.executeUpdate();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting one component");
        }
    }

}
