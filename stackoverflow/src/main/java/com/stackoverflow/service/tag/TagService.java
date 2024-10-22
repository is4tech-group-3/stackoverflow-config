package com.stackoverflow.service.tag;

import com.stackoverflow.bo.Tag;
import com.stackoverflow.dto.TagRequest;

import org.springframework.data.domain.Page;

public interface TagService {
    Tag createTag(TagRequest tagRequest);

    Page<Tag> getTags(int page, int size, String sortBy, String sortDirection);

    Tag findTagById(Long idTag);

    Tag updateTag(Long idTag, TagRequest tagRequest);

    void deleteTag(Long idTag);

    Tag changeStatusTag(Long idTag);
}
