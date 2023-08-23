package com.example.projectjavasimba.presentation.auth.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth.R
import com.example.projectjavasimba.presentation.auth.viewmodel.AuthViewModel
import com.example.core.BuildConfig
import com.example.projectjavasimba.presentation.main.view.MainActivity
import com.example.projectjavasimba.presentation.ui.defaultTextColor
import com.example.projectjavasimba.presentation.ui.dimens20
import com.example.projectjavasimba.presentation.ui.primaryColor
import com.example.projectjavasimba.presentation.ui.robotoRegular
import com.example.projectjavasimba.presentation.ui.windowPadding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class AuthActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Auth()
            }
        }
    }


    @Preview
    @Composable
    fun Auth() {
        val email = if (BuildConfig.DEBUG) {
            "Radik.app"
        } else ""

        val password = if (BuildConfig.DEBUG) {
            "12345678"
        } else ""

        val isEnabled: State<Boolean> = viewModel._isEnabled.observeAsState(false)

        Column {
            AppBar("Регистрация")
            LazyColumn(
                Modifier.padding(windowPadding),
            ) {
                item { TextDescription(title = getString(com.example.projectjavasimba.R.string.auth_accros_soc_network)) }
                item { AuthSocNetwork() }
                item { TextDescription(title = getString(com.example.projectjavasimba.R.string.auth_application)) }
                item { EditText("Email:", email, true) }
                item { EditText("Пароль:", password, false) }
                item {
                    AuthButton("Войти", isEnabled.value) {
                        startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                        finish()
                    }
                }
                item { BlockWithUnderlineText() }
            }
        }

    }

    @Composable
    fun AppBar(title: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff66a636))
        ) {
            Image(
                painter = painterResource(id = com.example.projectjavasimba.R.drawable.baseline_arrow_back_24),
                contentDescription = "Назад",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .offset(x = 15.dp),
                alignment = Alignment.Center
            )
            Text(
                title,
                Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = robotoRegular,
                color = Color.White,
                fontSize = 18.sp,
            )
        }
    }

    @Composable
    fun TextDescription(title: String) {
        Text(
            text = title,
            color = defaultTextColor,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = robotoRegular,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )
    }

    @SuppressLint("CheckResult")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditText(label: String, debug: String = "", isEmail: Boolean = false) {
        var text by remember {
            mutableStateOf(debug)
        }

        DisposableEffect(text) {
            val disposable = Observable.just(text)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    if (isEmail) {
                        viewModel.setEmail(response)
                    } else {
                        viewModel.setPassword(response)
                    }
                }

            onDispose {
                disposable.dispose()
            }
        }

        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimens20),
            placeholder = { Text(text = "Введите $label:") },
            onValueChange = {
                text = it
            },
            label = { Text(label) },
            shape = TextFieldDefaults.outlinedShape
        )
    }

    @Composable
    fun AuthButton(
        title: String,
        isEnabled: Boolean,
        clickListener: () -> Unit
    ) {
        Button(
            onClick = {
                clickListener()
            },
            enabled = isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = dimens20)
                .clip(CircleShape)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor
            )
        ) {
            Text(
                text = title.uppercase(),
                fontWeight = FontWeight.Bold,
                fontFamily = robotoRegular,
                color = Color.White,
            )
        }
    }

    @Composable
    fun AuthSocNetwork() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimens20, bottom = dimens20),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(R.drawable.vk),
                contentDescription = "VK"
            )
            Image(
                painter = painterResource(R.drawable.fb),
                contentDescription = "FB"
            )
            Image(
                painter = painterResource(R.drawable.ok),
                contentDescription = "ОК"
            )
        }
    }

    @Composable
    fun BlockWithUnderlineText() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimens20),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextUnderline(title = "Забыли пароль?")
            TextUnderline(title = "Регистрация")
        }
    }

    @Composable
    fun TextUnderline(title: String) {
        Text(
            text = title,
            color = primaryColor,
            textDecoration = TextDecoration.Underline,
        )
    }

}