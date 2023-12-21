package shamsiddin.project.tapomessenger.screen

import android.content.Context
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import shamsiddin.project.tapomessenger.model.User
import shamsiddin.project.tapomessenger.navigation.ScreenType
import shamsiddin.project.tapomessenger.utils.Firebase
import shamsiddin.project.tapomessenger.utils.SharedPreferences

@Composable
fun RegistrationScreen(navController: NavController){
    RegistrationView(navController = navController, LocalContext.current)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationView(navController: NavController, context: Context){
    val firebase = Firebase

    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var nameError by remember {
        mutableStateOf(false)
    }

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
            .weight(1.5f)
            .wrapContentHeight()
        ) {
            Text(text = "Create an Account", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color(android.graphics.Color.parseColor("#33BDE6")))
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it.trim()
                    if (it.length < 6){
                        nameError = true
                    } },
                isError = name.length >= 6,
                supportingText = {
                    if (nameError){
                        Text(text = "Limit $name/${6}", color = Color.Red, modifier = Modifier.fillMaxWidth())
                    } },
                label = { Text(text = "Full name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "") }
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email = it.trim()},
                label = { Text(text = "Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "") }
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {username = it.trim()},
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "") }
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password = it.trim()},
                label = { Text(text = "Password")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "")}
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()){
                        firebase.checkUsername(username){similarity ->
                            if (similarity){
                                firebase.signup(User(username = username, fullName = name, email = email, password = password, image = "", key = ""), context){
                                    if (it){
                                        Toast.makeText(context, "Successfully registered", Toast.LENGTH_SHORT).show()
                                        navController.navigate(ScreenType.Chats.route)
                                    }else{
                                        Log.d("Sign Up failed", "failed")
                                    }
                                }
                            }else{
                                Log.d("Similarity", "true")
                            }

                        }
                    } },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#33BDE6"))),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Sign Up", color = Color.White, fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Already have an account?", color = Color.Gray, fontSize = 15.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Sign In",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(ScreenType.Login.route)
                    }
                )
            }
        }
    }
}


@Composable
@Preview
fun RegistrationPreview(){
    RegistrationView(navController = rememberNavController(), LocalContext.current)
}