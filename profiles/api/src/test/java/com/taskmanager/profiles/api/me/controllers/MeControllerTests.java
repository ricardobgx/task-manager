package com.taskmanager.profiles.api.me.controllers;

public interface MeControllerTests {
    void givenUserIsAuthenticated_WhenGetAuthenticatedUserInfo_ThenReturnStatus201AndUserInfo();
    void givenUserIsNotAuthenticated_WhenGetAuthenticatedUserInfo_ThenReturnStatus403();
}
