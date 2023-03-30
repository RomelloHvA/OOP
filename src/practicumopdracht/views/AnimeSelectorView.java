package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import practicumopdracht.models.Anime;

import java.io.File;

public class AnimeSelectorView extends View{


    private Button deleteButton;
    private Button reviewsButton;
    private TextField animeNameTextField;
    private DatePicker releaseDatePicker;
    private CheckBox watchedCheckBox;
    private CheckBox downloadedCheckBox;
    private TextField episodeCountTextField;
    private TextArea synopsisTextArea;
    private Button newButton;
    private Button saveButton;
    private ListView<Anime> animeList;
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu sortMenu;

    private MenuItem save;
    private MenuItem load;
    private MenuItem exit;
    private MenuItem ascendingMenuItem;
    private MenuItem descendingMenuItem;


    public MenuItem getSave() {
        return save;
    }

    public MenuItem getLoad() {
        return load;
    }

    public MenuItem getExit() {
        return exit;
    }

    public MenuItem getAscendingMenuItem() {
        return ascendingMenuItem;
    }

    public MenuItem getDescendingMenuItem() {
        return descendingMenuItem;
    }

    @Override
    protected Parent initializeView() {

        VBox masterBox = new VBox();
        masterBox.setSpacing(DEFAULT_SPACING);


        fileMenu = new Menu("File");
        save = new MenuItem("Save");
        save.setGraphic(new ImageView(new Image(new File("src/practicumopdracht/views/images/save_icon.png").toURI().toString())));

        load = new MenuItem("Load");
        ImageView directoryIcon = new ImageView(new Image(new File("src/practicumopdracht/views/images/directory_icon.png").toURI().toString()));
        directoryIcon.setFitWidth(DEFAULT_WIDTH_MENU_ICON);
        directoryIcon.setFitHeight(DEFAULT_HEIGHT_MENU_ICON);
        load.setGraphic(directoryIcon);



        exit = new MenuItem("Exit");
        ImageView closeIcon = new ImageView(new Image(new File("src/practicumopdracht/views/images/close_icon.png").toURI().toString()));
        closeIcon.setFitHeight(DEFAULT_HEIGHT_MENU_ICON);
        closeIcon.setFitWidth(DEFAULT_WIDTH_MENU_ICON);
        exit.setGraphic(closeIcon);
        fileMenu.getItems().addAll(save,load,exit);

        sortMenu = new Menu("Sort");

        ascendingMenuItem = new MenuItem("Name (A-Z)");
        descendingMenuItem = new MenuItem("Name (Z-A)");
        sortMenu.getItems().addAll(ascendingMenuItem,descendingMenuItem);

        menuBar = new MenuBar(fileMenu, sortMenu);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        masterBox.getChildren().add(borderPane);




        HBox animeListBox = new HBox();
        animeListBox.setSpacing(DEFAULT_SPACING);
        animeListBox.setPadding(DEFAULT_TOP_LEFT_PADDING);

//        List view
        animeList = new ListView<>();

        animeList.setMinWidth(DEFAULT_WIDTH_LIST);
        animeList.setMaxWidth(DEFAULT_WIDTH_LIST);
        animeList.setMaxHeight(DEFAULT_HEIGT_LIST);
        animeList.setMinHeight(DEFAULT_HEIGT_LIST);

        VBox reviewDeleteButtonBox = new VBox();
        reviewDeleteButtonBox.setSpacing(DEFAULT_SPACING);
        reviewDeleteButtonBox.setPadding(DEFAULT_LEFT_PADDING);
        deleteButton = new Button("Delete anime");
        reviewsButton = new Button(" Go to Reviews");
        reviewDeleteButtonBox.getChildren().addAll(reviewsButton, deleteButton);

        animeListBox.getChildren().addAll(animeList,reviewDeleteButtonBox);
        masterBox.getChildren().add(animeListBox);


        HBox animeNameAndDateBox = new HBox();
        animeNameAndDateBox.setSpacing(DEFAULT_SPACING);
        animeNameAndDateBox.setPadding(DEFAULT_LEFT_PADDING);

        Label animeNameLabel = new Label("Anime Name:");
        animeNameTextField = new TextField();
        Label releaseDateLabel = new Label("Release Date:");
        releaseDatePicker = new DatePicker();

        animeNameAndDateBox.getChildren().addAll(animeNameLabel, animeNameTextField, releaseDateLabel, releaseDatePicker);
        masterBox.getChildren().add(animeNameAndDateBox);

// This is where the synopsis, watched,downloaded and episode count should be in.
        GridPane animeDetailBox = new GridPane();
        animeDetailBox.setPadding(DEFAULT_LEFT_PADDING);

        HBox watchedAndDownloadedBox = new HBox();
        watchedAndDownloadedBox.setPadding(DEFAULT_LEFT_PADDING);
        Label watchedLabel = new Label("Watched:");
        watchedCheckBox = new CheckBox();
        watchedCheckBox.setPadding(DEFAULT_LEFT_PADDING);
        Label downloadedLabel = new Label("Downloaded:");
        downloadedLabel.setPadding(DEFAULT_LEFT_PADDING);
        downloadedCheckBox = new CheckBox();
        downloadedCheckBox.setPadding(DEFAULT_LEFT_PADDING);

        watchedAndDownloadedBox.getChildren().addAll(watchedLabel, watchedCheckBox, downloadedLabel, downloadedCheckBox);

        animeDetailBox.getChildren().add(0,watchedAndDownloadedBox);
        masterBox.getChildren().add(animeDetailBox);

        Label episodeCountLabel = new Label("Number of episodes: " );
        episodeCountLabel.setPadding(DEFAULT_LEFT_PADDING);
        episodeCountTextField = new TextField();
        animeDetailBox.add(episodeCountLabel, 1, 0);
        animeDetailBox.add(episodeCountTextField,2,0);

        HBox synopsisBox = new HBox();
        synopsisBox.setSpacing(DEFAULT_SPACING);
        Label synopsisLabel = new Label("Synopsis:");
        synopsisLabel.setPadding(DEFAULT_LEFT_PADDING);
        synopsisTextArea = new TextArea();

        synopsisBox.getChildren().addAll(synopsisLabel, synopsisTextArea);
        masterBox.getChildren().add(synopsisBox);

        HBox newAndSaveButtonBox = new HBox();
        newAndSaveButtonBox.setSpacing(DEFAULT_SPACING);
        newAndSaveButtonBox.setPadding(DEFAULT_LEFT_PADDING);
        newButton = new Button("Add new anime");
        saveButton = new Button("Save (Changes)");

        newAndSaveButtonBox.getChildren().addAll(newButton, saveButton);
        masterBox.getChildren().add(newAndSaveButtonBox);
        masterBox.setStyle("-fx-font-family: Arial; -fx-font-size: 12px;");

        return masterBox;
    }
    public ListView<Anime> getAnimeList() {
        return animeList;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getReviewsButton() {
        return reviewsButton;
    }

    public TextField getAnimeNameTextField() {
        return animeNameTextField;
    }

    public DatePicker getReleaseDatePicker() {
        return releaseDatePicker;
    }

    public CheckBox getWatchedCheckBox() {
        return watchedCheckBox;
    }

    public CheckBox getDownloadedCheckBox() {
        return downloadedCheckBox;
    }

    public TextField getEpisodeCountTextField() {
        return episodeCountTextField;
    }

    public TextArea getSynopsisTextArea() {
        return synopsisTextArea;
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

}
