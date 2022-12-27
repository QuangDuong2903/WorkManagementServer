package com.workmanagement.api.response;

public class LoginResponse {

	private long id;
	private String usename;
	private String givenName;
	private String familyName;
	private String displayName;
	private String avatar;
	private String email;
	private String accessToken;
	private String refreshToken;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public LoginResponse(long id, String usename, String givenName, String familyName, String displayName,
			String avatar, String email, String accessToken, String refreshToken) {
		this.id = id;
		this.usename = usename;
		this.givenName = givenName;
		this.familyName = familyName;
		this.displayName = displayName;
		this.avatar = avatar;
		this.email = email;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
