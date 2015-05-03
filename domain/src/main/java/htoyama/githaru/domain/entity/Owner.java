package htoyama.githaru.domain.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *  Represents a Owner who manage a Github Contents in the domain layer.
 */
public class Owner extends Entity {

    public final String name;
    public int id;

    public Owner(String ownerName) {
        this.name = ownerName;
    }

    @Override
    public boolean equals(Object target) {
        return false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
        /*
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ Owner Detail -----" + n);
        b.append("name = " + name + n);
        b.append("id = " + id + n);
        b.append("-------------------------------------");

        return b.toString();
        */
    }
}
