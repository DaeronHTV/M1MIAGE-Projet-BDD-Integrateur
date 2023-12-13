package asmr.visiopad.enumeration;

public class EnumUtilities<T> {

    public EnumUtilities(){}

    public T fromValue(String v, T[] valuesToCheck) {
        for (T c: valuesToCheck) {
            if (c.toString().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}