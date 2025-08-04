package id.study.demo.common.utils;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * ParamChecker digunakan untuk validasi parameter request
 * dengan pesan error yang otomatis menyebut nama parameter.
 */
public class ParamChecker {

    /**
     * Pastikan parameter tidak null.
     * Contoh: ParamChecker.notNull(request.getEmail(), "email");
     */
    public static void notNull(Object o, String paramName) {
        AssertUtil.notNull(o, String.format("Parameter '%s' cannot be null", paramName));
    }

    /**
     * Pastikan parameter null.
     * Contoh: ParamChecker.isNull(existingUser, "user");
     */
    public static void isNull(Object o, String paramName) {
        AssertUtil.isNull(o, String.format("Parameter '%s' must be null", paramName));
    }

    /**
     * Pastikan string tidak kosong.
     * Contoh: ParamChecker.notEmpty(request.getUsername(), "username");
     */
    public static void notEmpty(String s, String paramName) {
        AssertUtil.notEmpty(s, String.format("Parameter '%s' cannot be empty", paramName));
    }

    /**
     * Pastikan kondisi sesuai ekspektasi.
     * Contoh: ParamChecker.isExpected(password.equals(confirmPassword), "confirmPassword");
     */
    public static void isExpected(boolean b, String paramName) {
        AssertUtil.isTrue(b, String.format("Value of '%s' is not valid", paramName));
    }

    public static void isEmail(String email, String paramName) {
        boolean isValid = EmailValidator.getInstance().isValid(email);
        AssertUtil.isTrue(isValid, String.format("Value of '%s' is not valid", paramName));
    }

    public static void minLength(String value, int minLength, String paramName) {
        boolean isValid = value != null && value.length() >= minLength;
        AssertUtil.isTrue(isValid,
                String.format("parameter %s must be at least %d characters long", paramName, minLength));
    }

    public static void maxLength(String value, int maxLength, String paramName) {
        boolean isValid = value != null && value.length() <= maxLength;
        AssertUtil.isTrue(isValid,
                String.format("parameter %s must not exceeded %d characters", paramName, maxLength));
    }

    private ParamChecker() {
        // Prevent instantiation
    }
}