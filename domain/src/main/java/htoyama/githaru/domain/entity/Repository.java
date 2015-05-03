package htoyama.githaru.domain.entity;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public class Repository extends Entity {

    public final String name;
    public final Owner owner;

    public Repository(String repositoryName, Owner owner) {
        this.name = repositoryName;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object target) {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ Repository Detail -----" + n);
        b.append("name = " + name + n);
        b.append("owner = " + owner.toString() + n);
        b.append("-------------------------------------");

        return b.toString();
    }
}
