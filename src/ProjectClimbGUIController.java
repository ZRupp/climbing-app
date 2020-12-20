import javafx.stage.Stage;

public class ProjectClimbGUIController {


    private Stage stage;
    private ProjectClimbGUIView view;

    public ProjectClimbGUIController(Stage stage, ProjectClimbGUIView view){
        this.view = view;
        this.stage = stage;
    }

    public ProjectClimbGUIView getView(){
        return view;
    }

    public Stage getStage(){
        return stage;
    }

}
