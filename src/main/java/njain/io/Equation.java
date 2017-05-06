package njain.io;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Setter
@Getter
@XmlRootElement
public class Equation implements Serializable {
    private Integer x;
    private Integer y;
    private char operator;

    public Equation(Integer x, Integer y, char operator) {
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    public Equation() {
    }

    @Override
    public String toString() {
        return x + " " + operator + " " + y + " " + "=";
    }

}
