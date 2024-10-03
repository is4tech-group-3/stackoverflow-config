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
    public ResponseEntity<Tag> getTag(@PathVariable Long id) {
        Tag tag = tagService.getTag(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody TagRequest tagRequest) {
        Tag tag = tagService.updateTag(id, tagRequest);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>("Tag deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tag> changeStatusTag(@PathVariable Long id) {
        Tag tag = tagService.changeStatus(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
