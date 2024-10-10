package com.stackoverflow.service;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;
import com.stackoverflow.repository.TagRepository;
import com.stackoverflow.util.ValidationUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag createTag(TagRequest tagRequest) {
        if (tagRepository.findByName(tagRequest.getName()).isPresent()) {
            throw new IllegalArgumentException("Tag name already exists");
        }

        ValidationUtil.validateNotEmpty(tagRequest.getName(), "Name");
        ValidationUtil.validateNotEmpty(tagRequest.getDescription(), "Description");

        ValidationUtil.validateMaxLength(tagRequest.getName(), 15, "Name");
        ValidationUtil.validateMaxLength(tagRequest.getDescription(), 30, "Description");

        Tag tag = Tag.builder()
                .name(tagRequest.getName())
                .description(tagRequest.getDescription())
                .status(true)
                .build();
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findTagById(Long idTag) {
        return tagRepository.findById(idTag)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + idTag));
    }

    @Override
    public Tag updateTag(Long idTag, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(idTag)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + idTag));

        if (!tag.getName().equals(tagRequest.getName()) && tagRepository.findByName(tagRequest.getName()).isPresent()) {
            throw new IllegalArgumentException("Tag name already exists");
        }

        ValidationUtil.validateNotEmpty(tagRequest.getName(), "Name");
        ValidationUtil.validateNotEmpty(tagRequest.getDescription(), "Description");

        ValidationUtil.validateMaxLength(tagRequest.getName(), 15, "Name");
        ValidationUtil.validateMaxLength(tagRequest.getDescription(), 30, "Description");

        tag.setName(tagRequest.getName());
        tag.setDescription(tagRequest.getDescription());
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long idTag) {
        tagRepository.findById(idTag)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + idTag));
        tagRepository.deleteById(idTag);
    }

    @Override
    public Tag changeStatusTag(Long idTag) {
        Tag tag = tagRepository.findById(idTag)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + idTag));
        tag.setStatus(!tag.getStatus());
        return tagRepository.save(tag);
    }
}
