package com.example.classproject;

public class Hostel {

    private String studentId;
    private String hostelLocation;
    private String roomType;
    private boolean sharable;
    private long timestamp;

    // Required empty constructor for Firebase
    public Hostel() { }

    public Hostel(String studentId, String hostelLocation, String roomType,
                  boolean sharable, long timestamp) {
        this.studentId = studentId;
        this.hostelLocation = hostelLocation;
        this.roomType = roomType;
        this.sharable = sharable;
        this.timestamp = timestamp;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getHostelLocation() { return hostelLocation; }
    public void setHostelLocation(String hostelLocation) { this.hostelLocation = hostelLocation; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public boolean isSharable() { return sharable; }
    public void setSharable(boolean sharable) { this.sharable = sharable; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
