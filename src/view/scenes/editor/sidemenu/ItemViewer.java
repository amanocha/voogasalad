// This entire file is part of my masterpiece.
// Ryan Anders


/*
 * This class gathers and displays the items that the user
 * can place in the editor. I chose this class because it
 * addressed a tough design problem we were facing--as we
 * added more and more items to the editor, the menu was
 * becoming cluttered and disorganized. We also had to
 * revisit how images in the menu were stored once we added
 * functionality for pre-loaded and custom multi-block images.
 * 
 * I decided on using a TreeMap to store the images, due to
 * it's ability to alphabetically sort objects in our menu.
 * I also decided to filter through the large collections of
 * images and only display the ones that we wanted available
 * (i.e. the full item for multi-tile objects, or the "on"
 * state of a switch item). I accomplished this through
 * efficient naming (collaboration with Harshil's image splitter)
 * as well as a nested class that defines a FilenameFilter.
 * 
 * This is a concise, effective, readable solution.
 */

package view.scenes.editor.sidemenu;

import model.block.blocktypes.BlockType;
import view.scenes.editor.objects.GameObject;
import view.scenes.editor.objects.MultipleBlockGameObject;
import view.scenes.editor.objects.SingleBlockGameObject;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Harshil Garg, Ryan Anders
 *         <p>
 *         Used for displaying overworld items.
 */
public class ItemViewer {

    private final String image_root_path = "resources/images/tiles/";
    private final String root_path = "src/" + image_root_path;
    private final String on_string = "ON";
    private final String extension = ".png";

    private GameObject selected = null;


    List<GameObject> getObjects(BlockType type) {
        String directory = root_path + type.name().toLowerCase();
        File file = new File(directory);
        String[] contents = file.list();

        if (type == BlockType.SWITCH_FLOOR) {
            String[] noOnSwitchContents = file.list(new myFilenameFilter());
            contents = noOnSwitchContents;
        }

        List<GameObject> items = new ArrayList<>();
        Map<String, ArrayList<String>> map = new TreeMap<>();
        for (String content : contents) {
            String prefix = content.substring(0, content.indexOf('.'));
            if (map.containsKey(prefix)) {
                map.get(prefix).add(content);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(content);
                map.put(prefix, list);
            }
        }
        for (String key : map.keySet()) {
            ArrayList<String> value = map.get(key);
            if (value.size() == 1)
                items.add(buildSingleItem(key, type));
            else
                items.add(buildMultipleItem(key, value, type));
        }
        return items;
    }

    private GameObject buildSingleItem(String prefix, BlockType type) {
        return new SingleBlockGameObject(getFullURIFileName(prefix, type), type);
    }

    private GameObject buildMultipleItem(String prefix, ArrayList<String> list, BlockType type) {
        int maxRow = 0;
        int maxCol = 0;
        for (String s : list) {
            int firstIndex = s.indexOf('.');
            int lastIndex = s.lastIndexOf('.');
            if (firstIndex == lastIndex) {
                continue;
            }
            String temp = s.substring(firstIndex + 1, lastIndex);
            String[] dimensions = temp.split("_");
            int row = Integer.parseInt(dimensions[0]);
            int col = Integer.parseInt(dimensions[1]);
            if (row > maxRow)
                maxRow = row;
            if (col > maxCol)
                maxCol = col;
        }

        return new MultipleBlockGameObject(getFullURIFileName(prefix, type), type, maxRow, maxCol);

    }

    private String getFullURIFileName(String prefix, BlockType type) {
        File file = new File(root_path + type.name().toLowerCase() + "/" + prefix + extension);
        return file.toURI().toString();
    }


    void select(GameObject obj) {
        selected = obj;
    }


    GameObject getSelected() {
        return selected;
    }

    private class myFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            int len = name.length();
            String onPic = name.substring(len - 6, len - 4);
            return !onPic.equals(on_string);
        }

    }
}
