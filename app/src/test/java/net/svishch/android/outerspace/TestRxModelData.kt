package net.svishch.android.outerspace

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.outerspace.mvp.model.ModelData
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import net.svishch.android.outerspace.mvp.presenter.MarsPhotosListPresenter
import net.svishch.android.outerspace.mvp.presenter.MarsPhotosPresenter
import net.svishch.android.outerspace.mvp.presenter.ScreenState
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TestRxModelData {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    var isLoadDb = false

    private lateinit var presenter: MarsPhotosPresenter

    @Mock
    private lateinit var modelData: ModelData

    @Mock
    private lateinit var marsPhotosListPresenter: MarsPhotosListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MarsPhotosPresenter(Schedulers.io(), modelData, marsPhotosListPresenter)
    }


    @Test
    fun getMarsPhotos() {
        Mockito.`when`(modelData.getMarsPhotos(isLoadDb)).thenReturn(
            Single.just(Photos(listOf()))
        )
        presenter.loadData(isLoadDb)
        Mockito.verify(marsPhotosListPresenter, times(1)).update(mutableListOf())

    }

    @Test
    fun getMarsPhotosError() {
        val observer = Observer<ScreenState> {}
        val error = Throwable(ERROR_TEXT)
        val liveData = presenter.subscribeToLiveData()

        Mockito.`when`(modelData.getMarsPhotos(isLoadDb)).thenReturn(
            Single.error(error)
        )

        //Подписываемся на LiveData
        try {
            liveData.observeForever(observer)
            presenter.loadData(isLoadDb)

            // Задержка на отработку ошибки в presenter
            Thread.sleep(1000)

            //Проверяем возврат ошибки
            val value: ScreenState.Error = liveData.value as ScreenState.Error
            Assert.assertEquals(value.error, error.message)
        } finally {
            // Очистка
            liveData.removeObserver(observer)
        }

    }

    @Test
    fun loadFavorites() {

        Mockito.`when`(modelData.getFavorites()).thenReturn(
            Single.just(Photos(listOf()))
        )

        presenter.loadFavorites()
        Mockito.verify(marsPhotosListPresenter, times(1)).update(mutableListOf())

    }

    companion object {
        private const val ERROR_TEXT = "error"
    }
}