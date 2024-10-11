package com.stackoverflow.controller;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;
import com.stackoverflow.service.tag.TagService;
import com.stackoverflow.util.AuditAnnotation;
import lombok.AllArgsConstructor;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    private final TagService tagService;
    private final String ENTITY_NAME = "TAG";

    @AuditAnnotation(ENTITY_NAME)
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody TagRequest tagRequest) {
        Tag tag = tagService.createTag(tagRequest);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @AuditAnnotation(ENTITY_NAME)
    @GetMapping
    public Page<Tag> getQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {
        return tagService.getTags(page, size, sortBy, sortDirection);
    }

    @AuditAnnotation(ENTITY_NAME)
    @GetMapping("/{id}")
    public ResponseEntity<Tag> findTagById(@PathVariable("id") Long idTag) {
        Tag tag = tagService.findTagById(idTag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @AuditAnnotation(ENTITY_NAME)
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") Long idTag, @RequestBody TagRequest tagRequest) {
        Tag tag = tagService.updateTag(idTag, tagRequest);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @AuditAnnotation(ENTITY_NAME)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable("id") Long idTag) {
        tagService.deleteTag(idTag);
        return new ResponseEntity<>("Tag deleted successfully", HttpStatus.OK);
    }

    @AuditAnnotation(ENTITY_NAME)
    @PatchMapping("/{id}")
    public ResponseEntity<Tag> changeStatusTag(@PathVariable("id") Long idTag) {
        Tag tag = tagService.changeStatusTag(idTag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
