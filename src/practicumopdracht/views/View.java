package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public abstract class View {
    private Parent root;
    protected final int DEFAULT_SPACING = 15;
    protected final Insets DEFAULT_TOP_PADDING = new Insets(DEFAULT_SPACING, 0, 0, 0);
    protected final Insets DEFAULT_TOP_LEFT_PADDING = new Insets(DEFAULT_SPACING, 0, 0, DEFAULT_SPACING);
    protected final Insets DEFAULT_LEFT_PADDING = new Insets(0, 0, 0, DEFAULT_SPACING);

    protected final int DEFAULT_WIDTH_LIST = 400;
    protected final int DEFAULT_HEIGT_LIST = 100;
    protected final double DEFAULT_HEIGHT_MENU_ICON = 16.0;
    protected final double DEFAULT_WIDTH_MENU_ICON = 16.0;

    public View() {
        root = initializeView();
    }

    protected abstract Parent initializeView();

    public Parent getRoot() {
        return root;
    }


}
