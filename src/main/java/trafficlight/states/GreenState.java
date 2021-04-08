package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class GreenState implements State{
    @Override
    public void nextState(TrafficLightCtrl ctrl) {
        ctrl.setCurrentState(ctrl.getRedState());
    }

    @Override
    public TrafficLightColor getState() {
        return TrafficLightColor.GREEN;
    }
}
