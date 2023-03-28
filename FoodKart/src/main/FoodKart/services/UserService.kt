package services

import constants.enumGender
import data.Dao
import exception.CustomException
import model.UserModel

class UserService {
    private fun UserService(){

    }
    companion object {
        @Volatile private var instance: UserService? = null

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
    private val userDao:Dao = Dao.getInstance()
    public fun registerUser(phoneNumber:Long,userName:String,pinCode:Long,gender: enumGender): UserModel? {
        if(phoneNumber < 100){
            throw CustomException("Phone number is not valid")
        }else if(pinCode == null || pinCode < 6){
            throw CustomException("Invalid value for pinCode")
        }else if(userName.isEmpty()){
            throw CustomException("User Name is Empty")
        }
        return userDao.registerUser(phoneNumber,userName,pinCode,gender)
    }
    public fun login(userId:Int):UserModel?{
        return userDao.login(userId)
    }
}