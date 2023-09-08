package builder

import models.Comment
import utils.IdGenerator
import utils.timestamp

class CommentBuilder {
    private  var comment: Comment ?= Comment()
    fun setCommentId():CommentBuilder{
        comment?.commentId = IdGenerator.commentId()
        return this
    }
    fun setUserCommented(userCommented:String):CommentBuilder {
        comment?.userCommented = userCommented
        return this
    }
    fun setMessage(message:String):CommentBuilder{
        comment?.message = message
        return this
    }
    fun setTimeStamp():CommentBuilder{
        comment?.timestamp = timestamp.setTimeStamp(System.currentTimeMillis())
        return this
    }
    fun build():Comment{
        return this.comment!!
    }


}