package br.com.quizmarvel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import br.com.quizmarvel.dao.JogadorExcel;
import br.com.quizmarvel.dao.PerguntaExcel;
import br.com.quizmarvel.utl.QuizMarvelUtil;

public class TelaPerguntaNv3 extends JFrame {

	private static final long serialVersionUID = -593560721618928435L;
	private JPanel contentPane;
	private double valorParar = 5000.0;
	private double valorErrar = 2500.0;

	public TelaPerguntaNv3(String[][] pergRespNv3, String[] respostaCorretaNv3, String nomeJogador, String nivelJogo) {
		super("Nível 3 - QuizMarvel");
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JLabel lblJogador = new JLabel("Jogador: " + nomeJogador);
		lblJogador.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblJogador.setBounds(20, 25, 377, 42);
		contentPane.add(lblJogador);

		JLabel lblNivel = new JLabel(nivelJogo);
		lblNivel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNivel.setBounds(829, 29, 106, 34);
		contentPane.add(lblNivel);

		JLabel lblNumPergunta = new JLabel("0");
		lblNumPergunta.setBounds(20, 160, 0, 0);
		contentPane.add(lblNumPergunta);

		JLabel lblPergunta = new JLabel(pergRespNv3[0][1]);
		lblPergunta.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblPergunta.setBounds(10, 78, 964, 82);
		contentPane.add(lblPergunta);

		JRadioButton rdbtnResp1 = new JRadioButton(pergRespNv3[0][2]);
		rdbtnResp1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		rdbtnResp1.setBounds(32, 180, 930, 52);
		rdbtnResp1.setSelected(true);
		contentPane.add(rdbtnResp1);

		JRadioButton rdbtnResp2 = new JRadioButton(pergRespNv3[0][3]);
		rdbtnResp2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		rdbtnResp2.setBounds(32, 250, 930, 52);
		contentPane.add(rdbtnResp2);

		JRadioButton rdbtnResp3 = new JRadioButton(pergRespNv3[0][4]);
		rdbtnResp3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		rdbtnResp3.setBounds(32, 320, 930, 52);
		contentPane.add(rdbtnResp3);

		JRadioButton rdbtnResp4 = new JRadioButton(pergRespNv3[0][5]);
		rdbtnResp4.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		rdbtnResp4.setBounds(32, 390, 930, 52);
		contentPane.add(rdbtnResp4);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnResp1);
		buttonGroup.add(rdbtnResp2);
		buttonGroup.add(rdbtnResp3);
		buttonGroup.add(rdbtnResp4);

		JLabel lblErrar = new JLabel("Errar R$ ");
		lblErrar.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblErrar.setBounds(20, 485, 96, 34);
		contentPane.add(lblErrar);

		JLabel lblValorErrar = new JLabel("2500.0");
		lblValorErrar.setForeground(Color.BLACK);
		lblValorErrar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblValorErrar.setBounds(115, 484, 200, 35);
		contentPane.add(lblValorErrar);

		JLabel lblParar = new JLabel("Parar R$ ");
		lblParar.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblParar.setBounds(338, 485, 89, 34);
		contentPane.add(lblParar);

		JLabel lblValorParar = new JLabel("5000.0");
		lblValorParar.setForeground(Color.BLACK);
		lblValorParar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblValorParar.setBounds(431, 485, 200, 35);
		contentPane.add(lblValorParar);

		JLabel lblAcertar = new JLabel("Acertar R$ ");
		lblAcertar.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblAcertar.setBounds(641, 485, 112, 34);
		contentPane.add(lblAcertar);

		JLabel lblValorAcertar = new JLabel("10000.0");
		lblValorAcertar.setForeground(Color.BLACK);
		lblValorAcertar.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblValorAcertar.setBounds(757, 485, 200, 35);
		contentPane.add(lblValorAcertar);

		JButton btnResponder = new JButton("Responder");
		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double pontuacaoAcertar;
				double pontuacaoParar;
				double pontuacaoErrar;
				int numPergunta;
				String repostaEscolhida = "";

				pontuacaoAcertar = Double.parseDouble(lblValorAcertar.getText()) + 10000;
				pontuacaoParar = Double.parseDouble(lblValorAcertar.getText());
				pontuacaoErrar = Double.parseDouble(lblValorAcertar.getText()) / 2;
				numPergunta = Integer.parseInt(lblNumPergunta.getText());

				if (rdbtnResp1.isSelected()) {
					repostaEscolhida = rdbtnResp1.getText();
				} else if (rdbtnResp2.isSelected()) {
					repostaEscolhida = rdbtnResp2.getText();
				} else if (rdbtnResp3.isSelected()) {
					repostaEscolhida = rdbtnResp3.getText();
				} else if (rdbtnResp4.isSelected()) {
					repostaEscolhida = rdbtnResp4.getText();
				}

