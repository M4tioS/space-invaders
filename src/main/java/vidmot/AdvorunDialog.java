package vidmot;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AdvorunDialog extends Alert {
    private static final String I_LAGI = "Play Again!";
    public static final ButtonType BTYPE = new ButtonType(I_LAGI,
            ButtonBar.ButtonData.OK_DONE);
    private static final String HAETTA_VID = "Scoreboard";
    public static final ButtonType HTYPE = new ButtonType(HAETTA_VID,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);

    /**
     * Smiður sem setur titil, haus og spurningu í Alert dialog
     *
     * @param t   title
     * @param h   header
     * @param s   spyrning sem er á borð
     */
    public AdvorunDialog(String t, String h, String s) {
        super(AlertType.NONE, s, BTYPE, HTYPE);  // kallar á smið yfirklasans
        setTitle(t);
        setHeaderText(h);
    }

}


