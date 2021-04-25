package com.example.newproject.di

import com.example.newproject.view.ui.login.LoginViewModel
import com.example.newproject.view.ui.main.MainViewModel
import com.example.newproject.view.ui.sign_up.SignUpViewModel
import com.example.newproject.view.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mvvmModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel() }
}
val serviceModule = module {
    //factory는 요청시 마다 새로운 인스턴스 생성, single은 단일 인스턴스를 반환
//    factory { ConnectionConfigue.provideOkHttpClient(get()) }
//    factory { ConnectionConfigue.provideRetrofit(get()) }
//    factory { ConnectionConfigue.provideInterceptor() }
//    single { ConnectionConfigue.provideUserApi(get()) }
//    single { ConnectionConfigue.provideETCApi(get()) }

}