package model

class PostModel() {

    var postId:String?=null
        get() = field
        set(value){
            field = value
        }
    var userName:String?=null
        get() = field
        set(value){
            field = value
        }
    var post:String?=null
        get() = field
        set(value){
            field = value
        }

    var upvotes:Int?=null
        get() = field
        set(value){
            field = value
        }
    var downvotes:Int?=null
        get() = field
        set(value){
            field = value
        }

    var timestamp:String?=null
        get() = field
        set(value){
            field = value
        }
    var replyPost:ArrayList<PostModel> = ArrayList()
        get() = field
        set(value){
            field = value
        }

}