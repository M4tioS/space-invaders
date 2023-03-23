package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Menu {
    @FXML
    private Button fxLoka;
    @FXML
    public VBox fxVbox;
    private final static String TITLE = "Space Invaders!";

    @FXML
    private void playHandler () throws IOException {
        Stage stage = (Stage) fxVbox.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SpaceInvadersApplication.class.getResource("spaceinvaders-view.fxml"));
        Parent root = fxmlLoader.load();
        SpaceInvadersController sc = fxmlLoader.getController();
        stage.setTitle(TITLE);
        Scene scene = new Scene(root, 400, 500);
        sc.orvatakkar(scene); // kalla á mapping fall fyrir takka
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void exitHandler() {
        Stage stage = (Stage) fxLoka.getScene().getWindow();
        stage.close();
    }
}
