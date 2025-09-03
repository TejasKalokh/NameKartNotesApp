
package org.notesapp.note;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicNoteController {

    private final NoteService service;

    public PublicNoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/p/{shareId}")
    public Note publicView(@PathVariable String shareId) {
        Note n = service.getByShareId(shareId);
        Note publicDto = new Note();
        publicDto.setId(n.getId());
        publicDto.setTitle(n.getTitle());
        publicDto.setContent(n.getContent());
        // hide internal flags
        return publicDto;
    }
}
