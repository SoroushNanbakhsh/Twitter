package com.example.finalproject.Models.RequestAndResponse;

/**
 * This class is the ResponseStatus sent from server to client with the Response.
 * each response has a response status that determines whether
 * the request was handled successfully or not in the server.
 * The responseStatus represents how the request was handled by the server.
 */

public enum ResponseStatus {
    // signing up
    INVALID_USERNAME,
    VALID_USERNAME,
    SIGNUP_INVALID,
    SIGNUP_VALID,
    EMAIL_VALID,
    EMAIL_INVALID,
    PHONE_NUMBER_VALID,
    PHONE_NUMBER_INVALID,

    // logging in
    LOG_IN_VALID,
    LOG_IN_INVALID,
    LOG_OUT_VALID,
    LOG_IN_WITH_TIME_VALID,
    LOG_IN_WITH_TIME_INVALID,

    // TWEET
    ADD_TWEET_VALID,
    ADD_TWEET_INVALID,
    GET_NEW_TWEETS_INVALID,
    GET_NEW_TWEETS_VALID,
    GET_USER_TWEETS_INVALID,
    GET_USER_TWEETS_VALID,
    UPDATE_TWEET_VALID,
    UPDATE_TWEET_INVALID,

    // PROFILE
    SEARCH_IN_USERS_INVALID,
    SEARCH_IN_USERS_VALID,
    UPDATE_USER_INVALID,
    UPDATE_USER_VALID,
    CHANGE_PROFILE_USERNAME_INVALID,
    CHANGE_PROFILE_USERNAME_VALID,
    CHANGE_PROFILE_PASSWORD_INVALID,
    CHANGE_PROFILE_PASSWORD_VALID,
    CHANGE_PROFILE_FIRSTNAME_INVALID,
    CHANGE_PROFILE_FIRSTNAME_VALID,
    CHANGE_PROFILE_LASTNAME_VALID,
    CHANGE_PROFILE_LASTNAME_INVALID,
    CHANGE_PROFILE_BIO_VALID,
    CHANGE_PROFILE_BIO_INVALID,
    CHANGE_PROFILE_AVATAR_VALID,
    CHANGE_PROFILE_AVATAR_INVALID,

    // miscellaneous
    FOLLOW_INVALID,
    FOLLOW_VALID,
    UNFOLLOW_VALID,
    UNFOLLOW_INVALID,
    BLOCK_VALID,
    BLOCK_INVALID,

    // DIRECT
    DIRECT_MESSAGE_INVALID,
    DIRECT_MESSAGE_VALID,
    GET_DIRECT_MESSAGE_INVALID,
    GET_DIRECT_MESSAGE_VALID,
    SEND_MESSAGE_INVALID,
    SEND_MESSAGE_VALID,
    GET_CHAT_VALID,
    GET_CHAT_INVALID,
}
