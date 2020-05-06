package sample;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.FileWriter;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        RSA rsa = new RSA();
        String RSASetup = rsa.getInfoString();
        System.out.println(RSASetup);


        try {
            FileWriter fileWriter = new FileWriter("RSA_setup.txt");
            fileWriter.write(RSASetup);
            fileWriter.close();
        } catch (Exception ex) {
        }

        AdvancedTextarea plainTextarea = new AdvancedTextarea("Plaintext", 365, "input", 500, primaryStage, true);
        AdvancedTextarea encryptionTextarea = new AdvancedTextarea("Ciphertext", 362,  "output", 500, primaryStage, false);
        encryptionTextarea.textArea.setWrapText(true);

        AdvancedTextarea cipherTextarea = new AdvancedTextarea("Ciphertext", 354, "input", 500, primaryStage, true);
        AdvancedTextarea decryptionTextarea = new AdvancedTextarea("Plaintext", 372,  "output", 500, primaryStage, false);
        cipherTextarea.textArea.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(50);
        gridPane.setHgap(30);

        Button encrypt_btn = new Button("Encrypt →");
        gridPane.add(plainTextarea.gridPane, 0, 0);
        gridPane.add(encrypt_btn, 1, 0);
        gridPane.add(encryptionTextarea.gridPane, 2, 0);

        Button decrypt_btn = new Button("Decrypt →");
        gridPane.add(cipherTextarea.gridPane, 0, 2);
        gridPane.add(decrypt_btn, 1, 2);
        gridPane.add(decryptionTextarea.gridPane, 2, 2);

        encrypt_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                encryptionTextarea.textArea.setText(rsa.encrypt(plainTextarea.textArea.getText()));
            }
        });


        decrypt_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    decryptionTextarea.textArea.setText(rsa.decrypt(cipherTextarea.textArea.getText()));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid number!");
                    alert.showAndWait();
                }
            }
        });

        VBox vBox = new VBox(gridPane);
        Scene scene = new Scene(vBox);
        primaryStage.setTitle("RSA"); //Setting title to the Stage
        primaryStage.setScene(scene); //Adding scene to the stage2
        primaryStage.show(); //Displaying the contents of the stage
    }
}