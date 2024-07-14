package org.kernel360.simpleboard.post.controller;

import java.util.List;

import org.kernel360.simpleboard.common.Api;
import org.kernel360.simpleboard.post.db.PostEntity;
import org.kernel360.simpleboard.post.model.PostRequest;
import org.kernel360.simpleboard.post.model.PostViewRequest;
import org.kernel360.simpleboard.post.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {
	private final PostService postService;

	@PostMapping("")
	public PostEntity create(
		@Valid
		@RequestBody PostRequest postRequest
	) {
		return postService.create(postRequest);
	}

	@PostMapping("/view")
	public PostEntity view(
		@Valid
		@RequestBody PostViewRequest postViewRequest
	) {
		return postService.view(postViewRequest);
	}

	@GetMapping("/all")
	public Api<List<PostEntity>> list(
		@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
		Pageable pageable
	) {
		return postService.all(pageable);
	}

	@PostMapping("/delete")
	public void delete(
		@Valid
		@RequestBody PostViewRequest postViewRequest
	) {
		postService.delete(postViewRequest);
	}
}
