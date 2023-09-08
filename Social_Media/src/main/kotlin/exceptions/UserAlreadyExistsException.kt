package exceptions

class UserAlreadyExistsException : Exception() {
     fun UserAlreadyExistsException(){
        // Call constructor of parent Exception
        "user not found, please signup".also { super.message == it }
    }
}