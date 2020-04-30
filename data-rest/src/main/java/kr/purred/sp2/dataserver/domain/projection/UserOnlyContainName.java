package kr.purred.sp2.dataserver.domain.projection;

import kr.purred.sp2.dataserver.domain.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "getOnlyName", types = { User.class})
public interface UserOnlyContainName
{
	String getName ();
}
