package com.zosh.service;

import com.zosh.exception.UserException;
import com.zosh.payload.request.SignupDto;
import com.zosh.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws Exception;
    AuthResponse signup(SignupDto req) throws Exception;
    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;
}
