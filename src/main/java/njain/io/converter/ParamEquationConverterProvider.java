package njain.io.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import njain.io.Equation;

/**
 * Created by nitesh.jain on 06-05-2017.
 */
@Provider
public class ParamEquationConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType,
            Annotation[] annotations) {
        if (rawType.getName().equals(Equation.class.getName())) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    Equation equation = new Equation(2, 3, '*');
                    return rawType.cast(equation);
                }

                @Override
                public String toString(T value) {
                    if (value != null) {
                        return value.toString();
                    }
                    return null;
                }
            };
        }
        return null;
    }
}
