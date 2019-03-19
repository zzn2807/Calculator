package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class CalculatorModel {
    private ArrayList<CalculatorObserver> observers;
    private String output;
    private LinkedList<CalculatorButton> commands;

    public CalculatorModel(){
        observers=new ArrayList<>();
        commands=new LinkedList<>();
        output="0";

    }

    public void clear(){
        output="0";
        updateObservers();
    }

    public void addObserver(CalculatorObserver observer){
        observers.add(observer);
    }
    public void updateObservers(){
        for(CalculatorObserver observer: observers){
            observer.update(output);
        }

    }

    public void addCommand(CalculatorButton command){
        if(commands.size()==0&&!(command instanceof Number)){
            commands.add(0,new Number(output));
            commands.add(command);
            updateObservers();
            output="0";
        }
        else if(commands.size()==0){
            output=command.toString();
            commands.add(command);
            updateObservers();
        }
        else {
            if(commands.getLast() instanceof Operator && command instanceof Operator){
                output="Syntax Error";
               updateObservers();
               output="0";
               commands=new LinkedList<>();
               commands.add(new Number(output));
               return;
            }
            commands.add(command);
            if (command instanceof Operator) {
                output = "0";
            } else {
                if (output.equals("0")) {
                    output = command.toString();
                    updateObservers();
                } else {
                    output += command.toString();
                    updateObservers();
                }
            }
        }

    }

    public void removeCommand(){
        if(commands.size()<=0){
            return;
        }
        else{
            CalculatorButton command=commands.removeLast();
            if (command instanceof Operator){

            }
            else{
                output=output.substring(0,output.length()-1);
            }
            updateObservers();
        }
    }

    public void calculate(){
       output= String.valueOf(calculateBodmas(Operator.ADD));
       updateObservers();
       }

       public double calculateBodmas(Operator prev){
        if(commands.size()==0){
            if(prev==Operator.MULTIPLY||prev==Operator.DIVIDE){
                return 1;
            }
            else {
                return 0;
            }
        }
        else{
            String firstNum="";
            while(commands.getFirst() instanceof Number || commands.getFirst() instanceof DecimalPoint){
                firstNum+=commands.removeFirst().toString();
                if(commands.size()==0){
                    break;
                }
            }
            if(commands.size()==0){
                return Double.parseDouble(firstNum);
            }
            Operator operator=(Operator) commands.removeFirst();
            if(operator==Operator.ADD){
                return Double.parseDouble(firstNum)+calculateBodmas(operator);
            }
            else if(operator==Operator.SUBTRACT){
                return Double.parseDouble(firstNum)-calculateBodmas(operator);
            }
            else if(operator==Operator.MULTIPLY){
                return Double.parseDouble(firstNum)*calculateBodmas(operator);
            }
            else{
                return Double.parseDouble(firstNum)/calculateBodmas(operator);
            }
        }

       }
    public LinkedList<CalculatorButton> getCommands() {
        return commands;
    }
}

