package com.forum2.forum.config.validation;

public class FormErrorDto {

    private String field;
    private String erro;

    public FormErrorDto(String field, String erro){
        this.field = field;
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
    public String getField() {
        return field;
    }
}
