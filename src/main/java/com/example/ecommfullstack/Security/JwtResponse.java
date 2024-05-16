package com.example.ecommfullstack.Security;

public class JwtResponse {
    private String token;
    private String redirectUrl;
    
    
    
    
	public JwtResponse(String token) {
		super();
		this.token = token;
	}


	public JwtResponse(String token, String redirectUrl) {
		super();
		this.token = token;
		this.redirectUrl = redirectUrl;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getRedirectUrl() {
		return redirectUrl;
	}


	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

    
    
    
}
