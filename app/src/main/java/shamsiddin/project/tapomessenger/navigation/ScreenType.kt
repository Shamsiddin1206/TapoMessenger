package shamsiddin.project.tapomessenger.navigation

sealed class ScreenType(val route: String){
    object Splash: ScreenType("splash_screen")
    object Registration: ScreenType("registration_screen")
    object Login: ScreenType("login_screen")
    object Chats: ScreenType("chats_screen")
    object Contacts: ScreenType("contacts_screen")
    object Communication: ScreenType("communication_screen")
    object Profile: ScreenType("profile_screen")
}
