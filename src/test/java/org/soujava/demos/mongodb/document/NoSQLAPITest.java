package org.soujava.demos.mongodb.document;

import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.eclipse.jnosql.databases.mongodb.mapping.MongoDBTemplate;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.core.Converters;
import org.eclipse.jnosql.mapping.document.DocumentTemplate;
import org.eclipse.jnosql.mapping.document.spi.DocumentExtension;
import org.eclipse.jnosql.mapping.reflection.Reflections;
import org.eclipse.jnosql.mapping.reflection.spi.ReflectionEntityMetadataExtension;
import org.eclipse.jnosql.mapping.semistructured.EntityConverter;
import org.jboss.weld.junit5.auto.AddExtensions;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


@EnableAutoWeld
@AddPackages(value = {Database.class, EntityConverter.class, DocumentTemplate.class, MongoDBTemplate.class})
@AddPackages(Room.class)
@AddPackages(ManagerSupplier.class)
@AddPackages(MongoDBTemplate.class)
@AddPackages(Reflections.class)
@AddPackages(Converters.class)
@AddExtensions({ReflectionEntityMetadataExtension.class, DocumentExtension.class})
class NoSQLAPITest {

    @Inject
    private DocumentTemplate template;

    public static Stream<Arguments> roomsProvider() {
        return Stream.of(Arguments.of(new RoomBuilder()
                .id("room-1")
                .roomNumber(101)
                .type(RoomType.SUITE)
                .status(RoomStatus.AVAILABLE)
                .cleanStatus(CleanStatus.CLEAN)
                .smokingAllowed(false)
                .underMaintenance(false)
                .build()));
    }

    @Test
    @DisplayName("Should be able to execute fluent API to create and insert a Room document")
    void shouldExecuteFluentAPI() {
        Room room = new RoomBuilder()
                .id("room-1")
                .roomNumber(101)
                .type(RoomType.SUITE)
                .status(RoomStatus.AVAILABLE)
                .cleanStatus(CleanStatus.CLEAN)
                .smokingAllowed(false)
                .underMaintenance(false)
                .build();

        template.insert(room);

        Stream<Room> rooms = template.select(Room.class).where(_Room.TYPE).eq(RoomType.SUITE).stream();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(rooms).isNotNull();
            softly.assertThat(rooms.count()).isEqualTo(1);
        });
    }

    @ParameterizedTest(name = "Should be able to execute Query API to create and insert a Room document")
    @MethodSource("roomsProvider")
    void shouldExecuteQueryAPI(Room room) {
        template.insert(room);

        Stream<Room> rooms = template.select(Room.class)
                .where(_Room.TYPE).eq(RoomType.SUITE)
                .and(_Room.STATUS).eq(RoomStatus.AVAILABLE)
                .stream();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(rooms).isNotNull();
            softly.assertThat(rooms.count()).isEqualTo(1);
        });
    }

    @ParameterizedTest
    @MethodSource("roomsProvider")
    void shouldCount(Room room) {
        template.insert(room);
        long count = template.select(Room.class)
                .where(_Room.TYPE).eq(RoomType.SUITE)
                .and(_Room.STATUS).eq(RoomStatus.AVAILABLE)
                .count();

        Assertions.assertThat(count).isEqualTo(1);
    }

}