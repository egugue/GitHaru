package htoyama.githaru.infra.repository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.infra.entitiy.GistEntity;
import htoyama.githaru.infra.entitiy.mapper.GistEntityMapper;
import htoyama.githaru.infra.net.GithubApi;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class GistDataRepositoryTest {
    private static final String FAKE_ID = "fake_id";

    private GistDataRepository sut;

    @Mock
    private GithubApi mGithubApi;

    @Mock
    private GistEntityMapper mMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new GistDataRepository(mGithubApi, mMapper);
    }

    @Test
    public void getList_getGistListAndMapIsInvoked_whenSuccess() {
        //setup
        List<GistEntity> entityList = Arrays.asList(new GistEntity());
        List<Gist> gistList = Arrays.asList(new Gist(FAKE_ID));

        given(mGithubApi.getGistList(anyString(), anyString()))
                .willReturn(entityList);

        given(mMapper.map(entityList))
                .willReturn(gistList);

        //exercise
        List<Gist> actual = sut.getList("fake_user_name");

        //verify
        assertThat(actual, is(gistList));
    }

    @Test
    public void get_getGistAndMapIsInvoked_whenSuccess() {
        GistEntity entity = new GistEntity();
        Gist gist = new Gist(FAKE_ID);
        when(mGithubApi.getGist(FAKE_ID)).thenReturn(entity);
        when(mMapper.map(entity)).thenReturn(gist);

        Gist actual = sut.get(FAKE_ID);

        assertThat(actual, is(gist));
    }

    @Ignore("must refactoring")
    @Test
    public void create_createGistIsInvoked_whenSuccess() {
        Gist gist = new Gist(FAKE_ID);
        sut.create(new Gist(FAKE_ID));

        /*
        verify(mGithubApi).createGist(
                Secret.token, GistRequest.with(gist));
                */
    }

    @Ignore("must refactoring")
    @Test
    public void edit_editGistIsInvoked_whenSuccess() {

    }

    @Ignore("must refactoring")
    @Test
    public void delete_deleteGistIsInvoked_whenSucess() {
        sut.delete(FAKE_ID);

    //    verify(mGithubApi).deleteGist(Secret.token, FAKE_ID);
    }

}