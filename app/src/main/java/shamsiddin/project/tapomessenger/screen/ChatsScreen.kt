package shamsiddin.project.tapomessenger.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tapomessenger.R

@Composable
fun ChatsScreen(navController: NavController){
    ChatsView(navController = navController)
}
@Composable
fun ChatsView(navController: NavController){
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
            Spacer(modifier = Modifier.width(10.dp))
            Image(painter = painterResource(id = R.drawable.menu_ic), contentDescription = "", modifier = Modifier.size(35.dp))
            Text(text = "TapoMessenger", color = Color.White, fontSize = 17.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        val arr = mutableListOf<Int>(R.drawable.personprofile_example,R.drawable.personprofile_example,R.drawable.personprofile_example,R.drawable.personprofile_example,R.drawable.personprofile_example,R.drawable.personprofile_example,R.drawable.personprofile_example)
        LazyColumn(modifier = Modifier){
            items(arr.size){index ->
                arr[index]
                ChatItem(int = arr[index])
            }
        }

    }
}

@Composable
fun ChatItem(int: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = int),
            contentDescription = "",
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Dilmurod Yo'ldoshev", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(5.dp, 0.dp, 20.dp, 0.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Manga shu ishlarni qilib ber", color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(5.dp, 0.dp, 20.dp, 0.dp), fontSize = 16.sp, maxLines = 1)
        }
    }
}

@Composable
@Preview
fun ChartsPreview(){
    ChatsView(navController = rememberNavController())
}


