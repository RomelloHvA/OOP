package practicumopdracht.controllers;

import practicumopdracht.views.View;

import java.awt.*;
import java.time.LocalDate;
import java.util.EventListener;
import javafx.scene.control.TextField;

public abstract class Controller implements EventListener {
    protected final double MAX_RATING = 5.0;
    protected final double MIN_RATING = 0;
    protected final LocalDate MIN_ANIME_RELEASEDATE = LocalDate.ofEpochDay(30-5-1917);
    protected final LocalDate CURRENT_DATE = LocalDate.now();
    protected final int MIN_EPISODES = 1;


    protected boolean isEmptyTextField(TextField textField) {
        return textField.getText().isEmpty();

    }

    protected boolean isEmptyTextArea(TextArea textArea){
        return textArea.getText().isEmpty();
    }



    public abstract View getView();
}
