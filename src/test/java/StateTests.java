import org.junit.jupiter.api.*;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.GreenState;
import trafficlight.states.OffState;
import trafficlight.states.RedState;
import trafficlight.states.State;

import static org.junit.jupiter.api.Assertions.*;

// Link to repository: https://github.com/GabrielHuebner/prog2-ss21-exercise1.git

public class StateTests {
    private TrafficLightCtrl ctrl;
    private State state = null;

    @BeforeAll
    static void init() {
    }

    @BeforeEach
    void setUp() {
        ctrl = TrafficLightCtrl.getInstance();
    }

    @AfterEach
    void setDown(){
        OffState state = new OffState(ctrl);
        ctrl.setCurrentState(state);
    }

    @DisplayName("Check if it switches to red state after off state")
    @Test
    void checkStateOffToRed() {
        ctrl.nextState();
        state = ctrl.getRedState();
        assertEquals(ctrl.getCurrentState(), state);
    }

    @DisplayName("Check if it switches to yellow state after red state")
    @Test
    void checkStateRedToYellow() {
        ctrl.nextState();
        ctrl.nextState();
        state = ctrl.getYellowState();
        assertEquals(ctrl.getCurrentState(), state);
    }

    @DisplayName("Check if it switches to green state after yellow state")
    @Test
    void checkStateYellowToGreen() {
        ctrl.nextState();
        ctrl.nextState();
        ctrl.nextState();
        state = ctrl.getGreenState();
        assertEquals(ctrl.getCurrentState(), state);
    }

    @DisplayName("Check if it switches to yellow state after green state")
    @Test
    void checkStateGreenToYellow() {
        ctrl.nextState();
        ctrl.nextState();
        ctrl.nextState();
        ctrl.nextState();
        state = ctrl.getYellowState();
        assertEquals(ctrl.getCurrentState(), state);
    }

    @DisplayName("Check if it switches to red state after yellow state")
    @Test
    void checkStateYellowToRed() {
        ctrl.nextState();
        ctrl.nextState();
        ctrl.nextState();
        ctrl.nextState();
        ctrl.nextState();
        state = ctrl.getRedState();
        assertEquals(ctrl.getCurrentState(), state);
    }
}