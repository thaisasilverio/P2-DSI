package loginnew;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaInicio extends JFrame {
    private final JPanel tela;

    private boolean exclusaoValida;

    public TelaInicio() {

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Menu - Senac");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        Color corFundo = new Color(255, 214, 227);
        Color corTexto = new Color(140, 40, 90);
        Color corBotao = new Color(244, 168, 196);
        Color corBotaoTexto = new Color(90, 20, 55);

        tela = new JPanel();
        tela.setBackground(corFundo);
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblUsuario = new JLabel("Bem vindo " + Usuario.nomeUsuario);
        lblUsuario.setBounds(24, 65, 200, 15);
        lblUsuario.setForeground(corTexto);
        tela.add(lblUsuario);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(2, 130, 117, 25);
        btnExcluir.setBackground(corBotao);
        btnExcluir.setForeground(corBotaoTexto);
        tela.add(btnExcluir);

        JButton btnAlterar = new JButton("Alterar Dados");
        btnAlterar.setBounds(150, 130, 117, 25);
        btnAlterar.setBackground(corBotao);
        btnAlterar.setForeground(corBotaoTexto);
        tela.add(btnAlterar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(300, 130, 117, 25);
        btnVoltar.setBackground(corBotao);
        btnVoltar.setForeground(corBotaoTexto);
        tela.add(btnVoltar);

        btnVoltar.addActionListener((ActionEvent e) -> {
            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de login "
                        + ec.getMessage());
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                TelaAlteracao telaAlterar = new TelaAlteracao();
                telaAlterar.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de alteração "
                        + ec.getMessage());
            }
        });

        btnExcluir.addActionListener((ActionEvent e) -> {
            try {
                Object[] options = { "Sim", "Não" };
                int opcao = JOptionPane.showOptionDialog(null,
                        Usuario.nomeUsuario +
                        ", deseja se excluir?", "Atenção",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[0]);

                if (opcao == 0) {
                    Usuario usu = new Usuario();

                    exclusaoValida = usu.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoValida == true) {
                        JOptionPane.showMessageDialog(null,
                                "Usuário deletado corretamente, voltaremos "
                                + "a tela de login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);

                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao excluir o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao excluir usuário "
                        + ec.getMessage());
            }
        });
    }

    public void abreTela() {
        TelaInicio telaInicio = new TelaInicio();
        telaInicio.setVisible(true);
    }
}