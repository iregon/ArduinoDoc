package mvp.controllers;

import java.util.Optional;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import services.dataStorage.DiskOperation;

public class SaveFileController {

	private TextField path;
	private Optional<String> filename;
	private TextArea code;

	public SaveFileController(TextField path, TextArea code) {
		this.path = path;
		this.code = code;
	}

	public void save() {
		TextInputDialog dialog;
		dialog = new TextInputDialog("");
		dialog.setHeaderText("Insert file name");

		filename = dialog.showAndWait();

		filename.ifPresent(s -> new DiskOperation().saveData(code.getText(), path.getText() + "\\" + s + ".txt"));
	}

}
