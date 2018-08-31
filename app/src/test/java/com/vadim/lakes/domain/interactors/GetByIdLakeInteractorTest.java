package com.vadim.lakes.domain.interactors;

import com.vadim.lakes.data.specifications.LakeByIdSpecification;
import com.vadim.lakes.domain.executors.Executor;
import com.vadim.lakes.domain.executors.IMainThread;
import com.vadim.lakes.domain.executors.MainThreadTest;
import com.vadim.lakes.domain.executors.ThreadExecutor;
import com.vadim.lakes.domain.models.Lake;
import com.vadim.lakes.domain.repositories.ILakesRepository;
import com.vadim.lakes.domain.specifications.BaseSpecification;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetByIdLakeInteractorTest {

    private GetByIdLakeInteractor mGetByIdLakeInteractor;

    IMainThread mMainThread;


    private BaseSpecification mBaseSpecification = null;
    private Executor mExecutor = new ThreadExecutor();

    @Mock
    private ILakesRepository mLakesRepository;
    @Mock
    private IGetByIdLakeInteractor.Callback mCallback;

    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();

    @Before
    public void setUp() {
        mMainThread = new MainThreadTest();

    }

    @After
    public void tearDown() {
        mMainThread = null;
    }

    @Test
    public void testGetByIdInteractorGoodRequest() {
        mBaseSpecification = mock(LakeByIdSpecification.class);
        Lake lake = mock(Lake.class);
        when(mLakesRepository.queryOne(any(LakeByIdSpecification.class))).thenReturn(lake);

        mGetByIdLakeInteractor = new GetByIdLakeInteractor(mExecutor, mMainThread, mLakesRepository, mCallback, mBaseSpecification);
        mGetByIdLakeInteractor.run();

        verify(mLakesRepository).queryOne(mBaseSpecification);
        verifyNoMoreInteractions(mLakesRepository);
        verify(mCallback).onLakeReceived(any(Lake.class));
    }


    @Test
    public void testGetByIdInteractorErrorRepository() {
        mExpectedException.expect(Exception.class);
        when(mLakesRepository.queryOne(null)).thenThrow(new Exception("BaseSpecification is null"));

        mGetByIdLakeInteractor = new GetByIdLakeInteractor(mExecutor, mMainThread, mLakesRepository, mCallback, mBaseSpecification);
        mGetByIdLakeInteractor.run();

        verify(mLakesRepository).queryOne(null);
        verifyNoMoreInteractions(mLakesRepository);
        verify(mCallback).onError(anyString());
    }

    //TODO etc.

}
