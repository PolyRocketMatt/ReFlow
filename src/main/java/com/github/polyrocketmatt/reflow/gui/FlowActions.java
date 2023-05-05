package com.github.polyrocketmatt.reflow.gui;

import com.github.polyrocketmatt.reflow.handler.ClassHandler;
import com.github.polyrocketmatt.reflow.utils.decompilation.JExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static com.github.polyrocketmatt.reflow.ReFlow.TEMP_DIR;

public class FlowActions {

    private final FlowInterface flowInterface;
    private final Logger logger = LoggerFactory.getLogger("FlowActions");

    private File currentFile;
    private ClassHandler currentClassHandler;

    public FlowActions(FlowInterface flowInterface) {
        this.flowInterface = flowInterface;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public ClassHandler getCurrentClassHandler() {
        return currentClassHandler;
    }

    public void openFileAction(ActionEvent event) {
        //  Create file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select JAR File");

        //  Show dialog and process result
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            logger.info("Selected file: " + currentFile.getName());
        }
    }

    public void dragFileAction(File file) {
        currentFile = file;
    }

    public void exitAction(ActionEvent event) {
        System.exit(0);
    }

    public void initializeClassTree() {
        currentClassHandler = null;

        try {
            //  Create class handler
            currentClassHandler = new ClassHandler(currentFile);

            //  Extract classes from JAR and store in temp directory
            JExtractor.extractClasses(currentFile, TEMP_DIR, currentClassHandler.getClasses().keySet());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
