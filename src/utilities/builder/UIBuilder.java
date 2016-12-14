package utilities.builder;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * @author Harshil Garg, Robert Steilberg, Aninda Manocha, Nisakorn Valyasevi
 *         <p>
 *         This class is used to build JavaFX ui.scenes.controller.editor.objects and add them to the stage.
 */
public class UIBuilder<E> {
    private ComponentBuilder alertBuilder;
    private ComponentBuilder buttonBuilder;
    private ComponentBuilder imageViewBuilder;
    private ComponentBuilder labelBuilder;
    private ComponentBuilder textFieldBuilder;
    private ComponentBuilder radioButtonBuilder;
    private ComponentBuilder comboBoxBuilder;
    private ComponentBuilder dialogBubbleBuilder;

    public UIBuilder() {
        alertBuilder = new AlertBuilder();
        buttonBuilder = new ButtonBuilder();
        imageViewBuilder = new ImageViewBuilder();
        labelBuilder = new LabelBuilder();
        textFieldBuilder = new TextFieldBuilder();
        radioButtonBuilder = new RadioButtonBuilder();
        comboBoxBuilder = new ComboBoxBuilder<E>();
        dialogBubbleBuilder = new DialogBubbleBuilder();
    }

    /**
     * Create a new group that can serve as a region holding other nodes
     *
     * @param layoutX is the x coordinate of the region
     * @param layoutY is the y coordinate of the region
     * @return the new region as a Group
     */
    public Pane addRegion(int layoutX, int layoutY) {
        Pane region = new Pane();
        region.setLayoutX(layoutX);
        region.setLayoutY(layoutY);
        return region;
    }

    /**
     * Adds a JavaFX node to the specified Group or Pane
     *
     * @param layout    is the Group or Pane to which the node will be added
     * @param component is the JavaFX node to be added
     * @return the added node
     */
    public Node addComponent(Parent layout, Node component) {
        if (layout instanceof Pane) {
            Pane pane = (Pane) layout;
            pane.getChildren().add(component);
        } else if (layout instanceof Group) {
            Group pane = (Group) layout;
            pane.getChildren().add(component);
        } else if (layout instanceof VBox) {
            VBox pane = (VBox) layout;
            pane.getChildren().add(component);
        } else {
            return null;
        }
        return component;
    }

    /**
     * Removes a JavaFX node from the specified Group or Pane
     *
     * @param layout    is the Group or Pane from which the node will be removed
     * @param component is the JavaFX node to be removed
     * @return the removed node
     */
    public Node removeComponent(Parent layout, Node component) {
        if (layout instanceof Pane) {
            Pane pane = (Pane) layout;
            pane.getChildren().remove(component);
        } else if (layout instanceof Group) {
            Group pane = (Group) layout;
            pane.getChildren().remove(component);
        } else {
            return null;
        }
        return component;
    }

    /**
     * Create a new JavaFX Button and adds it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the Button will be added
     * @param properties the ComponentProperties object containing information
     *                   about the Button
     * @return the newly added Button as a node
     */
    public Node addNewButton(Parent layout, ComponentProperties properties) {
        return addComponent(layout, buttonBuilder.createComponent(properties));
    }


    /**
     * Creates a new JavaFX ImageView and adds it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the ImageView will be added
     * @param properties the ComponentProperties object containing information
     *                   about the ImageView
     * @return the newly added ImageView as a node
     */
    public Node addNewImageView(Parent layout, ComponentProperties properties) {
        return addComponent(layout, imageViewBuilder.createComponent(properties));
    }

    /**
     * Creates a new JavaFX ImageView, sets its position, image myIconPath, width, and CSS id,
     * and adds it to the given Group or Pane
     *
     * @param layout is the Group or Pane to which the ImageView is added
     * @param xPos   is the X position of the ImageView
     * @param yPos   is the Y position of the ImageView
     * @param path   is the image myIconPath
     * @param width  is the width of the ImageView
     * @param id     is the CSS ID
     * @return
     */
    public Node addCustomImageView(Parent layout, int xPos, int yPos, String path, int width, String id) {
        return addNewImageView(layout, new ComponentProperties(xPos, yPos)
                .path(path)
                .preserveRatio(true)
                .width(width)
                .id(id));
    }

    /**
     * Create a new JavaFX Label and adds it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the Label will be added
     * @param properties the ComponentProperties object containing information
     *                   about the Label
     * @return the newly added Label as a node
     */
    public Node addNewLabel(Parent layout, ComponentProperties properties) {
        return addComponent(layout, labelBuilder.createComponent(properties));
    }

    /**
     * Create a customized JavaFX Label and add it to the given Group or Pane
     *
     * @param layout the Group or Pane to which the Label will be added
     * @param text   Text to be set in properties
     * @param x      Sets the X for properties
     * @param y      Sets the Y for properties
     * @return
     */
    public Node addCustomLabel(Parent layout, String text, int x, int y, String font, Color color, int size) {
        return addNewLabel(layout, new ComponentProperties(x, y)
                .text(text)
                .color(color)
                .font(font)
                .size(size));
    }

    /**
     * Create a new JavaFX text field and add it to the given Group or Pane
     *
     * @param layout     the Group or Pane to which the Label will be added
     * @param properties the ComponentProperties object containing information about the text field
     * @return
     */
    public Node addNewTextField(Parent layout, ComponentProperties properties) {
        return addComponent(layout, textFieldBuilder.createComponent(properties));
    }

    public Node addNewAlert(String header, String content) {
        return alertBuilder.createComponent(new ComponentProperties()
                .header(header)
                .content(content));
    }

    public Node addCustomAlert(ComponentProperties properties) {
        return alertBuilder.createComponent(new ComponentProperties()
                .header(properties.header)
                .content(properties.content));
    }

    public Node addNewRadioButton(Parent layout, ComponentProperties properties) {
        return addComponent(layout, radioButtonBuilder.createComponent(properties));
    }

    public Node addNewComboBox(Parent layout, ComponentProperties properties) {
        return addComponent(layout, comboBoxBuilder.createComponent(properties));
    }

    /**
     * Add dialog box to layout, must set params in properties for layout X & Y coordinates,
     * text string to be displayed, and height and width of bubble
     */
    public Node addNewDialogBubble(Parent layout, Stage stage, String message) {
        ComponentProperties properties = new ComponentProperties();
        properties.height(100);
        properties.width(600);
        properties.text(message);
        Node dialogNode = dialogBubbleBuilder.createComponent(properties);
        stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if ((keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.DOWN ||
                    keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT)
                    && layout.getChildrenUnmodifiable().contains(dialogNode)) {
                removeComponent(layout, dialogNode);
            }
        });
        addComponent(layout, dialogNode);
        dialogNode.setLayoutX(50);
        dialogNode.setLayoutY(550);
        return dialogNode;
    }

    /**
     * Initializes a JavaFX window with the specified stage and parameters given
     * in a properties file
     *
     * @param currStage          the JavaFX stage with which the window is created
     * @param propertiesFilePath the myIconPath to the properties file containing the
     *                           window's parameters
     */
    public void initWindow(Stage currStage, String propertiesFilePath) {
        ResourceBundle resources = ResourceBundle.getBundle(propertiesFilePath);
        currStage.setHeight(Integer.parseInt(resources.getString("windowHeight")));
        currStage.setWidth(Integer.parseInt(resources.getString("windowWidth")));
        currStage.centerOnScreen();
        currStage.show();
    }
}