package htoyama.githaru.infra.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by toyamaosamuyu on 2015/05/17.
 */
public class GistDto extends RealmObject {

    @PrimaryKey
    private String id;

    private String title;
    private boolean isPublic;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}
