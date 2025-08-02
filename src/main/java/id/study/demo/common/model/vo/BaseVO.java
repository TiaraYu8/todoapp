package id.study.demo.common.model.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serial;
import java.io.Serializable;

public class BaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 755467047442540952L;

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}