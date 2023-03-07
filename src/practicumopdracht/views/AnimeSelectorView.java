package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Anime;

import java.util.ArrayList;

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
    private ArrayList<Anime> animeArrayList;





    @Override
    protected Parent initializeView() {
        animeArrayList = new ArrayList<>();

        VBox masterBox = new VBox();
        masterBox.setSpacing(DEFAULT_SPACING);


        HBox animeListBox = new HBox();
        animeListBox.setSpacing(DEFAULT_SPACING);
        animeListBox.setPadding(DEFAULT_TOP_LEFT_PADDING);

//        List view
        animeList = new ListView<>();
        animeList.getItems().addAll(animeArrayList);

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

// This is where the synopsis, watched,downloaded, img and episode count should be in.
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







//        HBox imageAndSynopsisBox = new HBox();
//        Image posterImage = new Image(new FileInputStream("images/placeholder.jpg"));


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
