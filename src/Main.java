import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        FileReader fr = new FileReader("input.txt");
        Scanner input = new Scanner(fr);
        FileWriter fw = new FileWriter("Output.txt");

        while (input.hasNextLine()) {
            String s = input.nextLine();
            Calc calc = new Calc();
            try {
                fw.write(s + " = " + calc.printResult(s) + '\n');
            } catch (Exception e) {
                fw.write(s + " = " + e.getMessage() + '\n');
            }
        }
        fr.close();
        fw.close();
    }
}

class Calc {
    double a = 0;
    double b = 0;
    String action;
    boolean error = false;
    String resultText = "";

    private void inputData(String symbols) throws Exception {
        String[] data = symbols.split(" ");
        try {
            a = Double.parseDouble(data[0]);
            b = Double.parseDouble(data[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Error! Not number");
        }
        if ((data[1].equals("+") || data[1].equals("/") || data[1].equals("-") || data[1].equals("*"))) {
            action = data[1];
        } else {
            throw new Exception("Operation Error!");
        }
    }

    String printResult(String s) {
        try {
            inputData(s);
        } catch (Exception e) {
            resultText = e.getMessage();
            error = true;
        }
        if (!error) {
            switch (action) {
                case "+":
                    resultText = "" + (a + b);
                    break;
                case "-":
                    resultText = "" + (a - b);
                    break;
                case "*":
                    resultText = "" + (a * b);
                    break;
                case "/":
                    if (b != 0)
                        resultText = "" + (a / b);
                    else
                        resultText = "Error! Division by zero";
                    break;
            }
        }
        return resultText;
    }
}