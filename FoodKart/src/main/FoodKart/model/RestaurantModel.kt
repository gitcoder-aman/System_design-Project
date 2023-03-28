package model

class RestaurantModel{

    var restaurantId: Int ?=null
        get() = field
        set(value) {
            field = value
        }
    var restaurantName: String ?=null
        get() = field
        set(value) {
            field = value
        }

    var item: String?=null
        get() = field
        set(value) {
            field = value
        }

    var quantity: Int ?=null
        get() = field
        set(value) {
            field = value
        }

    var price: Int ?=null
        get() = field
        set(value) {
            field = value
        }
    var rating: Float ?=null
        get() = field
        set(value) {
            field = value
        }
    var listReview:ArrayList<ReviewModel> = ArrayList()
        get() = field
        set(value) {
            field = value
        }

    var listPinCode:List<Long> = ArrayList()
        get() = field
        set(value){
            field = value
        }
    var createdBy:Int ?= null
        get() = field
        set(value){
            field =value
        }

}