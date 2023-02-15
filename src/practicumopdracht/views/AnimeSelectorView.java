package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AnimeSelectorView extends View{


    @Override
    protected Parent initializeView() {

        // Vanaf hier wordt de filter box gestyled en toegepast.

        HBox animeFilterBox = new HBox();
        Label selectorLabel = new Label("Which anime do you want to see?");
        Button watchedAnime = new Button("Watched");
        Button unwatchedAnime = new Button("unwatched");

//        De styling wordt hier toegevoegd aan de Parent
        animeFilterBox.getChildren().addAll(selectorLabel,watchedAnime,unwatchedAnime);

        return animeFilterBox;
    }

}
