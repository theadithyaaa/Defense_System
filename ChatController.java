public class ChatController {
    private MainController mainController;
    private Helicopter helicopter;
    private Tank tank;
    private Submarine submarine;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void setSubmarine(Submarine submarine) {
        this.submarine = submarine;
    }

    public void sendMessageToMainController(String message) {
        if (mainController != null) {
            mainController.updateInfoTextArea(message);
        }
    }

    public void sendMessageToAll(String message) {
        if (helicopter != null) {
            helicopter.updateTextArea(message);
        }
        if (tank != null) {
            tank.updateTextArea(message);
        }
        if (submarine != null) {
            submarine.updateTextArea(message);
        }
    }

    public void updateAreaClearedStatus(boolean areaCleared) {
        if (helicopter != null) {
            helicopter.updateAreaStatusLabel(areaCleared);
        }
        if (tank != null) {
            tank.updateAreaStatusLabel(areaCleared);
        }
        if (submarine != null) {
            submarine.updateAreaStatusLabel(areaCleared);
        }
    }

    public void updateHelicopterPosition(boolean positioned) {
        if (mainController != null) {
            mainController.updateHelicopterPositionLabel(positioned);
        }
    }

    public void updateTankPosition(boolean positioned) {
        if (mainController != null) {
            mainController.updateTankPositionLabel(positioned);
        }
    }

    public void updateSubmarinePosition(boolean positioned) {
        if (mainController != null) {
            mainController.updateSubmarinePositionLabel(positioned);
        }
    }

    public void updateAttackButtons(int sliderValue) {
        if (helicopter != null) {
            helicopter.updateAttackButtons(sliderValue);
        }
        if (tank != null) {
            tank.updateAttackButtons(sliderValue);
        }
        if (submarine != null) {
            submarine.updateAttackButtons(sliderValue);
        }
    }
    public void requestDefenceDetails(String defenceType) {
        if (defenceType.equals("Helicopter") && helicopter != null) {
            String soldierCount = helicopter.getSoldierCount();
            String ammoCount = helicopter.getAmmoCount();
            mainController.updateSelectedDefenceDetails(soldierCount, ammoCount);
        } else if (defenceType.equals("Tank") && tank != null) {
            String soldierCount = tank.getSoldierCount();
            String ammoCount = tank.getAmmoCount();
            mainController.updateSelectedDefenceDetails(soldierCount, ammoCount);
        } else if (defenceType.equals("Submarine") && submarine != null) {
            String soldierCount = submarine.getSoldierCount();
            String ammoCount = submarine.getAmmoCount();
            mainController.updateSelectedDefenceDetails(soldierCount, ammoCount);
        }
    }
}
