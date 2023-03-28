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
        public fun signupUser(userName:String):UserModel{
            if(userName.isEmpty()){
                return throw CustomException("UserName is null")
            }
            return userDao.signupUser(userName)
        }
        //login user
        public fun loginUser(userName:String):UserModel{
            if(userName.isEmpty()){
                return throw CustomException("UserName is null")
            }
            return userDao.loginUser(userName)
        }
}