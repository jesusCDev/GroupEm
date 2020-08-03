package org.example.controllers_ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Project_Info_Editor_UI_Manager {

    private ArrayList<String> alTechnologiesUsed = new ArrayList<>();
    private ArrayList<String> alProducts = new ArrayList<>();
    private GridPane gpDevices;
    private GridPane gpTech;

    public ArrayList<String> get_DevicesList(){
        return alProducts;
    }

    public ArrayList<String> get_TechnologiessList(){
        return alTechnologiesUsed;
    }

    public GridPane createCheckBox(HBox hbContainer, String[] list, String techOrProduct){
        int col = 0;
        int row = 0;
        int colMax = 6;

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        for(String item: list){
            CheckBox cb = new CheckBox(item);
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
                    if(techOrProduct.equals("Tech")){
                        if(new_val){
                            alTechnologiesUsed.add(cb.getText());
                        }else{
                            for(int i = 0; i < alTechnologiesUsed.size(); i++){
                                if(alTechnologiesUsed.get(i).equals(cb.getText())){
                                    alTechnologiesUsed.remove(alTechnologiesUsed.get(i));
                                    break;
                                }
                            }
                        }
                    }else{
                        if(new_val){
                            alProducts.add(cb.getText());
                        }else{
                            for(int i = 0; i < alProducts.size(); i++){
                                if(alProducts.get(i).equals(cb.getText())){
                                    alProducts.remove(alProducts.get(i));
                                    break;
                                }
                            }
                        }
                    }
                }
            });
            gp.add(cb, col, row);
            col++;
            if(col == colMax){
                col = 0;
                row++;
            }
        }

        hbContainer.getChildren().add(gp);
        return gp;
    }

    public GridPane getGpDevices() {
        return gpDevices;
    }

    public void setGpDevices(GridPane gpDevices) {
        this.gpDevices = gpDevices;
    }

    public GridPane getGpTech() {
        return gpTech;
    }

    public void setGpTech(GridPane gpTech) {
        this.gpTech = gpTech;
    }
}
