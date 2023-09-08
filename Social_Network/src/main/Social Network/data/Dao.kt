package data

import exception.CustomException
import model.PostModel
import model.UserModel
import utils.IdGenerator
import java.sql.Timestamp
import kotlin.math.sign

class Dao {
    private fun Dao() {

    }

    companion object {
        @Volatile
        private var instance: Dao? = null

        fun getInstance(): Dao {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Dao()
                    }
                }
            }
            return instance!!
        }
    }

    private val loginMap: HashMap<String, UserModel> = HashMap() //phoneNumber,UserModel
    private var loggedInUser: UserModel? = null

    private val postMap: HashMap<String, PostModel> = HashMap() //postId,PostModel
    private val postIdList: ArrayList<String> = ArrayList() //all post id list for showing all post with help of post id

    fun signupUser(userName: String): String {

        if (loginMap.containsKey(userName)) {
             throw CustomException("User is already created! try to new user")
        }

        val signUpModel = UserModel()
        signUpModel.userId = IdGenerator.getUserId()
        signUpModel.userName = userName

        loginMap[userName] = signUpModel
        println("Successful create this user with User Id " + signUpModel.userName)
        return signUpModel.userId!!
    }

    fun loginUser(userName: String): UserModel {
        if (!loginMap.containsKey(userName)) {
            return throw CustomException("User not found!")
        }
        loggedInUser = loginMap[userName]!!
        println("${loggedInUser?.userName} user login successfully")
        allPostShow()
        return loggedInUser!!
    }

    fun allPostShow() {
        if (postMap.isEmpty()) {
            println("No any one post")
        } else {
            for (i in postIdList) {
                println(postMap[i]?.postId)
                println("( " + postMap[i]?.upvotes + " upvotes , " + postMap[i]?.downvotes!! + " downvotes )")
                println(postMap[i]?.userName)
                println(postMap[i]?.post)
                println(postMap[i]?.timestamp)

                //all post show particular post Id
                for (i in postMap[i]?.replyPost!!) {

                    println("         " + i.postId)
                    println("         " + i.userName)
                    println("         " + i.post)
                    println("         " + i.timestamp)
                }
//                for(i in postMap[i].replyPost)
            }
        }
    }

    fun postFeed(postText: String): PostModel? {
        if (loggedInUser == null) {
            return throw CustomException("Please first Login User")
        }

        val postModel: PostModel = PostModel()
        postModel.postId = IdGenerator.getPostId()
        postModel.upvotes = 0
        postModel.downvotes = 0
        postModel.userName = loggedInUser?.userName
        postModel.post = postText

        val currentMillis = System.currentTimeMillis()
        val timeStamp = Timestamp(currentMillis)
        postModel.timestamp = timeStamp.toString()

        //save on dataStructure
        postMap[postModel.postId!!] = postModel  // save the post
        postIdList.add(postModel.postId!!)  //add to post id in array list

        println("Your post published")
        return postModel
    }

    fun upvote(postId: String) {
        if (!postMap.containsKey(postId)) {
            println("No any one post with this given post Id")
        } else {
            postMap[postId]?.upvotes = postMap[postId]?.upvotes?.plus(1)
            println("You do upvoted")
        }
    }

    fun downvote(postId: String) {
        if (!postMap.containsKey(postId)) {
            println("No any one post with this given post Id")
        } else {
            postMap[postId]?.downvotes = postMap[postId]?.downvotes?.plus(1)
            println("You do downvoted")
        }
    }

    fun followUser(userName: String?) {
        if (!loginMap.containsKey(userName)) {
            println("No one User this Name")
        } else {
            loggedInUser?.followUser?.add(userName!!)
            println("you just follow this $userName")
        }
    }

    fun replyUser(postId: String?, replyPost: String?) {
        if (!postMap.containsKey(postId)) {
            println("here no post of this postID")
        } else {
            val postModel: PostModel = PostModel()
            postModel.postId = IdGenerator.getPostId()
            postModel.userName = loggedInUser?.userName
            postModel.post = replyPost

            val currentMillis = System.currentTimeMillis()
            val timeStamp = Timestamp(currentMillis)
            postModel.timestamp = timeStamp.toString()
            //add to arrayList of reply post
            postModel.replyPost.add(postModel)

            postMap[postId]?.replyPost?.add(postModel) //multiple comment store of one post id

            println("you replied to this postId $postId")
        }
    }

    fun followUserShow() {
        if (loggedInUser?.followUser?.isEmpty() == true) {
            println("No any one you follow user")
        } else {
            println("You are following these all Users")
            for (followUser in loggedInUser?.followUser!!) {
                println(followUser)
            }
        }
    }
}