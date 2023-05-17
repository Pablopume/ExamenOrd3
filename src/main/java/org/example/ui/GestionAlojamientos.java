package org.example.ui;

import org.example.common.EstrellasException;
import org.example.domain.Alojamiento;
import org.example.domain.CasaRural;
import org.example.domain.Hotel;
import org.example.service.IServicioAlojamientos;
import org.example.service.ServiciosAlojamientos;

import java.util.Scanner;

public class GestionAlojamientos {
    private IServicioAlojamientos servicio;

    public GestionAlojamientos() {
        this.servicio = new ServiciosAlojamientos();

    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            leerAlojamiento();
            System.out.println("Escribe 1 para añadir alojamiento," +
                    " 2 para consultar alojamientos por provincia y tarifa," +
                    " 3 para ver los alojamientos de una provincia por valoración media," +
                    " 4 para actualizar las estrellas de un hotel," +
                    " 5 para eliminar alojamientos introduciendo una provincia," +
                    " 6 para ordenar los hoteles por estrellas, ascendentemente o descendentemente," +
                    " y 7 para salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1: anyadeAlojamiento();
                break;
                case 2: consultarProvinciaPrecio();
                break;
                case 3: valoracionMedia();
                break;
                case 4: actualizarEstrellas();
                break;
                case 5: eliminarAloj();
                break;
                case 6: ordenarAsc();
                break;
                case 7:
                    System.out.println("Buenas tardes");

                    break;
                default:
                    System.out.println("Opción no válida");
            }


        } while (opcion != 7);
        servicio.escribirFicherioBinario(servicio.getDatabase());
        escribirAlojamientos();
    }

    private void leerAlojamiento() {
        servicio.leerFichero();
    }

    private void escribirAlojamientos(){
        servicio.escribirFichero(servicio.getDatabase().getAlojamientos());
    }

    public void anyadeAlojamiento() {
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Escribe 1 para añader un hotel, 2 para añadir una casa rural");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion != 1 && opcion != 2);
        if (opcion == 1) {
            servicio.anyadirAlojamiento(anyadirHotel());
        } else {
            servicio.anyadirAlojamiento(anyadirCasa());
        }
    }


    public static Alojamiento anyadirHotel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del hotel");
        String nombre = sc.nextLine();
        System.out.println("Valoracion");
        int valoracion = sc.nextInt();
        sc.nextLine();
        System.out.println("Nombre de la provincia");
        String provincia = sc.nextLine();
        System.out.println("Precio");
        int precio = sc.nextInt();
        sc.nextLine();
        System.out.println("Estrellas");
        int estrellas = sc.nextInt();
        sc.nextLine();
        Alojamiento alojamiento = null;
        try {
            alojamiento = new Hotel(nombre, valoracion, provincia, precio, estrellas);
        } catch (EstrellasException e) {
            throw new RuntimeException(e);
        }
        return alojamiento;
    }

    public static Alojamiento anyadirCasa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del hotel");
        String nombre = sc.nextLine();
        System.out.println("Valoracion");
        int valoracion = sc.nextInt();
        sc.nextLine();
        System.out.println("Nombre de la provincia");
        String provincia = sc.nextLine();
        System.out.println("Precio");
        int precio = sc.nextInt();
        sc.nextLine();
        int piscina;
        do {
            System.out.println("Hay piscina? 1 para sí, 2 para no");
            piscina = sc.nextInt();
            sc.nextLine();
        }
        while (piscina != 1 && piscina != 2);
        boolean hayPiscina = true;
        if (piscina == 2) {
            hayPiscina = false;
        }
       return new CasaRural(nombre, provincia, precio, valoracion, hayPiscina);

    }
    public void consultarProvinciaPrecio(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Cuál es tu precio mínimo");
        int minimo=sc.nextInt();
        sc.nextLine();
        System.out.println("Cuál es tu precio máximo");
        int maximo=sc.nextInt();
        sc.nextLine();
        System.out.println("De que provincia quieres buscar casas");
        String provincia=sc.nextLine();
        servicio.listarPrecioProvincia(minimo,maximo,provincia).forEach(System.out::println);
    }

    public void valoracionMedia(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Que provincia quieres listar por valoración");
        String provincia=sc.nextLine();
        servicio.listarProvinciaValor(provincia).forEach(System.out::println);
    }

    public void actualizarEstrellas(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nombre del hotel a actualizar");
        String nombre=sc.nextLine();
        System.out.println("Nuevo número de estrellas");
        int estrellas=sc.nextInt();
        sc.nextLine();
        servicio.cambiarEstrellas(nombre, estrellas);
    }

    public void eliminarAloj(){
        System.out.println("De que provincia quieres ver si eliminas");
        Scanner sc=new Scanner(System.in);
        String provincia=sc.nextLine();
        servicio.porProvincia(provincia).forEach(alojamiento -> {
            System.out.println("Quieres eliminar el alojamiento "+alojamiento.toString()+" 1 para sí, 2 para nó");
            int respuesta=sc.nextInt();
            sc.nextLine();
            if (respuesta==1){
                System.out.println("Si estás seguro escribe 1, sino 2");
                respuesta=sc.nextInt();
                if (respuesta==1){
                    servicio.eliminarAlojamiento(alojamiento);
                }
            }
        });

    }

    public void ordenarAsc(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Si quieres ordenar los hoteles ascendentemente escribe 1, la quieres descendentemente escribe 2.");
        int orden=sc.nextInt();
        sc.nextLine();
        boolean ascendente;
        if (orden==1){
            ascendente=true;
        }
        else ascendente=false;
        servicio.porEstrellas(ascendente).forEach(System.out::println);
    }
}

