package kr.purred.sp2.dataserver.repository;

import kr.purred.sp2.dataserver.domain.User;
import kr.purred.sp2.dataserver.domain.projection.UserOnlyContainName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserOnlyContainName.class)
public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail (String email);
}
