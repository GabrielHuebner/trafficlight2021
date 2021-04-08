package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class OffState implements State {
    @Override
    public void nextState(TrafficLightCtrl ctrl) {
        ctrl.setCurrentState(ctrl.getRedState());
    }

    @Override
    public TrafficLightColor getState() {
        return TrafficLightColor.OFF;
    }
}
