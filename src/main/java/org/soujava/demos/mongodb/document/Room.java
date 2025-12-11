package org.soujava.demos.mongodb.document;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Objects;

@Entity
public class Room {

    @Id
    private String id;

    @Column
    private int number;

    @Column
    private RoomType type;

    @Column
    private RoomStatus status;

    @Column
    private CleanStatus cleanStatus;

    @Column
    private boolean smokingAllowed;

    @Column
    private boolean underMaintenance;


    public Room() {
    }

    public Room(String id, int number,
                RoomType type, RoomStatus status,
                CleanStatus cleanStatus,
                boolean smokingAllowed, boolean underMaintenance) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.status = status;
        this.cleanStatus = cleanStatus;
        this.smokingAllowed = smokingAllowed;
        this.underMaintenance = underMaintenance;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public CleanStatus getCleanStatus() {
        return cleanStatus;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomNumber=" + number +
                ", type=" + type +
                ", status=" + status +
                ", cleanStatus=" + cleanStatus +
                ", smokingAllowed=" + smokingAllowed +
                ", underMaintenance=" + underMaintenance +
                '}';
    }


    public static RoomBuilder builder() {
        return new RoomBuilder();
    }
}