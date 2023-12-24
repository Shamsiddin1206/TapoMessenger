package shamsiddin.project.tapomessenger.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import shamsiddin.project.tapomessenger.model.Messages
import shamsiddin.project.tapomessenger.model.User

class Firebase private constructor(){
    companion object{
        private val users = FirebaseDatabase.getInstance().reference.child("users")

        fun signup(user: User, context: Context, callback: (Boolean) -> Unit){
            val sharedPreferences = SharedPreferences.getInstance(context)
            val mutableList = mutableListOf<User>()
            val key = users.push().key.toString()
            user.key = key
            users.child(key).setValue(user)
            mutableList.add(user)
            sharedPreferences.setUser(mutableList)
            callback(true)
        }

        fun checkUsername(username: String, callback: (Boolean) -> Unit){
            users.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.children.toMutableList().isEmpty()){
                        callback(true)
                    }else{
                        p0.children.forEach {
                            val user = it.getValue(User::class.java)
                            if (username == user!!.username){
                                callback(false)
                                return
                            }
                        }
                    }

                    callback(true)
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.d("checkUsername", "Access denied")
                }
            })
        }


        fun signIn(username: String, password: String, context: Context, callback: (Boolean) -> Unit){
            val sharedPreferences = SharedPreferences.getInstance(context)
            val mutableList = mutableListOf<User>()
            users.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        val user = it.getValue(User::class.java)
                        if (username == user!!.username && password == user.password){
                            mutableList.add(user)
                            sharedPreferences.setUser(mutableList)
                            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                            callback(true)
                            return
                        }
                    }
                    Toast.makeText(context, "Wrong password or username", Toast.LENGTH_SHORT).show()
                    callback(false)
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

        fun getAllUsers(callback: (MutableList<User>) -> Unit){
            users.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val mutableList = mutableListOf<User>()
                    snapshot.children.forEach {
                        mutableList.add(it.getValue(User::class.java)!!)
                    }
                    callback(mutableList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("getAllUsers", "$error")
                }

            })
        }

        fun getChats(key: String, callback: (users: MutableList<User>, lastMessage: MutableList<Messages>) -> Unit){
            val userMessages = users.child(key).child("messages")
            userMessages.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val allMessages = mutableListOf<Messages>()
                    val allUsers = mutableListOf<User>()
                    val lastMessage = mutableListOf<Messages>()
                    val allKeys = mutableListOf<String>()
                    snapshot.children.forEach {
                        val message = it.getValue(Messages::class.java)
                        allMessages.add(message!!)
                    }
                    if (allMessages.isEmpty()) callback(allUsers, lastMessage)
                    allMessages.sortByDescending { it.date }
                    allMessages.forEach {
                        val userKey = if (it.from == key) it.to else it.from
                        if (!allKeys.contains(it.key)){
                            allKeys.add(userKey!!)
                            lastMessage.add(it)
                        }
                    }
                    allKeys.forEach {

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("getChats", "onCancelled: $error")
                }

            })
        }

    }
}