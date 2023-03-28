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
    var followUser:ArrayList<String>?= ArrayList()
        get() = field
        set(value){
            field = value
        }

}