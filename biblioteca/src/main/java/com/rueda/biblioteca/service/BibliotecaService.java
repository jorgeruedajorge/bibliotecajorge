package com.rueda.biblioteca.service;

import com.rueda.biblioteca.model.Biblioteca;
import com.rueda.biblioteca.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BibliotecaService {
    private BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository){
        this.bibliotecaRepository = bibliotecaRepository;
    }

    //Traer todos
    public List<Biblioteca> traerTodos(){
        return bibliotecaRepository.findAll();
    }

    //Guardar
    public Biblioteca guardar (Biblioteca biblioteca){
        return bibliotecaRepository.save(biblioteca);
    }

    //Traer uno
    public Optional<Biblioteca> traerUno(int id){
        return bibliotecaRepository.findById(id);
    }

    // EDITAR
    public Optional<Biblioteca> editar(int id, Biblioteca bibliotecaActualizado) {
        return bibliotecaRepository.findById(id)
                .map(bibliotecaExiste -> {
                    bibliotecaExiste.setTitulo(bibliotecaActualizado.getTitulo());
                    bibliotecaExiste.setAutor(bibliotecaActualizado.getAutor());
                    bibliotecaExiste.setAnio(bibliotecaActualizado.getAnio());
                    bibliotecaExiste.setDisponible(bibliotecaActualizado.isDisponible());

                    return bibliotecaRepository.save(bibliotecaExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( bibliotecaRepository.existsById(id)){
            bibliotecaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //disponible true
    public List<Biblioteca> disponibles() {
        return bibliotecaRepository.findAll()
                .stream()
                .filter(biblioteca -> biblioteca.isDisponible())
                .collect(Collectors.toList());
    }
}
