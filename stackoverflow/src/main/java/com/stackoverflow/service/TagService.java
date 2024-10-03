package com.stackoverflow.service;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;

import java.util.List;

public interface TagService {
    List<Tag> getTags();

    Tag getTag(Long idTag);

    Tag createTag(TagRequest tagRequest);

    Tag updateTag(Long idTag, TagRequest tagRequest);

    void deleteTag(Long idTag);

    Tag changeStatus(Long idTag);
}
