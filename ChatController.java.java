public class ChatController {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void sendMessageToMainController(String message) {
        if (mainController != null) {
            mainController.updateInfoTextArea(message);
        }
    }
}
