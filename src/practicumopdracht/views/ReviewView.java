package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;

public class ReviewView extends View {

    private ComboBox<Double> ratingComboBox;
    private ComboBox<Anime> animeComboBox;
    private Button returnButton;
    private Button deleteReviewButton;
    private TextField writtenByTextField;
    private DatePicker writeDatePicker;
    private CheckBox recommendedCheckBox;
    private TextField reviewRating;
    private TextArea reviewTextArea;
    private Button newReviewButton;
    private Button saveReviewButton;
    private ListView<Review> reviewListView;
    private RadioButton sortRatingAsc;
    private RadioButton sortRatingDes;
    private RadioButton sortWrittenByAsc;
    private RadioButton sortWrittenByDes;


    public RadioButton getSortRatingAsc() {
        return sortRatingAsc;
    }

    public RadioButton getSortRatingDes() {
        return sortRatingDes;
    }

    public RadioButton getSortWrittenByAsc() {
        return sortWrittenByAsc;
    }

    public RadioButton getSortWrittenByDes() {
        return sortWrittenByDes;
    }

    @Override
    protected Parent initializeView() {

        VBox masterBox = new VBox();
        masterBox.setPadding(DEFAULT_TOP_PADDING);

        HBox sortAndNameBox = new HBox();
        sortAndNameBox.setSpacing(DEFAULT_SPACING);
        sortAndNameBox.setPadding(DEFAULT_LEFT_PADDING);
        Label animeName = new Label("Anime: ");
        animeComboBox = new ComboBox<>();

        sortAndNameBox.getChildren().addAll(animeName, animeComboBox);
        masterBox.getChildren().add(sortAndNameBox);


        HBox reviewListAndButtonBox = new HBox();
        reviewListView = new ListView<>();
        reviewListView.setMinWidth(DEFAULT_WIDTH_LIST);
        reviewListView.setMaxWidth(DEFAULT_WIDTH_LIST);
        reviewListView.setMinHeight(DEFAULT_HEIGT_LIST);
        reviewListView.setMaxHeight(DEFAULT_HEIGT_LIST);

        VBox returnAndDeleteButtonBox = new VBox();
        returnButton = new Button("Return");
        deleteReviewButton = new Button("Delete Review");
        returnAndDeleteButtonBox.getChildren().addAll(returnButton, deleteReviewButton);
        returnAndDeleteButtonBox.setSpacing(DEFAULT_SPACING);
        returnAndDeleteButtonBox.setPadding(DEFAULT_LEFT_PADDING);

        reviewListAndButtonBox.getChildren().addAll(reviewListView, returnAndDeleteButtonBox);
        reviewListAndButtonBox.setPadding(DEFAULT_TOP_LEFT_PADDING);
        masterBox.getChildren().add(reviewListAndButtonBox);

        GridPane reviewDetailPane = new GridPane();
        reviewDetailPane.setPadding(DEFAULT_TOP_LEFT_PADDING);
        reviewDetailPane.setHgap(DEFAULT_SPACING);
        reviewDetailPane.setVgap(DEFAULT_SPACING);

        sortRatingAsc = new RadioButton("Rating (ASC)");
        sortRatingDes = new RadioButton("Rating (DES)");
        sortWrittenByAsc = new RadioButton("Author (ASC)");
        sortWrittenByDes = new RadioButton("Author (DES)");

        reviewDetailPane.add(sortRatingAsc, 0, 0);
        reviewDetailPane.add(sortRatingDes, 1, 0);
        reviewDetailPane.add(sortWrittenByAsc, 2, 0);
        reviewDetailPane.add(sortWrittenByDes, 3, 0);

        Label writtenByLabel = new Label("Written by:");
        writtenByTextField = new TextField();

        reviewDetailPane.add(writtenByLabel, 0, 1);
        reviewDetailPane.add(writtenByTextField, 1, 1);

        Label writeDateLabel = new Label("Written on:");
        writeDatePicker = new DatePicker();
        reviewDetailPane.add(writeDateLabel, 2, 1);
        reviewDetailPane.add(writeDatePicker, 3, 1);

        Label recommendedLabel = new Label("Recommended:");
        recommendedCheckBox = new CheckBox();
        reviewDetailPane.add(recommendedLabel, 0, 2);
        reviewDetailPane.add(recommendedCheckBox, 1, 2);

        Label ratingLabel = new Label("Rating:");
        reviewRating = new TextField();
        reviewDetailPane.add(ratingLabel, 2, 2);
        reviewDetailPane.add(reviewRating, 3, 2);

        masterBox.getChildren().add(reviewDetailPane);

        HBox reviewAndButtonBox = new HBox();
        reviewAndButtonBox.setPadding(DEFAULT_LEFT_PADDING);

        VBox reviewBox = new VBox();
        Label reviewLabel = new Label("Review");
        reviewTextArea = new TextArea();
        reviewTextArea.setMaxWidth(DEFAULT_WIDTH_LIST);

        reviewBox.getChildren().addAll(reviewLabel, reviewTextArea);
        reviewAndButtonBox.getChildren().add(reviewBox);

        VBox newAndSaveButtonBox = new VBox();
        newAndSaveButtonBox.setSpacing(DEFAULT_SPACING);
        newAndSaveButtonBox.setPadding(DEFAULT_TOP_LEFT_PADDING);

        newReviewButton = new Button("New review");
        saveReviewButton = new Button("Save review");
        newAndSaveButtonBox.getChildren().addAll(newReviewButton, saveReviewButton);
        reviewAndButtonBox.getChildren().add(newAndSaveButtonBox);

        masterBox.getChildren().add(reviewAndButtonBox);


        return masterBox;
    }

    public ComboBox<Double> getRatingComboBox() {
        return ratingComboBox;
    }

    public ComboBox<Anime> getAnimeComboBox() {
        return animeComboBox;
    }

    public Button getReturnButton() {
        return returnButton;
    }

    public Button getDeleteReviewButton() {
        return deleteReviewButton;
    }

    public TextField getWrittenByTextField() {
        return writtenByTextField;
    }

    public DatePicker getWriteDatePicker() {
        return writeDatePicker;
    }

    public CheckBox getRecommendedCheckBox() {
        return recommendedCheckBox;
    }

    public TextField getReviewRating() {
        return reviewRating;
    }

    public TextArea getReviewTextArea() {
        return reviewTextArea;
    }

    public Button getNewReviewButton() {
        return newReviewButton;
    }

    public Button getSaveReviewButton() {
        return saveReviewButton;
    }
    public ListView<Review> getReviewListView() {
        return reviewListView;
    }
}
