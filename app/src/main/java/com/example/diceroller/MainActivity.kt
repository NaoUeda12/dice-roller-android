package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        RoundText(
            text = stringResource(R.string.title_text)
        )

        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        Button(
            onClick = {
                isRolling = true
            }
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.tap_button),
            fontSize = 19.sp,
            color = colorResource(R.color.purple_500),

            )
    }

    if (isRolling) {
        LaunchedEffect(isRolling) {
            repeat(10) { // サイコロが2.5秒間(10回×300ms)回転する
                result = (1..6).random() // ランダムなサイコロの目を表示
                delay(250L)
            }
            isRolling = false // 最後に回転を終了する
        }
    }
}

@Composable
fun RoundText(text: String) {
    Box(
        modifier = Modifier
            .size(150.dp) // 円のサイズ
            .background(color = colorResource(R.color.ivory), shape = CircleShape), // 円形の背景
        contentAlignment = Alignment.Center // 中央揃え
    ) {
        Text(
            text = text,
            fontSize = 26.sp,
            color = colorResource(R.color.purple_200),
            fontWeight = FontWeight.Bold
        )
    }
}