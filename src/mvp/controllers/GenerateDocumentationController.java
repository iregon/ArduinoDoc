package mvp.controllers;

//import java.net.URL;

import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import services.documentation.Documentation;

public class GenerateDocumentationController {
	
	private TextArea arduinoTextArea;
	private WebView previewWebView;

	public GenerateDocumentationController(TextArea arduinoTextArea, WebView previewWebView) {
		this.arduinoTextArea = arduinoTextArea;
		this.previewWebView = previewWebView;
	}

	public void generate() {
		String html = getDocumentation();
		setContentWebEngine(html);
	}

	private void setContentWebEngine(String html) {
		WebEngine engine = previewWebView.getEngine();
		engine.loadContent(html);
	}
	
	private String getDocumentation() {
		Documentation doc = new Documentation();
		doc.generate(arduinoTextArea.getText());
		return doc.getHTMLVersion();
	}

}
