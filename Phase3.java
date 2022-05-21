/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phase3;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

/**
 *
 * @author Satender
 */
public class Phase3 extends Application {
    
//    fields 
    private TextField phraseField;
    private Label playerScore;
    private Label hackerScore;
    private Label message;
    private TextField generatedPwd;
    
    AdvancedComponents game = new AdvancedComponents();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
//        creating a label to display favorite phrase instructions
    Label mainLabel = new Label("  Enter your favorite phase (a minimum of 15 characters)  ");
    
    phraseField = new TextField();
    
    Button checkLengthBtn = new Button("Check Length");
    
    Label playerLabel = new Label("Your Score: ");
    
    Label hackerLabel = new Label("Hacker's Score: ");
    
    playerScore = new Label();
    playerScore.setStyle("-fx-text-fill: green");
    
    hackerScore = new Label();
    hackerScore.setStyle("-fx-text-fill: red");
    
    message = new Label();
    
    Button genPwdBtn = new Button ("Generate a Strong Password With Custom Phrase");
    
    
    
   
//    listview controls
    ListView<String> listView1 = new ListView<>();
    listView1.setPrefSize(300, 150);
    listView1.getItems().addAll("A Fool and His Money are Soon Parted", "Playing For Keeps", "Wild Goose Chase", "On the Same Page", "Short End of the Stick");
    
    ListView<String> listView2 = new ListView<>();
    listView2.setPrefSize(150, 150);
    listView2.getItems().addAll("0","1", "2", "3", "4", "5", "6", "7", "8", "9");
    
    ListView<String> listView3 = new ListView<>();
    listView3.setPrefSize(150, 150);
    listView3.getItems().addAll("!", "@", "#", "$", "%", "&");
    
//    ENDS HERE
    
    Button genPwdBtn2 = new Button ("Generate a Strong Password With Selected Choices");
    
    generatedPwd = new TextField();
    
    Label question = new Label ("Is writing down your password on paper correct way to store it?");
    
    RadioButton yes = new RadioButton ("Yes");
    RadioButton no = new RadioButton("No");
    
    //    yes.setSelected(true);
    ToggleGroup radioGroup = new ToggleGroup();
    yes.setToggleGroup(radioGroup);
    no.setToggleGroup(radioGroup);
    
    HBox firstHB = new HBox (10, phraseField, checkLengthBtn);
    HBox secondHB = new HBox (10, playerLabel, playerScore, hackerLabel, hackerScore);
    HBox thirdHB = new HBox (10, yes, no);
    HBox fourthBH = new HBox (10,listView1, listView2, listView3);
    
    VBox verticals = new VBox(10, mainLabel, firstHB, secondHB, message,genPwdBtn, fourthBH, genPwdBtn2 , generatedPwd, question, thirdHB);
    
//    Alignments!
    firstHB.setAlignment(Pos.CENTER);
    secondHB.setAlignment(Pos.CENTER);
    verticals.setAlignment(Pos.CENTER);
    verticals.setPadding(new Insets(10));
    
    
    
    
//    register the event handlers
    checkLengthBtn.setOnAction(new checkLengthBtnHandler());
    genPwdBtn.setOnAction(new genPwdBtnHandler());
    genPwdBtn2.setOnAction(event ->
    {
        String selected1 = listView1.getSelectionModel().getSelectedItem();
        String selected2 = listView2.getSelectionModel().getSelectedItem();
        String selected3 = listView3.getSelectionModel().getSelectedItem();
        String combinedStr = selected1 + selected1 + selected3;
        
        game.writeToRAM(combinedStr);
        
        combinedStr = combinedStr.replaceAll(" ","");
        
        String newPwd = game.generateStrongPwd(combinedStr);
        generatedPwd.setText(newPwd);
    });
    
//    Register an action event handler for radio buttons
//    CODE FOR SELECTING RADIO BUTTONS
    
    no.setOnAction(event -> 
    {
        int score = game.getPlayerScore();
        score++;
        game.setPlayerScore(score);
        String input = Integer.toString(game.getPlayerScore());
        playerScore.setText(input);
    });
    
    yes.setOnAction(event -> 
    {
        int score = game.getHackerScore();
        score++;
        game.setHackerScore(score);
        String input = Integer.toString(game.getHackerScore());
        hackerScore.setText(input);
    });
    

//    ENDS HERE
    
//    create a scene
    
    Scene scene = new Scene(verticals);
    
//    add the scene to the Stage
    primaryStage.setScene(scene);
    
//    set stage title
    primaryStage.setTitle("Password Game");
    
//    Show the window
    primaryStage.show();
    }
    
    class checkLengthBtnHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
//          get the phrase 
            String phrase = phraseField.getText();
            
            game.writeToRAM(phrase);
            
            phrase = phrase.replaceAll(" ","");
            
            game.setPhrase(phrase);
            
            int len = game.getLength();
            
            String msg = game.displayLengthMessage(len);
            
            message.setText(msg);
            
            if (len > 15)
            {
                int score = game.getPlayerScore();
                score++;
                game.setPlayerScore(score);
                String input = Integer.toString(game.getPlayerScore());
                playerScore.setText(input);
            }
            else
            {
                int score = game.getHackerScore();
                score++;
                game.setHackerScore(score);
                String input = Integer.toString(game.getHackerScore());
                hackerScore.setText(input);
            }
            
        }
    }

    
    class genPwdBtnHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            String phrase = game.getPhrase();
            
            String newPwd = game.generateStrongPwd(phrase);
            
            generatedPwd.setText(newPwd);
          
        }
            
        }
    
}
