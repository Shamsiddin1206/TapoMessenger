package shamsiddin.project.tapomessenger.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun RegistrationScreen(navController: NavController){
    RegistrationView(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationView(navController: NavController){
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var name by remember { mutableStateOf("") }

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
            Text(text = "Create an Account", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color(android.graphics.Color.parseColor("#33BDE6")))
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
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
                onValueChange = {email = it},
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
                onValueChange = {username = it},
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
                onValueChange = {password = it},
                label = { Text(text = "Password")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "")}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Forgot password?", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#33BDE6")))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#33BDE6"))),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Login", color = Color.White, fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth()) {
//                Box(modifier = Modifier.height(3.dp).background(Color.Gray))
                Text(text = "OR", color = Color.Gray, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(id = R.drawable.google_ic), contentDescription = "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Login with Google", color = Color.Gray, fontSize = 17.sp)
                }
            }
        }
    }
}

@Composable
@Preview
fun RegistrationPreview(){
    RegistrationView(navController = rememberNavController())
}