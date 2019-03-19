package view;

import javafx.scene.control.Label;
import model.CalculatorButton;
import model.CalculatorModel;
import model.CalculatorObserver;



public class CalculatorController implements CalculatorObserver {
    private Label label;
    private CalculatorModel model=new CalculatorModel();
    CalculatorController(Label label){
        this.label=label;
        model.addObserver(this);
    }

    public void buttonPressed(CalculatorButton button){
        model.addCommand(button);
    }

    @Override
    public void update(String output) {
        label.setText(output);
    }

    public void equalsToPressed(){
        model.calculate();
    }

    public void deletePressed(){
        if(model.getCommands().size()==0){
            model.clear();
        }
        else{
            model.removeCommand();
        }
    }
}
