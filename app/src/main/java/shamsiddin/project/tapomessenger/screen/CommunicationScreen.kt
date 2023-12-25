package shamsiddin.project.tapomessenger.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.model.Messages
import shamsiddin.project.tapomessenger.navigation.ScreenType
import shamsiddin.project.tapomessenger.utils.Firebase
import shamsiddin.project.tapomessenger.utils.SharedPreferences

@Composable
fun CommunicationScreen(navController: NavController, to: String){
    CommunicationView(navController = navController, context = LocalContext.current, to)
}

@Composable
fun CommunicationView(navController: NavController, context: Context, to: String){
    var enterMessage by remember { mutableStateOf("") }
    val sharedPreferences = SharedPreferences.getInstance(context)
    val userKey = sharedPreferences.getUser()[0].key!!

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

        val messages = remember { mutableStateListOf<Messages>() }
        Firebase.getMessages(LocalContext.current, to) { m ->
            messages.clear()
            messages.addAll(m)
        }
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()){
            items(messages.size) { index ->
                MessageItem(messages[index])
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = enterMessage,
                onValueChange = { enterMessage = it },
                label = { Text(text = "Enter message") },
                trailingIcon = {
                    IconButton(onClick = {
                        Firebase.sendMessage(enterMessage.trim(), to, userKey, context)
                        enterMessage = ""
                    }) {
                        Icon(painterResource(id = R.drawable.send_ic), contentDescription = "")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}


@Composable
fun MessageItem(
    message: Messages
) {
    val currentUserKey = SharedPreferences.getInstance(LocalContext.current).getUser()[0].key!!
    val fromMe = message.from == currentUserKey
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        if (fromMe) Spacer(modifier = Modifier.width(100.dp))
        Card(
            modifier = Modifier
                .padding(12.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(if (fromMe) Color.Blue else Color.Gray)
        ) {
            Text(
                text = message.message!!,
                color = Color.White,
                modifier = Modifier.padding(12.dp),
                textAlign = if (fromMe) TextAlign.End else TextAlign.Start
            )
        }
        if (!fromMe) Spacer(modifier = Modifier.width(100.dp))
    }
}

@Composable
@Preview
fun CommunicationPreview(){
    CommunicationView(
        navController = rememberNavController(),
        context = LocalContext.current,
        to = ""
    )
}