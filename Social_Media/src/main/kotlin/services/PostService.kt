package services

import data.Dao
import models.Comment
import models.Post
import utils.timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


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

    private val postDao: Dao = Dao.getInstance()
    fun post(message: String) {
        if (message.isEmpty()) {
            println("message can not be empty")
            return
        }
        postDao.post(message)
    }

    fun upvote(postId: String) {
        if (postId.isEmpty()) {
            println("postId can not be empty")
            return
        }
        postDao.upvote(postId)
    }

    fun downVote(postId: String) {
        if (postId.isEmpty()) {
            println("postId can not be empty")
            return
        }
        postDao.downVote(postId)
    }

    fun showNewsFeed() {
        val postList: ArrayList<Post> = postDao.showPost()
//        Collections.sort(postList, SortByDate())
        Collections.sort(postList, SortByVote())
        Collections.sort(postList, SortByCommentCount())

        for (post: Post in postList) {
            println("Post Id: " + post.getPostId())
            println("(" + post.getUpvote() + " upvotes " + post.getDownvote() + " downvotes)")
            println("Post UserName: " + post.getUserName())
            println("Post message: " + post.getMessage())
            println("Post Created Date: " + timestamp.setTimeStamp(post.getTimestamp()))

            val commentList = postDao.getCommentList(post.getPostId())
            for (comment: Comment in commentList) {
                println("         id: " + comment.commentId)
                println("         commented By: " + comment.userCommented)
                println("         comment: " + comment.message)
                println("         comment Created Date: " + comment.timestamp)
            }
            println()
            println()
        }
    }
}

class SortByCommentCount : Comparator<Post> {
    override fun compare(o1: Post?, o2: Post?): Int {
        return o2?.getCommentCount()?.minus(o1?.getCommentCount()!!)!!
    }
}

class SortByVote : Comparator<Post> {
    override fun compare(o1: Post?, o2: Post?): Int {
        println("o1 value "+o1?.getScore())
        println("o2 value "+o2?.getScore())
        return o2?.getScore()?.minus(o1?.getScore()!!)!!
    }

}

class SortByDate : Comparator<Post> {
    override fun compare(o1: Post?, o2: Post?): Int {
        return ((o2?.getTimestamp())?.minus(o1?.getTimestamp()!!))?.toInt()!!
    }

}
