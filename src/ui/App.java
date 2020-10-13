package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Caesercijfer;
import model.Spiegeling;
import model.Versleuteling;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Versleuteling versleuteling = new Versleuteling(new Caesercijfer());

        // Creer het venster
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        // Voeg tekst toe
        Text scenetitle = new Text("Codeer of decodeer jouw boodschap.");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        // Voeg tekst input toe voor boodschap
        Label boodschap = new Label("Uw boodschap:");
        grid.add(boodschap, 0, 1);

        TextField boodschapVeld = new TextField();
        grid.add(boodschapVeld, 0, 2);

        Label result = new Label("Uw resultaat:");
        grid.add(result, 1, 1);

        TextField resultVeld = new TextField();
        resultVeld.setDisable(true);
        grid.add(resultVeld, 1, 2);

        // Voeg combobox toe voor mogelijke versleutelingen
        ObservableList<String> versleutelingen = FXCollections.observableArrayList(
                "Caesercijfer",
                "Spiegeling"
        );

        final ComboBox comboBox = new ComboBox(versleutelingen);
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            // Pas de versleuteling aan wanneer de combobox veranderd en geef die mee aan versleuteling
            @Override
            public void handle(ActionEvent event) {
                String versleutelingNaam = (String) comboBox.getValue();
                if(versleutelingNaam.equals("Caesercijfer")) {
                    versleuteling.setVersleutelGedrag(new Caesercijfer());
                } else if(versleutelingNaam.equals("Spiegeling")) {
                    versleuteling.setVersleutelGedrag(new Spiegeling());
                }
            }
        });
        grid.add(comboBox, 0, 3, 2, 1);

        // Voeg de knoppen toe
        Button codeButton = new Button();
        codeButton.setText("Codeer");

        Button decodeButton = new Button();
        decodeButton.setText("Decodeer");

        // Gedrag van knoppen
        codeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String boodschap = boodschapVeld.getText();
                resultVeld.setText(versleuteling.codeer(boodschap));
            }
        });

        decodeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String boodschap = boodschapVeld.getText();
                resultVeld.setText(versleuteling.decodeer(boodschap));
            }
        });

        // Container voor de knoppen voor spacing
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(codeButton);
        buttonBox.getChildren().add(decodeButton);

        grid.add(buttonBox, 0, 4);

        Scene scene = new Scene(grid, 500, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
