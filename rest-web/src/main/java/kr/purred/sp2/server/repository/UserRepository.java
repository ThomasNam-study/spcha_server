package kr.purred.sp2.server.repository;

import kr.purred.sp2.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail (String email);
}
