package com.ejemplo.bookstorehub.utils;

public class Texto {

    public void menuPrincipal(){
        System.out.println("""
                
                -> Elija una opción:
               
                1 - Buscar y registrar Libro por Título
                2 - Buscar y registrar los Top 10 por Idioma
                3 - Listar Libros registrados
                4 - Listar Libros registrados por Idioma
                5 - Listar Libros resgistrados por Autor
                6 - Listar Autores registrados
                7 - Listar Autores vivos en un determinado año
                
                
                
                0- Salir
                
                """);
    }
    public void msgOpcionInvalida(){
        System.out.println("\n:: Ha elegido una opción inválida\n");
    }
    public void menuDeIdiomas(){
        System.out.println("""
                -> Elija el idioma que desea buscar:
                
                es : spanish
                en : english
                it : italian
                fr : french
                
                """);
    }
    public void infoFooter(){
        System.out.println("""
        :: Saliendo de Literalura BookStore... Gracias por tu visita!
        
        
        
        
        
        @ Literalura BookStore desarrollado por Claudia E. Julian
                           ALURA LATAM 2024
                """);
    }
    public void barraDivisoria(){
        System.out.println("\n" + "--".repeat(30) + "\n");
    }
    public void msgNoInformacion(){
        System.out.println("\n:: La imformación buscada no ha sido encontrada\n");
    }

}