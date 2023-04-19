package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SpaceInvadersApplication extends Application {
    public static final String TITLE = "Space Invaders!";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpaceInvadersApplication.class.getResource("menu-view.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle(TITLE);
        Scene scene = new Scene(root, 429, 342);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}