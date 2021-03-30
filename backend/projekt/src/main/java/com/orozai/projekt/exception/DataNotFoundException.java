package com.orozai.projekt.exception;
public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException() {
    super("Data not found");
  }
}
