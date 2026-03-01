package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.DatosListadoTopico;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.DatosActualizarTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository,
            CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosListadoTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new ValidacionDeIntegridad("El tópico con este título y mensaje ya existe");
        }

        var autor = usuarioRepository.findById(datosRegistroTopico.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
        var curso = cursoRepository.findById(datosRegistroTopico.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, autor, curso));
        DatosListadoTopico datosListadoTopico = new DatosListadoTopico(topico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new));
    }

    @PutMapping
    @jakarta.transaction.Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(datosActualizarTopico.id())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
