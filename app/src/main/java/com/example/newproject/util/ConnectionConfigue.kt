package com.example.newproject.util

import com.example.newproject.BuildConfig
import com.example.newproject.model.service.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectionConfigue {
    val API_URL = "https://www.dhlottery.co.kr"
//        if (!BuildConfig.DEBUG) "http://makeit-soft.com:8101" else "https://svr.truthcan.com"
//        if (!BuildConfig.DEBUG) "http://laddersvc.co.kr:8080" else "http://makeit-soft.com:8101"
        //admin은 8082 포트로 접근하면 됨
        //여기 url 나오면 포트 번호 있는 url을 바꾸고 (현재 변경되있는 상태) admin에서 업로드하고 확인하는고, 앱자체는 정확하게 나오는지 않나오는지 확인하고 전체적인 화면들을 확인해봐야됨
        //admin에서 업로드 하고 앱에서 확인 가능한거 위주로 봐야되는거면 html 에디터랑 앱이랑 관계있는 기능들 위주로 돌리고 정상적으로 출력되는지 확인하고ㅡ
        //나한테 중요한건 푸시랑 문자 제대로 동작하는지를 가장 중요하게 확인해야됨 나머지는 화면에 보여지는지만 확인
        //그리고, 지금은 로그인이 정상적이지 않음, api자체는 정상 작동하는데 db자체가 동기화 안되서 발생하는거 같음
        //일단은 여기까지 -> 내일확인 전체적으로 테스트 하고 클라이언트쪽으로 전달하면 끝임 내일 마저 다 확인하기

        //아직 관리자 웹 실서버에 안올라가 있음 올라간 다음에 다시 테스트 돌리기



    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun provideNetworkApi(retrofit: Retrofit) : NetworkService = retrofit.create(NetworkService::class.java)
//    fun provideUserApi(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)
//    fun provideResearchApi(retrofit: Retrofit): ResearchService =
//        retrofit.create(ResearchService::class.java)
//
//    fun provideNoticeApi(retrofit: Retrofit): NoticeService =
//        retrofit.create(NoticeService::class.java)
//
//    fun provideEducationApi(retrofit: Retrofit): EducationService =
//        retrofit.create(EducationService::class.java)
//
//    fun provideConsentApi(retrofit: Retrofit): ConsentService =
//        retrofit.create(ConsentService::class.java)
}