import trafficlight.ctrl.TrafficLightCtrl;

//REPO: https://github.com/GabrielHuebner/trafficlight2021.git
public class MCP {
    public static void main(String[] args) {
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        ctrl.run();
    }
}