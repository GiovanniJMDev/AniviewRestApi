package com.aniview.aniview.DTO;

public class PasswordChangeDTO {
    private String newPassword;

    // Getters and Setters
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean isValid() {
        return newPassword != null && !newPassword.trim().isEmpty();
    }
}