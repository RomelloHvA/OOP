package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;

public abstract class View {
   protected int defaultSpacing = 15;
   protected Insets topPadding = new Insets(defaultSpacing, 0,0,0);
   protected Insets rightPadding = new Insets(0,defaultSpacing,0,0);
   protected Insets bottomPadding = new Insets(0,0,defaultSpacing,0);
   protected Insets leftPadding = new Insets(0,0,0,defaultSpacing);
    private Parent root;

    public View() {
        root = initializeView();
    }

    protected abstract Parent initializeView();

    public Parent getRoot() {
        return root;
    }


}
