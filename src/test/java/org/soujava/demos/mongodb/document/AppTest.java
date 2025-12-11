package org.soujava.demos.mongodb.document;

import jakarta.inject.Inject;
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
import org.junit.jupiter.api.Test;


@EnableAutoWeld
@AddPackages(value = {Database.class, EntityConverter.class, DocumentTemplate.class, MongoDBTemplate.class})
@AddPackages(Room.class)
@AddPackages(ManagerSupplier.class)
@AddPackages(MongoDBTemplate.class)
@AddPackages(Reflections.class)
@AddPackages(Converters.class)
@AddExtensions({ReflectionEntityMetadataExtension.class, DocumentExtension.class})
class AppTest {

    @Inject
    private DocumentTemplate template;

    @Test
    void shouldTest() {
        Room room = new RoomBuilder()
                .id("room-1")
                .roomNumber(101)
                .type(RoomType.SUITE)
                .status(RoomStatus.AVAILABLE)
                .cleanStatus(CleanStatus.CLEAN)
                .smokingAllowed(false)
                .underMaintenance(false)
                .build();

        Room insert = template.insert(room);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(room.getId()).isEqualTo(insert.getId());
            softly.assertThat(room.getNumber()).isEqualTo(insert.getNumber());
            softly.assertThat(room.getType()).isEqualTo(insert.getType());
            softly.assertThat(room.getStatus()).isEqualTo(insert.getStatus());
            softly.assertThat(room.getCleanStatus()).isEqualTo(insert.getCleanStatus());
            softly.assertThat(room.isSmokingAllowed()).isEqualTo(insert.isSmokingAllowed());
            softly.assertThat(room.isUnderMaintenance()).isEqualTo(insert.isUnderMaintenance());
            softly.assertThat(insert.getId()).isNotNull();
        });
    }

}