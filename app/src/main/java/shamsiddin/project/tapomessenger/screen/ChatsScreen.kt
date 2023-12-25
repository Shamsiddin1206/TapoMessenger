package shamsiddin.project.tapomessenger.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.auth.User
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.model.Messages
import shamsiddin.project.tapomessenger.navigation.ScreenType
import shamsiddin.project.tapomessenger.utils.Firebase
import shamsiddin.project.tapomessenger.utils.SharedPreferences

@Composable
fun ChatsScreen(navController: NavController){
    ChatsView(navController = navController, LocalContext.current)
}
@Composable
fun ChatsView(navController: NavController, context: Context){
    val sharedPreferences = SharedPreferences.getInstance(context)
    val useres = remember { mutableStateListOf<shamsiddin.project.tapomessenger.model.User>() }
    val messages = remember { mutableStateListOf<Messages>() }

    Firebase.getChats(sharedPreferences.getUser()[0].key.toString()){users, lastMessage ->
        useres.clear()
        messages.clear()
        useres.addAll(users)
        messages.addAll(lastMessage)
        Log.d("TAG", useres.size.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(android.graphics.Color.parseColor("#33BDE6"))),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(0.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.personprofile_example),
                    contentDescription = "",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable { navController.navigate(ScreenType.Profile.route) },
                    contentScale = ContentScale.Crop
                )
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "TapoMessenger", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }
            Row(modifier = Modifier.weight(0.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                Image(painter = painterResource(id = R.drawable.search_ic), contentDescription = "", modifier = Modifier.clickable { navController.navigate(ScreenType.Contacts.route) })
                Spacer(modifier = Modifier.width(10.dp))
            }


            }
        Spacer(modifier = Modifier.height(10.dp))
        if (useres.isNotEmpty()){
            LazyColumn(modifier = Modifier){
                items(useres.size){
                    ChatItem(user = useres[it], messages = messages[it], navController)
                }
            }
        }

    }
}

@Composable
fun ChatItem(user: shamsiddin.project.tapomessenger.model.User, messages: Messages, navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { navController.navigate("communication_screen" + "/${user.key!!}") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.personprofile_example),
            contentDescription = "",
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = user.fullName.toString(), color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 0.dp, 20.dp, 0.dp))
            Text(text = messages.message.toString(), color = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 0.dp, 20.dp, 0.dp), fontSize = 16.sp, maxLines = 1)
        }
    }
}

@Composable
@Preview
fun ChartsPreview(){
    ChatsView(navController = rememberNavController(), LocalContext.current)
}


