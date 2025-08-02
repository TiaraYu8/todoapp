package id.study.demo.common.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serial;
import java.io.Serializable;

public class BaseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 755467047442540952L;

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

