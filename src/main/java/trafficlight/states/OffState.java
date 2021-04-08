package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class OffState implements State {
    private TrafficLightCtrl ctrl;
    public OffState(TrafficLightCtrl ctrl){
        this.ctrl = ctrl;
    }

    @Override
    public void nextState() {
        ctrl.setCurrentState(ctrl.getRedState());
    }

    @Override
    public TrafficLightColor getState() {
        return TrafficLightColor.OFF;
    }
}
