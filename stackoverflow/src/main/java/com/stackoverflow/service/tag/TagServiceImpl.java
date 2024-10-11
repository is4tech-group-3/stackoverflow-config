package com.stackoverflow.service.tag;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;
import com.stackoverflow.repository.TagRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final Validator validator;

    @Override
    public Tag createTag(TagRequest tagRequest) {
        if (tagRepository.findByName(tagRequest.getName()).isPresent()) {
            throw new DataIntegrityViolationException("Tag name already exists");
        }

        Tag tag = Tag.builder()
                .name(tagRequest.getName())
                .description(tagRequest.getDescription())
                .status(true)
                .build();
        return tagRepository.save(tag);
    }

    @Override
    public Page<Tag> getTags(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return tagRepository.findAll(pageable);
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

        if (!tag.getName().equals(tagRequest.getName()) && tagRepository.findByName(tagRequest.getName()).isPresent())
            throw new DataIntegrityViolationException("Tag name already exists");

        tag.setName(tagRequest.getName());
        tag.setDescription(tagRequest.getDescription());
        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
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
