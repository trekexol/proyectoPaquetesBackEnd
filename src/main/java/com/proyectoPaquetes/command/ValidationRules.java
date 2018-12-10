package com.proyectoPaquetes.command;

public interface ValidationRules {
    int EMAIL_MIN_SIZE = 12;
    int PASSWORD_MIN_SIZE = 8;
    int FIRST_LAST_NAME_MAX_SIZE = 40;
    int NAME_MAX_SIZE = 40;
    int FECHA = 5;
    String FIRST_LAST_NAME_REGEX = "[a-zA-Z ]([&.'-]?[a-zA-Z ][&.'-]?)*";
    String BIRTH_REGEX = "dd/mm/yyyy";

}
