package shamsiddin.project.tapomessenger.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.navigation.ScreenType

@Composable
fun SplashScreen(navController: NavController){
    var startAnimation by remember{ mutableStateOf(false)}
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(ScreenType.Registration.route)
    }
    SplashView(alpha = alphaAnimation.value)
}

@Composable
fun SplashView(alpha: Float){
    Box(Modifier.fillMaxSize().background(Color.White)) {
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "splash",
            modifier = Modifier.align(Alignment.Center).size(150.dp)
        )
    }
}

@Composable
@Preview
fun SplashPreview(){
    SplashView(alpha = 1f)
}