package team03.secondhand;

public class Util {
    public static String builder(Object... objs) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : objs) {
            builder.append(obj);
        }
        return builder.toString();
    }

}
