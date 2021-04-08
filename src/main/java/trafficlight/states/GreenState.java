package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class GreenState implements State{
    private TrafficLightCtrl ctrl;
    public GreenState(TrafficLightCtrl ctrl){
        this.ctrl = ctrl;
    }

    @Override
    public void nextState() {
        ctrl.setPreviousState(ctrl.getGreenState());
        ctrl.setCurrentState(ctrl.getYellowState());
    }

    @Override
    public TrafficLightColor getState() {
        return TrafficLightColor.GREEN;
    }
}
