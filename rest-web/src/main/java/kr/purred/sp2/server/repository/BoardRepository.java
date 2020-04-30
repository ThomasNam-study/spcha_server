package kr.purred.sp2.server.repository;

import kr.purred.sp2.server.domain.Board;
import kr.purred.sp2.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>
{
	Board findByUser (User user);
}
