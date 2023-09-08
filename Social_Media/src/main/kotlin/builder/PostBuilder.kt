package builder

import models.Post
import utils.IdGenerator

class PostBuilder{
    private  var post: Post ?= Post()

    fun setPostId(): PostBuilder {
        post?.setPostId(IdGenerator.postId())
        return this
    }

    fun setUserName(userName: String): PostBuilder {
        post?.setUserName(userName)
        return this
    }

    fun setUpvote(): PostBuilder {
        post?.setUpvote(0)
        return this
    }
    fun setDownVote(): PostBuilder {
        post?.setDownvote(0)
        return this
    }
    fun setMessage(message:String): PostBuilder {
        post?.setMessage(message)
        return this
    }
    fun setTimestamp():PostBuilder{
        post?.setTimestamp(System.currentTimeMillis())
        return this
    }
    fun setScore():PostBuilder{
        post?.setScore()
        return this
    }
    fun setCommentCount():PostBuilder{
        post?.setCommentCount()
        return this
    }
    fun build():Post{
        return this.post!!
    }

}