package model;

public enum  Operator implements CalculatorButton {
    MULTIPLY,
    DIVIDE,
    ADD,
    SUBTRACT;

    @Override
    public String toString() {
        if(this==Operator.ADD){
            return "+";
        }
        else if(this==Operator.DIVIDE){
            return "/";
        }
        else if(this==Operator.MULTIPLY){
            return "x";
        }
        else{
            return "-";
        }
    }
}
