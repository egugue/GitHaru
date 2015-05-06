package htoyama.githaru.infra.entitiy.mapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.entity.Owner;
import htoyama.githaru.infra.entitiy.GistEntity;
import htoyama.githaru.infra.entitiy.OwnerEntity;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GistEntityMapperTest {
    private static final String FAKE_OWNER_NAME = "fake_owner_name";
    private static final String FAKE_TITLE = "fake_title";
    private static final boolean FAKE_IS_PUBLIC = true;

    private GistEntityMapper sut;

    @Mock
    private OwnerEntityMapper mOwnerEntityMapper;

    @Mock
    private FileEntityMapper mFileEntityMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new GistEntityMapper(mOwnerEntityMapper, mFileEntityMapper);
    }

    @Test
    public void map_GistEntity() {
        when(mOwnerEntityMapper.map(any(OwnerEntity.class)))
                .thenReturn(new Owner(FAKE_OWNER_NAME));
        when(mFileEntityMapper.map(anyMap()))
                .thenReturn(new ArrayList<File>());

        Gist actual = sut.map(createFakeGistEntity());

        assertThat(actual.title, is(FAKE_TITLE));
        assertThat(actual.isPublic, is(FAKE_IS_PUBLIC));
        assertThat(actual.owner.name, is(FAKE_OWNER_NAME));
        assertThat(actual.fileList, is(instanceOf(List.class)));
    }

    @Test
    public void map_ListOfGistEntity() {
        GistEntity entity1 = mock(GistEntity.class);
        GistEntity entity2 = mock(GistEntity.class);
        List<GistEntity> entityList = Arrays.asList(entity1, entity2);

        List<Gist> actual = sut.map(entityList);

        assertThat(actual.size(), is(2));
        assertThat(actual.get(0), is(instanceOf(Gist.class)));
        assertThat(actual.get(1), is(instanceOf(Gist.class)));
    }

    private GistEntity createFakeGistEntity() {
        GistEntity entity = new GistEntity();
        entity.title = FAKE_TITLE;
        entity.isPublic = FAKE_IS_PUBLIC;
        return entity;
    }

}