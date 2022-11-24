package io;

import model.Root;
import util.JsonUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonWriter {

    private JsonWriter() {
    }

    private static final Logger LOGGER = Logger.getLogger(JsonWriter.class.getName());

    public static void createJsonReq(Root root) {
        LOGGER.log(Level.INFO, "JSON writing started.");

        createDirectory();
        write(root, "jsonReqs/students", root.getStudents());
        write(root, "jsonReqs/universities", root.getUniversities());
        write(root, "jsonReqs/statistics", root.getStatistics());

        LOGGER.log(Level.INFO, "JSON writing successfully.");
    }

    private static void write(Root root, String fileName, List<?> lists) {
        String unitJson = JsonUtil.writeToJson(lists);
        try (FileOutputStream stream = new FileOutputStream(fileName + root.getProcessedDate().getTime() + ".json")) {
            stream.write(unitJson.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed write json.", e);
        }
    }

    private static void createDirectory() {
        try {
            Files.createDirectories(Paths.get("jsonReqs"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed create directory.", e);
        }
    }
}
