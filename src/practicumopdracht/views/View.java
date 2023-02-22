package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;

public abstract class View {
   protected final int DEFAULT_SPACING = 15;
   protected final Insets DEFAULT_TOP_PADDING = new Insets(DEFAULT_SPACING, 0,0,0);
   protected final Insets DEFAULT_TOP_LEFT_PADDING = new Insets(DEFAULT_SPACING, 0,0, DEFAULT_SPACING);
   protected final Insets DEFAULT_LEFT_PADDING = new Insets(0,0,0, DEFAULT_SPACING);

   protected final int DEFAULT_WIDTH_LIST = 400;
   protected final int DEFAULT_HEIGT_LIST = 100;
    private Parent root;

    public View() {
        root = initializeView();
    }

    protected abstract Parent initializeView();

    public Parent getRoot() {
        return root;
    }


}
