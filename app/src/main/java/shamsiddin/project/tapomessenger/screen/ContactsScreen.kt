package shamsiddin.project.tapomessenger.screen

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import shamsiddin.project.tapomessenger.R
import shamsiddin.project.tapomessenger.model.User
import shamsiddin.project.tapomessenger.navigation.ScreenType
import shamsiddin.project.tapomessenger.utils.Firebase
import shamsiddin.project.tapomessenger.utils.SharedPreferences

@Composable
fun ContactsScreen(navController: NavController){
    ContactView(navController = navController, LocalContext.current)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactView(navController: NavController, context: Context){
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val list = remember { mutableStateListOf<User>() }
    var history = remember{ mutableStateListOf<User>() }
    var found = remember { mutableStateListOf<User>() }
    val sharedPreferences = SharedPreferences.getInstance(context)

    Firebase.getAllUsers {
        list.clear()
        it.forEach {user ->
            if (user.key != sharedPreferences.getUser()[0].key){
                list.add(user)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(android.graphics.Color.parseColor("#33BDE6"))), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(15.dp))
            Image(painter = painterResource(id = R.drawable.back_ic), contentDescription = "", modifier = Modifier
                .size(35.dp)
                .clickable { navController.navigate(ScreenType.Chats.route) })
            Text(text = "TapoMessenger", color = Color.White, fontSize = 17.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
        Scaffold {
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it},
                onSearch = {
                    active = false
                    if (searchText.isNotEmpty()){
                        list.forEach {
                            if (it.fullName.toString().toLowerCase().contains(searchText.toLowerCase().trim())){
                                if (history.contains(it)){
                                    history.remove(it)
                                }
                                history.add(it)
                            }
                        }
                    }
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = {
                    Text(text = "Search by name")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                trailingIcon = {
                    if (active){
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "", modifier = Modifier.clickable {
                            if (searchText.isNotEmpty()){
                                searchText = ""
                            }else {
                                active = false
                            }
                            found.clear()
                        })
                    }
                }
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
                ){
                    if (active){
                        if (searchText.isEmpty()){
                            Log.d("History of Contacts", "ContactView: ${history.joinToString()}")
                            items(history.size){
                                ContactItem(user = history[it], navController)
                            }
                        }else{
                            found.clear()
                            list.forEach {user ->
                                if (user.fullName.toString().toLowerCase().contains(searchText.toLowerCase().trim())){
                                    found.add(user)
                                }
                            }
                            if (found.isNotEmpty()){
                                items(found.size){ index ->
                                    ContactItem(user = found[index], navController = navController)
                                }
                            }else{
                               Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
                                return@LazyColumn
                            }
                        }
                    }
                }
            }
            if (!active && searchText.isNotEmpty()){
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
                ){
                    items(found.size){
                        ContactItem(user = list[it], navController)
                    }
                }
            }else if (!active){
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp)
                ){
                    items(list.size){
                        ContactItem(user = list[it], navController)
                    }
                }
            }
        }

    }
}

@Composable
fun ContactItem(user: User, navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { navController.navigate(ScreenType.Communication.route) }
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
            Text(text = "recently", color = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 0.dp, 20.dp, 0.dp), fontSize = 16.sp, maxLines = 1)
        }
    }
}

@Composable
@Preview
fun ContactsPreview(){
    ContactView(navController = rememberNavController(), LocalContext.current)
}