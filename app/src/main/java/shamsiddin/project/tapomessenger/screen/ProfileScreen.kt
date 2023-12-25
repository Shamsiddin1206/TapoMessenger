package shamsiddin.project.tapomessenger.screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun ProfileScreen(navController: NavController){
    var active by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var emailadress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    val sharedPreferences = SharedPreferences.getInstance(LocalContext.current)
    val currentUser = sharedPreferences.getUser()[0]

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        SimpleProfile(user = currentUser)

        if (active){
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = {username = it.trim()},
                    label = { Text(text = currentUser.username.toString().trim(), color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    maxLines = 1,
                    leadingIcon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "") }
                )
                OutlinedTextField(
                    value = emailadress,
                    onValueChange = {emailadress = it.trim()},
                    label = { Text(text = currentUser.email.toString().trim(), color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    maxLines = 1,
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "") }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = {name = it.trim()},
                    label = { Text(text = currentUser.fullName.toString().trim(), color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    maxLines = 1,
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "") }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it.trim()},
                    label = { Text(text = currentUser.password.toString().trim(), color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    maxLines = 1,
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "") }
                )

            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            var a = 0
            if (active){
                if (username.isEmpty()){
                    username = currentUser.username.toString().trim()
                    a++
                }
                if (password.isEmpty()){
                    password = currentUser.password.toString().trim()
                    a++
                }
                if (emailadress.isEmpty()){
                    emailadress = currentUser.email.toString().trim()
                    a++
                }
                if (name.isEmpty()){
                    name = currentUser.fullName.toString().trim()
                    a++
                }

                if (a==4){
                    active = !active
                }else{
                    Firebase.updateUser(currentUser.key.toString(), User(key = currentUser.key.toString(), fullName = name.toString(), image = null, username = username.toString(), password = password.toString(), email = emailadress.toString())){
                        if (it){
                            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show()
                            var mutableList = sharedPreferences.getUser()
                            mutableList.clear()
                            mutableList.add(User(key = currentUser.key.toString(), fullName = name.toString(), image = null, username = username.toString(), password = password.toString(), email = emailadress.toString()))
                            sharedPreferences.setUser(mutableList)
                            active = !active
                        }
                    }
                }
            }else{
                active = !active
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)) {
            Text(text = if (active) "Confirm" else "Edit")
        }
//        Button(onClick = {
//            var mutableList = sharedPreferences.getUser()
//            Log.d("Mutable1", "ProfileScreen: ${mutableList.joinToString()}")
//            mutableList.clear()
//            Log.d("Mutable2", "ProfileScreen: ${mutableList.joinToString()}")
//            sharedPreferences.setUser(mutableList)
//            Log.d("Mutable3", mutableList.joinToString())
//            navController.navigate(ScreenType.Login.route) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 10.dp, end = 10.dp), colors = ButtonDefaults.buttonColors(Color.Red)
//        ) {
//            Text(text = "Log Out", color = Color.White, fontSize = 18.sp)
//        }
    }
}

@Composable
fun SimpleProfile(user: User){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.personprofile_example),
            contentDescription = "",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(Modifier.padding(start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Username: ", fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#33BDE6")), fontSize = 17.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = user.username.toString(), color = Color.Black, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(Modifier.padding(start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Full name: ", fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#33BDE6")), fontSize = 17.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = user.fullName.toString(), color = Color.Black, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(Modifier.padding(start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Email: ", fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#33BDE6")), fontSize = 17.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = user.email.toString(), color = Color.Black, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(Modifier.padding(start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Password: ", fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#33BDE6")), fontSize = 17.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = user.password.toString(), color = Color.Black, fontSize = 15.sp)
        }
    }
}

@Composable
@Preview
fun ProfilePreview(){
    val sharedPreferences = SharedPreferences.getInstance(LocalContext.current)
    val currentUser = sharedPreferences.getUser()[0]
}
