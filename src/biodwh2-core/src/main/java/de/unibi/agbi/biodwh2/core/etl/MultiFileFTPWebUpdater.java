package de.unibi.agbi.biodwh2.core.etl;

import de.unibi.agbi.biodwh2.core.DataSource;
import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.exceptions.UpdaterConnectionException;
import de.unibi.agbi.biodwh2.core.exceptions.UpdaterException;
import de.unibi.agbi.biodwh2.core.model.Version;
import de.unibi.agbi.biodwh2.core.net.HTTPClient;
import de.unibi.agbi.biodwh2.core.net.HTTPFTPClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class MultiFileFTPWebUpdater<D extends DataSource> extends Updater<D> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiFileFTPWebUpdater.class);
    private static final Map<String, Integer> THREE_LETTER_MONTHS = new HashMap<>();

    static {
        THREE_LETTER_MONTHS.put("jan", 1);
        THREE_LETTER_MONTHS.put("feb", 2);
        THREE_LETTER_MONTHS.put("mar", 3);
        THREE_LETTER_MONTHS.put("apr", 4);
        THREE_LETTER_MONTHS.put("may", 5);
        THREE_LETTER_MONTHS.put("jun", 6);
        THREE_LETTER_MONTHS.put("jul", 7);
        THREE_LETTER_MONTHS.put("aug", 8);
        THREE_LETTER_MONTHS.put("sep", 9);
        THREE_LETTER_MONTHS.put("oct", 10);
        THREE_LETTER_MONTHS.put("nov", 11);
        THREE_LETTER_MONTHS.put("dec", 12);
    }

    protected final HTTPFTPClient client;

    public MultiFileFTPWebUpdater(final D dataSource) {
        super(dataSource);
        client = new HTTPFTPClient(getFTPIndexUrl());
    }

    @Override
    public Version getNewestVersion(final Workspace workspace) throws UpdaterException {
        try {
            Version latestVersion = null;
            for (final String filePath : getFilePaths(workspace)) {
                final Version fileVersion = getNewestVersionFromFilePath(Paths.get(filePath));
                if (latestVersion == null || (fileVersion != null && fileVersion.compareTo(latestVersion) > 0))
                    latestVersion = fileVersion;
            }
            return latestVersion;
        } catch (IOException e) {
            throw new UpdaterConnectionException(e);
        }
    }

    private Version getNewestVersionFromFilePath(final Path filePath) throws IOException {
        final Path directoryPath = filePath.getParent();
        final HTTPFTPClient.Entry[] entries = directoryPath == null ? client.listDirectory() : client.listDirectory(
                directoryPath.toString().replace("\\", "/"));
        for (final HTTPFTPClient.Entry entry : entries)
            if (entry.name.equals(filePath.getFileName().toString()))
                return getVersionForFileName(entry);
        return null;
    }

    private static Version getVersionForFileName(final HTTPFTPClient.Entry entry) {
        final String date = StringUtils.split(entry.modificationDate, " ", 2)[0];
        if (date.contains("-")) {
            final String[] dateParts = StringUtils.split(date, "-", 3);
            if (StringUtils.isNumeric(dateParts[1])) {
                return new Version(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
                                   Integer.parseInt(dateParts[2]));
            }
            final Integer monthNumber = THREE_LETTER_MONTHS.get(dateParts[1].toLowerCase(Locale.US));
            if (monthNumber != null) {
                return new Version(Integer.parseInt(dateParts[2]), monthNumber, Integer.parseInt(dateParts[0]));
            }
        }
        return null;
    }

    @Override
    protected boolean tryUpdateFiles(final Workspace workspace) throws UpdaterException {
        for (final String fileName : getFilePaths(workspace)) {
            if (LOGGER.isInfoEnabled())
                LOGGER.info("Downloading '" + fileName + "'...");
            int tries = 1;
            while (tries < 6) {
                final String localFileName = Paths.get(fileName).getFileName().toString();
                final String resolvedFilePath = dataSource.resolveSourceFilePath(workspace, localFileName);
                try {
                    HTTPClient.downloadFileAsBrowser(getFTPIndexUrl() + fileName, resolvedFilePath);
                    break;
                } catch (IOException e) {
                    tries++;
                    if (tries == 6) {
                        throw new UpdaterConnectionException(e);
                    }
                    if (LOGGER.isInfoEnabled())
                        LOGGER.info("\tDownloading '" + fileName + "' failed (try " + (tries - 1) +
                                    "/5), retrying in 5 seconds...");
                    try {
                        // Small wait to not overpower the server
                        Thread.sleep(5000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
        return true;
    }

    protected abstract String getFTPIndexUrl();

    protected abstract String[] getFilePaths(final Workspace workspace) throws UpdaterException;
}
