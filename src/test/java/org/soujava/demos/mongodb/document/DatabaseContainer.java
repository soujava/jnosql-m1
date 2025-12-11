package org.soujava.demos.mongodb.document;

import org.eclipse.jnosql.communication.Settings;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentConfiguration;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentConfigurations;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentManager;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentManagerFactory;
import org.eclipse.jnosql.mapping.core.config.MappingConfigurations;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.HashMap;
import java.util.Map;
public enum DatabaseContainer {

    INSTANCE;

    private final GenericContainer<?> mongodb =
            new GenericContainer<>("mongo:latest")
                    .withExposedPorts(27017)
                    .waitingFor(Wait.defaultWaitStrategy());

    {
        mongodb.start();
    }
    public MongoDBDocumentManager get(String database) {
        Settings settings = getSettings(database);
        MongoDBDocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        MongoDBDocumentManagerFactory factory = configuration.apply(settings);
        return factory.apply(database);
    }


    private Settings getSettings(String database) {
        Map<String,Object> settings = new HashMap<>();
        settings.put(MongoDBDocumentConfigurations.HOST.get()+".1", host());
        settings.put(MappingConfigurations.DOCUMENT_DATABASE.get(), database);
        return Settings.of(settings);
    }

    public String host() {
        return mongodb.getHost() + ":" + mongodb.getFirstMappedPort();
    }
}
