package njain.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import njain.io.exceptions.UnsupportedOperatorException;

public class AirthmeticService {

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int division(int a, int b) {
        return a / b;
    }

    public int solveEquation(Equation eqn) {
        int x = eqn.getX();
        int y = eqn.getY();
        char operator = eqn.getOperator();

        switch (operator) {
            case '+' : return sum(x, y);
            case '-' : return subtract(x, y);
            case '*' : return multiply(x, y);
            case '/' : return division(x, y);
            default: throw new UnsupportedOperatorException(operator + " is forbidden");
        }
    }

    public Equation createRandomEquation() {
        Random random = new Random();
        List<Character> ops = new ArrayList<>();
        ops.add('*');
        ops.add('+');
        ops.add('-');
        ops.add('/');
        Collections.shuffle(ops);
        return new Equation(random.nextInt(100), random.nextInt(100),
                ops.get(0));
    }
}
