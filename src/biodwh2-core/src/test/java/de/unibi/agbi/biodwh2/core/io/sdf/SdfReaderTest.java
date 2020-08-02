package de.unibi.agbi.biodwh2.core.io.sdf;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SdfReaderTest {
    private static final String PUBCHEM_IUPAC_CAS_NAME = "PUBCHEM_IUPAC_CAS_NAME";

    @Test
    void testReadingFromFileStream() throws IOException {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final String resourceFilePath = classLoader.getResource("test_pubchem.sdf").getFile();
        final File file = new File(resourceFilePath);
        assertTrue(file.exists());
        try (InputStream stream = Files.newInputStream(Paths.get(resourceFilePath))) {
            final SdfReader reader = new SdfReader(stream, "UTF-8");
            final Iterator<SdfEntry> entryIterator = reader.iterator();
            assertTrue(entryIterator.hasNext());
            SdfEntry entry = entryIterator.next();
            assertEquals("1", entry.getTitle());
            assertTrue(entry.properties.containsKey(PUBCHEM_IUPAC_CAS_NAME));
            assertEquals("3-acetyloxy-4-(trimethylammonio)butanoate", entry.properties.get(PUBCHEM_IUPAC_CAS_NAME));
            assertTrue(entryIterator.hasNext());
            entry = entryIterator.next();
            assertEquals("2", entry.getTitle());
            assertTrue(entry.properties.containsKey(PUBCHEM_IUPAC_CAS_NAME));
            assertEquals("(2-acetyloxy-3-carboxypropyl)-trimethylammonium",
                         entry.properties.get(PUBCHEM_IUPAC_CAS_NAME));
            assertFalse(entryIterator.hasNext());
        }
    }
}