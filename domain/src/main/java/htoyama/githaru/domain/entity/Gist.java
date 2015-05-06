package htoyama.githaru.domain.entity;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;


/**
 * Represent a Gist in the domain layer.
 */
public class Gist extends Entity {

    public final String id;
    public String title;
    public boolean isPublic;
    public Owner owner;
    public List<File> fileList = new ArrayList<>();

    public Gist(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }

        if (target == null || !(target instanceof Gist)) {
            return false;
        }

        return ((Gist) target).id.equals(this.id);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
        /*
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ Gist Detail -----" + n);
        b.append("id = " + id + n);
        b.append("title = " + title + n);
        b.append("isPublic = " + isPublic + n);
        b.append("owner = " + owner.toString() + n);
        b.append("fileList = " + fileList.toString() + n);
        b.append("-------------------------------------");

        return b.toString();
        */
    }

}
