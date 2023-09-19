import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorPelicula gestor = new GestorPelicula();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de Gestión de Películas:");
            System.out.println("1. Agregar Película");
            System.out.println("2. Eliminar Película");
            System.out.println("3. Mostrar todas las Películas");
            System.out.println("4. Mostrar Películas Disponibles");
            System.out.println("5. Mostrar Películas No Disponibles");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea en blanco

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID de la película: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir la línea en blanco
                    System.out.print("Ingrese el nombre de la película: ");
                    String nombre = scanner.nextLine();
                    Pelicula nuevaPelicula = new Pelicula(id, nombre, true);
                    gestor.agregarPelicula(nuevaPelicula);
                    System.out.println("Pelicula agregada con éxito.");
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la película a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    gestor.eliminarPelicula(idEliminar);
                    System.out.println("Pelicula eliminada con éxito.");
                    break;
                case 3:
                    List<Pelicula> todasLasPeliculas = gestor.obtenerPeliculas();
                    System.out.println("Todas las películas:");
                    for (Pelicula pelicula : todasLasPeliculas) {
                        System.out.println(pelicula);
                    }
                    break;
                case 4:
                    List<Pelicula> disponibles = gestor.obtenerPeliculasDisponibles();
                    System.out.println("Películas Disponibles:");
                    for (Pelicula pelicula : disponibles) {
                        System.out.println(pelicula);
                    }
                    break;
                case 5:
                    List<Pelicula> noDisponibles = gestor.obtenerPeliculasNoDisponibles();
                    System.out.println("Películas No Disponibles:");
                    for (Pelicula pelicula : noDisponibles) {
                        System.out.println(pelicula);
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}

class Pelicula {
    private int id;
    private String nombre;
    private boolean disponible;

    public Pelicula(int id, String nombre, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Disponible: " + disponible;
    }
}

class GestorPelicula {
    private List<Pelicula> peliculas;

    public GestorPelicula() {
        peliculas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public void eliminarPelicula(int id) {
        peliculas.removeIf(pelicula -> pelicula.getId() == id);
    }

    public List<Pelicula> obtenerPeliculas() {
        return peliculas;
    }

    public List<Pelicula> obtenerPeliculasDisponibles() {
        List<Pelicula> disponibles = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.estaDisponible()) {
                disponibles.add(pelicula);
            }
        }
        return disponibles;
    }

    public List<Pelicula> obtenerPeliculasNoDisponibles() {
        List<Pelicula> noDisponibles = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (!pelicula.estaDisponible()) {
                noDisponibles.add(pelicula);
            }
        }
        return noDisponibles;
    }
}
