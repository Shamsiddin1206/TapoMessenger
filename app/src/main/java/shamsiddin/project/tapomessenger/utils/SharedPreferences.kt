package shamsiddin.project.tapomessenger.utils

import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import shamsiddin.project.tapomessenger.model.User

class SharedPreferences private constructor(context: Context){
    private val shared = context.getSharedPreferences("data", 0)
    private val edit = shared.edit()
    val gson = Gson()

    companion object{
        private var instance: SharedPreferences? = null
        fun getInstance(context: Context): SharedPreferences{
            if (instance == null){
                instance = SharedPreferences(context)
            }
            return instance!!
        }
    }

    fun setUser(mutableList: MutableList<User>){
        edit.putString("userData", gson.toJson(mutableList)).apply()
    }
    fun getUser(): MutableList<User>{
        val data: String = shared.getString("userData", "")!!
        if (data == "") {
            return mutableListOf()
        }
        val typeToken = object :  TypeToken<MutableList<User>>() {}.type
        return gson.fromJson(data, typeToken)
    }


}