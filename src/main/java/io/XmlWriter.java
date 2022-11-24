package io;

import model.Root;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlWriter {

    private static final Logger LOGGER = Logger.getLogger(XmlWriter.class.getName());

    private XmlWriter() {
    }

    public static void createXmlReq(Root root) {
        LOGGER.log(Level.INFO, "XML marshalling started.");
        try {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try {
                Files.createDirectories(Paths.get("xmlReqs"));
                LOGGER.log(Level.INFO, "Directory created successfully.");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Directory already created.", e);
            }
            File request = new File("xmlReqs/req" + new Date().getTime() + ".xml");
            marshaller.marshal(root, request);

        } catch (JAXBException e) {
            LOGGER.log(Level.SEVERE, "Failed marshalling XML.", e);
        }

        LOGGER.log(Level.INFO, "XML marshalling finished successfully.");
    }

}
