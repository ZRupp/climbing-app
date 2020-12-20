import javafx.scene.Parent;
import javafx.scene.layout.*;

public abstract class ProjectClimbGUIView {
    protected VBox welcomeBox;
    protected GridPane logBox;

    abstract Parent getParent();
}
