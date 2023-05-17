package org.example.common;

public class EstrellasException extends Exception{
    public EstrellasException(int estrellas) {
        super("Has introducido en el hotel "+estrellas+"estrellas, no pueden ser menos de 1 ni más de 5");
    }
}
