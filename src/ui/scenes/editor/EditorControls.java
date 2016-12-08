package ui.scenes.editor;

import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ui.builder.UIBuilder;

import java.util.ResourceBundle;

/**
 * @author Robert Steilberg
 */
public class EditorControls {

    private static Parent myRoot;
    private static ResourceBundle myResources;
    private static UIBuilder myBuilder;
    private static ItemMenuUI myItemMenu;

    EditorControls(Parent root, ResourceBundle resources, ItemMenuUI itemMenu) {
        myRoot = root;
        myResources = resources;
        myBuilder = new UIBuilder();
        myItemMenu = itemMenu;
    }



    public void addEditorControls() {

        TabPane sideTabs = new TabPane();

        Tab itemTab = new Tab();
        itemTab.setText("Items");
        itemTab.setClosable(false);
        itemTab.setOnSelectionChanged(e -> {
            if (itemTab.isSelected()) {
                myBuilder.addComponent(myRoot, myItemMenu.getItemPanel());
            } else {
                myBuilder.removeComponent(myRoot, myItemMenu.getItemPanel());
            }
        });

        Tab playerTab = new Tab();
        playerTab.setText("Players");
        playerTab.setClosable(false);

        sideTabs.setSide(Side.LEFT);
        sideTabs.getTabs().addAll(itemTab, playerTab);
        sideTabs.setLayoutY(100);
        sideTabs.setLayoutX(1155);
        sideTabs.setId("control-tabs");
        sideTabs.getSelectionModel().select(playerTab);
//        sideTabs.getSelectionModel().selectedItemProperty().addListener(e -> {
//            handle(sideTabs);
//        });

        myBuilder.addComponent(myRoot, sideTabs);

    }

}