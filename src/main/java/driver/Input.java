package driver;

import model.operation.binary.Add;
import model.operation.function.AllClear;
import model.app.Calculator;
import model.operation.function.Pi;

public class Input {

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    Pi pi = new Pi();
    AllClear allClear = new AllClear();
    Add add = new Add();

    System.out.println(calculator.getDisplay().getValue());

    calculator.executeOperation(pi);
    System.out.println(calculator.getDisplay().getValue());

    calculator.clear();
    System.out.println(calculator.getDisplay().getValue());

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    // calculator.enterOperation("+");
    calculator.pushOperation(add);
    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    calculator.equals();
    System.out.println(calculator.getDisplay().getValue());

    // calculator.allClear();
    allClear.execute(calculator);
    System.out.println(calculator.getDisplay().getValue());
    System.out.println("actualOperandStack isEmpty: " + calculator.getOperandStack().isEmpty());
  }
}
