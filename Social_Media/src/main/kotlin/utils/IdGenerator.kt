package utils

class IdGenerator {

    companion object {

        private var userId = 0
        private var postId = 0
        private var commentId = 0
         fun userId(): String {
            userId++
            return "U$userId"
        }

         fun postId(): String {
            postId++
            return "P$postId"
        }

         fun commentId(): String {
            commentId++
            return "C$commentId"
        }
    }
}