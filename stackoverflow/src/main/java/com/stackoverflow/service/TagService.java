package com.stackoverflow.service;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;

import java.util.List;

public interface TagService {
    Tag createTag(TagRequest tagRequest);

    List<Tag> getTags();

    Tag findTagById(Long idTag);

    Tag updateTag(Long idTag, TagRequest tagRequest);

    void deleteTag(Long idTag);

    Tag changeStatusTag(Long idTag);
}
