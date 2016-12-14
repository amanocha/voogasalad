package ui.scenes.editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Deprecated
public class PopUp {
    private Stage stage = new Stage();
    private Group group = new Group();
    private Scene scene = new Scene(group, 100, 100);
    private boolean result;
    private String text;

    public PopUp(String text) {
        this.text = text;
        initStage();
        initWindow();
    }

    private void initStage() {
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(100);
    }

    private void initWindow() {
        Label label = new Label(text);

        Button cancelButton = new Button("Cancel");
        cancelButton.setLayoutX(0);
        cancelButton.setLayoutY(20);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                result = false;
                stage.close();
            }
        });

        Button okayButton = new Button("Okay");
        okayButton.setLayoutX(60);
        okayButton.setLayoutY(20);

        okayButton.setOnAction(event -> {
            result = true;
            stage.close();
        });

        group.getChildren().addAll(label, cancelButton, okayButton);
    }

    public boolean getResult() {
        return result;
    }

    public void show() {
        stage.setScene(scene);
        stage.show();
    }

}
