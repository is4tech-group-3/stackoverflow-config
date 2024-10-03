package com.stackoverflow.repository;

import com.stackoverflow.bo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TagRepository extends JpaRepository<Tag, Long> {
}
