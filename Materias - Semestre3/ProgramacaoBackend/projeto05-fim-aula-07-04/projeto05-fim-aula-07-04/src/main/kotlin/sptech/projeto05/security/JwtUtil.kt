package sptech.projeto05.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.expiration}")
    private val jwtExpirationMs = 0
    private var key: SecretKey? = null

    // Initializes the key after the class is instantiated and the jwtSecret is injected,
    // preventing the repeated creation of the key and enhancing performance
    @PostConstruct
    fun init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret?.toByteArray(StandardCharsets.UTF_8))
    }

    // Generate JWT token
    fun generateToken(username: String?): String {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date((Date()).getTime() + jwtExpirationMs))
            .signWith(key)
            .compact()
    }

    // Get username from JWT token
    fun getUsernameFromToken(token: String?): String {
        return Jwts.parser()
            .verifyWith(key).build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    // Validate JWT token
    fun validateJwtToken(token: String?): Boolean {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
            return true
        } catch (e: SecurityException) {
            println("Invalid JWT signature: " + e.message)
        } catch (e: MalformedJwtException) {
            println("Invalid JWT token: " + e.message)
        } catch (e: ExpiredJwtException) {
            println("JWT token is expired: " + e.message)
        } catch (e: UnsupportedJwtException) {
            println("JWT token is unsupported: " + e.message)
        } catch (e: IllegalArgumentException) {
            println("JWT claims string is empty: " + e.message)
        }
        return false
    }
}