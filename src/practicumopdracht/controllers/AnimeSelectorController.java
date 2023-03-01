package practicumopdracht.controllers;

import javafx.scene.Scene;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

public class AnimeSelectorController extends Controller{

    private View animeSelectorView;

    public AnimeSelectorController() {
        this.animeSelectorView = new AnimeSelectorView();
    }

    @Override
    public View getView() {
        return this.animeSelectorView;
    }
}
