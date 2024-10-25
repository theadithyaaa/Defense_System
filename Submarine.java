import javax.swing.*;
import java.awt.event.*;

public class Submarine extends JFrame {
    private JTextField sendTextField;
    private ChatController chatController;
    private JTextArea textArea;
    private JLabel areaStatusLabel;
    private JButton shootButton;
    private JButton sonarButton;
    private JButton tomahawkButton;
    private JSpinner soldierSpinner;
    private JSpinner ammoSpinner;
    private JCheckBox positionCheckbox;
    private JSlider slider;
    private JButton tridentButton;

    public Submarine(ChatController chatController) {
        this.chatController = chatController;
        chatController.setSubmarine(this);

        setTitle("Submarine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        areaStatusLabel = new JLabel("Area Not Cleared");
        areaStatusLabel.setBounds(20, 10, 150, 25);
        add(areaStatusLabel);

        shootButton = new JButton("Shoot");
        shootButton.setBounds(20, 50, 130, 25);
        shootButton.setEnabled(false);
        add(shootButton);

        sonarButton = new JButton("Sonar Operation");
        sonarButton.setBounds(180, 50, 140, 25);
        sonarButton.setEnabled(false);
        add(sonarButton);

        tomahawkButton = new JButton("Tomahawk Missile ");
        tomahawkButton.setBounds(20, 90, 130, 25);
        tomahawkButton.setEnabled(false);
        add(tomahawkButton);
        
        tridentButton = new JButton("Trident-2 Missile");
        tridentButton.setBounds(180, 90, 140, 25);
        tridentButton.setEnabled(false);
        add(tridentButton);

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
                sonarButton.setEnabled(isChecked && slider.getValue() >= 40);
                tomahawkButton.setEnabled(isChecked && slider.getValue() >= 60);
                tridentButton.setEnabled(isChecked && slider.getValue() >=60);
                chatController.updateSubmarinePosition(isChecked);
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
                chatController.sendMessageToMainController("Submarine: " + message);
                sendTextField.setText("");               
            }
        });
        add(sendButton);
        
        JLabel energy = new JLabel("Energy");
        energy.setBounds(520, 0, 50, 20); 
        add(energy);

        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        slider.setBounds(520, 20, 50, 300);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);
        
         JLabel oxygen = new JLabel("Oxygen");
        oxygen.setBounds(580, 0, 50, 20); 
        add(oxygen);

        JSlider slider2 = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        slider2.setBounds(580, 20, 50, 300); 
        slider2.setMajorTickSpacing(20);
        slider2.setMinorTickSpacing(5);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        add(slider2);

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
        sonarButton.setEnabled(isChecked && sliderValue >= 40);
        tomahawkButton.setEnabled(isChecked && sliderValue >= 60);
        tridentButton.setEnabled(isChecked && sliderValue >=60);
    }

    public String getSoldierCount() {
        return soldierSpinner.getValue().toString();
    }

    public String getAmmoCount() {
        return ammoSpinner.getValue().toString();
    }
}
