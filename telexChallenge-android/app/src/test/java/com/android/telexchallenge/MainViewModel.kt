package com.android.telexchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.telexchallenge.data.RepositoryHelper
import com.android.telexchallenge.data.network.Item
import com.android.telexchallenge.data.network.SubTopic
import com.android.telexchallenge.data.network.Topic
import com.android.telexchallenge.helpers.CoroutineTestRule
import com.android.telexchallenge.helpers.LifeCycleTestOwner
import com.android.telexchallenge.ui.MainViewModel
import com.android.telexchallenge.utils.LiveDataWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModel {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repositoryHelper: RepositoryHelper

    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    private lateinit var mainViewModel: MainViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()
        mainViewModel = MainViewModel(repositoryHelper)
    }

    @After
    fun tearDown() {
        lifeCycleTestOwner.onDestroy()
    }

    @Test
    fun smokeTest() {
        assertTrue(true)
    }


    //there're still dozens of test scenarios...
    @Test
    fun `fetching Topics Should Return Successful Result`() = runBlocking {
        val topic = Topic(listOf(Item("title", "horizontal", "https://dumy.com"), Item("title2", "horizontal", "https://dum2y.com")))
        Mockito.`when`(repositoryHelper.fetchTopics()).thenReturn(topic)
        mainViewModel.fetchTopics()
        mainViewModel.topicLiveData.observeForever { }
        assertNotNull(mainViewModel.topicLiveData.value)
        assert(mainViewModel.topicLiveData.value!!.responseRESPONSESTATUS == LiveDataWrapper.RESPONSESTATUS.LOADING)
    }



}