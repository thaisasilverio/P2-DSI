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

public class TelaLogin extends JFrame{

    private final JPanel  panelTela;
    private final JTextField txtUsuario;
    private final JPasswordField pswSenha;
    private boolean usuarioValido;

    public TelaLogin() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login - Senac");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        Color corFundo = new Color(255, 214, 227);
        Color corTexto = new Color(140, 40, 90);
        Color corBotao = new Color(244, 168, 196);
        Color corBotaoTexto = new Color(90, 20, 55);
        Color corCampo = new Color(255, 245, 248);

        panelTela = new JPanel();
        panelTela.setBackground(corFundo);
        setContentPane(panelTela);
        panelTela.setLayout(null);

        JLabel lblIdentificacao = new JLabel("IDENTIFICAÇÃO");
        lblIdentificacao.setBounds(144, 0, 160, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        lblIdentificacao.setForeground(Color.BLACK);
        panelTela.add(lblIdentificacao);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(24, 65, 70, 15);
        lblUsuario.setForeground(corTexto);
        panelTela.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 92, 70, 15);
        lblSenha.setForeground(corTexto);
        panelTela.add(lblSenha);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        txtUsuario.setBackground(corCampo);
        txtUsuario.setForeground(corTexto);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        pswSenha.setBackground(corCampo);
        pswSenha.setForeground(corTexto);
        panelTela.add(pswSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 136, 117, 25);
        btnEntrar.setBackground(corBotao);
        btnEntrar.setForeground(corBotaoTexto);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 136, 117, 25);
        btnCadastrar.setBackground(corBotao);
        btnCadastrar.setForeground(corBotaoTexto);
        panelTela.add(btnCadastrar);

        btnCadastrar.addActionListener((ActionEvent e) -> {
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.abreTela();
            dispose();
        });

        btnEntrar.addActionListener((ActionEvent e) -> {
            Usuario usu = new Usuario();

            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(pswSenha.getText());

            if ("".equals(txtUsuario.getText())){
                JOptionPane.showMessageDialog(null,
                        "Campo usuário precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                txtUsuario.grabFocus();
            }else if ("".equals(pswSenha.getText())) {

                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                pswSenha.grabFocus();
            }else{
                usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                        usu.getSenha());

                if (usuarioValido == true){
                    JOptionPane.showMessageDialog(null,
                            "Usuário válido em nossa base de dados",
                            "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);

                    TelaInicio telaInicio = new TelaInicio();
                    telaInicio.abreTela();

                    dispose();

                }else{
                    JOptionPane.showMessageDialog(null,
                            "Usuário inválido ou inexistente",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);

                    limpaText();

                    txtUsuario.grabFocus();
                }
            }
        });
    }

    public void abreTela(){
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }

    public void limpaText(){
        txtUsuario.setText("");
        pswSenha.setText("");
    }
}