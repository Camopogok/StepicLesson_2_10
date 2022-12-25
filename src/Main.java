import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Calc calc = new Calc();
        FileReader fr = new FileReader("input.txt");
        Scanner input = new Scanner(fr);
        String s = input.nextLine();
        fr.close();

        FileWriter fw = new FileWriter("Output.txt");

        System.out.println(s);
        try {
            System.out.println(calc.printResult(s));
            fw.write("" + calc.printResult(s));
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Calc {
    double a = 0;
    double b = 0;
    String action;
    boolean error = false;

    private void inputData (String symbols) throws Exception {
        String [] data = symbols.split(" ");
        try {
            a = Double.parseDouble(data[0]);
            b = Double.parseDouble(data[2]);
        }
        catch (NumberFormatException e) {
            System.out.println("Error! Not number");
            error = true;
            return;
        }
        if ((data[1].equals("+") || data[1].equals("/") || data[1].equals("-") || data[1].equals("*"))) {
            action = data[1];
        } else {
            throw new Exception("Operation Error!");
        }
    }

    Double printResult (String s) throws Exception {
        try {
            inputData(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            error = true;
        }
        Double result = null;
        if (!error) {
            switch (action) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b != 0)
                        result = a / b;
                    else
                        throw new Exception("Error! Division by zero");
                    break;
            }
        }
        return result;
    }
}