package com.ejemplo.bookstorehub.principal;

import com.ejemplo.bookstorehub.model.*;
import com.ejemplo.bookstorehub.repository.AutorRepository;
import com.ejemplo.bookstorehub.repository.LibroRepository;
import com.ejemplo.bookstorehub.service.ConsumoAPI;
import com.ejemplo.bookstorehub.service.ConvierteDatos;
import com.ejemplo.bookstorehub.utils.Texto;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner teclado = new Scanner(System.in);
    Texto texto = new Texto();
    private final String URL_BASE = "https://gutendex.com/books/";
    public List<Libro> libros;
    private List<Autor> autores;
    int cantidadAutores = 0;
    int cantidadLibros = 0;


    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository repositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepositorio = autorRepository;
    }

    public void showMenu() {
        texto.barraDivisoria();
        System.out.println("\n *** BIENVENIDOS AL BOOKSTORE LITERALURA *** \n");
        var option = -1;

        texto.menuPrincipal();
        try {
            while (option != 0) {
                texto.menuPrincipal();
                Scanner myScanner = new Scanner(System.in);
                option = myScanner.nextInt();
                myScanner.nextLine();

                texto.barraDivisoria();
                switch (option) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarTop10PorIdioma();
                        break;
                    case 3:
                        listarLibrosRegistrado();
                        break;
                    case 4:
                        listarLibrosPorIdioma();
                        break;
                    case 5:
                        listarLibrosPorAutor();
                        break;
                    case 6:
                        listarAutoresRegistrados();
                        break;
                    case 7:
                        listarAutoresVivosEnAno();
                        break;
                    case 0:
                        texto.infoFooter();
                        break;
                    default:
                        texto.msgOpcionInvalida();
                }
                texto.barraDivisoria();
            }
        } catch (InputMismatchException e) {
            texto.msgOpcionInvalida();
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("\n-> Escriba el nombre del libro que desea buscar: \n");
        var nombreLibro = teclado.nextLine();

        try {
            var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
            Datos datos = conversor.obtenerDatos(json, Datos.class); // trae los json y los convierte en DatosResultado
            DatosLibro datosLibro = datos.resultados().get(0); //en este caso trae solo el primer libro encontrado y lo convierte en DatosLibro
            registrarLibroEnBaseDatos(datosLibro); // funcion para cargarlo en la DB

        } catch (DataIntegrityViolationException e) {
            System.out.println("\n :: El libro solicitado ya se encuentra registrado.\n");
        } catch (IndexOutOfBoundsException e) {
            texto.msgNoInformacion();
        }
    }

    private void listarLibrosRegistrado() {
        libros = repositorio.findAll();
        cantidadLibros = libros.size();
        System.out.println("\nLIBROS REGISTRADOS EN LITERALURA: \n");
        if (cantidadLibros > 0) {
            System.out.println("\n:: Cantidad de Libros Registrados: " + cantidadLibros + " libro/s \n");
            libros.stream().forEach(System.out::println);
        } else {
            texto.msgNoInformacion();
        }

    }

    private void listarAutoresRegistrados() {
        autores = autorRepositorio.findAll();
        cantidadAutores = autores.size();
        if (cantidadAutores > 0) {
            System.out.println("\nAUTORES REGISTRADOS EN LITERALURA: \n");
            System.out.println("\n:: Cantidad de Autores Registrados: " + cantidadAutores + " autor/es \n");
            autores.stream().forEach(System.out::println);
        } else {
            texto.msgNoInformacion();
        }
    }

    private void listarAutoresVivosEnAno() {
        System.out.println("\n-> Escriba el año que desea buscar: \n");
        Integer anoBuscado = teclado.nextInt();

        autores = autorRepositorio.autoresVivosEnAno(anoBuscado);
        cantidadAutores = autores.size();

        if (cantidadAutores > 0) {
            System.out.printf("\n:: Cantidad de Autores Vivos en %d:  %s autor/es \n", anoBuscado, cantidadAutores);
            autores.stream().forEach(System.out::println);
        } else {
            texto.msgNoInformacion();
        }
    }

    private void listarLibrosPorIdioma() {
        texto.menuDeIdiomas();
        String idiomaElegido = teclado.nextLine();

        if (!idiomaElegido.equals("en") && !idiomaElegido.equals("fr") && !idiomaElegido.equals("it") && !idiomaElegido.equals("es")) {
            texto.msgOpcionInvalida();
        } else {
            System.out.println("\nLIBROS REGISTRADOS EN EL IDIOMA ELEGIDO : \n");

            List<Libro> libros = repositorio.listarLibrosPorIdioma(idiomaElegido);
            cantidadLibros = libros.size();

            if (cantidadLibros > 0) {
                System.out.println("\n:: Cantidad de libros registrados: " + cantidadLibros + " libro/s" + "\n");
                libros.stream().forEach(System.out::println);
            } else {
                texto.msgNoInformacion();
            }
        }
    }

    private void listarLibrosPorAutor() {
        System.out.println("\n-> Escriba el autor que desea buscar: \n");
        String autorABuscar = teclado.nextLine();

        List<Libro> libros = repositorio.listarLibrosPorAutor(autorABuscar);
        cantidadLibros = libros.size();

        if (cantidadLibros > 0) {
            System.out.printf("\n:: Hay registrados %d libro/s de %s \n", cantidadLibros, autorABuscar.toUpperCase());
            libros.stream().forEach(System.out::println);
        } else {
            texto.msgNoInformacion();
        }

    }

    ;

    private void listarTop10PorIdioma() {
        texto.menuDeIdiomas();
        String idiomaElegido = teclado.nextLine();

        if (!idiomaElegido.equals("fr") && !idiomaElegido.equals("it") && !idiomaElegido.equals("en") && !idiomaElegido.equals("es")) {
            texto.msgOpcionInvalida();
        } else {
            try {
                var jsonLanguage = consumoAPI.obtenerDatos(URL_BASE + "?languages=" + idiomaElegido);
                Datos datosPorIdioma = conversor.obtenerDatos(jsonLanguage, Datos.class);

                List<DatosLibro> datosLibros = datosPorIdioma.resultados().stream()
                        .limit(10)
                        .collect(Collectors.toList());

                libros = datosLibros.stream()
                        .map(l -> new Libro(l, idiomaElegido))
                        .collect(Collectors.toList());

                System.out.println(datosLibros);
                System.out.println("\nLOS 10 MEJORES LIBROS EN TU IDIOMA ELEGIDO");
                System.out.println("Fuente experta: Gutendex\n");
                libros.forEach(System.out::println);

                datosLibros.forEach(l -> registrarLibroEnBaseDatos(l));

            } catch (IndexOutOfBoundsException e) {
                e.getMessage();
            }

        }
    }

    private void registrarLibroEnBaseDatos(DatosLibro datosLibro) {
        if (datosLibro.autor().isEmpty()) {
            try {
                Libro libroARegistrar = new Libro(datosLibro);
                repositorio.save(libroARegistrar);
                System.out.println("\n :: " + libroARegistrar.getTitulo() + " fue registrado con éxito.\n");
            } catch (DataIntegrityViolationException e) {
                System.out.println("\n :: El libro " + " ya se encuentra registrado.\n");
            }

        } else {

            try {
                DatosAutor datosAutor = datosLibro.autor().get(0);
                Libro libroARegistrar = new Libro(datosLibro);
                Autor autorARegistrar = new Autor(datosAutor);
                List<Autor> autorBuscado = autorRepositorio.buscarAutorPorNombre(autorARegistrar.getNombre());
                if (autorBuscado.size() == 0) {
                    autorRepositorio.save(autorARegistrar);
                    System.out.println("\n :: " + libroARegistrar.getTitulo() + " y su autor fueron registrados con éxito.\n");
                } else {
                    repositorio.save(libroARegistrar);
                    System.out.println("\n :: " + libroARegistrar.getTitulo() + " fue registrado con éxito.\n");
                }

            } catch (DataIntegrityViolationException e) {
                System.out.println("\n :: El libro " + " ya se encuentra registrado.\n");
            }
        }
    }
}
