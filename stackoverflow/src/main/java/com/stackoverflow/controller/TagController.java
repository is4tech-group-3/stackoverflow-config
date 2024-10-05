package com.stackoverflow.controller;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;
import com.stackoverflow.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody TagRequest tagRequest) {
        Tag tag = tagService.createTag(tagRequest);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.getTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findTagById(@PathVariable("id") Long idTag) {
        Tag tag = tagService.findTagById(idTag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") Long idTag, @RequestBody TagRequest tagRequest) {
        Tag tag = tagService.updateTag(idTag, tagRequest);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable("id") Long idTag) {
        tagService.deleteTag(idTag);
        return new ResponseEntity<>("Tag deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tag> changeStatusTag(@PathVariable("id") Long idTag) {
        Tag tag = tagService.changeStatusTag(idTag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
