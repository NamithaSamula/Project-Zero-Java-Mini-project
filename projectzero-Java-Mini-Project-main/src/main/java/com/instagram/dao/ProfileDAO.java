package com.instagram.dao;

import com.instagram.model.Profile;

import java.util.List;

public interface ProfileDAO {

    boolean createProfile(Profile profile);

    Profile getProfileById(int profileId);

    Profile getProfileByUserId(int userId);

    boolean updateProfile(Profile profile);

    boolean deleteProfile(int profileId);

    List<Profile> getAllProfiles();
}