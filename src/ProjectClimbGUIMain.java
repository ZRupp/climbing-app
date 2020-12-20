import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProjectClimbGUIMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ProjectClimbGUIController controller = new ProjectClimbGUIWelcomeController(primaryStage, new ProjectClimbGUIWelcomeView());
        Scene landingPage = new Scene(controller.getView().getParent(), 900, 550, Color.web("#353F52"));
        landingPage.getStylesheets().add("style.css");

        primaryStage.setTitle("Project Climb");
        primaryStage.setScene(landingPage);
        primaryStage.setResizable(false);
        primaryStage.show();
        landingPage.getRoot().requestFocus();
    }
}
