import javafx.animation.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.util.Duration;

public class ProjectClimbGUIWelcomeController extends ProjectClimbGUIController {

    private Climber climberModel;

    public ProjectClimbGUIWelcomeController(Stage stage, ProjectClimbGUIWelcomeView view) {
        super(stage, view);

        view.setOnAction(this::processButton);

    }

    private void processButton(ActionEvent e){

        // Make this an instance data variable?
        ProjectClimbGUIWelcomeView view = (ProjectClimbGUIWelcomeView) getView();

        if (view.getFirstNameField().isBlank() || view.getLastNameField().isBlank() || view.getAgeField() < 6){
            if (!view.getErrorBoxVisibility()) {
                view.setErrorBoxVisible();
            }
        } else {
            if (view.getErrorBoxVisibility()) {
                view.setErrorBoxVisible();
            }
            climberModel =new Climber.ClimberBuilder(view.getFirstNameField(),
                    view.getLastNameField(), view.getAgeField())
                    .build();
            climberModel.setWeightInPounds(view.getWeightInPoundsField());
            climberModel.setBodyFatPercentage(view.getBodyFatPercentageField());

            fadeOut();

        }

    }

    private void fadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(getView().getParent().translateXProperty(), -(getStage().getScene().getWidth()/2), Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
        tl.getKeyFrames().add(kf);
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(getView().getParent());
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(this::changeScene);
        tl.play();
        fadeTransition.play();

    }

    private void changeScene(ActionEvent event){
        ProjectClimbGUIController controller = new ProjectClimbGUILogController(getStage(), new ProjectClimbGUILogView(), climberModel);
        getStage().setScene(new Scene(controller.getView().getParent(), 900, 550, Color.web("#353F52")));
        getStage().getScene().getStylesheets().add("style.css");

    }

}
