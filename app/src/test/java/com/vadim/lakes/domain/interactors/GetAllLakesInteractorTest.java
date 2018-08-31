package com.vadim.lakes.domain.interactors;

import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.executors.MainThreadTest;
import com.vadim.lakes.domain.executors.ThreadExecutor;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetAllLakesInteractorTest {


    private GetAllLakesInteractor mGetAllLakesInteractor;

    IMainThread mMainThread;

    @Mock
    private Executor mExecutor = new ThreadExecutor();
    @Mock
    private ILakesRepository mLakesRepository;
    @Mock
    private IGetAllLakesInteractor.Callback mCallback;

    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        mMainThread = new MainThreadTest();
    }

    @After
    public void tearDown(){
        mMainThread = null;
    }

    @Test
    public void testGetAllLakesInteractorGoodRequest(){
        List<Lake> lakes = spy(new ArrayList<Lake>());
        when(mLakesRepository.query(null)).thenReturn(lakes);
        mGetAllLakesInteractor = new GetAllLakesInteractor(mExecutor, mMainThread, mLakesRepository, mCallback);
        mGetAllLakesInteractor.run();

        verify(mLakesRepository).query(null);
        verifyNoMoreInteractions(mLakesRepository);
        verify(mCallback).onLakesReceived(ArgumentMatchers.<Lake>anyList());
    }

    @Test
    public void testGetAllLakesInteractorErrorJson() {
        mExpectedException.expect(Exception.class);
        mGetAllLakesInteractor = new GetAllLakesInteractor(mExecutor, mMainThread, mLakesRepository, mCallback);
        mGetAllLakesInteractor.run();

        doThrow(new Exception("Json parse exception or Syntax exception or different exception")).when(mLakesRepository).query(null);

        verify(mLakesRepository).query(null);
        verifyNoMoreInteractions(mLakesRepository);
        verify(mCallback).onError(anyString());
    }

}
