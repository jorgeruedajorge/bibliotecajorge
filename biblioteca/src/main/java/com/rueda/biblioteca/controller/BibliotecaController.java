package com.rueda.biblioteca.controller;

import com.rueda.biblioteca.model.Biblioteca;
import com.rueda.biblioteca.repository.BibliotecaRepository;
import com.rueda.biblioteca.service.BibliotecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {
    private final BibliotecaRepository bibliotecaRepository;
    private BibliotecaService bibliotecaService;

    public  BibliotecaController (BibliotecaService bibliotecaService, BibliotecaRepository bibliotecaRepository){
        this.bibliotecaService = bibliotecaService;
        this.bibliotecaRepository = bibliotecaRepository;
    }

    @GetMapping
    public List<Biblioteca> verTodos(){
        return bibliotecaService.traerTodos();
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Biblioteca> guardar(@RequestBody Biblioteca biblioteca){
        Biblioteca nuevoBiblioteca = bibliotecaService.guardar(biblioteca);
        return new ResponseEntity<>(nuevoBiblioteca, HttpStatus.CREATED);
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> traerUno(@PathVariable int id){
        return bibliotecaService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> editar(
            @PathVariable int id,
            @RequestBody Biblioteca biblioteca) {
        return bibliotecaService.editar(id, biblioteca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity <Biblioteca> eliminar (@PathVariable int id){
        if (bibliotecaService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    // Libros disponibles
    @GetMapping("/disponibles")
    public List<Biblioteca> disponibles() {
        return bibliotecaService.disponibles();
    }

}
