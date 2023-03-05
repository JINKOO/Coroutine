package com.example.coroutineapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coroutineapp.ui.theme.CoroutineAppTheme

/**
 *  과제
 *  1. UserId, password를 입력받아서 로그인을 처리하는 함수
 *  2. AccessKey -> true / false를 반환하는 함수
 *  3. 유저 정보를 조회하는 메소드 -> User
 *
 *  결론 :: 1,2,3번을 3초 이내 실행(1번, 2번 실행 후 3번 실행)
 */
class LoginActivity : ComponentActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoroutineApp(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CoroutineApp(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {

    viewModel.getUserInfo("jinkweonko", "123123")
}