package com.instagram.service;

import com.instagram.dao.ProfileDAO;
import com.instagram.dao.ProfileDAOImpl;
import com.instagram.model.Profile;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    private ProfileDAO profileDAO;

    public ProfileServiceImpl() {
        this.profileDAO = new ProfileDAOImpl();
    }


    @Override
    public boolean createProfile(Profile profile) {
        // TODO: Add validation and call ProfileDAO
        if (profile == null) {
            return false;
        }

        if (profile.getUserId() <= 0) {
            return false;
        }

        if (profile.getProfileName() == null ||
                profile.getProfileName().trim().isEmpty()) {
            return false;
        }
        return profileDAO.createProfile(profile);
    }

    @Override
    public Profile getProfileById(int profileId) {
        // TODO: Call ProfileDAO
        return profileDAO.getProfileById(profileId);
    }

    @Override
    public Profile getProfileByUserId(int userId) {
        // TODO: Call ProfileDAO

        if (userId <= 0) {
            return null;
        }
        return profileDAO.getProfileByUserId(userId);
    }

    @Override
    public boolean updateProfile(Profile profile) {
        // TODO: Add validation and call ProfileDAO

        if (profile == null) {
            return false;
        }

        if (profile.getProfileId() <= 0) {
            return false;
        }

        if (profile.getUserId() <= 0) {
            return false;
        }

        if (profile.getProfileName() == null ||
                profile.getProfileName().trim().isEmpty()) {
            return false;
        }

        return profileDAO.updateProfile(profile);
    }

    @Override
    public boolean deleteProfile(int profileId) {
        // TODO: Call ProfileDAO
        if (profileId <= 0) {
            return false;
        }

        return profileDAO.deleteProfile(profileId);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileDAO.getAllProfiles();
    }
}