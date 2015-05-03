package htoyama.githaru.infra.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import htoyama.githaru.infra.entitiy.RepositoryEntity;
import htoyama.githaru.infra.entitiy.mapper.RepositoryEntityMapper;
import htoyama.githaru.infra.net.GithubApi;
import retrofit.RetrofitError;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class RepositoryDataRepositoryTest {

    RepositoryDataRepository mSut;

    @Mock
    private GithubApi mGithubApi;

    @Mock
    private RepositoryEntityMapper mMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mSut = new RepositoryDataRepository(mGithubApi, mMapper);
    }

    @Test
    public void getList_GithubApiAndMapperIsInvoked_WhenHappyCase() {
        //setup
        List<RepositoryEntity> list = new ArrayList<>();
        list.add(new RepositoryEntity());

        given(mGithubApi.getRepositoryList(anyString()))
                .willReturn(list);

        //exercise
        mSut.getList("hoge");

        //verify
        verify(mGithubApi).getRepositoryList("hoge");
        verify(mMapper).map(list);
    }

    @Test
    public void getList_GithubApiAndMapperIsInvoked_WhenException() {
        //setup
        given(mGithubApi.getRepositoryList(anyString()))
                .willThrow(RetrofitError.unexpectedError("hoge",
                        new RuntimeException()));

        //exercise
        //assertThat(mSut.getList("hoge"), is(Collections.EMPTY_LIST));

        //verify
        /*
        verify(mGithubApi).getRepositoryList("hoge");
        verify(mMapper).map(list);
        */
    }

}