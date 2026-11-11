package loginnew;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static loginnew.Usuario.usuarioSistema;
import java.awt.HeadlessException;

public class TelaAlteracao extends JFrame{
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;

    private boolean atualizacaoValida;

    public TelaAlteracao() {

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Senac - Alteração");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        Color corFundo = new Color(255, 214, 227);
        Color corTexto = new Color(140, 40, 90);
        Color corBotao = new Color(244, 168, 196);
        Color corBotaoTexto = new Color(90, 20, 55);
        Color corCampo = new Color(255, 245, 248);

        tela = new JPanel();
        tela.setBackground(corFundo);
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblIdentificacao = new JLabel("Informar campos para alteração");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        lblIdentificacao.setForeground(Color.BLACK);
        tela.add(lblIdentificacao);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 35, 100, 15);
        lblNome.setForeground(corTexto);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 35, 218, 20);
        txtNome.setBackground(corCampo);
        txtNome.setForeground(corTexto);
        tela.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setBounds(24, 60, 70, 15);
        lblSenhaAtual.setForeground(corTexto);
        tela.add(lblSenhaAtual);

        passAtual = new JPasswordField();
        passAtual.setBounds(120, 60, 219, 19);
        passAtual.setBackground(corCampo);
        passAtual.setForeground(corTexto);
        tela.add(passAtual);

        JLabel lblnovasenha = new JLabel("Nova Senha");
        lblnovasenha.setBounds(24, 85, 70, 15);
        lblnovasenha.setForeground(corTexto);
        tela.add(lblnovasenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(120, 85, 219, 19);
        passSenha.setBackground(corCampo);
        passSenha.setForeground(corTexto);
        tela.add(passSenha);

        JLabel lblConfSenha = new JLabel("Confirmar Senha");
        lblConfSenha.setBounds(24, 110, 100, 15);
        lblConfSenha.setForeground(corTexto);
        tela.add(lblConfSenha);

        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(120, 110, 219, 19);
        confPassSenha.setBackground(corCampo);
        confPassSenha.setForeground(corTexto);
        tela.add(confPassSenha);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200, 136, 117, 25);
        btnAlterar.setBackground(corBotao);
        btnAlterar.setForeground(corBotaoTexto);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 136, 117, 25);
        btnCancelar.setBackground(corBotao);
        btnCancelar.setForeground(corBotaoTexto);
        tela.add(btnCancelar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try{
                Usuario usu = new Usuario();

                usu.setSenha(confPassSenha.getText());
                usu.setUsuario(usuarioSistema);

                if ("".equals(usu.getNome())){
                    JOptionPane.showMessageDialog(null,
                            "Campo nome do usuário precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    txtNome.grabFocus();
                }else if("".equals(usu.getSenha())) {
                    JOptionPane.showMessageDialog(null,
                            "Campo senha precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();
                }else if(usu.verificaUsuario(usu.getUsuario(),
                        passAtual.getText()) == false){
                    JOptionPane.showMessageDialog(null,
                            "Senha inválida, verifique!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();
                }else if(!passSenha.getText().equals(confPassSenha.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Campos de Senha e Confirmação não são iguais!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();

                }else{

                    atualizacaoValida =
                            usu.alteraUsuario(txtNome.getText(),
                                    usu.getUsuario(),
                                    usu.getSenha());

                    if (atualizacaoValida == true){
                        JOptionPane.showMessageDialog(null,
                                "Dado(s) do usuário alterado(s) retornaremos "
                                + "a tela de login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);

                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();

                        dispose();

                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao atualizar o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch (HeadlessException ec) {
                System.out.println("Erro ao alterar usuário "
                        + ec.getMessage());
            }
        });

        txtNome.setText(Usuario.nomeUsuario);
    }

    public void abreTela(){
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    }
}