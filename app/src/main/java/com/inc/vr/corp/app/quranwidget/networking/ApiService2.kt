package com.inc.vr.corp.app.quranwidget.networking

import com.inc.vr.corp.app.quranwidget.model.main.ModelAyat
import com.inc.vr.corp.app.quranwidget.model.main.ModelWidget
import com.inc.vr.corp.app.quranwidget.viewmodel.SurahViewModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

abstract class ApiService2 {
    @GET("/99c279bb173a6e28359c/surat/{nomor}")
    abstract suspend fun getEmployee(@Path("nomor") nomor: String) : Response<ModelWidget>
}