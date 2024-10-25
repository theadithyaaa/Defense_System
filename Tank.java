import javax.swing.*;
import java.awt.event.*;

public class Tank extends JFrame {
    private JTextField sendTextField;
    private ChatController chatController;
    private JTextArea textArea;
    private JLabel areaStatusLabel;
    private JButton shootButton;
    private JButton missileButton;
    private JButton radarButton;
    private JButton rotateButton;
    private JSpinner soldierSpinner;
    private JSpinner ammoSpinner;
    private JCheckBox positionCheckbox;
    private JSlider slider;

    public Tank(ChatController chatController) {
        this.chatController = chatController;
        chatController.setTank(this);

        setTitle("Tank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        areaStatusLabel = new JLabel("Area Not Cleared");
        areaStatusLabel.setBounds(20, 10, 150, 25);
        add(areaStatusLabel);

        shootButton = new JButton("Shoot");
        shootButton.setBounds(20, 50, 140, 25);
        shootButton.setEnabled(false);
        add(shootButton);

        missileButton = new JButton("Missile Operation");
        missileButton.setBounds(180, 50, 140, 25);
        missileButton.setEnabled(false);
        add(missileButton);

        radarButton = new JButton("Radar Operation");
        radarButton.setBounds(20, 90, 140, 25);
        radarButton.setEnabled(false);
        add(radarButton);

        rotateButton = new JButton("Rotate Shooting");
        rotateButton.setBounds(180, 90, 140, 25);
        rotateButton.setEnabled(false);
        add(rotateButton);

        JLabel soldierCountLabel = new JLabel("Soldier Count");
        soldierCountLabel.setBounds(340, 50, 100, 25);
        add(soldierCountLabel);

        soldierSpinner = new JSpinner();
        soldierSpinner.setBounds(450, 50, 50, 25);
        add(soldierSpinner);

        JLabel ammoCountLabel = new JLabel("Ammo Count");
        ammoCountLabel.setBounds(340, 90, 100, 25);
        add(ammoCountLabel);

        ammoSpinner = new JSpinner();
        ammoSpinner.setBounds(450, 90, 50, 25);
        add(ammoSpinner);

        positionCheckbox = new JCheckBox("Position");
        positionCheckbox.setBounds(340, 120, 100, 25);
        positionCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                boolean isChecked = e.getStateChange() == ItemEvent.SELECTED;
                shootButton.setEnabled(isChecked && slider.getValue() >= 20);
                missileButton.setEnabled(isChecked && slider.getValue() >= 40);
                radarButton.setEnabled(isChecked && slider.getValue() >= 60);
                rotateButton.setEnabled(isChecked && slider.getValue() >= 60);
                chatController.updateTankPosition(isChecked);
            }
        });
        add(positionCheckbox);

        textArea = new JTextArea();
        JScrollPane textAreaScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textAreaScrollPane.setBounds(20, 170, 350, 100);
        textArea.setEditable(false);
        add(textAreaScrollPane);

        sendTextField = new JTextField();
        sendTextField.setBounds(20, 300, 350, 25);
        add(sendTextField);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(400, 300, 80, 25);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = sendTextField.getText();
                chatController.sendMessageToMainController("Tank: " + message);
                sendTextField.setText("");
            }
        });
        add(sendButton);

        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        slider.setBounds(520, 20, 50, 300);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);

        setVisible(true);
    }

    public void updateTextArea(String message) {
        textArea.append(message + "\n");
    }

    public void updateAreaStatusLabel(boolean areaCleared) {
        areaStatusLabel.setText(areaCleared ? "Area Cleared" : "Area Not Cleared");
    }

    public void updateAttackButtons(int sliderValue) {
        boolean isChecked = positionCheckbox.isSelected();
        shootButton.setEnabled(isChecked && sliderValue >= 20);
        missileButton.setEnabled(isChecked && sliderValue >= 40);
        radarButton.setEnabled(isChecked && sliderValue >= 60);
        rotateButton.setEnabled(isChecked && sliderValue >= 60);
    }

    public String getSoldierCount() {
        return soldierSpinner.getValue().toString();
    }

    public String getAmmoCount() {
        return ammoSpinner.getValue().toString();
    }
}
