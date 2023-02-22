package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Anime;

import java.io.FileInputStream;
import java.time.LocalDate;

public class AnimeSelectorView extends View{


    @Override
    protected Parent initializeView() {



        VBox masterbox = new VBox();
        masterbox.setSpacing(defaultSpacing);


        HBox animeListBox = new HBox();
        animeListBox.setSpacing(defaultSpacing);
        animeListBox.setPadding(leftPadding);

//        List view
        ListView<Anime> animeList = new ListView<>();
        int widthAnimeList = 400;
        int heightAnimeList = 100;
        animeList.setMinWidth(widthAnimeList);
        animeList.setMaxWidth(widthAnimeList);
        animeList.setMaxHeight(heightAnimeList);
        animeList.setMinHeight(heightAnimeList);

        VBox reviewDeleteButtonBox = new VBox();
        reviewDeleteButtonBox.setSpacing(defaultSpacing);
        reviewDeleteButtonBox.setPadding(topPadding);
        Button deleteButton = new Button("Delete anime");
        Button reviewButton = new Button(" Go to Reviews");
        reviewDeleteButtonBox.getChildren().addAll(reviewButton,deleteButton);

        animeListBox.getChildren().addAll(animeList,reviewDeleteButtonBox);
        masterbox.getChildren().add(animeListBox);


        HBox animeNameAndDateBox = new HBox();
        animeNameAndDateBox.setSpacing(defaultSpacing);
        animeNameAndDateBox.setPadding(leftPadding);

        Label animeNameLabel = new Label("Anime Name:");
        TextField animeNameText = new TextField();
        Label releaseDateLabel = new Label("Release Date:");
        DatePicker datePicker = new DatePicker();

        animeNameAndDateBox.getChildren().addAll(animeNameLabel,animeNameText, releaseDateLabel, datePicker);
        masterbox.getChildren().add(animeNameAndDateBox);

// This is where the synopsis, watched,downloaded, img and episode count should be in.
        GridPane animeDetailBox = new GridPane();
        animeDetailBox.setPadding(leftPadding);

        HBox watchedAndDownloadedBox = new HBox();
        watchedAndDownloadedBox.setPadding(leftPadding);
        Label watchedLabel = new Label("Watched:");
        CheckBox watchedCheckBox = new CheckBox();
        watchedCheckBox.setPadding(leftPadding);
        Label downloadedLabel = new Label("Downloaded:");
        downloadedLabel.setPadding(leftPadding);
        CheckBox downloadedCheckBox = new CheckBox();
        downloadedCheckBox.setPadding(leftPadding);

        watchedAndDownloadedBox.getChildren().addAll(watchedLabel,watchedCheckBox, downloadedLabel, downloadedCheckBox);

        animeDetailBox.getChildren().add(0,watchedAndDownloadedBox);
        masterbox.getChildren().add(animeDetailBox);

        Label episodeCountLabel = new Label("Number of episodes: " );
        episodeCountLabel.setPadding(leftPadding);
        TextField episodeCountTextField = new TextField();
        animeDetailBox.add(episodeCountLabel, 1, 0);
        animeDetailBox.add(episodeCountTextField,2,0);

        HBox synopsisBox = new HBox();
        synopsisBox.setSpacing(defaultSpacing);
        Label synopsisLabel = new Label("Synopsis:");
        synopsisLabel.setPadding(leftPadding);
        TextArea synopsisTextArea = new TextArea();

        synopsisBox.getChildren().addAll(synopsisLabel,synopsisTextArea);
        masterbox.getChildren().add(synopsisBox);

        HBox newAndSaveButtonBox = new HBox();
        newAndSaveButtonBox.setSpacing(defaultSpacing);
        newAndSaveButtonBox.setPadding(leftPadding);
        Button newButton = new Button("Add new anime");
        Button saveButton = new Button("Save (Changes)");

        newAndSaveButtonBox.getChildren().addAll(newButton,saveButton);
        masterbox.getChildren().add(newAndSaveButtonBox);







//        HBox imageAndSynopsisBox = new HBox();
//        Image posterImage = new Image(new FileInputStream("images/placeholder.jpg"));



        return masterbox;
    }

}
