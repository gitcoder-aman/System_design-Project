package models

import utils.timestamp

class Post {
    private var postId:String ?= null
    private var upvotes:Int ?= 0
    private var downvotes:Int ?= 0
    private var userName:String ?= null
    private var message:String ?= null
    private var timestamp:Long ?= null
    private var score:Int ?= 0
    private var commentCount:Int ?= 0

    fun setPostId(postId:String){
        this.postId = postId
    }
    fun getPostId():String{
        return postId!!
    }
    fun setUpvote(upvote:Int){
        this.upvotes = upvote
    }
    fun getUpvote():Int{
        return upvotes!!
    }
    fun setDownvote(downvote:Int){
        this.downvotes = downvote!!
    }
    fun getDownvote():Int{
        return downvotes!!
    }
    fun setUserName(userName:String){
        this.userName = userName
    }
    fun getUserName():String{
        return userName!!
    }
    fun setMessage(message:String){
        this.message = message
    }
    fun getMessage():String{
        return message!!
    }
    fun setTimestamp(timestamp:Long){
        this.timestamp = timestamp
    }
    fun getTimestamp():Long{
        return timestamp!!
    }
    fun setScore(){
        this.score = upvotes?.minus(downvotes!!)
    }
    fun getScore():Int{
        return score!!
    }
    fun setCommentCount(){
        this.commentCount = commentCount?.plus(1)
    }
    fun getCommentCount():Int{
        return commentCount!!
    }

}