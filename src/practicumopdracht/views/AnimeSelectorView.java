package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class AnimeSelectorView extends View{


    @Override
    protected Parent initializeView() {

        GridPane masterPane = new GridPane();
        Insets masterPanePadding = new Insets(10,10,10,0);
        masterPane.setPadding(masterPanePadding);
        masterPane.setBackground(Background.fill(Color.BLACK));

        HBox animeFilterBox = new HBox();

        Label selectorLabel = new Label("Which anime do you want to see?");
        selectorLabel.setTextFill(Color.ORANGE);
        selectorLabel.setAlignment(Pos.CENTER_LEFT);

        Button watchedAnime = new Button("Watched");
        Button unwatchedAnime = new Button("Unwatched");

//        De styling wordt hier toegevoegd aan de Parent
        animeFilterBox.getChildren().addAll(selectorLabel,watchedAnime,unwatchedAnime);

// Hier worden alle
        masterPane.getChildren().addAll(animeFilterBox);
        masterPane.setAlignment(Pos.TOP_RIGHT);

        return masterPane;
    }

}
