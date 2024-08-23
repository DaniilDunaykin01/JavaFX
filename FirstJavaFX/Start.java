import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URL;

public class Start extends Application {

    public static ObservableList<Person> personsList = FXCollections.observableArrayList();

    @Override
    public void start(@NotNull Stage stage) throws Exception {
        String pathToFXML = "src/main/resources/SceneBuilder.fxml";
        URL url = new File(pathToFXML).toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        AnchorPane rootElement = loader.load();
        Scene scene = new Scene(rootElement);
        stage.setScene(scene);
        stage.setTitle("Приложение JavaFX #1");
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Start.class, args);
    }
}