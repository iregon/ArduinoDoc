package mvp.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.scene.control.Alert.AlertType;
import services.translation.Translation;

public class MainController {

    @FXML private TextField inputFileTextField;
    @FXML private TextField outputPathTextField;
    @FXML private TextArea arduinoCodeTextArea;
    @FXML private WebView previewWebView;

    @FXML private Button loadFileButton;
    @FXML private Button outputPathButton;
    @FXML private Label arduinoCodeLabel;
    @FXML private Label documentationPreviewLabel;
    @FXML private Button generateDocumentationButton;
    @FXML private Button exportDocumentationButton;
    @FXML private Label inputFileLabel;
    @FXML private Label outputPathLabel;

    public MainController() {
        Platform.runLater(() -> translateGUIElements());
    }

    private void translateGUIElements() {
        loadFileButton.setText(Translation.getInstance().getString("inputFileButton"));
        outputPathButton.setText(Translation.getInstance().getString("outputPathButton"));
        arduinoCodeLabel.setText(Translation.getInstance().getString("arduinoCodeLabel"));
        documentationPreviewLabel.setText(Translation.getInstance().getString("documentationPreviewLabel"));
        generateDocumentationButton.setText(Translation.getInstance().getString("generateDocumentationButton"));
        exportDocumentationButton.setText(Translation.getInstance().getString("exportDocumentationButton"));
        inputFileLabel.setText(Translation.getInstance().getString("inputFileLabel"));
        outputPathLabel.setText(Translation.getInstance().getString("outputPathLabel"));
    }

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
