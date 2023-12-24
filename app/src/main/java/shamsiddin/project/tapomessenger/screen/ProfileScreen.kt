package shamsiddin.project.tapomessenger.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.model.User
import shamsiddin.project.tapomessenger.navigation.ScreenType
import shamsiddin.project.tapomessenger.utils.SharedPreferences

@Composable
fun ProfileScreen(navController: NavController){
    var active by remember {
        mutableStateOf(false)
    }
    val sharedPreferences = SharedPreferences.getInstance(LocalContext.current)

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        if (active){
            ProfileEdit()
        }else{

        }
    }
}

@Composable
fun ProfileEdit(){
    var name by remember { mutableStateOf("") }
    var emailadress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Image(
            painter = painterResource(id = R.drawable.personprofile_example),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@Preview
fun ProfilePreview(){
    ProfileEdit()
}
