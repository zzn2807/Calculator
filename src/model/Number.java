package model;

public class Number implements CalculatorButton {
    private String number;
    public Number(String number){
        this.number=number;
    }

    public String toString(){
        return number;
    }
}
