package com.uff.config.classes.converter;

import java.io.Serializable;


public interface CSVPropertyConverter<T> extends Serializable {

    public String getName();

    public T convertFrom(String text);

    public String convertTo(T object);
}
