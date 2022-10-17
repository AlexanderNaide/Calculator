package com.gb.calculator;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;


public class CalculatorController extends Application {
    public TextField output;
    boolean isResult = false;
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calculator.fxml")));
        stage.setScene(new Scene(parent));
        stage.setTitle("Calculator");
        stage.show();
    }

    public void inputDigit(ActionEvent actionEvent) {
        if (isResult) {
            cencel(actionEvent);
        }
        Button button = (Button) actionEvent.getSource();
        String digit = button.getText();
        output.appendText(digit);
    }


    public void inputResult(ActionEvent actionEvent) throws EvaluationException, ParseException {
        try{
            String text = output.getText();
            Expression expression = new Expression(text);
            EvaluationValue result = expression.evaluate();
            Button button = (Button) actionEvent.getSource();
            String digit = button.getText();
            output.appendText(digit);
            output.appendText(result.getStringValue());
            isResult = true;
        } catch (EvaluationException | ParseException e){
            output.clear();
            output.appendText("error");
            isResult = true;
        }
    }

    public void cencel(ActionEvent actionEvent) {
        output.clear();
        isResult = false;
    }
}
