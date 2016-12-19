package utilities.builder;

import javafx.collections.ObservableList;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

/**
 * 
 * CODE MASTERPIECE hg75
 * This class goes hand in hand with the UIBuilder. We can create a new list of properties by simply doing new ComponentProperties(x, y).width(20).height(20).text("You get the idea");
 * This can then be passed into the UIBuilder, and then a subclass of UIBuilder will build the specific object.
 * 
 * @author Harshil Garg, Robert Steilberg
 *         <p>
 *         This class holds information used to build JavaFX ui.scenes.controller.editor.objects.
 */
public class ComponentProperties<E> {

    protected String id;
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected String text;
    protected String path;
    protected boolean preserveRatio;
    protected String font;
    protected int size;
    protected String title;
    protected String header;
    protected String content;
    protected Color color;
    protected double rotate;
    protected ToggleGroup toggleGroup;
    protected boolean selected;
    protected ObservableList<E> options;

    public ComponentProperties() {
    }

    public ComponentProperties(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public ComponentProperties id(String id) {
        this.id = id;
        return this;
    }

    public ComponentProperties width(double width) {
        this.width = width;
        return this;
    }

    public ComponentProperties height(double height) {
        this.height = height;
        return this;
    }

    public ComponentProperties text(String text) {
        this.text = text;
        return this;
    }

    public ComponentProperties path(String path) {
        this.path = path;
        return this;
    }
    
    public ComponentProperties preserveRatio(boolean preserveRatio) {
        this.preserveRatio = preserveRatio;
        return this;
    }

    public ComponentProperties font(String font) {
        this.font = font;
        return this;
    }

    public ComponentProperties size(int size) {
        this.size = size;
        return this;
    }

    public ComponentProperties header(String header) {
        this.header = header;
        return this;
    }

    public ComponentProperties content(String content) {
        this.content = content;
        return this;
    }

    public ComponentProperties color(Color color) {
        this.color = color;
        return this;
    }

    public ComponentProperties rotate(double rotate) {
        this.rotate = rotate;
        return this;
    }

    public ComponentProperties title(String title) {
        this.title = title;
        return this;
    }

    public ComponentProperties toggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
        return this;
    }

    public ComponentProperties selected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public ComponentProperties<E> options(ObservableList<E> options) {
        this.options = options;
        return this;
    }

}
