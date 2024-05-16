package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.MainDataDto;
import com.example.ms1.note.MainService;
import com.example.ms1.note.ParamHandler;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import com.example.ms1.note.note.tag.NoteTag;
import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes/{noteId}/tag")
public class TagController {
    private final TagService tagService;
    private final NoteService noteService;
    private final MainService mainService;

    @PostMapping("{tagId}")
    public String show(@PathVariable("noteId") Long noteId, @PathVariable("tagId") Long tagId, ParamHandler paramHandler, Model model){
        Tag tag = tagService.getTag(tagId);
        Note note = noteService.getNote(noteId);

        Notebook notebook = note.getNotebook();

        List<NoteTag> noteTagList = tag.getNoteTagList();

        List<Note> tagNoteList = new ArrayList<>();

        for (NoteTag noteTag : noteTagList){
            tagNoteList.add(noteTag.getNote());
        }

        MainDataDto mainDataDto = mainService.mainDataDto(notebook.getId(), noteId, paramHandler.getKeyword(), paramHandler.getSort());

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("tagNoteList", tagNoteList);
        model.addAttribute("targetTag", tag);

        return "main";
    }
}
