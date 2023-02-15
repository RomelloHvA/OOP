package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.views.AnimeSelectorView;
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
        View AnimeSelectorView = new AnimeSelectorView();
        Scene homeScene = new Scene(AnimeSelectorView.getRoot());
        stage.setScene(homeScene);
        stage.show();
    }
}
