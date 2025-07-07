package com.taskmanager.profiles.api.profile.services;

public interface FindProfileByUsernameServiceTests {
    void givenProfileDoesNotExist_WhenExecute_ThenReturnAnonymousProfile();
    void givenProfileExists_WhenExecute_ThenReturnExistingProfile();
}
