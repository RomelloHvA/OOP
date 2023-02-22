package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Review;

public class ReviewView extends View{
    @Override
    protected Parent initializeView() {

        VBox masterBox = new VBox();
        masterBox.setPadding(DEFAULT_TOP_PADDING);

        HBox sortAndNameBox = new HBox();
        sortAndNameBox.setSpacing(DEFAULT_SPACING);
        sortAndNameBox.setPadding(DEFAULT_LEFT_PADDING);
        Label sortByRatingLabel = new Label("Sort by rating: ");
        ComboBox<Double> ratingComboBox = new ComboBox<>();
        Label animeName = new Label("Anime: ");

        sortAndNameBox.getChildren().addAll(sortByRatingLabel,ratingComboBox,animeName);
        masterBox.getChildren().add(sortAndNameBox);


        HBox reviewListAndButtonBox = new HBox();
        ListView<Review> reviewListView = new ListView<>();
        reviewListView.setMinWidth(DEFAULT_WIDTH_LIST);
        reviewListView.setMaxWidth(DEFAULT_WIDTH_LIST);
        reviewListView.setMinHeight(DEFAULT_HEIGT_LIST);
        reviewListView.setMaxHeight(DEFAULT_HEIGT_LIST);

        VBox returnAndDeleteButtonBox = new VBox();
        Button returnButton = new Button("Return");
        Button deleteReviewButton = new Button("Delete Review");
        returnAndDeleteButtonBox.getChildren().addAll(returnButton,deleteReviewButton);
        returnAndDeleteButtonBox.setSpacing(DEFAULT_SPACING);
        returnAndDeleteButtonBox.setPadding(DEFAULT_LEFT_PADDING);

        reviewListAndButtonBox.getChildren().addAll(reviewListView, returnAndDeleteButtonBox);
        reviewListAndButtonBox.setPadding(DEFAULT_TOP_LEFT_PADDING);
        masterBox.getChildren().add(reviewListAndButtonBox);

        GridPane reviewDetailPane = new GridPane();
        reviewDetailPane.setPadding(DEFAULT_TOP_LEFT_PADDING);
        reviewDetailPane.setHgap(DEFAULT_SPACING);
        reviewDetailPane.setVgap(DEFAULT_SPACING);

        Label writtenByLabel = new Label("Written by:");
        TextField writtenByTextField = new TextField();
        reviewDetailPane.add(writtenByLabel,0,0);
        reviewDetailPane.add(writtenByTextField,1,0);

        Label writeDateLabel = new Label("Written on:");
        DatePicker writeDatePicker = new DatePicker();
        reviewDetailPane.add(writeDateLabel,2,0);
        reviewDetailPane.add(writeDatePicker,3,0);

        Label recommendedLabel = new Label("Recommended:");
        CheckBox recommendedCheckBox = new CheckBox();
        reviewDetailPane.add(recommendedLabel,0,1);
        reviewDetailPane.add(recommendedCheckBox,1,1);

        Label ratingLabel = new Label("Rating:");
        TextField reviewRating = new TextField();
        reviewDetailPane.add(ratingLabel,2,1);
        reviewDetailPane.add(reviewRating,3,1);

        masterBox.getChildren().add(reviewDetailPane);

        HBox reviewAndButtonBox = new HBox();
        reviewAndButtonBox.setPadding(DEFAULT_LEFT_PADDING);

        VBox reviewBox = new VBox();
        Label reviewLabel = new Label("Review");
        TextArea reviewTextArea = new TextArea();
        reviewTextArea.setMaxWidth(DEFAULT_WIDTH_LIST);

        reviewBox.getChildren().addAll(reviewLabel,reviewTextArea);
        reviewAndButtonBox.getChildren().add(reviewBox);


        VBox newAndSaveButtonBox = new VBox();
        newAndSaveButtonBox.setSpacing(DEFAULT_SPACING);
        newAndSaveButtonBox.setPadding(DEFAULT_TOP_LEFT_PADDING);

        Button newReviewButton = new Button("New review");
        Button saveReviewButton = new Button("Save review");
        newAndSaveButtonBox.getChildren().addAll(newReviewButton,saveReviewButton);
        reviewAndButtonBox.getChildren().add(newAndSaveButtonBox);

        masterBox.getChildren().add(reviewAndButtonBox);


        return masterBox;
    }
}
