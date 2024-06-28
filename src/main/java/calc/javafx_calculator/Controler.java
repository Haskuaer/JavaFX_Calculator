package calc.javafx_calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static java.lang.Double.parseDouble;

public class Controler {

    private boolean setA = false;
    private double a, b;
    private String display = "", operator;
    @FXML
    private Button  zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, addBtn, subBtn, multiplyBtn,
            divBtn, percentBtn, fractionBtn, powerBtn, squareBtn, signBtn, comaBtn, ceBtn, cBtn, backspaceBtn, eqBtn;
    @FXML
    private Button[] numBtns = new Button[10];
    @FXML
    private Label operationDisplay, resultDisplay;

    public void initialize(){

        initBtns();

        for(Button button: numBtns){
            button.setOnAction(event -> {
                display += button.getText();
                resultDisplay.setText(display);
            });
        }

        addBtn.setOnAction(event -> {
            operator = addBtn.getText();
            a = parseDouble(resultDisplay.getText());
            setA = true;
            operationDisplay.setText(display + " " + operator);
            display = "";
        });

        eqBtn.setOnAction(event -> {
            if(!operator.isEmpty()){
                b = parseDouble(resultDisplay.getText());
                display = (trimDouble(String.valueOf(a)) + " " + operator + " " + trimDouble(String.valueOf(b)) + " = " );
                operationDisplay.setText(display);
                display = String.valueOf(getResult(a, b, operator));
                display = trimDouble(display);
                resultDisplay.setText(display);
                operator = "";
                display = "";
            }
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
            default:
                return 0;
        }
    }

    private String trimDouble(String text) {

        if (text.endsWith(".0")) {
            text = text.replaceAll(".0", "");
        }

        return text;
    }
}