				if (!repostaEscolhida.equals(respostaCorretaNv3[numPergunta])) {
					JogadorExcel jogadorExcel = new JogadorExcel();
					try {
						jogadorExcel.atualizarJogadorPontuacao(nomeJogador, valorErrar);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					TelaFimPerdeu telaFimPerdeu = new TelaFimPerdeu(nomeJogador, valorErrar);
					telaFimPerdeu.setVisible(true);
					setVisible(false);
				}
				// montando as perguntas do próximo nível
				if ((numPergunta == 4) && (repostaEscolhida.equals(respostaCorretaNv3[numPergunta]))) {
					PerguntaExcel perguntaExcel = new PerguntaExcel();
					String[][] perguntasNv4 = null;
					try {
						perguntasNv4 = perguntaExcel.capturarPerguntas(3);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					String[][] pergNv4Sorteadas = perguntaExcel.sortearPerguntas(perguntasNv4);
					String[] respostaCorretaNv4 = new String[5];
					for (int i = 0; i < pergNv4Sorteadas.length; i++) {
						respostaCorretaNv4[i] = pergNv4Sorteadas[i][2];
					}
					String[][] pergRespNv4Sorteadas = perguntaExcel.sortearPerguntasRespostas(pergNv4Sorteadas);
					TelaPerguntaNv4 telaPerguntaNv4 = new TelaPerguntaNv4(pergRespNv4Sorteadas, respostaCorretaNv4, nomeJogador, "Nível 4");
					telaPerguntaNv4.setVisible(true);
					setVisible(false);
				}
				switch (numPergunta) {
				case 0:
					lblPergunta.setText(pergRespNv3[numPergunta + 1][1]);
					rdbtnResp1.setText(pergRespNv3[numPergunta + 1][2]);
					rdbtnResp2.setText(pergRespNv3[numPergunta + 1][3]);
					rdbtnResp3.setText(pergRespNv3[numPergunta + 1][4]);
					rdbtnResp4.setText(pergRespNv3[numPergunta + 1][5]);
					rdbtnResp1.setSelected(true);
					lblValorAcertar.setText(String.valueOf(pontuacaoAcertar));
					lblValorParar.setText(String.valueOf(pontuacaoParar));
					lblValorErrar.setText(String.valueOf(pontuacaoErrar));
					valorParar = pontuacaoParar;
					valorErrar = pontuacaoErrar;
					numPergunta += 1;
					lblNumPergunta.setText(String.valueOf(numPergunta));
					break;
				case 1:
					lblPergunta.setText(pergRespNv3[numPergunta + 1][1]);
					rdbtnResp1.setText(pergRespNv3[numPergunta + 1][2]);
					rdbtnResp2.setText(pergRespNv3[numPergunta + 1][3]);
					rdbtnResp3.setText(pergRespNv3[numPergunta + 1][4]);
					rdbtnResp4.setText(pergRespNv3[numPergunta + 1][5]);
					rdbtnResp1.setSelected(true);
					lblValorAcertar.setText(String.valueOf(pontuacaoAcertar));
					lblValorParar.setText(String.valueOf(pontuacaoParar));
					lblValorErrar.setText(String.valueOf(pontuacaoErrar));
					valorParar = pontuacaoParar;
					valorErrar = pontuacaoErrar;
					numPergunta += 1;
					lblNumPergunta.setText(String.valueOf(numPergunta));
					break;
				case 2:
					lblPergunta.setText(pergRespNv3[numPergunta + 1][1]);
					rdbtnResp1.setText(pergRespNv3[numPergunta + 1][2]);
					rdbtnResp2.setText(pergRespNv3[numPergunta + 1][3]);
					rdbtnResp3.setText(pergRespNv3[numPergunta + 1][4]);
					rdbtnResp4.setText(pergRespNv3[numPergunta + 1][5]);
					rdbtnResp1.setSelected(true);
					lblValorAcertar.setText(String.valueOf(pontuacaoAcertar));
					lblValorParar.setText(String.valueOf(pontuacaoParar));
					lblValorErrar.setText(String.valueOf(pontuacaoErrar));
					valorParar = pontuacaoParar;
					valorErrar = pontuacaoErrar;
					numPergunta += 1;
					lblNumPergunta.setText(String.valueOf(numPergunta));
					break;
				case 3:
					lblPergunta.setText(pergRespNv3[numPergunta + 1][1]);
					rdbtnResp1.setText(pergRespNv3[numPergunta + 1][2]);
					rdbtnResp2.setText(pergRespNv3[numPergunta + 1][3]);
					rdbtnResp3.setText(pergRespNv3[numPergunta + 1][4]);
					rdbtnResp4.setText(pergRespNv3[numPergunta + 1][5]);
					rdbtnResp1.setSelected(true);
					lblValorAcertar.setText(String.valueOf(pontuacaoAcertar));
					lblValorParar.setText(String.valueOf(pontuacaoParar));
					lblValorErrar.setText(String.valueOf(pontuacaoErrar));
					valorParar = pontuacaoParar;
					valorErrar = pontuacaoErrar;
					numPergunta += 1;
					lblNumPergunta.setText(String.valueOf(numPergunta));
					break;
				default:
					lblNumPergunta.setText(String.valueOf(numPergunta));
					break;
				}

			}
		});
		btnResponder.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnResponder.setBounds(608, 591, 145, 34);
		contentPane.add(btnResponder);

		JButton btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JogadorExcel jogadorExcel = new JogadorExcel();
				try {
					jogadorExcel.atualizarJogadorPontuacao(nomeJogador, Double.parseDouble(lblValorParar.getText()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				TelaFimPerdeu telaFimPerdeu = new TelaFimPerdeu(nomeJogador, valorParar);
				telaFimPerdeu.setVisible(true);
				setVisible(false);
			}
		});
		btnParar.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnParar.setBounds(199, 591, 145, 34);
		contentPane.add(btnParar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(QuizMarvelUtil.IMAGEM_NV3));
		lblBackground.setBounds(0, 0, 1000, 700);
		contentPane.add(lblBackground);

	}

}
