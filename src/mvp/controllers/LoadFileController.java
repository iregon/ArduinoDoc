package mvp.controllers;

import java.io.File;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.dataStorage.DiskOperation;

public class LoadFileController {

    private TextField inputFileTextField;
    private TextArea arduinoCodeTextArea;
    private FileChooser fileChooser = new FileChooser();
    private File file;

    public LoadFileController(TextField inputFileTextField, TextArea arduinoCodeTextArea) {
        this.inputFileTextField = inputFileTextField;
        this.arduinoCodeTextArea = arduinoCodeTextArea;
        configureDirectoryChooser();
    }

    private void configureDirectoryChooser() {
        this.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Supported file extensions", "*.ino"));
    }

    public void load() {
        this.file = fileChooser.showOpenDialog(new Stage());
        if(file != null) {
            this.inputFileTextField.setText(file.getPath());
            loadFile();
        }
    }

    private void loadFile() {
        this.arduinoCodeTextArea.setText(new DiskOperation().readData(file.getAbsolutePath()));
    }
}
