package kr.purred.sp2.dataserver.controller;

import kr.purred.sp2.dataserver.domain.Board;
import kr.purred.sp2.dataserver.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestResource
@RequiredArgsConstructor
public class BoardRestController
{
	private final BoardRepository boardRepository;

	@GetMapping("/boards")
	public @ResponseBody
	PagedModel<Board> simpleBoard (@PageableDefault Pageable pageable)
	{
		Page<Board> boards = boardRepository.findAll (pageable);

		PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata (pageable.getPageSize (), boards.getNumber (), boards.getTotalElements ());

		PagedModel<Board> resources = new PagedModel<> (boards.getContent (), pageMetadata);

		resources.add (linkTo (methodOn (BoardRestController.class).simpleBoard (pageable)).withSelfRel ());

		return resources;
	}
}
