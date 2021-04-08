package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.*;
//REPO: https://github.com/GabrielHuebner/trafficlight2021.git
public class TrafficLightCtrl {

    private static TrafficLightCtrl instance = null;

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private TrafficLightGui gui;

    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
    }

    public static TrafficLightCtrl getInstance(){
        if(instance == null){
            instance = new TrafficLightCtrl();
        }
        return instance;
    }

    public State stateFactory(String name){
        State state = null;
        if(name.equals("off")){
            state = new OffState(this);
        }
        if(name.equals("green")){
            state = new GreenState(this);
        }
        if(name.equals("yellow")){
            state = new YellowState(this);
        }
        if(name.equals("red")){
            state = new RedState(this);
        }
        return state;
    }

    private void initStates() {
        //TODO create the states and set current and previous state
        greenState = stateFactory("green");
        redState = stateFactory("red");
        yellowState = stateFactory("yellow");
        currentState = stateFactory("off");
        previousState = currentState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public void run() {
        gui.run();
    }

    public void nextState() {
        //TODO handle GUi request and update GUI
        currentState.nextState();
        gui.setLight(currentState.getState());
    }
}