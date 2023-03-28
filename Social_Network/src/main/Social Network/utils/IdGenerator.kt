package utils

class IdGenerator {
    companion object{
        private var postId:Int = 0

        public fun getPostId(): String {
            postId++
            return "00$postId"
        }
    }
}