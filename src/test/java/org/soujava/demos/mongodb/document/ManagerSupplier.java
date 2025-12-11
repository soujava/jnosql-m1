package org.soujava.demos.mongodb.document;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Typed;
import jakarta.interceptor.Interceptor;
import org.eclipse.jnosql.communication.semistructured.DatabaseManager;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentManager;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.DatabaseType;

import java.util.function.Supplier;

@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class ManagerSupplier implements Supplier<DatabaseManager> {

    @Produces
    @Database(DatabaseType.DOCUMENT)
    @Default
    @Typed({DatabaseManager.class, MongoDBDocumentManager.class})
    public MongoDBDocumentManager get() {
        return DatabaseContainer.INSTANCE.get("hotel");
    }
}