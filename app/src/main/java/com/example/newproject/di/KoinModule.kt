package com.example.newproject.di

import android.widget.ListView
import com.example.newproject.model.repository.NetworkRepository
import com.example.newproject.util.ConnectionConfigue
import com.example.newproject.view.ui.login.LoginViewModel
import com.example.newproject.view.ui.main.MainViewModel
import com.example.newproject.view.ui.my_page.MyPageViewModel
import com.example.newproject.view.ui.sign_up.SignUpViewModel
import com.example.newproject.view.ui.splash.SplashViewModel
import com.example.newproject.view.ui.test_activity.TestViewModel
import com.example.newproject.view.ui.times_list.ListViewModel
import com.example.newproject.view.ui.update_password.UpdatePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mvvmModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { UpdatePasswordViewModel() }
    viewModel { ListViewModel() }
    viewModel { TestViewModel(get()) } // 얘는 찐 테스트 화면
}
val serviceModule = module {
    //factory는 요청시 마다 새로운 인스턴스 생성, single은 단일 인스턴스를 반환
    factory { ConnectionConfigue.provideOkHttpClient(get()) }
    factory { ConnectionConfigue.provideRetrofit(get()) }
    factory { ConnectionConfigue.provideInterceptor() }
    single { ConnectionConfigue.provideNetworkApi(get()) }
    single { NetworkRepository(get()) }

}