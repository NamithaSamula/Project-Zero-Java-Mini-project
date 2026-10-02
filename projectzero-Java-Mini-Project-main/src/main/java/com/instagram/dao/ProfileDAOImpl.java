package com.instagram.dao;

import com.instagram.model.Profile;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileDAOImpl implements ProfileDAO {

    private static final Logger logger =
            Logger.getLogger(ProfileDAOImpl.class.getName());

    @Override
    public boolean createProfile(Profile profile) {

        String sql = "INSERT INTO profiles " +
                "(user_id, full_name, bio, phone, profile_image_url) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, profile.getUserId());
            statement.setString(2, profile.getProfileName());
            statement.setString(3, profile.getBio());
            statement.setString(4, profile.getPhone());
            statement.setString(5, profile.getProfileImageUrl());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                logger.info("Profile added successfully for user ID: "
                        + profile.getUserId());
                return true;
            }

        } catch (SQLException e) {
            logger.log(
                    Level.SEVERE,
                    "Unable to add profile for user ID: "
                            + profile.getUserId(),
                    e
            );
        }

        return false;
    }

    @Override
    public Profile getProfileById(int profileId) {

        String sql = "SELECT * FROM profiles WHERE profile_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, profileId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Profile profile = new Profile();

                    profile.setProfileId(
                            resultSet.getInt("profile_id"));

                    profile.setUserId(
                            resultSet.getInt("user_id"));

                    profile.setProfileName(
                            resultSet.getString("full_name"));

                    profile.setBio(
                            resultSet.getString("bio"));

                    profile.setPhone(
                            resultSet.getString("phone"));

                    profile.setProfileImageUrl(
                            resultSet.getString("profile_image_url"));

                    return profile;
                }
            }

        } catch (SQLException e) {
            logger.log(
                    Level.SEVERE,
                    "Unable to get profile with ID: "
                            + profileId,
                    e
            );
        }

        return null;
    }

    @Override
    public Profile getProfileByUserId(int userId) {

        String sql = "SELECT * FROM profiles WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Profile profile = new Profile();

                    profile.setProfileId(
                            resultSet.getInt("profile_id"));

                    profile.setUserId(
                            resultSet.getInt("user_id"));

                    profile.setProfileName(
                            resultSet.getString("full_name"));

                    profile.setBio(
                            resultSet.getString("bio"));

                    profile.setPhone(
                            resultSet.getString("phone"));

                    profile.setProfileImageUrl(
                            resultSet.getString("profile_image_url"));

                    return profile;
                }
            }

        } catch (SQLException e) {
            logger.log(
                    Level.SEVERE,
                    "Unable to get profile for user ID: "
                            + userId,
                    e
            );
        }

        return null;
    }

    @Override
    public List<Profile> getAllProfiles() {

        String sql = "SELECT * FROM profiles";

        List<Profile> profiles = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Profile profile = new Profile();

                profile.setProfileId(
                        resultSet.getInt("profile_id"));

                profile.setUserId(
                        resultSet.getInt("user_id"));

                profile.setProfileName(
                        resultSet.getString("full_name"));

                profile.setBio(
                        resultSet.getString("bio"));

                profile.setPhone(
                        resultSet.getString("phone"));

                profile.setProfileImageUrl(
                        resultSet.getString("profile_image_url"));

                profiles.add(profile);
            }

            return profiles;

        } catch (SQLException e) {

            logger.log(
                    Level.SEVERE,
                    "Unable to get all profiles",
                    e
            );

            return List.of();
        }
    }

    @Override
    public boolean updateProfile(Profile profile) {

        String sql = "UPDATE profiles SET " +
                "full_name = ?, " +
                "bio = ?, " +
                "phone = ?, " +
                "profile_image_url = ? " +
                "WHERE profile_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, profile.getProfileName());
            statement.setString(2, profile.getBio());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getProfileImageUrl());
            statement.setInt(5, profile.getProfileId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                logger.info(
                        "Profile updated successfully with ID: "
                                + profile.getProfileId());
                return true;
            }

        } catch (SQLException e) {
            logger.log(
                    Level.SEVERE,
                    "Unable to update profile with ID: "
                            + profile.getProfileId(),
                    e
            );
        }

        return false;
    }

    @Override
    public boolean deleteProfile(int profileId) {

        String sql = "DELETE FROM profiles WHERE profile_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, profileId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                logger.info(
                        "Profile deleted successfully with ID: "
                                + profileId);
                return true;
            }

        } catch (SQLException e) {
            logger.log(
                    Level.SEVERE,
                    "Unable to delete profile with ID: "
                            + profileId,
                    e
            );
        }

        return false;
    }
}