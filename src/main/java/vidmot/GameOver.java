package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vinnsla.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GameOver {
    @FXML
    private Label fxLokaStig;
    @FXML
    private AnchorPane fxAnchorPane;
    @FXML
    private ListView<String> fxListView = new ListView<>();
    @FXML
    private TextField fxUsername;
    @FXML
    private Button fxAddScore;
    @FXML
    private Button fxPlayAgain;
    @FXML
    private Button fxExit;
    private final static String TITLE = "Space Invaders!";
    private final Data data = Data.getInstance();
    private final File saveFile = new File("Scoreboard.txt");

    public GameOver(){
        iLagi();
        fxLokaStig.setText("Þú náði: " + data.getScore() + " stig");

        if (saveFile.exists()){
            try {
                List<String> fileLoaded = Files.readAllLines(saveFile.toPath());
                fxListView.getItems().addAll(fileLoaded);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void iLagi() {
        Node fxIlagi = fxAddScore;
        fxIlagi.disableProperty().bind(fxUsername.textProperty().isEmpty());
    }

    @FXML
    private void addScoreHandler() {
        fxListView.getItems().add(data.getScore() + " --- " + fxUsername.getText());
        fxUsername.setText(null);
        fxUsername.setDisable(true);
        try {
            Files.write(saveFile.toPath(), fxListView.getItems().subList(0,fxListView.getItems().size()));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Takki til að spila aftur
     * @throws IOException e
     */
    @FXML
    private void playAgainHandler() throws IOException {
        Stage stage = (Stage) fxAnchorPane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SpaceInvadersApplication.class.getResource("spaceinvaders-view.fxml"));
        Parent root = fxmlLoader.load();
        SpaceInvadersController sc = fxmlLoader.getController();
        stage.setTitle(TITLE);
        Scene scene = new Scene(root, 400, 500);
        sc.orvatakkar(scene); // kalla á mapping fall fyrir takka
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Takki sem fer út ur leik
     */
    @FXML
    private void exitGameHandler() {
        Stage stage = (Stage) fxExit.getScene().getWindow();
        stage.close();
    }
}
