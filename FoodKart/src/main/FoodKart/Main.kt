import constants.enumGender
import model.OrderModel
import model.RestaurantModel
import model.ReviewModel
import model.UserModel
import services.RestaurantService
import services.UserService
import services.OrderService

fun main() {

    println("Execution Started")

    val userService :UserService = UserService.getInstance()
    val restaurantService:RestaurantService = RestaurantService.getInstance()
    val orderService:OrderService = OrderService.getInstance()

    val user1: UserModel? = userService.registerUser(123456789,"Aman",804404,enumGender.MALE)
    val user2: UserModel? = userService.registerUser(829488812,"Rohit",823003,enumGender.MALE)
    val user3: UserModel? = userService.registerUser(443567543,"Anushka",81002,enumGender.FEMALE)

    //first user login
    var login :UserModel = userService.login(user1?.userId!!)!!
    println(login.userName)

    var restaurant1:RestaurantModel ?= restaurantService.registerRestaurant("Chandan Briyani","804404,81002,10003,801001","Briyani",300,10)
    val restaurant2:RestaurantModel ?= restaurantService.registerRestaurant("Gaurav Restaurant","804404,81002,10003,801001","Burger",150,7)

    val reviewModel1:ReviewModel = restaurantService.rateRestaurant("Chandan Briyani",3.0F,"Good")
    val reviewModel2:ReviewModel = restaurantService.rateRestaurant("Gaurav Restaurant",5.0F,"Best")
//    println("Rating by user: "+reviewModel1.rating)
//    println("Comment by user: "+reviewModel1.comment)
    println()

    restaurantService.showListOfRestaurant("rating")

    //update the quantity
    restaurant1 = restaurantService.updateQuantity("Gaurav Restaurant",20)
    println(restaurant1?.quantity)

    //second user login
    login = userService.login(user2?.userId!!)!!
    println(login.userName)

    val restaurant3:RestaurantModel ?= restaurantService.registerRestaurant("Himalaya Restaurant","804404,81002,10003,801001,823003","Paneer",120,7)
    println(restaurant3?.quantity)

    //rate the restaurant
   val reviewModel3:ReviewModel = restaurantService.rateRestaurant("Himalaya Restaurant",4.0f,"Nice")
    println("Rating by user: "+reviewModel3.rating)
    println("Comment by user: "+reviewModel3.comment)

    //show Restaurant
    restaurantService.showListOfRestaurant("rating")

    val orderModel:OrderModel = orderService.placeOrder("Himalaya Restaurant",5)

    println("Logging user: "+login.userName)

    orderService.listOrderHistory()

}