import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.List;

public class ProjectClimbGUILogController extends ProjectClimbGUIController {

    private Climber climberModel;

    public ProjectClimbGUILogController(Stage stage, ProjectClimbGUILogView view, Climber climberModel){
        super(stage, view);
        this.climberModel = climberModel;
        view.setOnAction(this::processButton);
    }

    private void processButton(ActionEvent e) {

        // make this an instance data variable?
        ProjectClimbGUILogView view = (ProjectClimbGUILogView) getView();

        System.out.println("Hi");

        Climb loggedClimb;

        if (view.getClimbType().equals(ClimbType.OUTDOOR_ROUTE)){
            loggedClimb = ClimbFactory.newClimb(
                    view.getClimbType(),
                    view.getClimbNameField(),
                    view.getFaceType(),
                    (view.isBoulder() ? view.getOfficialBoulderGrade() : view.getOfficialRouteGrade()),
                    (view.isBoulder() ? view.getUserBoulderGrade() : view.getUserRouteGrade()),
                    view.isSent(),
                    view.getAttemptsValue(),
                    (view.isGymClimb() ? view.getColorField() : view.getLocationField()),
                    (view.isGymClimb() ? view.getWallNameField() : view.getRockTypeField()),
                    view.getNumberOfPitchesField()
                    );
            System.out.println(1);
        } else {
            loggedClimb = ClimbFactory.newClimb(
                    view.getClimbType(),
                    view.getClimbNameField(),
                    view.getFaceType(),
                    (view.isBoulder() ? view.getOfficialBoulderGrade() : view.getOfficialRouteGrade()),
                    (view.isBoulder() ? view.getUserBoulderGrade() : view.getUserRouteGrade()),
                    view.isSent(),
                    view.getAttemptsValue(),
                    (view.isGymClimb() ? view.getColorField() : view.getLocationField()),
                    (view.isGymClimb() ? view.getWallNameField() : view.getRockTypeField())
                    );
            System.out.println(2);
            System.out.println(loggedClimb);
        }
        System.out.println(climberModel);
        climberModel.logClimb(loggedClimb);
        climberModel.printClimbs();
        updateGraph();

        //Hmmm.... I need to have this update whenever I click which graph to display.
        if (view.getBoulderChart().isVisible()) {
            view.setAverageGradeText(climberModel.getAvgBoulderGrade());
        } else {
            view.setAverageGradeText(climberModel.getAvgRouteGrade());
        }
    }

    private void updateGraph(){
        ProjectClimbGUILogView view = (ProjectClimbGUILogView) getView();
        List<Climb> climbList = climberModel.getBoulderLog().getClimbList();
        climbList.addAll(climberModel.getRouteLog().getClimbList());

        for (Climb climb : climbList){
            System.out.println(climb);
            if (climb instanceof GymBoulder || climb instanceof OutdoorBoulder){

                view.getBoulderSeries().getData().add(new XYChart.Data<>(view.getOfficialBoulderGrade()
                        .getRepresentation(), climb.getAttempts()));

            } else {
                view.getRouteSeries().getData().add(new XYChart.Data<>(view.getOfficialRouteGrade()
                        .getRepresentation(), climb.getAttempts()));
            }
        }
    }


}
