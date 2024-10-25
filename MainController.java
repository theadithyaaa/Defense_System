import javax.swing.*;
import java.awt.event.*;

public class MainController extends JFrame {
    private JTextArea infoTextArea1;
    private ChatController chatController;
    private JLabel HelicopterPosition;
    private JLabel TankPosition;
    private JLabel SubmarinePosition;
    private JLabel selectedSoldierCount;
    private JLabel selectedAmmoCount;
    private JComboBox defenceComboBox;
 

    public MainController(ChatController chatController) {
        this.chatController = chatController;
        chatController.setMainController(this);

        setTitle("Main Controller");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400); 
        setLayout(null);
        setLocationRelativeTo(null);

        String[] defenceOptions = { "Select Defence", "Helicopter", "Tank", "Submarine" };
        defenceComboBox = new JComboBox(defenceOptions);
        defenceComboBox.setBounds(20, 20, 150, 25);
        defenceComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) defenceComboBox.getSelectedItem();
                if (!selected.equals("Select Defence")) {
                    chatController.requestDefenceDetails(selected);
                }
            }
        });
        add(defenceComboBox);

        JButton collectButton = new JButton("Collect Informations");
        collectButton.setBounds(200, 20, 150, 25);
        add(collectButton);

        JCheckBox areaClearCheckbox = new JCheckBox("Area Clear");
        areaClearCheckbox.setBounds(400, 20, 100, 25);
        areaClearCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                boolean isChecked = e.getStateChange() == ItemEvent.SELECTED;
                chatController.updateAreaClearedStatus(isChecked);
            }
        });
        add(areaClearCheckbox);

        JLabel soldierCountLabel = new JLabel("Soldier Count :");
        soldierCountLabel.setBounds(20, 60, 100, 25);
        add(soldierCountLabel);

        JLabel fuelAmountLabel = new JLabel("Fuel Amount");
        fuelAmountLabel.setBounds(20, 100, 100, 25);
        add(fuelAmountLabel);

        JLabel ammoAmountLabel = new JLabel("Ammo Amount :");
        ammoAmountLabel.setBounds(20, 140, 100, 25);
        add(ammoAmountLabel);
        
        selectedSoldierCount = new JLabel("");
        selectedSoldierCount.setBounds(130, 60, 100, 25);
        add(selectedSoldierCount);

        selectedAmmoCount = new JLabel("");
        selectedAmmoCount.setBounds(130, 140, 100, 25);
        add(selectedAmmoCount);

        JLabel positionLabel = new JLabel("Position");
        positionLabel.setBounds(20, 180, 100, 25);
        add(positionLabel);

        HelicopterPosition = new JLabel("Helicopter: ✖️");
        HelicopterPosition.setBounds(100, 180, 100, 25);
        add(HelicopterPosition);

        TankPosition = new JLabel("Tank: ✖️");
        TankPosition.setBounds(190, 180, 100, 25);
        add(TankPosition);

        SubmarinePosition = new JLabel("Submarine: ✖️");
        SubmarinePosition.setBounds(250, 180, 100, 25);
        add(SubmarinePosition);

        JSlider positionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        positionSlider.setBounds(20, 210, 400, 50);
        positionSlider.setMajorTickSpacing(20);
        positionSlider.setMinorTickSpacing(5);
        positionSlider.setPaintTicks(true);
        positionSlider.setPaintLabels(true);
        positionSlider.addChangeListener(e -> {
            int value = positionSlider.getValue();
            chatController.updateAttackButtons(value);
        });
        add(positionSlider);

        JCheckBox sendPrivateCheckbox = new JCheckBox("Send Private");
        sendPrivateCheckbox.setBounds(450, 200, 150, 25);
        add(sendPrivateCheckbox);

        JTextArea messageTextArea = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(messageTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(400, 60, 150, 100);
        add(scrollPane1);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(460, 230, 80, 25);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String message = messageTextArea.getText();
                chatController.sendMessageToAll("Control room: " + message);
                messageTextArea.setText("");
            }
        });
        add(sendButton);

        infoTextArea1 = new JTextArea();
        infoTextArea1.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(infoTextArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(20, 270, 150, 70);
        add(scrollPane2);

        JTextArea infoTextArea2 = new JTextArea();
        infoTextArea2.setEditable(false);
        JScrollPane scrollPane3 = new JScrollPane(infoTextArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane3.setBounds(200, 270, 150, 70);
        add(scrollPane3);

        setVisible(true);
    }

    public void updateInfoTextArea(String message) {
        infoTextArea1.append(message + "\n");
    }

    public void updateHelicopterPositionLabel(boolean positioned) {
        HelicopterPosition.setText("Helicopter: " + (positioned ? "✔️" : "✖️"));
    }

    public void updateTankPositionLabel(boolean positioned) {
        TankPosition.setText("Tank: " + (positioned ? "✔️" : "✖️"));
    }

    public void updateSubmarinePositionLabel(boolean positioned) {
        SubmarinePosition.setText("Submarine: " + (positioned ? "✔️" : "✖️"));
    }
    public void updateSelectedDefenceDetails(String soldierCount, String ammoCount) {
        selectedSoldierCount.setText(soldierCount);
        selectedAmmoCount.setText(ammoCount);
    }

    public static void main(String[] args) {
        ChatController chatController = new ChatController();
        SwingUtilities.invokeLater(() -> {
            new MainController(chatController);
            new Helicopter(chatController);
            new Tank(chatController);
            new Submarine(chatController);
        });
    }
}
