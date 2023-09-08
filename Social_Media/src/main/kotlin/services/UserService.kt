package services

import data.Dao
import exceptions.UserAlreadyExistsException
import exceptions.UserDoesNotExistsException

class UserService {
    private fun UserService(){

    }
    companion object{
        private var instance: UserService ?= null

        fun getInstance(): UserService? {
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance = UserService()
                    }
                }
            }
            return instance
        }
    }
    private val userDao: Dao = Dao.getInstance()
    private val postService: PostService? = PostService.getInstance()

    fun signupUser(userName:String){
        if(userName.isEmpty()){
            println("Name field cann't be empty")
            return
        }
        if(userDao.existsUser(userName)){
            throw UserAlreadyExistsException()
        }
        userDao.signupUser(userName)
    }
    fun loginUser(userName:String){
        if(userName.isEmpty()){
            println("Name field cann't be empty")
            return
        }
        if(!userDao.existsUser(userName)){
            throw UserDoesNotExistsException()
        }
        userDao.loginUser(userName)
        showPost()
    }
    private fun showPost(){
        postService?.showNewsFeed()
    }
     fun follow(userName:String){
        if(userName.isEmpty()){
            println("Name field cann't be empty")
            return
        }
        if(!userDao.existsUser(userName)){
            throw UserDoesNotExistsException()
        }
        userDao.follow(userName)
    }
    fun commentPost(postId:String,comment:String){
        userDao.commentPost(postId,comment)
    }

}