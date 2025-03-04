package trafficlight.gui;

import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.TrafficLightColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//REPO: https://github.com/GabrielHuebner/trafficlight2021.git
public class TrafficLightGui extends JFrame implements ActionListener {

    public static final String ACTION_COMMAND_NEXT = "next";

    public static final String ACTION_COMMAND_AUTO_MODE = "autoMode";
    public static final String NAME_OF_THE_GAME = "Traffic Light";

    private JButton buttonNextState;
    private JCheckBox checkBoxAutoMode;
    private JLabel labelAutoMode;

    private Light green = null;
    private Light yellow = null;
    private Light red = null;

    private TrafficLightCtrl trafficLightCtrl = null;

    private boolean isAutoMode = false;
    private boolean doExit = false;

    private int yellowIntervall = 500;

    private int intervall = 1500;

    public TrafficLightGui(TrafficLightCtrl ctrl){
        super(NAME_OF_THE_GAME);
        trafficLightCtrl = ctrl;
        initLights();
        init();
    }

    private void initLights() {
        green  = new Light(Color.green);
        yellow = new Light(Color.yellow);
        red    = new Light(Color.red);
    }

    private void init() {
        getContentPane().setLayout(new GridLayout(2, 1));
        buttonNextState = new JButton("next State");
        buttonNextState.setActionCommand(ACTION_COMMAND_NEXT);
        buttonNextState.addActionListener(this);

        labelAutoMode = new JLabel("AutoMode ");

        checkBoxAutoMode = new JCheckBox();
        checkBoxAutoMode.setActionCommand("autoMode");
        checkBoxAutoMode.addActionListener(this);

        JPanel p1 = new JPanel(new GridLayout(3,1));
        p1.add(red);
        p1.add(yellow);
        p1.add(green);


        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(buttonNextState);
        p2.add(labelAutoMode);
        p2.add(checkBoxAutoMode);

        getContentPane().add(p1);
        getContentPane().add(p2);
        pack();
    }

    public void run() {
        while (!doExit) {
            try {
                if (yellow.isOn) {
                    while (trafficLightCtrl.getCurrentState().equals(trafficLightCtrl.getYellowState()) && !isAutoMode) {
                        yellow.turnOn(true);
                        try {
                            Thread.sleep(yellowIntervall);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        yellow.turnOn(false);
                        try {
                            Thread.sleep(yellowIntervall);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Thread.sleep(intervall);
                }
            } catch (InterruptedException e) {
                JOptionPane pane = new JOptionPane();
                JDialog dialog = pane.createDialog(this,"Traffic Light Problem");
                JOptionPane.showMessageDialog(dialog, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
             while (isAutoMode) {
                 //TODO call the controller
                 trafficLightCtrl.nextState();

                try {
                    if (yellow.isOn) {
                        for (int i = 3; i > 0; i--) {
                            yellow.turnOn(true);
                            try {
                                Thread.sleep(yellowIntervall);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            yellow.turnOn(false);
                            try {
                                Thread.sleep(yellowIntervall);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Thread.sleep(intervall);
                    }
                } catch (InterruptedException e) {
                    JOptionPane pane = new JOptionPane();
                    JDialog dialog = pane.createDialog(this,"Traffic Light Problem");
                    JOptionPane.showMessageDialog(dialog, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    isAutoMode = false;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if (ACTION_COMMAND_NEXT.equals(e.getActionCommand())){
           trafficLightCtrl.nextState();
        } else if (ACTION_COMMAND_AUTO_MODE.equals(e.getActionCommand())){
            isAutoMode = !isAutoMode;
            System.out.println("set Auto mode to "+isAutoMode);
        }
    }

    public void setLight(TrafficLightColor trafficLightColor){
        //TODO setLight
        if(trafficLightColor.equals(TrafficLightColor.OFF)){
            green.turnOn(false);
            red.turnOn(false);
            yellow.turnOn(false);
        }
        else if(trafficLightColor.equals(TrafficLightColor.GREEN)){
            green.turnOn(true);
            red.turnOn(false);
            yellow.turnOn(false);
        }
        else if(trafficLightColor.equals(TrafficLightColor.YELLOW)){
            yellow.turnOn(true);
            red.turnOn(false);
            green.turnOn(false);
        }
        else if(trafficLightColor.equals(TrafficLightColor.RED)){
            red.turnOn(true);
            green.turnOn(false);
            yellow.turnOn(false);
        }
    }
}
