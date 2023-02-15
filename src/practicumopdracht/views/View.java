package practicumopdracht.views;

import javafx.scene.Parent;

public abstract class View {
    private Parent root;


    protected abstract Parent initializeView();

    public Parent getRoot() {
        return root;
    }

    public View() {
        root = initializeView();
    }

}
