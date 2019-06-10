package br.com.quizmarvel.app;

import java.awt.EventQueue;

import br.com.quizmarvel.gui.TelaInicio;

public class App {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					TelaInicio telaInicio = new TelaInicio();
					telaInicio.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
