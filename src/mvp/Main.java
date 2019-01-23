package mvp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final String WINDOW_TITLE = "ArduinoDoc";
    private final int MIN_WINDOW_WIDTH = 700;
    private final int MIN_WINDOWS_HEIGHT = 500;

    private Parent rootNode;

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        if (Platform.isFxApplicationThread()) loadFxml();
        else Platform.runLater(() -> loadFxml());
    }

    public void loadFxml() {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/Main.fxml"));
            rootNode = fxmlLoader.load();
        } catch (Exception e) {
            //TODO eccezione caricamento fxml
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(rootNode));
        stage.setTitle(WINDOW_TITLE);
        stage.setMinHeight(MIN_WINDOWS_HEIGHT);
        stage.setMinWidth(MIN_WINDOW_WIDTH);
        stage.show();
    }
}
