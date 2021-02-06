package retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import net.svishch.android.outerspace.mvp.model.api.nasa.IDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiHolder {
    val api: IDataSource by lazy {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create()

        Retrofit.Builder()
                .baseUrl("https://api.nasa.gov")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

                .create(IDataSource::class.java)
    }
}