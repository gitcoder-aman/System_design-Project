package exceptions

class UserDoesNotExistsException : Exception() {
     fun UserDoesNotExistsException(){
        // Call constructor of parent Exception
        "user does not exists please register".also { super.message == it }
    }
}