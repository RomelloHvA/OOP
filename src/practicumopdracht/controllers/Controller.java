package practicumopdracht.controllers;

import practicumopdracht.views.View;

import java.util.EventListener;

public abstract class Controller implements EventListener {

    public abstract View getView();
}
