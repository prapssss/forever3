package com.forever3.service;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;
 
public class ProfileService {
 
    // Get user profile by userId
    public UserModel getUserProfile(int userId) {
        String query = "SELECT * FROM customer WHERE id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
 
            if (result.next()) {
                return mapToUserModel(result);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    // Update user profile
    public boolean updateUserProfile(UserModel user) {
        String query = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, number = ?, password = ? WHERE id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getNumber());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    private UserModel mapToUserModel(ResultSet result) throws SQLException {
        UserModel user = new UserModel();
        user.setId(result.getInt("id"));
        user.setUserName(result.getString("user_name"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setNumber(result.getString("number"));
        user.setPassword(result.getString("password"));
        user.setImageUrl(result.getString("image_url"));
        return user;
    }
}

 