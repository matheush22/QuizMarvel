package br.com.quizmarvel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.quizmarvel.utl.QuizMarvelUtil;

public class TelaFimGanhou extends JFrame {

	private static final long serialVersionUID = 5298709484706474704L;
	private JPanel contentPane;

	public TelaFimGanhou(String nomeJogador, double pontuacaoJogador) {
		super("Parabéns - QuizMarvel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
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
				if (JOptionPane.showConfirmDialog(null, "Deseja sair?", "Logoff Quiz Marvel", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(TelaInicio.DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(TelaInicio.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		JLabel lblFimDeJogo = new JLabel("Fim de Jogo");
		lblFimDeJogo.setForeground(Color.WHITE);
		lblFimDeJogo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblFimDeJogo.setBounds(216, 11, 141, 35);
		contentPane.add(lblFimDeJogo);

		JLabel lblNomejogador = new JLabel("Nome do Jogador:");
		lblNomejogador.setForeground(Color.WHITE);
		lblNomejogador.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNomejogador.setBounds(38, 105, 172, 25);
		contentPane.add(lblNomejogador);

		JLabel lblJogador = new JLabel(nomeJogador);
		lblJogador.setForeground(Color.WHITE);
		lblJogador.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblJogador.setBounds(150, 141, 400, 25);
		contentPane.add(lblJogador);

		JLabel lblValorPontuacao = new JLabel("Pontua\u00E7\u00E3o:");
		lblValorPontuacao.setForeground(Color.WHITE);
		lblValorPontuacao.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblValorPontuacao.setBounds(105, 193, 97, 25);
		contentPane.add(lblValorPontuacao);

		JLabel lblPontuacao = new JLabel(String.valueOf(pontuacaoJogador));
		lblPontuacao.setForeground(Color.WHITE);
		lblPontuacao.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblPontuacao.setBounds(150, 229, 400, 25);
		contentPane.add(lblPontuacao);

		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRanking telaRanking = null;
				try {
					telaRanking = new TelaRanking();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				telaRanking.setVisible(true);
				setVisible(false);
			}
		});
		btnRanking.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnRanking.setBounds(22, 325, 97, 25);
		contentPane.add(btnRanking);

		JButton btnJogarNovamente = new JButton("Jogar Novamente");
		btnJogarNovamente.addActionListener(new ActionListener() {
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
		btnJogarNovamente.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnJogarNovamente.setBounds(201, 325, 181, 25);
		contentPane.add(btnJogarNovamente);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Logoff Quiz Marvel", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnSair.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnSair.setBounds(466, 325, 97, 25);
		contentPane.add(btnSair);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(QuizMarvelUtil.IMAGEM_GANHOU));
		lblBackground.setBounds(0, 0, 600, 400);
		contentPane.add(lblBackground);
		
	}
	
}
