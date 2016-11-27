package editor;

import java.util.*;
import java.util.ResourceBundle;
import sun.security.tools.policytool.Resources;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import grid.*;
import ObjectMenuObjects.*;
import ui.builder.*;

public class SidePanel {
    private static final String SIDEPANEL_RESOURCES = "resources/sidepanel";
    private Group myRegion;
    private ResourceBundle myResources;
    private UIBuilder myBuilder;
    private SidePanelMenuObjects handler;

    public SidePanel (Group region) {
        myBuilder = new UIBuilder();
        handler = new SidePanelMenuObjects();
        myRegion = region;
        myResources = Resources.getBundle(SIDEPANEL_RESOURCES);
        initSidePanel();
    }

    private Tab createTab (String label, ScrollPane scrollPane) {
        Tab tab = new Tab();
        tab.setText(myResources.getString(label));
        tab.setContent(scrollPane);
        return tab;
    }
    
   @Deprecated
    private void populatePane(ScrollPane pane, String label){
        List<GameObjects> list;
        if(label.equals("decoration")){
            list = handler.getDecorations();
        }
        else{
            list = handler.getObstacles();
        }
        int buffer = 0;
        for(GameObjects obj: list){
            Node node = myBuilder.addCustomButton(pane, obj.getPath(), buffer, 0, 50);
            System.out.println("here");
            System.out.println(node);
            node.setOnMouseClicked(e->{handler.select(obj);});
            buffer += 50;
        }
    }

    private ScrollPane createScrollPane (String label) {
        VBox content = new VBox();
        List<GameObjects> list;
        if(label.equals("decoration")){
            list = handler.getDecorations();
        }
        else{
            list = handler.getObstacles();
        }
        int buffer = 0;
        for(GameObjects obj: list){
            Node node = myBuilder.addCustomButton(content, obj.getPath(), 20, 0, 50);
            System.out.println("here");
            System.out.println(node);
            node.setOnMouseClicked(e->{handler.select(obj);});
            buffer += 50;
        }
        ScrollPane scrollPane = new ScrollPane(content);
        return scrollPane;
    }

    private void initSidePanel () {
        TabPane tp = new TabPane();
        ScrollPane decPane = createScrollPane("decoration");
        ScrollPane objPane = createScrollPane("obstacles");
        ScrollPane switchPane = createScrollPane("switch");
        ScrollPane npcPane = createScrollPane("npc");
        Tab decTab =
                createTab("decoration", decPane);
        Tab objTab =
                createTab("obstacle", objPane);
        Tab switchTab = createTab("switch", switchPane);
        Tab npcTab = createTab("NPC", npcPane);
        tp.getTabs().addAll(decTab, objTab, switchTab, npcTab);
        tp.setSide(Side.TOP);
        myRegion.getChildren().add(tp);
    }
    
}
