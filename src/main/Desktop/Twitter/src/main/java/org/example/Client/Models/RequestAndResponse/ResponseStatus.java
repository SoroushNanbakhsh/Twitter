package org.example.Client.Models.RequestAndResponse;

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
    CHANGE_PROFILE_INVALID,
    CHANGE_PROFILE_VALID,
    SEARCH_IN_USERS_INVALID,
    SEARCH_IN_USERS_VALID,
    UPDATE_USER_INVALID,
    UPDATE_USER_VALID,

    // miscellaneous
    FOLLOW_INVALID,
    FOLLOW_VALID,
    UNFOLLOW_VALID,
    UNFOLLOW_INVALID,

}
