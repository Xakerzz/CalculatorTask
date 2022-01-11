import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {
    private double result;
    private double secondOperand;
    private char operation;
    private int processId;
    boolean isInfinityCalculating = false;

    Scanner scanner = new Scanner(System.in);


    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setFirstOperand() {
        setProcessId(1);
        programMessage(1);
        checkUserEnter();
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand() {
        setProcessId(3);
        programMessage(3);
        checkUserEnter();
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation() {
        setProcessId(2);
        programMessage(2);
        checkOperation();
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public boolean isInfinityCalculating() {
        return isInfinityCalculating;
    }

    public void setInfinityCalculating(boolean infinityCalculating) {
        isInfinityCalculating = infinityCalculating;
    }

    ///////////////////////////////////////// Перегруженные setter-ы для установки значений по умолчанию //////////////////////////////////////
    public void setSecondOperand(double secondOperand){
        this.secondOperand = secondOperand;
    }

    public void setOperation(char operation){
        this.operation = operation;
    }

    public void programMessage(int idMessage) {
        switch (idMessage) {
            case 1:
                System.out.println("Enter first number.");
                break;
            case 2:
                System.out.println("Enter operation.");
                break;
            case 3:
                System.out.println("Enter next number.");
                break;
            case 4:
                System.out.println("You can't divide by zero!!! Try again!!!");
                break;
            case 5:
                System.out.println("It is not number. Enter first number again.");
                break;
            case 6:
                System.out.println("Wrong operation or wrong number.Enter again.");
                break;
            case 7:
                System.out.println("It is not number. Enter second number.");
                break;
            case 8:
                int number = (int) getResult();
                if (getResult() % number == 0) {
                    System.out.println("Your result equals - " + new DecimalFormat("#").format(getResult()));
                } else {
                    System.out.println("Your result equals - " + new DecimalFormat("#.##").format(getResult()));
                }

                break;
            case 9:
                System.out.println("Calculator is closed. Thank you. Goodbye! ");
                break;
            case 10:
                System.out.println("Result equals : 0");
                break;
            case 11:
                System.out.println("You did all by default");
                break;
            case 12:
                System.out.println("You wrote nothing in the line. Try again.");
                break;
        }
    }


    public String getNextLine() {
        return scanner.nextLine();
    }

    public void checkUserEnter() {
        String userEntry = getNextLine();
        stringToDouble(userEntry);
    }


    public void stringToDouble(String userEntry) {
        double operand;
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < userEntry.length(); i++) {
                if (userEntry.charAt(i) == ',') {
                    sb.append('.');
                } else {
                    sb.append(userEntry.charAt(i));
                }
            }
            userEntry = sb.toString();
             operand = Double.parseDouble(userEntry);
             if (!isInfinityCalculating) {
                 setResult(operand);
                 setInfinityCalculating(true);
             }else {
                 setSecondOperand(operand);
             }
        } catch (NumberFormatException e) {
            try {
                if (userEntry.charAt(0) == 's' || userEntry.charAt(0) == 'c') {
                    controlOperation(userEntry.charAt(0));
                } else {
                    programMessage(6);
                    checkProcessId(getProcessId());
                }
            } catch (StringIndexOutOfBoundsException ex) {
                programMessage(12);
                checkProcessId(getProcessId());
            }
        }
    }

    public void checkProcessId(int processId) {

        switch (processId) {
            case 1:
                setFirstOperand();
                break;
            case 2:
                setOperation();
                break;
            case 3:
                setSecondOperand();
                break;
        }
    }


    public void checkOperation() {
        char letter;
        String userEntry = getNextLine();
        try {
            if (userEntry.charAt(0) == 's' || userEntry.charAt(0) == 'c') {

                controlOperation(userEntry.charAt(0));

            } else {
                if (userEntry.charAt(0) == '+' || userEntry.charAt(0) == '-' || userEntry.charAt(0) == '*' || userEntry.charAt(0) == '/') {
                    letter = userEntry.charAt(0);
                    setOperation(letter);

                } else {
                    programMessage(6);
                    checkProcessId(getProcessId());
                }
            }
        } catch (StringIndexOutOfBoundsException ex) {
            programMessage(12);
            checkProcessId(getProcessId());
        }
    }

    public void controlOperation(char operation) {
        switch (operation) {
            case 'c' -> {
                programMessage(11);
                defaultValue();
                run();
            }
            case 's' -> {
                programMessage(9);
                System.exit(0);
            }
        }
    }

    public void defaultValue() {
        setInfinityCalculating(false);
        setResult(0);
        setOperation(' ');
        setSecondOperand(0);
    }

    public void getResolve() {
        switch (getOperation()) {
            case '+':
                setResult(getResult() + getSecondOperand());
                break;
            case '-':
                setResult(getResult() - getSecondOperand());
                break;
            case '*':
                setResult(getResult() * getSecondOperand());
                break;
            case '/':
                if (getSecondOperand() != 0) {
                    setResult(getResult() / getSecondOperand());
                } else {
                    programMessage(4);
                    run();
                }
                break;
        }
        programMessage(8);
    }


    public void run() {
        setFirstOperand();
        while (isInfinityCalculating) {
            setOperation();
            setSecondOperand();
            getResolve();
        }
    }
}
