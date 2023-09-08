import models.Post
import models.User
import services.PostService
import services.UserService
import utils.commandEnum

fun main(args: Array<String>) {

    println("Execution started")
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
             userService?.signupUser(userName!!)

        } else if (input.equals("login")) {
            println("Enter user name for login")
            val userName = readlnOrNull()
            userService?.loginUser(userName!!)
        } else if (input.equals("post")) {
            println("Enter any post")
            val postInput = readlnOrNull()
            postService?.post(postInput!!)
        } else if(input.equals("upvote")){
            println("Enter postId for upvote")
            val postId = readlnOrNull()
            postService?.upvote(postId!!)
        } else if(input.equals("downvote")){
            println("Enter postId for downvote")
            val postId = readlnOrNull()
            postService?.downVote(postId!!)
        } else if(input.equals("follow")){
            println("Enter userName which one to follow")
            val userName = readlnOrNull()
            userService?.follow(userName!!)
        } else if(input.equals("comment")){
            println("Enter postId and Comment Post")
            val postId = readlnOrNull()
            val commentPost = readlnOrNull()
            userService?.commentPost(postId!!,commentPost!!)
        }else if(input.equals("shownewsfeed")){
            postService?.showNewsFeed()
        }else{
            input.equals("logout")
        }
    } while (!input.equals("logout"))

}