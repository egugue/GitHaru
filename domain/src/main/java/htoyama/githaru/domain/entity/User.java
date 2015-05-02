package htoyama.githaru.domain.entity;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public class User extends Entity {

    public final String userName;
    public int id;
    public int publicRepos;
    public int followers;
    public int following;

    public User(final String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }

        if (target == null || !(target instanceof User)) {
            return false;
        }

        return ((User) target).userName.equals(
                this.userName);
    }

}
