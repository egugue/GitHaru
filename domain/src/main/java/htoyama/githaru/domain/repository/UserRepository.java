package htoyama.githaru.domain.repository;

import htoyama.githaru.domain.entity.User;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public interface UserRepository {
    User getUser(String userName);
}
