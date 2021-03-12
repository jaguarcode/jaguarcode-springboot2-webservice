package com.jaguarcode.book.springboot.service;

import com.jaguarcode.book.springboot.domain.posts.Posts;
import com.jaguarcode.book.springboot.domain.posts.PostsRepository;
import com.jaguarcode.book.springboot.web.dto.PostsResponseDto;
import com.jaguarcode.book.springboot.web.dto.PostsSaveRequestDto;
import com.jaguarcode.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post " + id + " is not exist."));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post " + id + " is not exist."));

        return new PostsResponseDto(entity);
    }
}
