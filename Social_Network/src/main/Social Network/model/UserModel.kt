package model

class UserModel() {

    var userId:String?=null
        get() = field
        set(value){
            field = value
        }
    var userName:String?=null
        get() = field
        set(value){
            field = value
        }
    var phoneNumber:String?=null
        get() = field
        set(value){
            field = value
        }
    var followUser:MutableSet<String>? = null
        get() = field
        set(value){
            field = value
        }

}