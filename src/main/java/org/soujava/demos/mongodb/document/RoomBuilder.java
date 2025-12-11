package org.soujava.demos.mongodb.document;

public class RoomBuilder {
    private String id;
    private int roomNumber;
    private RoomType type;
    private RoomStatus status;
    private CleanStatus cleanStatus;
    private boolean smokingAllowed;
    private boolean underMaintenance;

    public RoomBuilder id(String id) {
        this.id = id;
        return this;
    }

    public RoomBuilder roomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomBuilder type(RoomType type) {
        this.type = type;
        return this;
    }

    public RoomBuilder status(RoomStatus status) {
        this.status = status;
        return this;
    }

    public RoomBuilder cleanStatus(CleanStatus cleanStatus) {
        this.cleanStatus = cleanStatus;
        return this;
    }

    public RoomBuilder smokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
        return this;
    }

    public RoomBuilder underMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
        return this;
    }

    public Room build() {
        return new Room(id, roomNumber, type, status, cleanStatus, smokingAllowed, underMaintenance);
    }
}