
package org.notesapp.note;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Note> list() { return service.list(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note create(@Valid @RequestBody Note note) {
        return service.create(note);
    }

//    @GetMapping("/{id}")
//    public Note get(@PathVariable Long id) { return service.get(id); }
//
//    @PutMapping("/{id}")
//    public Note update(@PathVariable Long id, @RequestBody Note patch) {
//        return service.update(id, patch);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long id) { service.delete(id); }
//
//    @PostMapping("/{id}/share")
//    public Note share(@PathVariable Long id, @RequestParam boolean makePublic) {
//        return service.togglePublic(id, makePublic);
//    }
@GetMapping("/{id}")
public Note get(@PathVariable("id") Long id) {
    return service.get(id);
}

    @PutMapping("/{id}")
    public Note update(@PathVariable("id") Long id, @RequestBody Note patch) {
        return service.update(id, patch);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/share")
    public Note share(@PathVariable("id") Long id, @RequestParam("makePublic") boolean makePublic) {
        return service.togglePublic(id, makePublic);
    }
}
