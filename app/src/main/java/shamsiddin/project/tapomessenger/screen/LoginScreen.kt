package shamsiddin.project.tapomessenger.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.navigation.ScreenType
import shamsiddin.project.tapomessenger.utils.Firebase
import shamsiddin.project.tapomessenger.utils.SharedPreferences

@Composable
fun LoginScreen(navController: NavController){
    LoginView(navController, LocalContext.current)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController, context: Context){
    val firebase = Firebase

    var username by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberStatus = remember { mutableStateOf(false) }


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo", modifier = Modifier
            .size(150.dp)
            .weight(1f))
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Text(text = "Welcome back", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(android.graphics.Color.parseColor("#33BDE6")))
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                label = { Text(text = "Username")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "")}
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "")}
            )
//            Spacer(modifier = Modifier.height(10.dp))
//            Text(text = "Forgot password?", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#33BDE6")))


        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()){
                    firebase.signIn(username = username, password = password, context = context){
                        if (it){
                            navController.navigate(ScreenType.Chats.route)
                        }
                    }
                } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#33BDE6"))),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Login", color = Color.White, fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Have not registered yet?", color = Color.Gray, fontSize = 15.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Sign Up",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(ScreenType.Registration.route)
                    }
                )
            }
        }
    }

//    Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(12.dp, 20.dp, 12.dp, 10.dp)) {
//        Column (Modifier.weight(1f)){
//           Text(text = "Hi, Welcome Back!\uD83D\uDC4B", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
//           Spacer(modifier = Modifier.height(3.dp))
//           Text(text = "You have been missed!", fontSize = 15.sp, fontStyle = FontStyle.Italic, color = Color.Gray)
//
//            SignInLottie(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .size(350.dp)
//            )
//       }
//
//        Column(Modifier.fillMaxWidth()) {
//                OutlinedTextField(
//                    value = username,
//                    onValueChange = {username = it},
//                    label = { Text(text = "Email Address")},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(60.dp),
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//                OutlinedTextField(
//                    value = password,
//                    onValueChange = {password = it},
//                    label = { Text(text = "Password")},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(60.dp),
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(5.dp))
//                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(
//                        checked = rememberStatus.value,
//                        onCheckedChange = {rememberStatus.value = it},
//                        colors = CheckboxDefaults.colors(checkedColor = Color(android.graphics.Color.parseColor("#33BDE6")), checkmarkColor = Color.White)
//                    )
//                    Spacer(modifier = Modifier.width(1.dp))
//                    Text(text = "Remember Me")
//
//                    Text(text = "Forgot password", textAlign = TextAlign.End, modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(0.dp, 0.dp, 7.dp, 0.dp), color = Color.Red, fontWeight = FontWeight.Medium)
//                }
//            Spacer(modifier = Modifier.height(50.dp))
//
//        }
//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(55.dp),
//            shape = RoundedCornerShape(5.dp),
//            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#33BDE6"))),
//        ) {
//            Text(text = "Login", color = Color.White, fontSize = 16.sp)
//        }
//
//
//    }
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
    LoginView(rememberNavController(), LocalContext.current)
}