package de.unibi.agbi.biodwh2.core.io.mvstore;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MVStoreIndexTest {
    @Test
    void putTest() throws IOException {
        final Path tempFilePath = Files.createTempFile("putTest", ".db");
        final MVStoreDB db = new MVStoreDB(tempFilePath.toString());
        final MVStoreIndex index = new MVStoreIndex(db, "index", "test", false);
        final MVStoreId id = new MVStoreId();
        index.put("value", id);
        final Set<Long> foundIds = index.find("value");
        assertEquals(1, foundIds.size());
        assertEquals(id.getIdValue(), foundIds.stream().findFirst().get());
        db.close();
    }

    @Test
    void putArrayTest() throws IOException {
        final Path tempFilePath = Files.createTempFile("putArrayTest", ".db");
        final MVStoreDB db = new MVStoreDB(tempFilePath.toString());
        final MVStoreIndex index = new MVStoreIndex(db, "index", "test", true);
        final MVStoreId id = new MVStoreId();
        final String[] array = new String[]{"value1", "value2", "value3"};
        index.put(array, id);
        for (final String value : array) {
            final Set<Long> foundIds = index.find(value);
            assertEquals(1, foundIds.size());
            assertEquals(id.getIdValue(), foundIds.stream().findFirst().get());
        }
        db.close();
    }
    @Test
    void removeTest() throws IOException {
        final Path tempFilePath = Files.createTempFile("putTest", ".db");
        final MVStoreDB db = new MVStoreDB(tempFilePath.toString());
        final MVStoreIndex index = new MVStoreIndex(db, "index", "test", false);
        final MVStoreId id1 = new MVStoreId();
        final MVStoreId id2 = new MVStoreId();
        index.put("value", id1);
        index.put("value", id2);
        Set<Long> foundIds = index.find("value");
        assertEquals(2, foundIds.size());
        index.remove("value", id1);
        foundIds = index.find("value");
        assertEquals(1, foundIds.size());
        assertEquals(id2.getIdValue(), foundIds.stream().findFirst().get());
        db.close();
    }

    @Test
    void pagesTest() throws IOException {
        final Path tempFilePath = Files.createTempFile("pagesTest", ".db");
        final MVStoreDB db = new MVStoreDB(tempFilePath.toString());
        final MVStoreIndex index1 = new MVStoreIndex(db, "index1", "i", false, 10);
        final MVStoreIndex index2 = new MVStoreIndex(db, "index2", "label", false, 10);
        for (int i = 0; i < 55; i++) {
            final MVStoreId id = new MVStoreId();
            index1.put(i, id);
            index2.put("TEST", id);
            assertEquals(i + 1, index2.find("TEST").size());
            final Set<Long> foundIds = index1.find(i);
            assertEquals(1, foundIds.size());
            assertEquals(id.getIdValue(), foundIds.stream().findFirst().get());
        }
        db.close();
    }
}