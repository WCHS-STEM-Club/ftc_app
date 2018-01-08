package org.firstinspires.ftc.teamcode;

abstract class ParamCheck {
    static boolean arrayOfLength(Object[] array, int len) {
        return array.length == len;
    }

    static boolean containsNull(Object[]... arrays) {
        // Will return true as soon as a null object is seen, otherwise returns false
        for (Object[] array : arrays) for (Object object : array) if (object == null) return false;
        return true;
    }
    static boolean areNull(Object... objects) {
        return containsNull(objects);
    }
    static boolean isNull(Object object) {
        return object == null;
    }
}
