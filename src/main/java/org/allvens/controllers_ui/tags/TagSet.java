package org.allvens.controllers_ui.tags;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TagSet {

    public int TYPE_SET_NORMAL = 1;
    public int TYPE_SET_EDIT = -1;
    private int type;

    private GridPane tag_table;
    private HBox continer;

    private ArrayList<Tag_Value> tags = new ArrayList<>();

    private int col = 0;
    private int rows = 0;
    private int columnMax = 6;

    public TagSet(HBox container, String[] values){
        this.continer = container;
        set_Tags(values);
    }

    public void set_Type(int type) {
        this.type = type;
    }

    public ArrayList<Tag_Value> get_Tags(){
        return tags;
    }

    public String[] get_TagValues(){
        String[] tag_values = new String[tags.size()];
        for(int i = 0; i < tags.size(); i++){
            tag_values[i] = tags.get(i).getTag_value();
        }
        return tag_values;
    }

    public void set_Tags(String[] tag_values){
        tags = new ArrayList<>();
        for(String tag_value: tag_values){
            tags.add(new Tag_Value(tag_value));
        }
    }

    public void create_TagSet(){
        continer.getChildren().clear();

        tag_table = new GridPane();
        tag_table.setHgap(10);
        tag_table.setVgap(10);

        col = 0;
        rows = 0;

        for(Tag_Value tag_value: tags){
            Button tag = add_Functionality(create_TagView(tag_value.getTag_value()), tag_value);

            tag_table.add(tag, col, rows);
            col++;
            if(col == columnMax) {
                rows++;
                col = 0;
            }
        }
        continer.getChildren().add(tag_table);
    }

    public void add_Tag(Tag_Value tag_value){
        Button tag = add_Functionality(create_TagView(tag_value.getTag_value()), tag_value);
        tags.add(tag_value);

        tag_table.add(tag, col, rows);
        col++;
        if(col >= columnMax){
            rows++;
            col = 0;
        }
    }

    private Button create_TagView(String tag_value){
        String subText = "";
        if(type == TYPE_SET_EDIT) subText = "x ";

        Button tag = new Button(subText + tag_value);
        tag.getStyleClass().add("item_view");
        if(type == TYPE_SET_EDIT) tag.getStyleClass().add("item_vie_selectable");

        return tag;
    }

    private Button add_Functionality(Button tag, Tag_Value tag_value){
        if(type == TYPE_SET_EDIT){
            tag.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    tags.remove(tag_value);
                    create_TagSet();
                }
            });
        }
        return tag;
    }
}
