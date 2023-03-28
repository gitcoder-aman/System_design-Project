package services

import data.Dao
import exception.CustomException
import model.PostModel

class PostService {
    private fun PostService() {

    }

    companion object {
        private var instance: PostService? = null
        fun getInstance(): PostService? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = PostService()
                    }
                }
            }
            return instance
        }
    }

    val postDao: Dao = Dao.getInstance()

     fun postFeed(postText: String): PostModel? {
        if (postText.isEmpty()) {
            return throw CustomException("Post is empty ! Please something write .")
        }
        return postDao.postFeed(postText)
    }

     fun upvote(postId: String) {
        if (postId.isEmpty()) {
            println("Post Id is empty")
        } else {
            postDao.upvote(postId)
        }
    }
     fun downvote(postId: String) {
        if (postId.isEmpty()) {
            println("Post Id is empty")
        } else {
            postDao.downvote(postId)
        }
    }

    fun followUser(userName: String?) {
        if(userName?.isEmpty() == true){
            println("User name is empty")
        }else{
            postDao.followUser(userName)
        }
    }

    fun replyUser(postId: String?, replyPost: String?) {
        if(postId?.isEmpty() == true && replyPost?.isEmpty() == true){
            println("Enter postId and post Description")
        }else{
            postDao.replyUser(postId,replyPost)
        }
    }
    fun allPostShow(){
        postDao.allPostShow()
    }
}