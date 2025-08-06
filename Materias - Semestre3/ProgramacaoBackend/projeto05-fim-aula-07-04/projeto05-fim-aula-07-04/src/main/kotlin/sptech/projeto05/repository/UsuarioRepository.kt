package sptech.projeto05.repository

import org.springframework.data.jpa.repository.JpaRepository
import sptech.projeto05.entity.Usuario

interface UsuarioRepository : JpaRepository<Usuario, Int> {

    fun findByLoginAndSenha(login: String, senha: String): Usuario?

}