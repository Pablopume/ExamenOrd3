package org.example.common;

public class Comprobacion {
    public static void estrellasOk(int estrellas) throws EstrellasException {
        if (estrellas>5 || estrellas<1){
            throw new EstrellasException(estrellas);
        }
    }
}
