package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.AnimeSelectorController;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.ReviewView;
import practicumopdracht.views.View;

public class MainApplication extends Application {

    private final String TITLE = Main.studentNaam;
    private final int WIDTH = 640;
    private final int HEIGHT = 480;

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", TITLE));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        View reviewView = new ReviewView();
        AnimeSelectorController masterController = new AnimeSelectorController();
        Scene reviewScene = new Scene(masterController.getView().getRoot());
        stage.setScene(reviewScene);
        stage.show();
    }
}
