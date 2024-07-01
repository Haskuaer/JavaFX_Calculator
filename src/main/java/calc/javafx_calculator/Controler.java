package calc.javafx_calculator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.Double.parseDouble;

public class Controler {

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage;

    private boolean aSet = false, bSet = false, comaSet = false;
    private double a, b;
    private String display = "", operator = "";

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button  zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, addBtn, subBtn, multiplyBtn,
            divBtn, percentBtn, fractionBtn, powerBtn, squareBtn, signBtn, comaBtn, ceBtn, cBtn, backspaceBtn, eqBtn, minimizeBtn, closeBtn;
    @FXML
    private Button[] numBtns = new Button[10];
    @FXML
    private Label operationDisplay, resultDisplay;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(){

        rootPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        rootPane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        initBtns();

        for(Button button: numBtns){
            button.setOnAction(event -> {
                if(button.getText().equals("0") && display.isEmpty()){
                    resultDisplay.setText("0");
                } else {
                    display += button.getText();
                    resultDisplay.setText(display);
                }
            });
        }

        signBtn.setOnAction(event -> {
            if(display.isEmpty()){
                resultDisplay.setText("0");
            } else if(!display.isEmpty() && display.startsWith("-")){
                display = display.substring(1);
                resultDisplay.setText(display);
            } else {
                display = "-" + display;
                resultDisplay.setText(display);
            }
        });

        comaBtn.setOnAction(event -> {
            if(!comaSet){
                if(display.isEmpty()){
                    display = "0.";
                    resultDisplay.setText(display);
                    comaSet = true;
                } else {
                    display += ".";
                    resultDisplay.setText(display);
                    comaSet = true;
                }
            }
        });

        addBtn.setOnAction(event -> {
            operator = addBtn.getText();
            a = parseDouble(resultDisplay.getText());
            aSet = true;
            comaSet = false;
            operationDisplay.setText(trimDouble(String.valueOf(a)) + " " + operator);
            display = "";
        });

        subBtn.setOnAction(event -> {
            operator = subBtn.getText();
            a = parseDouble(resultDisplay.getText());
            aSet = true;
            comaSet = false;
            operationDisplay.setText(trimDouble(String.valueOf(a)) + " " + operator);
            display = "";
        });

        multiplyBtn.setOnAction(event -> {
            operator = multiplyBtn.getText();
            a = parseDouble(resultDisplay.getText());
            aSet = true;
            comaSet = false;
            operationDisplay.setText(trimDouble(String.valueOf(a)) + " " + operator);
            display = "";
        });

        divBtn.setOnAction(event -> {
            operator = divBtn.getText();
            a = parseDouble(resultDisplay.getText());
            aSet = true;
            comaSet = false;
            operationDisplay.setText(trimDouble(String.valueOf(a)) + " " + operator);
            display = "";
        });

        fractionBtn.setOnAction(event -> {
            operator = fractionBtn.getText();
            a = parseDouble(resultDisplay.getText());
            display = String.valueOf(getResult(a, 0, operator));
            display = trimDouble(display);
            resultDisplay.setText(display);
            comaSet = false;
            operator = "";
            display = "";
        });

        powerBtn.setOnAction(event -> {
            operator = powerBtn.getText();
            a = parseDouble(resultDisplay.getText());
            display = String.valueOf(getResult(a, 0, operator));
            display = trimDouble(display);
            resultDisplay.setText(display);
            comaSet = false;
            operator = "";
            display = "";
        });

        squareBtn.setOnAction(event -> {
            operator = squareBtn.getText();
            a = parseDouble(resultDisplay.getText());
            display = String.valueOf(getResult(a, 0, operator));
            display = trimDouble(display);
            resultDisplay.setText(display);
            comaSet = false;
            operator = "";
            display = "";
        });

        percentBtn.setOnAction(event -> {
            if(aSet) {
                if (operator.equals("-") || operator.equals("+")) {
                    b = parseDouble(resultDisplay.getText());
                    b = a * (b/100);
                    bSet = true;
                    display = a + " " + operator + " " + b;
                    operationDisplay.setText(display);
                } else if (operator.equals("*") || operator.equals("/")) {
                    b = parseDouble(resultDisplay.getText());
                    b = b/100;
                    bSet = true;
                    display = a + " " + operator + " " + b;
                    operationDisplay.setText(display);
                } else {
                    resultDisplay.setText("0");
                    display = "";
                }
            } else {
                resultDisplay.setText("0");
                display = "";
            }
        });

        eqBtn.setOnAction(event -> {
            if(!operator.isEmpty()){
                if(bSet){
                    display = String.valueOf(getResult(a, b, operator));
                    display = trimDouble(display);
                    resultDisplay.setText(display);
                    comaSet = false;
                    operator = "";
                    display = "";
                } else {
                    b = parseDouble(resultDisplay.getText());
                    bSet = true;
                    display = (trimDouble(String.valueOf(a)) + " " + operator + " " + trimDouble(String.valueOf(b)) + " = ");
                    operationDisplay.setText(display);
                    display = String.valueOf(getResult(a, b, operator));
                    display = trimDouble(display);
                    resultDisplay.setText(display);
                    comaSet = false;
                    operator = "";
                    display = "";
                }
            } else {
                a = parseDouble(resultDisplay.getText());
                display = String.valueOf(a);
                display = trimDouble(display);
                operationDisplay.setText(display + " =");
                comaSet = false;
                display = "";
            }
        });

        backspaceBtn.setOnAction(event -> {
            if(!display.isEmpty()){
                if(display.length() == 1){
                    display = "0";
                    resultDisplay.setText(display);
                    display = "";
                } else {
                    if(display.indent(1).equals(".")){
                        comaSet = false;
                    }
                    display = display.substring(0, display.length() - 1);
                    resultDisplay.setText(display);
                }
            }
        });

        ceBtn.setOnAction(event -> {
            if(bSet){
                resultDisplay.setText("0");
                operationDisplay.setText("");
                display = "";
                aSet = false;
                comaSet = false;
            } else {
                resultDisplay.setText("");
                display = "";
                comaSet = false;
            }
        });

        cBtn.setOnAction(event -> {
            resultDisplay.setText("0");
            operationDisplay.setText("");
            display = "";
            aSet = false;
            bSet = false;
            comaSet = false;
        });

        minimizeBtn.setOnAction(event -> {
            stage.setIconified(true);
        });

        closeBtn.setOnAction(event -> {
            Platform.exit();
        });
    }

    private void initBtns(){

        numBtns[0] = zeroBtn;
        numBtns[1] = oneBtn;
        numBtns[2] = twoBtn;
        numBtns[3] = threeBtn;
        numBtns[4] = fourBtn;
        numBtns[5] = fiveBtn;
        numBtns[6] = sixBtn;
        numBtns[7] = sevenBtn;
        numBtns[8] = eightBtn;
        numBtns[9] = nineBtn;
    }

    private double getResult(double a, double b, String operator){

        switch(operator){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            case "1/x":
                return 1/a;
            case "x^2":
                return a*a;
            case "sqrt(x)":
                return Math.sqrt(a);
            default:
                return 0;
        }
    }

    private String trimDouble(String text) {

        if (text.endsWith(".0")) {
            text = text.replace(".0", "");
        } else if(text.endsWith(".")){
            text = text.replace(".", "");
        }

        return text;
    }
}
