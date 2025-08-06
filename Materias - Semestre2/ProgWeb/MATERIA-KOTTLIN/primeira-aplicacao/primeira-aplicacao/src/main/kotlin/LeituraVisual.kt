import javax.swing.JOptionPane

fun main() {

    // abre uma "telinha" e recebe o que o usuário digitou
    val nome = JOptionPane.showInputDialog("Digite seu nome")

    // exibe a mensagem em uma "telinha"
    JOptionPane.showMessageDialog(null, "seu nome é $nome")
}