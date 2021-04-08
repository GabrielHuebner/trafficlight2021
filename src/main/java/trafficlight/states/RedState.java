package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class RedState implements State {
    @Override
    public void nextState(TrafficLightCtrl ctrl) {
        ctrl.setCurrentState(ctrl.getYellowState());
    }

    @Override
    public TrafficLightColor getState() {
        return TrafficLightColor.RED;
    }
}
