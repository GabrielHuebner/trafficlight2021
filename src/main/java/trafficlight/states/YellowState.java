package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class YellowState implements State{
    private TrafficLightCtrl ctrl;
    public YellowState(TrafficLightCtrl ctrl){
        this.ctrl = ctrl;
    }

    @Override
    public void nextState() {
        if(ctrl.getPreviousState().equals(ctrl.getRedState())){
            ctrl.setCurrentState(ctrl.getGreenState());
        }
        else if(ctrl.getPreviousState().equals(ctrl.getGreenState())) {
            ctrl.setCurrentState(ctrl.getRedState());
        }
    }

    @Override
    public TrafficLightColor getState() {
        return TrafficLightColor.YELLOW;
    }
}
