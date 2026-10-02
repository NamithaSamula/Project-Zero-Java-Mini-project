package com.instagram.service;

import com.instagram.model.Profile;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceImplTest {

    @Test
    void createProfile() {

        ProfileService profileService = new ProfileServiceImpl();

        Profile profile = new Profile();

        profile.setUserId(14);
        profile.setProfileName("Vinitha_kad");
        profile.setBio("Web Developer");
        profile.setPhone("98978292987");
        profile.setProfileImageUrl("vinitha.jpg");

        boolean result = profileService.createProfile(profile);

        assertTrue(result);
    }

    @Test
    void getProfileById() {

        ProfileService profileService = new ProfileServiceImpl();

        Profile profile = profileService.getProfileById(1);

        assertNotNull(profile);
        assertEquals(1, profile.getProfileId());
        assertEquals(17, profile.getUserId());
        assertEquals("Namitha M", profile.getProfileName());

        System.out.println("Profile{" +" profileId=" + profile.getProfileId()
                + " userId=" + profile.getUserId()
                +" profileName=" + profile.getProfileName()
                + " bio= " + profile.getBio()
                + " phone=" + profile.getPhone()
                + " profileImageUrl="+profile.getProfileImageUrl() +"}");
    }

    @Test
    void getProfileByUserId() {
        ProfileService profileService = new ProfileServiceImpl();

        Profile profile = profileService.getProfileByUserId(17);

        assertNotNull(profile);
        assertEquals(17, profile.getUserId());

        System.out.println(profile);
    }

    @Test
    void updateProfile() {

        ProfileService profileService = new ProfileServiceImpl();

        Profile profile = profileService.getProfileById(1);

        assertNotNull(profile);

        profile.setProfileName("namitha M Updated");
        profile.setBio("Java Full Stack Developer");
        profile.setPhone("6305452805");
        profile.setProfileImageUrl("Namitha_updated.jpg");

        boolean result = profileService.updateProfile(profile);

        assertTrue(result);
        System.out.println(profile);
    }

    @Test
    void deleteProfile() {

        ProfileService profileService = new ProfileServiceImpl();

        boolean result = profileService.deleteProfile(1);

        assertTrue(result);

    }

    @Test
    void getAllProfiles() {
        ProfileService profileService = new ProfileServiceImpl();

        List<Profile> profiles = profileService.getAllProfiles();

        assertNotNull(profiles);
        assertFalse(profiles.isEmpty());

        for (Profile profile : profiles) {
            System.out.println(profile);
        }
    }
}