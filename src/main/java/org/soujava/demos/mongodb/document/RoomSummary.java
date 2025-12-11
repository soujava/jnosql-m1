package org.soujava.demos.mongodb.document;

import jakarta.nosql.Projection;

@Projection
public record RoomSummary(int number, RoomType type, RoomStatus status) {
}
