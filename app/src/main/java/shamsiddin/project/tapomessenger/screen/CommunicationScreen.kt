package shamsiddin.project.tapomessenger.screen

import android.content.Context
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.model.User
import shamsiddin.project.tapomessenger.navigation.ScreenType

@Composable
fun CommunicationScreen(navController: NavController){
    CommunicationView(navController = navController, context = LocalContext.current)
}

@Composable
fun CommunicationView(navController: NavController, context: Context){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(android.graphics.Color.parseColor("#33BDE6"))), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Image(painter = painterResource(id = R.drawable.back_ic), contentDescription = "", modifier = Modifier
                .size(30.dp)
                .clickable { navController.navigate(ScreenType.Chats.route) })
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.personprofile_example),
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(50)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Shamsiddin", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "recently", color = Color.White, fontSize = 15.sp)
            }
        }
    }
}

@Composable
@Preview
fun CommunicationPreview(){
    CommunicationView(navController = rememberNavController(), context = LocalContext.current)
}