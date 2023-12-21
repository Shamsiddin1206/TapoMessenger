package shamsiddin.project.tapomessenger.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

    }
}