package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application
{
    private static final double WIDTH = 500;
    private static final double HEIGHT = 800;

    private TextArea textArea;
    private Font font;

    private boolean isFile;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("NotepadXD");
        VBox layout = new VBox(20);
        font = new Font("Sans-Serif", 12);

        Scene scene = new Scene(layout, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);

        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");
        MenuItem file_new = new MenuItem("New File");
        file_new.setOnAction(event ->
        {
            System.out.println("Creating a new file...");
            isFile = true;
            initTextArea(layout);
        });
        MenuItem file_save = new MenuItem("Save");
        file_save.setOnAction(event ->
        {
            if(isFile)
            {
                System.out.println("Saving file...");
                FileChooser fileChooser = new FileChooser();
                File f = fileChooser.showSaveDialog(primaryStage);

                if (f != null)
                    Save(textArea.getText(), f);
            }
        });

        MenuItem file_load = new MenuItem("Load");
        file_load.setOnAction(e ->
        {
            System.out.println("Loading a file...");
            FileChooser fileChooser = new FileChooser();
            File f = fileChooser.showOpenDialog(primaryStage);
            if(f.exists())
            {
                initTextArea(layout);
                Load(f);
            }

        });

        Menu edit = new Menu("Edit");
        MenuItem font_size = new MenuItem("Font-Size");
        MenuItem font_color = new MenuItem("Font-Color");

        font_size.setOnAction(event ->
        {
            font_size_menu();
        });

        font_color.setOnAction(event ->
        {

        });

        mb.getMenus().addAll(file, edit);
        file.getItems().addAll(file_new, file_save, file_load);
        edit.getItems().addAll(font_size, font_color);
        layout.getChildren().add(mb);

        primaryStage.show();
    }

    private void initTextArea(VBox layout)
    {

        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefSize(WIDTH, HEIGHT);

        layout.getChildren().add(textArea);
    }
    private void Save(String content, File file)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void Load(File file)
    {
        try
        {
            StringBuilder content = new StringBuilder();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
                content.append(scanner.nextLine()).append('\n');
            textArea.setText(content.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void font_size_menu()
    {
        double font_size = 12.0;
        Stage stage = new Stage();
        stage.setTitle("font-size-menu");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.setX(760);
        stage.setY(440);
        HBox lay = new HBox(10);
        lay.setAlignment(Pos.CENTER);
        Scene scene = new Scene(lay, 300, 50);
        scene.setFill(Color.LIGHTBLUE);

        Label l1 = new Label();
        l1.setText("Font Size is ");
        l1.setTextFill(Color.BLACK);
        l1.setFont(new Font(15));

        Button b1 = new Button("Confirm");

        ChoiceBox<Double> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add(13.0);
        choiceBox.getItems().add(15.0);
        choiceBox.getItems().add(17.0);
        choiceBox.getItems().add(19.0);
        choiceBox.getItems().add(21.0);
        choiceBox.getItems().add(23.0);
        choiceBox.getItems().add(25.0);
        choiceBox.getItems().add(27.0);



        System.out.println("FONT SIZE IS " + font_size);
        lay.getChildren().addAll(l1, choiceBox, b1);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
