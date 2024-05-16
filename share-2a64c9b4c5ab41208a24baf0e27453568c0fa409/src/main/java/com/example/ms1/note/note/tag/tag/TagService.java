package com.example.ms1.note.note.tag.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public List<Tag> getTagList() {
        List<Tag> tagList = tagRepository.findAll();
        return tagList;
    }

    public Tag getTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId).get();
        return tag;
    }
}
