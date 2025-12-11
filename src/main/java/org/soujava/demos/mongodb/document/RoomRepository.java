package org.soujava.demos.mongodb.document;

import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.List;

@Repository
public interface RoomRepository {

    @Query("WHERE type = 'VIP_SUITE' AND status = 'AVAILABLE' AND underMaintenance = false")
    List<Room> findVipRoomsReadyForGuests();

    @Query(" WHERE type <> 'VIP_SUITE' AND status = 'AVAILABLE' AND cleanStatus = 'CLEAN'")
    List<Room> findAvailableStandardRooms();

    @Query("WHERE cleanStatus <> 'CLEAN' AND status <> 'OUT_OF_SERVICE'")
    List<Room> findRoomsNeedingCleaning();

    @Query("WHERE smokingAllowed = true AND status = 'AVAILABLE'")
    List<Room> findAvailableSmokingRooms();

    @Save
    void save(List<Room> rooms);

    @Save
    Room newRoom(Room room);
    void deleteBy();

    @Query("WHERE type = :type")
    List<Room> findByType(@Param("type") RoomType type);
}
