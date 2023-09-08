package data

import builder.CommentBuilder
import builder.PostBuilder
import builder.UserBuilder
import models.Comment
import models.Post
import models.User
import services.PostService
import services.UserService
import java.lang.Exception

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

    private var loggedInUser: User? = null
    private val userHashMap: HashMap<String, User> = HashMap()  //Name,UserModel
    private val postList:ArrayList<Post> = ArrayList()
    private val postHashMap:HashMap<String,Post> = HashMap() //postId,PostModel
    private val postCommentHashMap:HashMap<String,ArrayList<Comment>> = HashMap() //postId,ArrayList of comment

    fun existsUser(userName: String): Boolean {
        if (!userHashMap.containsKey(userName)) {
            return false
        }
        return true
    }

    fun signupUser(userName: String) {
        val userBuilder: UserBuilder = UserBuilder()
        val user: User = userBuilder
                         .setUserId()
                         .setUserName(userName)
                         .setFollowing()
                         .build()!!
        userHashMap[userName] = user
        println("Sign up successfully with "+user.userId)
    }

    fun loginUser(userName: String) {
        loggedInUser = userHashMap[userName]
        println("login successfully with "+loggedInUser?.userName)
    }

    fun follow(userName: String) {
        loggedInUser?.userFollowing?.add(userHashMap[userName]!!)
    }

    fun post(message: String) {
        if(loggedInUser != null) {
            val postBuilder: PostBuilder = PostBuilder()
            val post: Post = postBuilder
                .setPostId()
                .setMessage(message)
                .setUserName(loggedInUser?.userName!!)
                .setUpvote()
                .setDownVote()
                .setTimestamp()
                .setScore()
                .setCommentCount()
                .build()
            postList.add(post) // post add in arraylist
            postHashMap[post.getPostId()] = post //map  postId to postModel
            postCommentHashMap[post.getPostId()] = ArrayList<Comment>()
        }else{
            throw Exception("first Login again")
        }
    }
    fun commentPost(postId: String, comment: String) {
        if(!postCommentHashMap.containsKey(postId)){
            throw Exception("Post Does not exist")
        }
        val commentBuilder = CommentBuilder()
        val comment:Comment = commentBuilder
            .setCommentId()
            .setUserCommented(loggedInUser?.userName!!)
            .setMessage(comment)
            .setTimeStamp()
            .build()

        postCommentHashMap[postId]?.add(comment)  // comment add in arraylist and map to postID
        postHashMap[postId]?.setCommentCount()  //commentCount increase by postID

        println("Comment added into postId: $postId")
    }

    fun upvote(postId: String) {
        val post: Post? = postHashMap[postId]
        post?.setUpvote(post?.getUpvote()?.plus(1)!!)
        post?.setScore()  //update the score
    }

    fun downVote(postId: String) {
        val post: Post? = postHashMap[postId]
        post?.setDownvote(post?.getDownvote()?.plus(1)!!)
        post?.setScore() //update the score
    }

    fun showPost(): ArrayList<Post> {
        return postList
    }
    fun getCommentList(postId:String):ArrayList<Comment>{
        return postCommentHashMap[postId]!!
    }
}