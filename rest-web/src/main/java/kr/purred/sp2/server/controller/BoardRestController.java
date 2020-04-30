package kr.purred.sp2.server.controller;

import kr.purred.sp2.server.domain.Board;
import kr.purred.sp2.server.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardRestController
{
	private final BoardRepository boardRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBoards (@PageableDefault Pageable pageable)
	{
		Page<Board> boards = boardRepository.findAll (pageable);

		PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata (pageable.getPageSize (), boards.getNumber (), boards.getTotalElements ());

		PagedModel<Board> resources = new PagedModel<> (boards.getContent (), pageMetadata);

		resources.add (linkTo (methodOn (BoardRestController.class).getBoards (pageable)).withSelfRel ());

		return ResponseEntity.ok (resources);
	}

	@PostMapping
	public ResponseEntity<?> postBoard (@RequestBody Board board)
	{
		board.setCreateDateNow ();

		boardRepository.save (board);

		return new ResponseEntity<> ("{}", HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putBoard(@PathVariable("idx") Long idx, @RequestBody Board board)
	{
		Board persistBoard = boardRepository.getOne (idx);

		persistBoard.update (board);

		boardRepository.save (persistBoard);

		return new ResponseEntity<> ("{}", HttpStatus.OK);
	}

	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deleteBoard (@PathVariable("idx") Long idx)
	{
		boardRepository.deleteById (idx);

		return new ResponseEntity<> ("{}", HttpStatus.OK);
	}
}
