package org.example.sdaprojectvotix;

public class Administrator {
    private int adminID = 0;
    private long cnic;
    private String name;
    private long phone;
    private String email;
    private String username;
    private String password;

    public boolean login(String username, String password) { return true; }
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    // Additional getters and setters...
}

