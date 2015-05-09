package htoyama.githaru.presentation.view.gist;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.presentation.AppComponent;
import htoyama.githaru.presentation.GitharuApp;
import htoyama.githaru.presentation.R;
import htoyama.githaru.testutil.rule.MockInfraModule;
import htoyama.githaru.testutil.TestUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class TopActivityTest {

    @Inject
    GistRepository mGistRepository;

    @Rule
    public ActivityTestRule<TopActivity> mActivityTestRule = new ActivityTestRule<>(
            TopActivity.class,
            true,
            false
    );

    @Before
    public void setUp() {
        TestComponent component = DaggerTopActivityTest_TestComponent
                .builder()
                .build();
        component.inject(this);

        GitharuApp app = TestUtil.getApplication();
        app.setAppComponent(component);
    }

    @Test
    public void toolbar_isDisplayed() {
        when(mGistRepository.getList(anyString()))
                .thenReturn(fakeGistList());

        TopActivity activity = mActivityTestRule.launchActivity(null);
        Spoon.screenshot(activity, "start");

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Ignore()
    @Test
    public void drawer_closeDrawer_whenBackKeyPressedInStateOfOpenDrawer() {
        setDefalutRepository();

        TopActivity activity = mActivityTestRule.launchActivity(null);

        //onView(withId(android.R.id.home)).perform(click());
        SystemClock.sleep(3000);
    }

    private void setDefalutRepository() {
        //no connect internet for executing test quickly
        when(mGistRepository.getList(anyString()))
                .thenReturn(fakeGistList());
    }

    private List<Gist> fakeGistList() {
        Gist gist1 = new Gist("hoge1");
        Gist gist2 = new Gist("hoge2");

        return Arrays.asList(gist1, gist2);
    }

    @Singleton
    @Component(modules = {
            MockInfraModule.class
    })
    public interface TestComponent extends AppComponent {
        void inject(TopActivityTest activityTest);
    }

}