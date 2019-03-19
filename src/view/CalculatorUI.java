package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CalculatorObserver;
import model.DecimalPoint;
import model.Number;
import model.Operator;

public class CalculatorUI extends Application  {
    private Background BLACK_BACKGROUND =
            new Background(new BackgroundFill(Color.BLACK.darker(),
                    CornerRadii.EMPTY, Insets.EMPTY));

    private Background ORANGE_BACKGROUND =
            new Background(new BackgroundFill(Color.ORANGE.brighter(),
                    CornerRadii.EMPTY, Insets.EMPTY));

    private String numStyle="-fx-font: 25px Georgia;" +
            "-fx-alignment: center;" +
            "-fx-text-fill: white;" +
            "-fx-stroke: white;"+
            "-fx-stroke-width: 5;";

    private String numStyleHover="-fx-font: 30px Georgia;" +
            "-fx-font-weight: bold;"+
            "-fx-alignment: center;" +
            "-fx-background-color: #101010;"+
            "-fx-text-fill: white;" +
            "-fx-stroke: white;"+
            "-fx-stroke-width: 5;";

    private String numStyle2="-fx-font: 25px Georgia;" +
            "-fx-alignment: center;" +
            "-fx-text-fill: black;" +
            "-fx-stroke: black;" +
            "-fx-stroke-width: 5;";

    private String numStyle2Hover="-fx-font: 30px Georgia;" +
            "-fx-font-weight: bold;"+
            "-fx-alignment: center;" +
            "-fx-text-fill: black;" +
            "-fx-background-color: #FFA525;"+
            "-fx-stroke: black;" +
            "-fx-stroke-width: 5;";

    private String outputStyle="-fx-font: 40px Georgia;" +
            "-fx-text-fill: black;" +
            "-fx-stroke: black;" +
            "-fx-stroke-width: 5;";

    private Insets padding=new Insets(10);

    private CalculatorController controller;



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        VBox vbox=new VBox();
        HBox hbox=new HBox();
        VBox.setVgrow(hbox,Priority.ALWAYS);
        Label output=new Label();
        controller=new CalculatorController(output);
        output.setMaxWidth(100000000);
        output.setAlignment(Pos.BASELINE_RIGHT);
        output.setStyle(outputStyle);
        output.setText("0");
        output.autosize();
        output.setMinHeight(100);
        GridPane blackGrid=blackGrid();
        GridPane orangeGrid=orangeGrid();
        HBox.setHgrow(blackGrid,Priority.ALWAYS);
        HBox.setHgrow(orangeGrid,Priority.ALWAYS);
        hbox.getChildren().addAll(blackGrid,orangeGrid);
        vbox.getChildren().addAll(output,hbox);
        Scene scene= new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane blackGrid(){
        int width=3;
        int height=3;
        int count=9;
        RowConstraints row1=new RowConstraints();
        RowConstraints row2=new RowConstraints();
        RowConstraints row3=new RowConstraints();
        RowConstraints row4=new RowConstraints();
       row1.setPercentHeight(25);
        row2.setPercentHeight(25);
        row3.setPercentHeight(25);
        row4.setPercentHeight(25);
        ColumnConstraints col1=new ColumnConstraints();
        ColumnConstraints col2=new ColumnConstraints();
        ColumnConstraints col3=new ColumnConstraints();
        col1.setPercentWidth(100/3);
        col2.setPercentWidth(100/3);
        col3.setPercentWidth(34);
        GridPane grid=new GridPane();
        grid.getColumnConstraints().addAll(col1,col2,col3);
        grid.getRowConstraints().addAll(row1,row2,row3,row4);
        for(int row=0;row<height;row++){
            for(int column=0;column<width;column++){
                Button btn=new Button();
                btn.setBackground(BLACK_BACKGROUND);
                btn.setStyle(numStyle);
                btn.setPadding(padding);
                btn.setMinHeight(100);
                btn.setMaxHeight(1000000);
                btn.setMaxWidth(1000000);
                btn.setMinWidth(80);
                btn.setOnMouseEntered(event -> btn.setStyle(numStyleHover));
                btn.setOnMouseExited(event -> btn.setStyle(numStyle));
                String text= String.valueOf(count);
                btn.setText(text);
                btn.setOnAction(event -> controller.buttonPressed(new Number(text)));
                count--;
                grid.add(btn,column,row);
            }
            for(int col=0;col<3;col++){
                Button btn=new Button();
                btn.setBackground(BLACK_BACKGROUND);
                btn.setStyle(numStyle);
                btn.setMinHeight(100);
                btn.setMaxHeight(1000000);
                btn.setMaxWidth(1000000);
                btn.setMinWidth(80);
                btn.setOnMouseEntered(event -> btn.setStyle(numStyleHover));
                btn.setOnMouseExited(event -> btn.setStyle(numStyle));
                btn.setPadding(padding);
                String text;
                if(col==0){
                    text=".";
                }
                else if(col==1){
                    text="0";
                }
                else{
                    text="=";
                }
                btn.setText(text);
                if(text.equals("0")){
                    btn.setOnAction(event -> controller.buttonPressed(new Number(text)));
                }
                else if(text.equals(".")){
                    btn.setOnAction(event -> controller.buttonPressed(new DecimalPoint()));
                }
                else{
                    btn.setOnAction(event -> controller.equalsToPressed());
                }
                grid.add(btn,col,3);
            }
        }
        return grid;
    }

    public GridPane orangeGrid(){
        RowConstraints row1=new RowConstraints();
        RowConstraints row2=new RowConstraints();
        RowConstraints row3=new RowConstraints();
        RowConstraints row4=new RowConstraints();
        RowConstraints row5=new RowConstraints();
        row1.setPercentHeight(20);
        row2.setPercentHeight(20);
        row3.setPercentHeight(20);
        row4.setPercentHeight(20);
        row5.setPercentHeight(20);
        ColumnConstraints col=new ColumnConstraints();
        col.setPercentWidth(100);
        GridPane grid=new GridPane();
        grid.getColumnConstraints().add(col);
        grid.getRowConstraints().addAll(row1,row2,row3,row4,row5);
        for(int row=0;row<5;row++){
            Button btn=new Button();
            btn.setBackground(ORANGE_BACKGROUND);
            btn.setStyle(numStyle2);
            btn.setPadding(padding);
            btn.setMaxHeight(1000000);
            btn.setMaxWidth(1000000);
            btn.setOnMouseEntered(event -> btn.setStyle(numStyle2Hover));
            btn.setOnMouseExited(event -> btn.setStyle(numStyle2));
            btn.setMinHeight(80);
            btn.setMinWidth(100);
            String text;
            if(row==0){
                text="DEL";
            }
            else if(row==1){
                text="/";
            }
            else if(row==2){
                text="x";
            }
            else if(row==3){
                text="-";
            }
            else {
                text="+";
            }
            btn.setText(text);
            if(text.equals("DEL")){
                btn.setOnAction(event -> controller.deletePressed());
            }
            if(text.equals("/")){
                btn.setOnAction(event -> controller.buttonPressed(Operator.DIVIDE));
            }
            if(text.equals("x")){
                btn.setOnAction(event -> controller.buttonPressed(Operator.MULTIPLY));
            }
            if(text.equals("-")){
                btn.setOnAction(event -> controller.buttonPressed(Operator.SUBTRACT));
            }
            if(text.equals("+")){
                btn.setOnAction(event -> controller.buttonPressed(Operator.ADD));
            }
            grid.add(btn,0,row);
        }
        return grid;
    }
}
