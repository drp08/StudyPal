import io.github.drp08.studypal.Subject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class Greeting {
    private val client = HttpClient()

    suspend fun greeting(): Subject {
        val response = client.post("http://146.169.175.215:8080/schedule") {
            setBody("Chemistry")
        }
        return response.body<Subject>()
    }
}