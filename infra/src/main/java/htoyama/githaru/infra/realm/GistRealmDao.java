package htoyama.githaru.infra.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by toyamaosamuyu on 2015/05/17.
 */
public class GistRealmDao {
    private static final String TAG = GistRealmDao.class.getSimpleName();

    private Realm mRealm;

    public GistRealmDao(Context context) {
        mRealm = Realm.getInstance(context);
    }

    public GistDto get(String id) {
        RealmQuery<GistDto> query = mRealm.where(GistDto.class);

        query.equalTo("id", "hoge");
        RealmResults<GistDto> resultAll = query.findAll();

        return resultAll.get(0);
    }

    public void save() {
        mRealm.beginTransaction();

        GistDto dto = mRealm.createObject(GistDto.class);
        dto.setId("hoge");
        dto.setTitle("hoge");

        mRealm.commitTransaction();
    }

}
