
package org.notesapp.note;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public List<Note> list() {
        return repo.findAll();
    }

    public Note create(Note note) {
        note.setId(null);
        return repo.save(note);
    }

    public Note get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public Note update(Long id, Note patch) {
        Note existing = get(id);
        if (patch.getTitle() != null) existing.setTitle(patch.getTitle());
        if (patch.getContent() != null) existing.setContent(patch.getContent());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Note togglePublic(Long id, boolean makePublic) {
        Note n = get(id);
        n.setPublic(makePublic);
        if (makePublic && (n.getShareId() == null || n.getShareId().isBlank())) {
            n.setShareId(UUID.randomUUID().toString());
        }
        if (!makePublic) {
            n.setShareId(null);
        }
        return repo.save(n);
    }

    public Note getByShareId(String shareId) {
        return repo.findByShareId(shareId).orElseThrow(() -> new RuntimeException("Shared note not found"));
    }
}
