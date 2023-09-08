package services

import data.Dao
import exception.CustomException
import model.UserModel

class UserService {
    private fun UserService() {

    }

    companion object {
        @Volatile
        private var instance: UserService? = null

        fun getInstance(): UserService {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = UserService()
                    }
                }
            }
            return instance!!
        }
    }

        private val userDao: Dao = Dao.getInstance()

        //signUp User
         fun signupUser(userName:String): String {
            if(userName.isEmpty()){
                 throw CustomException("UserName is null")
            }
            return userDao.signupUser(userName)
        }
        //login user
         fun loginUser(userName:String):UserModel{
            if(userName.isEmpty()){
                return throw CustomException("user name can't be empty")
            }
            return userDao.loginUser(userName)
        }
}