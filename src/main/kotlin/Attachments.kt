interface Attachments {
    val type: String
}

data class Video(val id: Int, val title: String)
data class VideoAttachments(val video: Video) : Attachments {
    override val type = "video"
}
data class Photo(val id: Int, val albumId: Int, val owenrId: Int)
data class PhotoAttachments(val photo: Photo) : Attachments {
    override val type = "photo"
}
data class Audio(val id: Int, val artist: String)
data class AudioAttachments(val audio: Audio) : Attachments {
    override val type = "audio"
}
data class Doc(val id: Int, val title: String)
data class DocAttachments(val doc: Doc) : Attachments {
    override val type = "doc"
}
data class Other(val id: Int, val others: String)
data class OtherAttachments(val other: Other) : Attachments {
    override val type = "other"
}