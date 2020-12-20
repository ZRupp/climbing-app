import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProjectClimbGUILogView extends ProjectClimbGUIView {

    private TextField climbNameField, wallNameField, rockTypeField, locationField, colorField, numberOfPitchesField;
    private ToggleButton boulderToggleButton, routeToggleButton, gymToggleButton, outdoorsToggleButton;
    private Label attemptsLabel, sentLabel;
    private Text averageGradeText;
    private ComboBox<Grade> boulderOfficialGradeComboBox, boulderUserGradeComboBox, routeOfficialGradeComboBox, routeUserGradeComboBox;
    private ComboBox<Climb.Face> faceTypeComboBox;
    private Button logClimbButton;
    private XYChart.Series<String, Number> boulderSeries, routeSeries;
    private BarChart<String, Number> boulderChart, routeChart;




    public ProjectClimbGUILogView(){


        logBox = new GridPane();
        logBox.setBackground(Background.EMPTY);
        logBox.setHgap(50);
        logBox.setGridLinesVisible(false);
        logBox.setVgap(20);
        //logBox.setAlignment(Pos.TOP_CENTER);

        Label logInfoText = new Label("Enter Climb Details:");
        logInfoText.setStyle("-fx-font-size: 15");
        logInfoText.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(logInfoText, HPos.CENTER);

        VBox leftBox = new VBox();
        leftBox.getChildren().add(logInfoText);
        leftBox.setPadding(new Insets(60, 0, 50, 0));
        leftBox.setSpacing(20);
        leftBox.setAlignment(Pos.TOP_CENTER);

        climbNameField = new TextField();
        climbNameField.setPromptText("Climb Name");
        climbNameField.setMaxWidth(175);

        leftBox.getChildren().add(climbNameField);

        ToggleGroup boulderOrRouteGroup = new ToggleGroup();
        boulderToggleButton = new ToggleButton("Boulder");
        routeToggleButton = new ToggleButton("Route");
        boulderToggleButton.setToggleGroup(boulderOrRouteGroup);
        routeToggleButton.setToggleGroup(boulderOrRouteGroup);
        boulderToggleButton.setSelected(true);
        climbNameField = new TextField("Climb Name");
        HBox typeToggleBox = new HBox(boulderToggleButton, routeToggleButton);
        typeToggleBox.setSpacing(20);
        typeToggleBox.setPadding(new Insets(20, 0, 0, 0));
        typeToggleBox.setAlignment(Pos.CENTER);
        leftBox.getChildren().add(typeToggleBox);

        ToggleGroup gymOrOutdoorsGroup = new ToggleGroup();
        gymToggleButton = new ToggleButton("Gym");
        outdoorsToggleButton = new ToggleButton("Outdoors");
        gymToggleButton.setToggleGroup(gymOrOutdoorsGroup);
        outdoorsToggleButton.setToggleGroup(gymOrOutdoorsGroup);
        gymToggleButton.setSelected(true);
        HBox climbPlaceToggleBox = new HBox(gymToggleButton, outdoorsToggleButton);
        climbPlaceToggleBox.setSpacing(20);
        climbPlaceToggleBox.setAlignment(Pos.CENTER);
        leftBox.getChildren().add(climbPlaceToggleBox);

        wallNameField = new TextField();
        wallNameField.setPromptText("Wall Name");
        colorField = new TextField();
        colorField.setPromptText("Hold Color");
        HBox gymTextFieldBox = new HBox(wallNameField, colorField);
        gymTextFieldBox.setPadding(new Insets(10, 10, 10, 10));
        gymTextFieldBox.setSpacing(10);
        gymTextFieldBox.managedProperty().bind(gymTextFieldBox.visibleProperty());
        leftBox.getChildren().add(gymTextFieldBox);

        rockTypeField = new TextField();
        rockTypeField.setPromptText("Rock Type");
        rockTypeField.setMaxWidth(100);
        locationField = new TextField();
        locationField.setPromptText("Location");
        locationField.setMaxWidth(160);
        numberOfPitchesField = new TextField();
        numberOfPitchesField.setPromptText("Pitches");
        numberOfPitchesField.setMaxWidth(60);
        HBox outdoorTextFieldBox = new HBox(rockTypeField, locationField, numberOfPitchesField);
        outdoorTextFieldBox.setPadding(new Insets(10, 10, 10, 10));
        outdoorTextFieldBox.setSpacing(10);
        outdoorTextFieldBox.setVisible(false);
        outdoorTextFieldBox.managedProperty().bind(outdoorTextFieldBox.visibleProperty());
        leftBox.getChildren().add(outdoorTextFieldBox);

        sentLabel = new Label("Sent");
        sentLabel.setGraphic(new RadioButton());
        sentLabel.setContentDisplay(ContentDisplay.BOTTOM);
        attemptsLabel = new Label("Attempts:");
        attemptsLabel.setGraphic(new Spinner<Integer>(1, 10, 1));
        attemptsLabel.setContentDisplay(ContentDisplay.BOTTOM);

        HBox attemptsInfoBox = new HBox(sentLabel, attemptsLabel);
        attemptsInfoBox.setSpacing(20);
        attemptsInfoBox.setAlignment(Pos.CENTER);
        leftBox.getChildren().add(attemptsInfoBox);

        ObservableList<Grade> boulderComboBoxOptions = populateComboBoxOptions('V');
        boulderOfficialGradeComboBox = new ComboBox<Grade>(boulderComboBoxOptions);
        boulderOfficialGradeComboBox.setValue(Grade.V0);
        Label boulderOfficialGradeLabel = new Label("Official Grade:");
        boulderOfficialGradeLabel.setGraphic(boulderOfficialGradeComboBox);
        boulderOfficialGradeLabel.setContentDisplay(ContentDisplay.BOTTOM);
        boulderOfficialGradeLabel.managedProperty().bind(boulderOfficialGradeLabel.visibleProperty());

        boulderUserGradeComboBox = new ComboBox<Grade>(boulderComboBoxOptions);
        boulderUserGradeComboBox.setValue(Grade.V0);
        Label boulderUserGradeLabel = new Label("Your Grade:");
        boulderUserGradeLabel.setGraphic(boulderUserGradeComboBox);
        boulderUserGradeLabel.setContentDisplay(ContentDisplay.BOTTOM);
        boulderUserGradeLabel.managedProperty().bind(boulderUserGradeLabel.visibleProperty());

        ObservableList<Grade> routeComboBoxOptions = populateComboBoxOptions('5');
        //I need to rework this so that the combobox cell contains the string values of my enums.
        routeOfficialGradeComboBox = new ComboBox<Grade>(routeComboBoxOptions);
        routeOfficialGradeComboBox.setValue(Grade.YDS6);
        Label routeOfficialGradeLabel = new Label("Official Grade:");
        routeOfficialGradeLabel.setGraphic(routeOfficialGradeComboBox);
        routeOfficialGradeLabel.setContentDisplay(ContentDisplay.BOTTOM);
        routeOfficialGradeLabel.setVisible(false);
        routeOfficialGradeLabel.managedProperty().bind(routeOfficialGradeLabel.visibleProperty());


        routeUserGradeComboBox = new ComboBox<Grade>(routeComboBoxOptions);
        routeUserGradeComboBox.setValue(Grade.YDS6);
        Label routeUserGradeLabel = new Label("Your Grade:");
        routeUserGradeLabel.setGraphic(routeUserGradeComboBox);
        routeUserGradeLabel.setContentDisplay(ContentDisplay.BOTTOM);
        routeUserGradeLabel.setVisible(false);
        routeUserGradeLabel.managedProperty().bind(routeUserGradeLabel.visibleProperty());

        HBox gradeLabelsBox = new HBox(boulderOfficialGradeLabel, boulderUserGradeLabel, routeOfficialGradeLabel,
                routeUserGradeLabel);
        gradeLabelsBox.setSpacing(20);
        VBox gradeBox = new VBox(gradeLabelsBox);

        // Not sure if I should do this here or with the controller?
        boulderOrRouteGroup.selectedToggleProperty().addListener((curTog, oldTog, newTog) -> {
            if (newTog == null){
                oldTog.setSelected(true);
            } else if (newTog.equals(routeToggleButton)){
                boulderOfficialGradeLabel.setVisible(false);
                boulderUserGradeLabel.setVisible(false);
                routeOfficialGradeLabel.setVisible(true);
                routeUserGradeLabel.setVisible(true);
            } else if (newTog.equals(boulderToggleButton)){
                boulderOfficialGradeLabel.setVisible(true);
                boulderUserGradeLabel.setVisible(true);
                routeOfficialGradeLabel.setVisible(false);
                routeUserGradeLabel.setVisible(false);
            }
        });

        gymOrOutdoorsGroup.selectedToggleProperty().addListener((curTog, oldTog, newTog) -> {
            if (newTog == null){
                oldTog.setSelected(true);
            } else if (newTog.equals(outdoorsToggleButton)){
                gymTextFieldBox.setVisible(false);
                outdoorTextFieldBox.setVisible(true);
            } else if (newTog.equals(gymToggleButton)){
                gymTextFieldBox.setVisible(true);
                outdoorTextFieldBox.setVisible(false);
            }
        });

        ObservableList<Climb.Face> faceTypeOptions = FXCollections.observableArrayList((Arrays.asList(Climb.Face.values())));
        faceTypeComboBox = new ComboBox<Climb.Face>(faceTypeOptions);
        faceTypeComboBox.setValue(Climb.Face.OVERHANG);
        Label faceTypeLabel = new Label("Face Type:");
        faceTypeLabel.setGraphic(faceTypeComboBox);
        faceTypeLabel.setContentDisplay(ContentDisplay.BOTTOM);

        HBox comboBoxesBox = new HBox(faceTypeLabel, gradeBox);
        comboBoxesBox.setAlignment(Pos.CENTER);
        comboBoxesBox.setSpacing(20);
        leftBox.getChildren().add(comboBoxesBox);

        logClimbButton = new Button("Log Climb");
        leftBox.getChildren().add(logClimbButton);

        VBox rightBox = new VBox();

        rightBox.setPadding(new Insets(50, 0, 50, 0));
        rightBox.setSpacing(20);
        rightBox.setAlignment(Pos.TOP_CENTER);

        // Eventually I want this to be able to display time series data, but for now this will do.
        CategoryAxis boulderChartXAxis = new CategoryAxis();
        NumberAxis boulderChartYAxis = new NumberAxis();
        boulderChart = new BarChart<String, Number>(boulderChartXAxis, boulderChartYAxis);
        boulderChart.setTitle("Boulders Climbed");
        boulderChart.setLegendVisible(false);
        boulderChartXAxis.setLabel("Grade");
        boulderChartYAxis.setLabel("Climbs");
        boulderChartYAxis.setTickUnit(1);
        boulderChartYAxis.setMinorTickVisible(false);
        boulderChartXAxis.setCategories(populateXAxisCategories('V'));
        boulderSeries = new XYChart.Series<String, Number>();
        boulderChart.getData().add(boulderSeries);
        boulderChart.managedProperty().bind(boulderChart.visibleProperty());
        rightBox.getChildren().add(boulderChart);

        CategoryAxis routeChartXAxis = new CategoryAxis();
        NumberAxis routeChartYAxis = new NumberAxis();
        routeChart = new BarChart<String, Number>(routeChartXAxis, routeChartYAxis);
        routeChart.setTitle("Routes Climbed");
        routeChart.setLegendVisible(false);
        routeChartXAxis.setLabel("Grade");
        routeChartYAxis.setLabel("Climbs");
        routeChartYAxis.setTickUnit(1);
        routeChartYAxis.setMinorTickVisible(false);
        routeChartXAxis.setTickLabelRotation(90);
        routeChartXAxis.setCategories(populateXAxisCategories('5'));
        routeSeries = new XYChart.Series<String, Number>();
        routeChart.getData().add(routeSeries);
        routeChart.setVisible(false);
        routeChart.managedProperty().bind(routeChart.visibleProperty());

        HBox chartsBox = new HBox(boulderChart, routeChart);

        ToggleGroup chartToggleGroup = new ToggleGroup();
        ToggleButton boulderChartToggle = new ToggleButton("Boulder Chart");
        ToggleButton routeChartToggle = new ToggleButton("Route Chart");
        boulderChartToggle.setSelected(true);
        boulderChartToggle.setToggleGroup(chartToggleGroup);
        routeChartToggle.setToggleGroup(chartToggleGroup);

        chartToggleGroup.selectedToggleProperty().addListener((curTog, oldTog, newTog) -> {
            if (newTog == null){
                oldTog.setSelected(true);
            } else if (newTog.equals(routeChartToggle)){
                boulderChart.setVisible(false);
                routeChart.setVisible(true);
            } else if (newTog.equals(boulderChartToggle)){
                boulderChart.setVisible(true);
                routeChart.setVisible(false);
            }
        });

        HBox chartToggleBox = new HBox(boulderChartToggle, routeChartToggle);
        chartToggleBox.setAlignment(Pos.CENTER);
        chartToggleBox.setSpacing(20);
        rightBox.getChildren().addAll(chartsBox, chartToggleBox);

        Label averageGradeLabel = new Label("Average Grade: ");
        averageGradeText = new Text();
        averageGradeText.getStyleClass().add("view-text");
        HBox averageGradeBox = new HBox(averageGradeLabel, averageGradeText);
        averageGradeBox.setAlignment(Pos.CENTER);
        rightBox.getChildren().add(averageGradeBox);

        logBox.add(leftBox, 0, 0);
        logBox.add(rightBox, 1, 0);






    }

    public void setAverageGradeText(String s){
        averageGradeText.setText(s);
    }

    public ClimbType getClimbType(){
        if (isBoulder()){
            if (isGymClimb()){
                return ClimbType.GYM_BOULDER;
            } else {
                return ClimbType.OUTDOOR_BOULDER;
            }
        } else {
            if (isGymClimb()){
                return ClimbType.GYM_ROUTE;
            } else {
                return ClimbType.OUTDOOR_ROUTE;
            }
        }
    }

    public boolean isGymClimb(){
        return gymToggleButton.isSelected();
    }

    public boolean isOutdoorClimb(){
        return outdoorsToggleButton.isSelected();
    }

    public boolean isBoulder(){
        return boulderToggleButton.isSelected();
    }

    public boolean isRoute(){
        return routeToggleButton.isSelected();
    }

    public Text getAverageGradeText(){
        return averageGradeText;
    }

    public String getClimbNameField(){
        return climbNameField.getText();
    }

    public String getWallNameField(){
        return wallNameField.getText();
    }

    public String getRockTypeField(){
        return rockTypeField.getText();
    }

    public String getLocationField(){
        return locationField.getText();
    }

    public String getColorField(){
        return colorField.getText();
    }

    public int getNumberOfPitchesField(){
        try {
            return Integer.parseInt(numberOfPitchesField.getText());
        } catch (NumberFormatException e){
            System.out.println("Invalid number of pitches.");
        }
        return 0;
    }

    public int getAttemptsValue(){
        return ((Spinner<Integer>) attemptsLabel.getGraphic()).getValue();
    }

    public boolean isSent(){
        return ((RadioButton) sentLabel.getGraphic()).isSelected();
    }

    public Grade getOfficialBoulderGrade(){
        return boulderOfficialGradeComboBox.getValue();
    }

    public Grade getUserBoulderGrade(){
        return boulderUserGradeComboBox.getValue();
    }

    public Grade getOfficialRouteGrade(){
        return routeOfficialGradeComboBox.getValue();
    }

    public Grade getUserRouteGrade(){
        return routeUserGradeComboBox.getValue();
    }

    public Climb.Face getFaceType(){
        return faceTypeComboBox.getValue();
    }

    public BarChart<String, Number> getBoulderChart(){
        return boulderChart;
    }

    public XYChart.Series<String, Number> getBoulderSeries(){
        return boulderSeries;
    }

    public XYChart.Series<String, Number> getRouteSeries(){
        return routeSeries;
    }

    public Parent getParent(){
        return logBox;
    }

    public void setOnAction(EventHandler<ActionEvent> handler){
        logClimbButton.setOnAction(handler);
    }

    private ObservableList<String> populateXAxisCategories(char selector){
        List<String> gradeList = new ArrayList<>();
        for (Grade grade: Grade.values()){
            if (grade.getRepresentation().charAt(0) == selector){
                gradeList.add(grade.getRepresentation());
            }
        }
        return FXCollections.observableArrayList(gradeList);
    }

    private ObservableList<Grade> populateComboBoxOptions(char selector){
        List<Grade> gradeList = new ArrayList<>();
        for (Grade grade: Grade.values()){
            if (grade.getRepresentation().charAt(0) == selector){
                gradeList.add(grade);
            }
        }

        return FXCollections.observableArrayList(gradeList);

    }
}
