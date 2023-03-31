package vidmot;

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
    private Button fxExit;
    private final static String TITLE = "Space Invaders!";
    private final Data data = Data.getInstance();
    private final File saveFile = new File("Scoreboard.txt");

    public void initialize(){
        iLagi();
        fxLokaStig.setText(data.getScore() + " stig og ");



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

    /**
     * Regla sem bindar takka við textfield
     * Þarf að setja username inn til þess að geta bætt sig á scoreboard
     */
    private void iLagi() {
        Node fxIlagi = fxAddScore;
        fxIlagi.disableProperty().bind(fxUsername.textProperty().isEmpty());
    }

    /**
     * Bæta við stig eftir notandi settur inn usernamið inn
     * Vista þvi í txt skrá
     * Óvirkja svo textfield og takka
     */
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
