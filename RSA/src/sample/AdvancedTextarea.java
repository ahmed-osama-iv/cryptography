package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Scanner;

class AdvancedTextarea {

    File file;
    String text;
    GridPane gridPane = new GridPane();
    GridPane subGridPane = new GridPane();
    TextArea textArea = new TextArea();


    AdvancedTextarea(String title, int space, String type, int width, Stage primaryStage, boolean editable) {
        gridPane.setPadding(new Insets(1, 1, 1, 1));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Label label = new Label(title);
        Pane spacer = new Pane();
        spacer.setMinSize(space, 1);
        subGridPane.add(label, 0, 0);
        subGridPane.add(spacer, 1, 0);

        if(type == "input") {
            Button read_hex_btn = new Button("Open file");
            read_hex_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FileChooser fileChooser = new FileChooser();
                    file = fileChooser.showOpenDialog(primaryStage);
                    try {
                        Scanner scanner = new Scanner(file);
                        textArea.clear();
                        while(scanner.hasNext()) {
                            String line =  scanner.nextLine();
                            if(textArea.getText().length() != 0) {
                                textArea.appendText("\n");
                            }
                            textArea.appendText(line);
                        }
                    } catch (Exception ex) {
                    }
                }
            });

            subGridPane.add(read_hex_btn, 3, 0);
        } else if(type == "output") {
            Button save_btn = new Button("Save as");
            save_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FileChooser fileChooser = new FileChooser();
                    File savedFile = fileChooser.showSaveDialog(primaryStage);
                    try {
                        FileWriter fileWriter = new FileWriter(savedFile.toPath().toString());
                        fileWriter.write(textArea.getText());
                        fileWriter.close();
                    } catch (Exception ex) {
                    }
                }
            });
            subGridPane.add(save_btn, 3, 0);
        } else {

        }

        textArea.setPromptText(title);
        textArea.setEditable(editable);
        gridPane.add(subGridPane, 0, 0);
        gridPane.add(textArea, 0, 1);
        subGridPane.setMinWidth(width);
        subGridPane.setMaxWidth(width);
        gridPane.setMinWidth(width);
        gridPane.setMaxWidth(width);

    }

    GridPane getGridPane() {
        return gridPane;
    }
}