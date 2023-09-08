package models

class User {
    var userId:String ?= null
        get() = field
        set(value){
            field = value?.trim()
        }
    var userName:String ?= null
        get() = field
        set(value){
            field = value?.trim()
        }
    var userFollowing:ArrayList<User> ?= null
        get() = field
        set(value){
            field = value
        }

}