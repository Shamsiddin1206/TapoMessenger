package shamsiddin.project.tapomessenger.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import shamsiddin.project.tapomessenger.screen.ChatsScreen
import shamsiddin.project.tapomessenger.screen.CommunicationScreen
import shamsiddin.project.tapomessenger.screen.ContactsScreen
import shamsiddin.project.tapomessenger.screen.LoginScreen
import shamsiddin.project.tapomessenger.screen.ProfileScreen
import shamsiddin.project.tapomessenger.screen.RegistrationScreen
import shamsiddin.project.tapomessenger.screen.SplashScreen

@Composable
fun SetNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = ScreenType.Splash.route){
        composable(ScreenType.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(ScreenType.Login.route){
            LoginScreen(navController = navController)
        }
        composable(ScreenType.Registration.route){
            RegistrationScreen(navController = navController)
        }
        composable(ScreenType.Chats.route){
            ChatsScreen(navController = navController)
        }
        composable(ScreenType.Contacts.route){
            ContactsScreen(navController = navController)
        }
        composable(ScreenType.Communication.route, arguments = listOf(
            navArgument("key"){
                type = NavType.StringType
            }
        )){
            CommunicationScreen(navController = navController, it.arguments?.getString("key")!!)
        }
        composable(ScreenType.Profile.route){
            ProfileScreen(navController = navController)
        }
    }
}