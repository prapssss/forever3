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

        String query = "SELECT * FROM user WHERE id = ?";

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

        String query = user.getImageUrl() != null && !user.getImageUrl().isEmpty()

                ? "UPDATE user SET first_name = ?, last_name = ?, email = ?, number = ?, image_url = ? WHERE id = ?"

                : "UPDATE user SET first_name = ?, last_name = ?, email = ?, number = ? WHERE id = ?";
 
        try (Connection conn = DbConfig.getDbConnection();

             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());

            stmt.setString(2, user.getLastName());

            stmt.setString(3, user.getEmail());

            stmt.setString(4, user.getNumber());
 
            if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {

                stmt.setString(5, user.getImageUrl());

                stmt.setInt(6, user.getId());

            } else {

                stmt.setInt(5, user.getId());

            }
 
            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;
 
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }

        return false;

    }
 
    private UserModel mapToUserModel(ResultSet resultSet) throws SQLException {

        UserModel user = new UserModel();

        user.setId(resultSet.getInt("id"));

        user.setFirstName(resultSet.getString("first_name"));

        user.setLastName(resultSet.getString("last_name"));

        user.setUserName(resultSet.getString("user_name"));

        user.setEmail(resultSet.getString("email"));

        user.setNumber(resultSet.getString("number"));

        user.setPassword(resultSet.getString("password"));

        user.setImageUrl(resultSet.getString("image_url"));

        user.setRole(resultSet.getString("role"));

        return user;

    }

}

 