import model.PostModel
import model.UserModel
import services.PostService
import services.UserService
import utils.commandEnum

fun main(args: Array<String>) {

    enumValues<commandEnum>().forEach { print(it.name + " ") }
    println()

    val userService = UserService.getInstance()
    val postService = PostService.getInstance()
    do {
        println("What can do perform.")
        val input = readlnOrNull()
        println("You entered: $input")

        if (input.equals("signup")) {
            println("Enter user name for signup")
            val userName = readlnOrNull()
            val userId: String = userService.signupUser(userName!!)
            println("UserName : $userId")
        } else if (input.equals("login")) {
            println("Enter user name for login")
            val userName = readlnOrNull()
            val user2: UserModel = userService.loginUser(userName!!)
//            println("UserName : " + user2.userName)
        } else if (input.equals("post")) {
            println("Enter any post")
            val postInput = readlnOrNull()
            val post1: PostModel? = postService?.postFeed(postInput!!)
        } else if(input.equals("upvote")){
            println("Enter postId for upvote")
            val postId = readlnOrNull()
            postService?.upvote(postId!!)
        } else if(input.equals("downvote")){
            println("Enter postId for downvote")
            val postId = readlnOrNull()
            postService?.downvote(postId!!)
        } else if(input.equals("follow")){
            println("Enter userName which one to follow")
            val userName = readlnOrNull()
            postService?.followUser(userName)
        } else if(input.equals("reply")){
            println("Enter postId and replyPost")
            val postId = readlnOrNull()
            val replyPost = readlnOrNull()
            postService?.replyUser(postId,replyPost)
        }else if(input.equals("shownewsfeed")){
            postService?.allPostShow()
        }else if(input.equals("showfollow")){
            postService?.followUserShow()
        }else{
            input.equals("logout")
        }
    } while (!input.equals("logout"))
}