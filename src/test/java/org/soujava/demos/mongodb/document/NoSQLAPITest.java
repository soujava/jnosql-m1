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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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

        Room insert = template.insert(room);

        template.select(Room.class).where()

    }

}