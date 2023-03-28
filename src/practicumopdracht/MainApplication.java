package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.AnimeSelectorController;
import practicumopdracht.controllers.Controller;
import practicumopdracht.data.*;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;

public class MainApplication extends Application {

    private final String TITLE = Main.studentNaam;
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private static AnimeDAO animeDAO;
    private static ReviewDAO reviewDAO;
    private static Stage stage;

    private static boolean isLoaded;

    public static void setIsLoaded(boolean isLoaded) {
        MainApplication.isLoaded = isLoaded;
    }

    public static boolean isIsLoaded() {
        return isLoaded;
    }

    @Override
    public void start(Stage stage) {
        isLoaded = false;
        MainApplication.stage = stage;
//        animeDAO = new TextAnimeDAO();
        animeDAO = new BinaryAnimeDAO();
//        reviewDAO = new TextReviewDAO();
        reviewDAO = new ObjectReviewDAO();



        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);
            return;
        }
        AnimeSelectorController animeSelectorController = new AnimeSelectorController();
        switchController(animeSelectorController);
        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", TITLE));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.show();

    }

    public static void switchController(Controller controller){
        Scene currentScene = new Scene(controller.getView().getRoot());
        stage.setScene(currentScene);

    }

    public static AnimeDAO getAnimeDAO() {
        return animeDAO;
    }

    public static ReviewDAO getReviewDAO() {
        return reviewDAO;
    }
}
