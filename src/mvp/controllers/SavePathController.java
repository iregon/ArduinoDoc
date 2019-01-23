package mvp.controllers;

import java.io.File;

import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SavePathController {
	
	private DirectoryChooser directoryChooser;
	private TextField outputPathTextField;
	
	public SavePathController(TextField outputPathTextField) {
		this.outputPathTextField = outputPathTextField;
		directoryChooser = new DirectoryChooser();
	}

	public void selectPath() {
		File path = directoryChooser.showDialog(new Stage());
		if(path != null) outputPathTextField.setText(path.getAbsolutePath());
	}

}
