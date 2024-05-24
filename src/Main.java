import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del directorio a crear: ");
        String nombreDirectorio = scanner.nextLine();
        System.out.print("Ingrese el nombre del archivo a crear: ");
        String nombreArchivo = scanner.nextLine();
        crearArchivo(nombreDirectorio,nombreArchivo);

        System.out.print("Ingrese el nombre del archivo para buscar texto: ");
        nombreArchivo = scanner.nextLine();
        String pathArchivo = "src/" + nombreDirectorio + "/" + nombreArchivo;
        System.out.print("Ingrese el texto para buscar: ");
        String texto = scanner.nextLine();
        buscarTexto(pathArchivo,texto);

    }

    public static void crearArchivo(String directorio, String archivo) {

        //  Comprobaciones nombre archivo
        if(archivo.isEmpty()) {
            System.out.println("Error: Nombre de archivo invalido");
            return;
        }
        if(!archivo.endsWith(".txt")) {
            archivo += ".txt";
        }

        //  Creacion del directorio
        File directorioObj = new File("src/" + directorio);
        if(directorioObj.exists()) {
            System.out.println("El directorio ya existe");
        } else {
            try {
                if(directorioObj.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("El directorio no se pudo crear");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error al crear directorio");
                return;
            }
        }

        //  Creacion y Llenado del archivo
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Perro");
        lista.add("Gato");
        lista.add("Juan");
        lista.add("Daniel");
        lista.add("Juan");
        lista.add("Gato");
        lista.add("Perro");
        lista.add("Camila");
        lista.add("Daniel");
        lista.add("Camila");

        File archivoObj = new File(directorioObj.getAbsolutePath() + "/" + archivo);
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            archivoObj.createNewFile();
            fw = new FileWriter(archivoObj);
            bw = new BufferedWriter(fw);
            Iterator<String> iterator = lista.iterator();

            while (iterator.hasNext()) {
                bw.write(iterator.next());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void buscarTexto(String nombreArchivo,String texto) {

        // Validar nombre de archivo
        if(nombreArchivo.isEmpty()) {
            System.out.println("Error: Nombre de archivo invalido");
            return;
        }
        if(!nombreArchivo.endsWith(".txt")) {
            nombreArchivo += ".txt";
        }

        //  Validdar que el archivo exista
        File archivo = new File(nombreArchivo);
        if(!archivo.exists()) {
            System.out.println("El archivo ingresado no existe");
            return;
        }

        //  Buscar texto en el archivo
        FileReader fr = null;
        BufferedReader br = null;
        int count = 0;
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if(linea.equals(texto)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
                if(fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //  Mostrar la cantidad de veces que la palabra ingresada se encuentra
        if (count != 0) {
            System.out.printf("cantidad de repeticiones del texto -> %d.\n",count);
        } else {
            System.out.println("El texto no se encontró en el archivo.");
        }

    }

}