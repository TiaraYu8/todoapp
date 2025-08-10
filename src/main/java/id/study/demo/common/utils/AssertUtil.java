package id.study.demo.common.utils;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;

import id.study.demo.common.exception.BadRequestException;
import id.study.demo.common.exception.BusinessException;
import jakarta.annotation.Nullable;

public class AssertUtil {

    /**
     * Pastikan object tidak null.
     * Dipakai: validasi request body, entity dari repository, dsb.
     */
    public static void notNull(@Nullable Object o, String message) {
        if (o == null) {
            throw new BusinessException("PARAM_ILLEGAL", message);
        }
        Objects.requireNonNull(o);
    }

    /**
     * Pastikan object null.
     * Dipakai: validasi kalau data belum ada (misal username harus belum terdaftar).
     */
    public static void isNull(Object o, String message) {
        if (o != null) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Pastikan collection kosong.
     * Dipakai: validasi bahwa tidak ada data dalam collection (misal tidak boleh punya sub-task).
     */
    public static void isEmpty(Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Pastikan collection tidak kosong.
     * Dipakai: validasi bahwa ada minimal satu data (misal list task tidak boleh kosong).
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Pastikan string kosong.
     * Dipakai: validasi bahwa field harus kosong (jarang dipakai, lebih untuk aturan spesifik).
     */
    public static void isEmpty(String s, String message) {
        if (StringUtils.isNotEmpty(s)) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Pastikan string tidak kosong.
     * Dipakai: validasi input seperti username, email, dsb.
     */
    public static void notEmpty(String s, String message) {
        if (StringUtils.isEmpty(s)) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Pastikan kondisi benar.
     * Dipakai: validasi custom seperti "password >= 8 karakter", "jumlah task <= limit", dsb.
     */
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new BadRequestException(message);
        }
    }

    private AssertUtil() {
    }
}