package mvp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainController {

    @FXML private TextField inputFileTextField;
    @FXML private TextField outputPathTextField;
    @FXML private TextArea arduinoCodeTextArea;
    @FXML private WebView previewWebView;

    public MainController() {}

    @FXML
    private void loadFile(MouseEvent event) {
        LoadFileController loadFileController;
        loadFileController = new LoadFileController(inputFileTextField, arduinoCodeTextArea);
        loadFileController.load();
    }

    @FXML
    private void loadOutputPath(MouseEvent event) {
        SavePathController savepathController;
        savepathController = new SavePathController(outputPathTextField);
        savepathController.selectPath();
    }

    @FXML
    private void generateDocumentation(MouseEvent event) {
        if(arduinoCodeTextArea.getText().equals("")) {
            Alert alert;
            alert = new Alert(AlertType.ERROR, "Arduino code not loaded");
            alert.showAndWait();
        }
        else {
            GenerateDocumentationController generateDocumentationController;
            generateDocumentationController = new GenerateDocumentationController(arduinoCodeTextArea, previewWebView);
            generateDocumentationController.generate();
        }
    }

    @FXML
    private void exportDocumentation(MouseEvent event) {}

    // TODO: esportazione documentazione
//	@FXML
//	private void exportDocumentation(MouseEvent event) {
//		if(outputPathTextField.getText().equals("")) {
//			Alert alert = new Alert(AlertType.ERROR, "Output path not selected");
//			alert.showAndWait();
//		}
//		else {
//			if(previewTextArea.getText().equals("")) {
//				generateDocumentation(null);
//			}
//			if(!previewTextArea.getText().equals("")) {
//				SaveFileController saveFileController = new SaveFileController(outputPathTextField, previewTextArea);
//				saveFileController.save();
//			}
//		}
//	}
}
