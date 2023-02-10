import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.test.expect

class WallServiceTest {
    @Before
    fun clearBeforeTest() {

    }

    @Test(expected = WallService.PostNotFoundException::class)
    fun shouldThrow() {
        val post5 = Post(id = 5)
        WallService.add(post5)
        val comment5 = Comment(5, "ok", 5)
        WallService.createComment(comment5, 0)
    }

    @Test
    fun createComment1() {
        val post1 = Post(id = 1)
        WallService.add(post1)
        val comment9 = Comment(1, "ok", 1)
        WallService.createComment(comment9,1)
        val expected = "ok"
        val rezult = comment9.text
        assertEquals(expected, rezult)
    }


    @Test
    fun addPost() {
        val post2 = Post(2, 100, "Ivan", "text", 100, 2021, "Liza",
            search = "",
            checkCopyrightLink = "",
            likes = Likes(10)
        )
        val expected = post2
        WallService.add(post2)
        val result = WallService.add(post2)
        assertEquals(expected, result)
    }

    @Test
    fun update() {
        val service = WallService
        service.add(Post(2, 100, "Viktor", "text", 100, 2022, "Liza", "", "", Likes(1)))
        service.add(Post(3, 101, "Viktor", "text", 101, 2023, "Liza", "", "", Likes(12)))
        val update = Post(3,101, "Viktor", "text", 101, 2023, "Liza", "", "", Likes(12))

        val result = service.update(update)

        assertTrue(result)
    }
    @Test
    fun createComment() {
        val postComment = Post(1, 100, "Ivan", "text", 100, 2021, "Liza",
            search = "",
            checkCopyrightLink = "",
            likes = Likes(10)
        )
        val commentForTest1 = Comment(101, "yra", id = 1, canPost = false, groupsCanPost = false, canClose = false, canOpen = false)
        val expected = "yra"
        WallService.add(postComment)
        WallService.createComment(commentForTest1, 1)
        val result = commentForTest1.text
        assertEquals(expected, result)
    }

    @Test
    fun noPost() {
        val post1 = Post(1, 100, "Ivan", "text", 100, 2021, "Liza",
            search = "",
            checkCopyrightLink = "",
            likes = Likes(10)
        )
        WallService.add(post1)
        val commentTest2 = Comment(101, "text", id = 1, canPost = false, groupsCanPost = false, canClose = false, canOpen = false)
        WallService.createComment(commentTest2, 1)
    }
}
