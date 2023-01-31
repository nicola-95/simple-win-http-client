import libcurl.*
import kotlinx.cinterop.*

fun main() {
    val curl = curl_easy_init()
    curl?.let {
        val certPath = "C:\\Users\\Nicola\\Desktop\\cacert.pem"
        val url = "https://nicolamcornelio.com"

        setCurl(curl, certPath, url)
        val res = curl_easy_perform(curl)
        if (res != CURLE_OK) println("curl_easy_perform() failed ${curl_easy_strerror(res)?.toKString()}")
        curl_easy_cleanup(curl)

    } ?: println("curl_easy_init() failed to return curl easy handle")
}

private fun setCurl(curl: COpaquePointer?, certPath: String, url: String) {
    curl_easy_setopt(curl, CURLOPT_CAINFO, certPath)
    curl_easy_setopt(curl, CURLOPT_CAPATH, certPath)
    curl_easy_setopt(curl, CURLOPT_URL, url)
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L)
}