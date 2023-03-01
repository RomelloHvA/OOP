package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Anime;

public class AnimeSelectorView extends View{


    @Override
    protected Parent initializeView() {



        VBox masterBox = new VBox();
        masterBox.setSpacing(DEFAULT_SPACING);


        HBox animeListBox = new HBox();
        animeListBox.setSpacing(DEFAULT_SPACING);
        animeListBox.setPadding(DEFAULT_TOP_LEFT_PADDING);

//        List view
        ListView<Anime> animeList = new ListView<>();

        animeList.setMinWidth(DEFAULT_WIDTH_LIST);
        animeList.setMaxWidth(DEFAULT_WIDTH_LIST);
        animeList.setMaxHeight(DEFAULT_HEIGT_LIST);
        animeList.setMinHeight(DEFAULT_HEIGT_LIST);

        VBox reviewDeleteButtonBox = new VBox();
        reviewDeleteButtonBox.setSpacing(DEFAULT_SPACING);
        reviewDeleteButtonBox.setPadding(DEFAULT_LEFT_PADDING);
        Button deleteButton = new Button("Delete anime");
        Button reviewButton = new Button(" Go to Reviews");
        reviewDeleteButtonBox.getChildren().addAll(reviewButton,deleteButton);

        animeListBox.getChildren().addAll(animeList,reviewDeleteButtonBox);
        masterBox.getChildren().add(animeListBox);


        HBox animeNameAndDateBox = new HBox();
        animeNameAndDateBox.setSpacing(DEFAULT_SPACING);
        animeNameAndDateBox.setPadding(DEFAULT_LEFT_PADDING);

        Label animeNameLabel = new Label("Anime Name:");
        TextField animeNameText = new TextField();
        Label releaseDateLabel = new Label("Release Date:");
        DatePicker datePicker = new DatePicker();

        animeNameAndDateBox.getChildren().addAll(animeNameLabel,animeNameText, releaseDateLabel, datePicker);
        masterBox.getChildren().add(animeNameAndDateBox);

// This is where the synopsis, watched,downloaded, img and episode count should be in.
        GridPane animeDetailBox = new GridPane();
        animeDetailBox.setPadding(DEFAULT_LEFT_PADDING);

        HBox watchedAndDownloadedBox = new HBox();
        watchedAndDownloadedBox.setPadding(DEFAULT_LEFT_PADDING);
        Label watchedLabel = new Label("Watched:");
        CheckBox watchedCheckBox = new CheckBox();
        watchedCheckBox.setPadding(DEFAULT_LEFT_PADDING);
        Label downloadedLabel = new Label("Downloaded:");
        downloadedLabel.setPadding(DEFAULT_LEFT_PADDING);
        CheckBox downloadedCheckBox = new CheckBox();
        downloadedCheckBox.setPadding(DEFAULT_LEFT_PADDING);

        watchedAndDownloadedBox.getChildren().addAll(watchedLabel,watchedCheckBox, downloadedLabel, downloadedCheckBox);

        animeDetailBox.getChildren().add(0,watchedAndDownloadedBox);
        masterBox.getChildren().add(animeDetailBox);

        Label episodeCountLabel = new Label("Number of episodes: " );
        episodeCountLabel.setPadding(DEFAULT_LEFT_PADDING);
        TextField episodeCountTextField = new TextField();
        animeDetailBox.add(episodeCountLabel, 1, 0);
        animeDetailBox.add(episodeCountTextField,2,0);

        HBox synopsisBox = new HBox();
        synopsisBox.setSpacing(DEFAULT_SPACING);
        Label synopsisLabel = new Label("Synopsis:");
        synopsisLabel.setPadding(DEFAULT_LEFT_PADDING);
        TextArea synopsisTextArea = new TextArea();

        synopsisBox.getChildren().addAll(synopsisLabel,synopsisTextArea);
        masterBox.getChildren().add(synopsisBox);

        HBox newAndSaveButtonBox = new HBox();
        newAndSaveButtonBox.setSpacing(DEFAULT_SPACING);
        newAndSaveButtonBox.setPadding(DEFAULT_LEFT_PADDING);
        Button newButton = new Button("Add new anime");
        Button saveButton = new Button("Save (Changes)");

        newAndSaveButtonBox.getChildren().addAll(newButton,saveButton);
        masterBox.getChildren().add(newAndSaveButtonBox);







//        HBox imageAndSynopsisBox = new HBox();
//        Image posterImage = new Image(new FileInputStream("images/placeholder.jpg"));



        return masterBox;
    }

}
