package com.example.odczytplikow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderApp extends Application {

    private TextArea textArea;
    private TextArea inputTextArea;
    private Label label1 = new Label("Odczyt");
    private Label label2 = new Label("Zapis");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Odczyt i Zapis Program®");

        // odczytywanie sekcje
        TextField filePathInput = new TextField();
        filePathInput.setPromptText("Wprowadź nazwę pliku do odczytu...");

        Button readButton = new Button("Odczytaj plik");
        textArea = new TextArea();
        textArea.setEditable(false);

        readButton.setOnAction(event -> {
            String filePath = filePathInput.getText();
            readFile(filePath);
        });

        // zapisywanie sekcja
        TextField saveFilePathInput = new TextField();
        saveFilePathInput.setPromptText("Wprowadź nazwę pliku do zapisu...");

        inputTextArea = new TextArea();
        inputTextArea.setPromptText("Wpisz tekst do zapisania...");

        Button saveButton = new Button("Zapisz do pliku");
        saveButton.setOnAction(event -> {
            String filePath = saveFilePathInput.getText();
            String contentToSave = inputTextArea.getText();
            writeFile(filePath, contentToSave);
        });

        // Układ
        VBox vbox = new VBox(10,label1, filePathInput, readButton, textArea,
                label2,saveFilePathInput, inputTextArea, saveButton);
        Scene scene = new Scene(vbox, 400, 500);
        vbox.setStyle("-fx-background-color: #7687D9;");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void readFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            List<String> fileContent = Files.readAllLines(path);
            textArea.clear();
            for (String line : fileContent) {
                textArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            textArea.setText("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
        }
    }

    private void writeFile(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, List.of(content.split("\n")));
            textArea.setText("Dane zapisano pomyślnie do: " + filePath);
        } catch (IOException e) {
            textArea.setText("Wystąpił błąd podczas zapisu pliku: " + e.getMessage());
        }
    }
}