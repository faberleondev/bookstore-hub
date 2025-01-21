package com.ejemplo.bookstorehub.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}