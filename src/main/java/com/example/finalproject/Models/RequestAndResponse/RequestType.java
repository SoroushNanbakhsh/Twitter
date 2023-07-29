package com.example.finalproject.Models.RequestAndResponse;

/**
 * This enum contains all the different requests that the client can send to the server
 * in order to receive data needed for the program to function.
 * <p>
 * The request type determines how the server will respond to the request
 */
public enum RequestType {

    // signing up
    CHECK_USERNAME,
    SIGN_UP,
    LOG_IN,
    LOG_OUT,
    LOG_IN_WITH_TIME,
    CHECK_EMAIL,
    CHECK_PHONE_NUMBER,

    // TWEET
    ADD_TWEET,
    GET_NEW_TWEETS,
    GET_USER_TWEETS,
    UPDATE_TWEET,

    // Profile
    CHANGE_PROFILE_USERNAME,
    CHANGE_PROFILE_PASSWORD,
    CHANGE_PROFILE_AVATAR,
    CHANGE_PROFILE_BIO,
    CHANGE_PROFILE_FIRSTNAME,
    CHANGE_PROFILE_LASTNAME,
    SEARCH_IN_USERS,
    UPDATE_USER,

    // miscellaneous
    FOLLOW,
    UNFOLLOW,
    BLOCK,

    // direct
    DIRECT_MESSAGE,
    GET_DIRECT_MESSAGE,
    SEND_MESSAGE,
    GET_CHAT,

}
