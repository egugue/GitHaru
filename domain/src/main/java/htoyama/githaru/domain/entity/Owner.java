package htoyama.githaru.domain.entity;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public class Owner extends Entity {

    public final String userName;
    public int id;

    public Owner(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object target) {
        return false;
    }
}
