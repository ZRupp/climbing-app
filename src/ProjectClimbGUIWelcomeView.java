import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ProjectClimbGUIWelcomeView extends ProjectClimbGUIView {

    private TextField firstNameField, lastNameField, ageField, weightInPoundsField, bodyFatPercentageField;
    private Button continueButton;
    private HBox errorBox;


    public ProjectClimbGUIWelcomeView() {

        welcomeBox = new VBox();
        welcomeBox.setBackground(Background.EMPTY);
        welcomeBox.setSpacing(50);
        Image icon = new Image("icon.png");
        ImageView iconView = new ImageView(icon);
        VBox iconBox = new VBox(iconView);
        iconBox.setAlignment(Pos.TOP_CENTER);
        iconBox.setPadding(new Insets(60,10,25,10));
        welcomeBox.getChildren().add(iconBox);

        VBox userFieldsBox = new VBox();
        userFieldsBox.setAlignment(Pos.CENTER);
        userFieldsBox.setSpacing(10);
        Text requiredFieldsText = new Text("Required:");
        requiredFieldsText.setFill(Color.web("#F86400"));
        userFieldsBox.getChildren().add(requiredFieldsText);
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        ageField = new TextField();
        ageField.setPromptText("Age");
        HBox nameAgeBox = new HBox(firstNameField, lastNameField, ageField);
        nameAgeBox.setAlignment(Pos.CENTER);
        nameAgeBox.setSpacing(12);
        nameAgeBox.setPadding(new Insets(0, 0, 50, 0));
        userFieldsBox.getChildren().add(nameAgeBox);

        Text optionalFieldsText = new Text("Optional:");
        optionalFieldsText.setFill(Color.web("#F86400"));
        userFieldsBox.getChildren().add(optionalFieldsText);
        weightInPoundsField = new TextField();
        weightInPoundsField.setPromptText("Weight (lbs)");
        bodyFatPercentageField = new TextField();
        bodyFatPercentageField.setPromptText("Body Fat %");
        HBox bodyCompBox = new HBox(weightInPoundsField, bodyFatPercentageField);
        bodyCompBox.setAlignment(Pos.CENTER);
        bodyCompBox.setSpacing(12);
        userFieldsBox.getChildren().add(bodyCompBox);
        welcomeBox.getChildren().add(userFieldsBox);

        Label errorText = new Label("All required fields must be filled!");
        errorText.setTextFill(Color.DARKSALMON);
        errorText.setAlignment(Pos.CENTER);
        errorBox = new HBox(errorText);
        errorBox.setVisible(false);
        errorBox.setPadding(new Insets(-10, 0, -10, 0));
        errorBox.setAlignment(Pos.CENTER);
        welcomeBox.getChildren().add(errorBox);

        continueButton = new Button("Start Logging");
        HBox buttonBox = new HBox(continueButton);
        buttonBox.setAlignment(Pos.CENTER);
        welcomeBox.getChildren().add(buttonBox);



    }

    public Parent getParent(){
        return welcomeBox;
    }

    public String getFirstNameField() {
        return firstNameField.getText();
    }

    public String getLastNameField() {
        return lastNameField.getText();
    }

    public int getAgeField() {
        try {
            return Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e){
            System.out.println("Invalid age.");
            return -1;
        }

    }

    public double getWeightInPoundsField(){
        try {
            return Double.parseDouble(weightInPoundsField.getText());
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public double getBodyFatPercentageField(){
        try {
            return Double.parseDouble(bodyFatPercentageField.getText());
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public void setFirstNameField(String text) {
        firstNameField.setText(text);
    }

    public void setLastNameField(String text) {
        lastNameField.setText(text);
    }

    public void setAgeField(String text) {
        ageField.setText(text);
    }

    public void setOnAction(EventHandler<ActionEvent> handler) {
        continueButton.setOnAction(handler);
    }

    public boolean getErrorBoxVisibility(){
        return errorBox.isVisible();
    }

    public void setErrorBoxVisible(){
        if (errorBox.isVisible()){
            errorBox.setVisible(false);
        } else {
            errorBox.setVisible(true);
        }


    }
}
