package org.example.dao;

import org.example.common.EstrellasException;
import org.example.domain.Alojamiento;
import org.example.domain.CasaRural;
import org.example.domain.Hotel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DaoFicherosImplementacion {

    private static final String ficherob = "Fichero Binario";
    private static final String ficheronob = "Fichero de Texto";

    public static Database leerFicheroBinario() {
        Database database = null;
        File file = new File(ficherob);
        if (file.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
                database = (Database) is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            database = new Database();
        }
        return database;
    }

    public static void escribirFicheroBinario(Database database) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ficherob))) {
            os.writeObject(database);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Alojamiento> leerFichero() {
        List<Alojamiento> listaE = new ArrayList<>();
        File file = new File(ficheronob);
        if (file.exists()) {
            try {
                Stream<String> lines = null;
                try {
                    lines = Files.lines(file.toPath(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lines.forEach(line -> {
                    String[] article = line.split(";");
                    if (article[0].equalsIgnoreCase("HOTEL")) {
                        try {
                            listaE.add(new Hotel(article[1], Integer.parseInt(article[2]), (article[3]), Integer.parseInt(article[4]), Integer.parseInt(article[5])));
                        } catch (EstrellasException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        listaE.add(new CasaRural((article[1]), (article[2]), Integer.parseInt(article[3]), Integer.parseInt(article[4]), Boolean.parseBoolean(article[5])));
                    }
                });
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return listaE;
    }

    public static void escribirFichero(List<Alojamiento> casas) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ficheronob))) {
            for (Alojamiento casa : casas) {
                bufferedWriter.write(casa.toStringFichero());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


public static void escribirFichero2(List<Alojamiento> casas){
        try(BufferedWriter bf=new BufferedWriter(new FileWriter(ficheronob))) {
        for (Alojamiento casa: casas){
            bf.write(casa.toStringFichero());
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}



}

