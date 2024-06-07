import java.io.*;
import java.util.*;

public class AddressBook {
    private HashMap<String, String> contacts;

    public AddressBook() {
        contacts = new HashMap<>();
    }

    // Método para cargar contactos desde un archivo
    public void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    // Método para guardar contactos en un archivo
    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Método para listar todos los contactos
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Método para crear un nuevo contacto
    public void create(String number, String name) {
        contacts.put(number, name);
    }

    // Método para borrar un contacto
    public void delete(String number) {
        if (contacts.containsKey(number)) {
            contacts.remove(number);
        } else {
            System.out.println("El contacto con el número " + number + " no existe.");
        }
    }

    // Método para mostrar el menú interactivo
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        String filename = "contacts.txt"; // Archivo donde se guardarán los contactos

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Cargar contactos desde el archivo");
            System.out.println("5. Guardar contactos en el archivo");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (choice) {
                case 1:
                    list();
                    break;
                case 2:
                    System.out.print("Introduce el número: ");
                    String number = scanner.nextLine();
                    System.out.print("Introduce el nombre: ");
                    String name = scanner.nextLine();
                    create(number, name);
                    break;
                case 3:
                    System.out.print("Introduce el número del contacto a borrar: ");
                    number = scanner.nextLine();
                    delete(number);
                    break;
                case 4:
                    load(filename);
                    break;
                case 5:
                    save(filename);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.showMenu();
    }
}
