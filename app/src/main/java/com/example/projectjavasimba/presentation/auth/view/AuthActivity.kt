package com.example.projectjavasimba.presentation.auth.view

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.auth.databinding.ActivityAuthBinding
import com.example.projectjavasimba.presentation.auth.viewmodel.AuthViewModel
import com.example.core.BuildConfig
import com.example.projectjavasimba.presentation.ui.dimens20
import com.example.projectjavasimba.presentation.ui.primaryColor
import com.example.projectjavasimba.presentation.ui.robotoRegular
import com.example.projectjavasimba.presentation.ui.windowPadding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        observable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Auth()
            }
        }
//        binding.run {
//            if (BuildConfig.DEBUG) {
//                etEmail.setText("Radik.app")
//                etPassword.setText("12345678")
//            }
//
//            btnAuth.setOnClickListener {
//                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
//            }
//
//            etEmail.setOnLongClickListener {
//                etEmail.setText("Radik.app")
//                etPassword.setText("12345678")
//                true
//            }
//        }
//
//        RxTextView.textChanges(binding.etEmail)
//            .mergeWith(RxTextView.textChanges(binding.etPassword))
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { email ->
//                authViewModel.setEmail(email.toString())
//                authViewModel.validAuth()
//            }
//
//
//        RxTextView.textChanges(binding.etPassword)
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { password ->
//                authViewModel.setPassword(password.toString())
//                authViewModel.validAuth()
//            }
    }

    private fun observable() {
//        authViewModel.isEnabled.observe(this) { enabled ->
//            if (enabled) {
//                binding.btnAuth.isEnabled = true
//                binding.btnAuth.background = getDrawable(R.drawable.button_background)
//            } else {
//                binding.btnAuth.isEnabled = false
//                binding.btnAuth.background = getDrawable(R.drawable.dont_view_btn_background)
//            }
//        }
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

        Column {
            AppBar()
            LazyColumn(
                Modifier.padding(windowPadding),
            ) {
                item { TextDescription(title = getString(com.example.projectjavasimba.R.string.auth_accros_soc_network)) }
                item { AuthSocNetwork() }
                item { TextDescription(title = getString(com.example.projectjavasimba.R.string.auth_application)) }
                item { EditText("Email:", email) }
                item { EditText("Пароль:", password) }
                item { AuthButton("Войти") }
                item { BlockWithUnderlineText() }
            }
        }

    }

    @Composable
    fun AppBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff66a636))
        ) {
            Text(
                "Регистрация",
                Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = robotoRegular,
                color = Color.White,
                fontSize = 15.sp,
            )
        }
    }

    @Composable
    fun TextDescription(title: String) {
        Text(
            text = title,
            color = Color(0xffb3000000),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = robotoRegular,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditText(label: String, debug: String = "") {
        var text by remember {
            mutableStateOf(debug)
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
    fun AuthButton(title: String) {
        Button(
            onClick = {

            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = dimens20)
                .clip(CircleShape)
                .height(50.dp)
                .background(primaryColor)
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