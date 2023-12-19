package shamsiddin.project.tapomessenger.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import shamsiddin.project.tapomessenger.R

@Composable
fun LoginScreen(navController: NavController){
    LoginView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(){
    var username by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberStatus = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp, 20.dp, 12.dp, 10.dp)) {
        Column (Modifier.weight(1f)){
           Text(text = "Hi, Welcome Back!\uD83D\uDC4B", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
           Spacer(modifier = Modifier.height(3.dp))
           Text(text = "You have been missed!", fontSize = 15.sp, fontStyle = FontStyle.Italic, color = Color.Gray)

            SignInLottie(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(350.dp)
            )
       }

        Column(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = username,
                    onValueChange = {username = it},
                    label = { Text(text = "Email Address")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = { Text(text = "Password")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberStatus.value,
                        onCheckedChange = {rememberStatus.value = it},
                        colors = CheckboxDefaults.colors(checkedColor = Color(android.graphics.Color.parseColor("#33BDE6")), checkmarkColor = Color.White)
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(text = "Remember Me")

                    Text(text = "Forgot password", textAlign = TextAlign.End, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 7.dp, 0.dp), color = Color.Red, fontWeight = FontWeight.Medium)
                }
            Spacer(modifier = Modifier.height(50.dp))
            
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#33BDE6"))),
        ) {
            Text(text = "Login", color = Color.White, fontSize = 16.sp)
        }


    }
}

@Composable
fun SignInLottie(modifier: Modifier){
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.signin_lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}

@Composable
@Preview
fun LoginPreview(){
    LoginView()
}