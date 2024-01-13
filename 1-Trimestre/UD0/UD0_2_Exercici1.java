package UD0;

import java.util.*;
import java.io.*;

public class UD0_2_Exercici1 {

    static Scanner sc = new Scanner(System.in);

    public static String menu() {

        return "----------------------------" + "\n" +
                "            MENU            " + "\n" +
                "----------------------------" + "\n" +
                "1. Mostrar ficheros del directorio actual" + "\n" +
                "2. Modificar el directorio actual" + "\n" +
                "3. Volver atrás" + "\n" +
                "4. Salir del programa" + "\n" + "\n" +
                "Elige una opción: ";
    }

    public static String listaFicheros(String[] listado) {

        String fich = "----------------------------" + "\n" +
                "         FICHEROS            " + "\n" +
                "----------------------------" + "\n";

        if (listado == null || listado.length == 0) {
            // System.out.println("No hay elementos dentro de la carpeta actual");
            return "No hay elementos dentro de la carpeta actual \n";
        } else {
            for (int i = 0; i < listado.length; i++) {
                // System.out.println(listado[i]);

                fich += listado[i] + "\n";
            }
        }

        return fich;
    }

    public static void main(String[] args) {

        String rutaActual = null;
        Integer opcionElegida = null;

        System.out.println("Introduce la ruta inicial del explorador de archivos: ");
        rutaActual = sc.nextLine();



        System.out.println("Has seleccionado la ruta: " + rutaActual);

        System.out.println(menu());

        while ((opcionElegida = sc.nextInt()) != 4) {

            File carpeta = new File(rutaActual);
            String[] listado = carpeta.list();

            if (opcionElegida == 1) {

                System.out.println(listaFicheros(listado));

                System.out.println(menu());

            } else if (opcionElegida == 2) {

                System.out.println("¿Qué directorio quieres añadir ? : ");

                String[] parts = rutaActual.split("");

                if (parts[parts.length-1] != "/") {
                    rutaActual+="/";
                }

                rutaActual += sc.next();

                System.out.println("Has seleccionado la ruta: " + rutaActual);

                carpeta = new File(rutaActual);

                System.out.println(menu());

            } else if (opcionElegida == 3) {

                String[] parts = rutaActual.split("/");
                rutaActual = "";

                for (int i = 0; i < parts.length-1; i++) {
                    rutaActual += parts[i] + "/" ;
                }
                System.out.println(parts[parts.length-1]);

                System.out.println("Has seleccionado la ruta: " + rutaActual);

                carpeta = new File(rutaActual);

                System.out.println(menu());


            } else {
                System.out.println("Opción elegida no permitida");
                System.out.println(menu());
            }

        }

        System.out.println("\n--------------------");
        System.out.println("  Fin del programa");
        System.out.println("--------------------");

    }
}