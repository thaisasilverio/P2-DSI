package loginnew;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastro extends JFrame{
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;

    private boolean usuarioValido;
    private boolean cadastroValido;

    private String mensagemJOption;
    private int mensagemTipo = 0;

    public TelaCadastro() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 230);

        Color corFundo = new Color(255, 214, 227);
        Color corTexto = new Color(140, 40, 90);
        Color corBotao = new Color(244, 168, 196);
        Color corBotaoTexto = new Color(90, 20, 55);
        Color corCampo = new Color(255, 245, 248);

        tela = new JPanel();
        tela.setBackground(corFundo);
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblIdentificacao = new JLabel("Informar campos para cadastro");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        lblIdentificacao.setForeground(Color.BLACK);
        tela.add(lblIdentificacao);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 50, 70, 15);
        lblNome.setForeground(corTexto);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 50, 219, 19);
        txtNome.setBackground(corCampo);
        txtNome.setForeground(corTexto);
        tela.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(24, 75, 70, 15);
        lblUsuario.setForeground(corTexto);
        tela.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 75, 219, 19);
        txtUsuario.setBackground(corCampo);
        txtUsuario.setForeground(corTexto);
        tela.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblsenha = new JLabel("Senha");
        lblsenha.setBounds(24, 100, 70, 15);
        lblsenha.setForeground(corTexto);
        tela.add(lblsenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(120, 100, 219, 19);
        passSenha.setBackground(corCampo);
        passSenha.setForeground(corTexto);
        tela.add(passSenha);

        JLabel lblconfsenha = new JLabel("Confirmar Senha");
        lblconfsenha.setBounds(24, 125, 100, 15);
        lblconfsenha.setForeground(corTexto);
        tela.add(lblconfsenha);

        passConfSenha = new JPasswordField();
        passConfSenha.setBounds(120, 125, 219, 19);
        passConfSenha.setBackground(corCampo);
        passConfSenha.setForeground(corTexto);
        tela.add(passConfSenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(200, 156, 117, 25);
        btnCadastrar.setBackground(corBotao);
        btnCadastrar.setForeground(corBotaoTexto);
        tela.add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 156, 117, 25);
        btnCancelar.setBackground(corBotao);
        btnCancelar.setForeground(corBotaoTexto);
        tela.add(btnCancelar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
            dispose();
        });

        btnCadastrar.addActionListener((ActionEvent e) ->{
            try{
                Usuario usu = new Usuario();

                usu.setNome(txtNome.getText());
                usu.setUsuario(txtUsuario.getText());
                usu.setSenha(passSenha.getText());

                if("".equals(usu.getNome())){
                    mensagemJOption = "Campo nome do usuário precisa ser informado";
                    mensagemTipo = 0;
                }else if ("".equals(usu.getUsuario())){
                    mensagemJOption = "Campo usuário precisa ser informado";
                    mensagemTipo = 0;
                }else if(!usu.getSenha().equals(passConfSenha.getText())){
                    mensagemJOption = "Campos senha e confirmação de senha não coincidenm!";
                    mensagemTipo = 0;
                }else if("".equals(usu.getSenha())){
                    mensagemJOption = "A senha precisa ser informada!";
                    mensagemTipo = 0;
                }else{
                    usuarioValido = usu.verificaUsuario(usu.getUsuario());

                    if (usuarioValido == true){
                        mensagemJOption = "Usuário já existente na base de dados.";
                        mensagemTipo = 0;
                    }else{
                        cadastroValido = usu.cadastraUsuario(usu.getNome(), usu.getUsuario(), usu.getSenha());

                        if (cadastroValido == true) {
                            mensagemJOption = "Usuário cadastrado com sucesso.";
                            mensagemTipo = 1;
                        }else{
                            mensagemJOption = "Problemas ao inserir o usuário.";
                            mensagemTipo = 0;
                        }
                    }
                }

                JOptionPane.showMessageDialog(null,mensagemJOption,"Atenção",mensagemTipo);
                if (mensagemTipo == 1){
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    dispose();
                }

            }catch (HeadlessException ec){
                System.out.println("Erro no cadastro do usuário");
            }
        });
    }

    public void abreTela(){
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }
}