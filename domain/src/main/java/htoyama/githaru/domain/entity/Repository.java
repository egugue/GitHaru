package htoyama.githaru.domain.entity;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public class Repository extends Entity {

    public final String userName;
    public final String repositoryName;

    public Repository(String userName, String repositoryName) {
        this.userName = userName;
        this.repositoryName = repositoryName;
    }

    @Override
    public boolean equals(Object target) {
        //TODO
        return false;
    }
}
