package builder

import models.User
import utils.IdGenerator

class UserBuilder {
    private  var user: User? = User()

    fun setUserId():UserBuilder{
        user?.userId = IdGenerator.userId()
        return this
    }
     fun setUserName(userName:String):UserBuilder{
        user?.userName = userName
        return this
    }
    fun setFollowing():UserBuilder{
        user?.userFollowing = ArrayList<User>()
        return this
    }
    fun build(): User? {
        if(user== null){
            println("user Null")
        }
        return user
    }

}