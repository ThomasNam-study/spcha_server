package kr.purred.sp2.dataserver.repository;

import kr.purred.sp2.dataserver.domain.Board;
import kr.purred.sp2.dataserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BoardRepository extends JpaRepository<Board, Long>
{
	Board findByUser (User user);
}
