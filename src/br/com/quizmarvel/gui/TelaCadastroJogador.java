package br.com.quizmarvel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.quizmarvel.bns.Jogador;
import br.com.quizmarvel.dao.JogadorExcel;
import br.com.quizmarvel.utl.QuizMarvelUtil;

public class TelaCadastroJogador extends JFrame {

	private static final long serialVersionUID = 8031358545864972614L;
	private JPanel contentPane;
	private JTextField textoNomeJogador;

	public TelaCadastroJogador() {
		setTitle("Cadastro de Jogador - QuizMarvel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}

			private void formWindowClosing(java.awt.event.WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(null, "Deseja sair?", "Logoff Quiz Marvel",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(TelaInicio.DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(TelaInicio.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		JLabel lblCadastroDeJogador = new JLabel("Cadastro de Jogador");
		lblCadastroDeJogador.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblCadastroDeJogador.setBounds(135, 11, 196, 35);
		contentPane.add(lblCadastroDeJogador);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNome.setBounds(63, 88, 52, 25);
		contentPane.add(lblNome);

		textoNomeJogador = new JTextField();
		textoNomeJogador.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		textoNomeJogador.setBounds(125, 89, 259, 22);
		contentPane.add(textoNomeJogador);
		textoNomeJogador.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblCategoria.setBounds(33, 124, 82, 25);
		contentPane.add(lblCategoria);

		JComboBox comboCategoria = new JComboBox();
		comboCategoria.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		comboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Aluno", "Professor", "Outros"}));
		comboCategoria.setBounds(125, 125, 187, 22);
		contentPane.add(comboCategoria);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jogador jogador = new Jogador(textoNomeJogador.getText(), (String) comboCategoria.getSelectedItem(), 0,
						0);
				JogadorExcel jogadorExcel = new JogadorExcel();
				try {
					if (textoNomeJogador.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Digite seu nome de Usuário", "Alerta de Cadastro",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (jogadorExcel.verificaJogadorExistente(jogador)) {
						JOptionPane.showMessageDialog(null, "Usuário Existente", "Alerta de Cadastro",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						jogadorExcel.criarJogador(jogador);
						JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso", "Alerta de Cadastro",
								JOptionPane.INFORMATION_MESSAGE);
						textoNomeJogador.setText("");
						comboCategoria.setSelectedIndex(0);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		btnCadastrar.setBounds(269, 225, 127, 25);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaInicio telaInicio = new TelaInicio();
					telaInicio.setVisible(true);
					setVisible(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnVoltar.setBounds(42, 225, 97, 25);
		contentPane.add(btnVoltar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(QuizMarvelUtil.IMAGEM_CADASTRO));
		lblBackground.setBounds(0, 0, 450, 300);
		contentPane.add(lblBackground);
		
	}
	
}
