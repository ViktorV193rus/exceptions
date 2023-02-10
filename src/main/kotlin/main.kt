import java.lang.RuntimeException

data class Post(
    val id: Int = 0,
    val authorId: Long = 0,
    val authorName: String? = null,
    val content: String? = null,
    val published: Long = 0,
    val date: Int = 0,
    val repost: String? = null,
    val search: String? = null,
    val checkCopyrightLink: String? = null,
    var likes: Likes = Likes(0),
    var attachments: Array<Attachments> = emptyArray()
)

data class Comment(
    val count: Long = 0,
    val text: String? = null,
    val id: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

class Likes(likes: Int) {
    var likes = likes
        set(value) {
            if (value < 0) {
                return
            }
            field = value
        }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var standartPostId: Int = 1
    private var standartCommentID: Int = 1
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        posts += post.copy(id = standartPostId)
        standartPostId++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, item) in posts.withIndex()) {
            if (item.id == post.id) {
                posts[index] = post.copy(id = post.id, date = post.date)
                return true
            }
        }
        return false
    }

    fun createComment(comment: Comment, postId: Int): Comment {
        for (post in posts) {
            if (post.id == postId) {
                standartCommentID++
                val createComment = comment.copy(id = standartCommentID)
                comments += createComment
                return comments.last()
            }
        }
        throw PostNotFoundException(message = String())
    }

    class PostNotFoundException(message: String) : RuntimeException(message) //{
        //override val message: String
            //get() = "ошибка"
    //}

    fun printPost() {
        for (post in posts) {
            println(post)
        }
        println()
    }
}


fun main() {
    WallService.add(
        post = Post(
            1, 100, "Ivan", "lala", 5, 2021, "Nina", "",
            "", Likes(0), attachments = arrayOf(VideoAttachments(Video(1, "ala")))
        )
    )
    WallService.createComment(Comment(1, "good", 1,
        canPost = false,
        groupsCanPost = false,
        canClose = false,
        canOpen = false
    ),1)

    WallService.printPost()

}

