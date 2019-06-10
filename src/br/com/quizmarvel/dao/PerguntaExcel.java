package br.com.quizmarvel.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.quizmarvel.utl.QuizMarvelUtil;

public class PerguntaExcel {
	
	private FileInputStream fileInputStream;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cellrow;

	// inicio - metodo para capturar do excel as 10 perguntas
	public String[][] capturarPerguntas(int nivelPergunta) throws FileNotFoundException, IOException {

		fileInputStream = new FileInputStream(new File(QuizMarvelUtil.PERGUNTA_ARQUIVO));
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(nivelPergunta);

		int rowIndex = 0;
		int cellIndex = 0;

		String[][] perguntas = new String[10][9];

		try {
			do {
				row = sheet.getRow(rowIndex);
				do {
					cellrow = row.getCell(cellIndex);
					if (cellIndex == 0) {
						perguntas[rowIndex][cellIndex] = String.valueOf(cellrow.getNumericCellValue());
					} else {
						perguntas[rowIndex][cellIndex] = cellrow.getStringCellValue();
					}
					cellIndex++;
				} while (cellIndex < 9);
				cellIndex = 0;
				rowIndex++;
			} while (!cellrow.getStringCellValue().equals(null));
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
		return perguntas;
	}
	// fim - metodo para capturar do excel as 10 perguntas
	
	// inicio - metodo para sortear as 5 perguntas das 10 sem repetir
	public String[][] sortearPerguntas(String[][] perguntas){
		
		Random random = new Random();
		String[][] pergSorteadas = new String[5][9];
		String[][] pergSorteadasTmp = new String[5][9];

		int cont = 0;
		pergSorteadas[0] = perguntas[random.nextInt(perguntas.length)];
		pergSorteadasTmp[0] = pergSorteadas[0]; 
		
		for (int i = 1; i < 5; i++) {
			pergSorteadas[i] = perguntas[random.nextInt(perguntas.length)];
			cont++;
			for (int j = 0; j < cont; j++) {
				while (pergSorteadas[i][0].equals(pergSorteadasTmp[j][0])) {
					pergSorteadas[i] = perguntas[random.nextInt(perguntas.length)];
					j = 0;
				}
			}
			pergSorteadasTmp[i] = pergSorteadas[i];
		}

		return pergSorteadas;
	}
	// fim - metodo para sortear as 5 perguntas das 10 sem repetir
	
	// inicio - metodo para sortear as 4 respostas
	public String[][] sortearPerguntasRespostas(String[][] perguntas){
		
		String[][] pergRespSorteadas = new String[5][6];
		
		// inicio - preenche as 2 primeiras colulas(id, pergunta)
		for (int i = 0; i < perguntas.length; i++) {
			for (int j = 0; j < 2; j++) {
				pergRespSorteadas[i][j] = perguntas[i][j];
			}
		}
		// fim - preenche as 2 primeiras colulas(id, pergunta)
		
		Random random = new Random();
		String[][] respSorteadasCap = new String[5][7];
		
		// inicio - preenche as respostas capturadas
		for (int i = 0; i < respSorteadasCap.length; i++) {
			for (int j = 0; j < respSorteadasCap[0].length; j++) {
				respSorteadasCap[i][j] = perguntas[i][j+2];
			}
		}
		// fim - preenche as respostas capturadas
		
		String[][] respSorteadas = new String[5][4];
		String[][] respSorteadasTmp = new String[5][4];
		
		// inicio - sortear 3 respostas alternativas aleatórias sem repetir
		int cont = 1;
		
		// inicio - define um range para o random
		int maximo = 6;
		int minimo = 1;
		int range = maximo - minimo + 1;
		// fim - define um range para o random
		
		// inicio - preenche as 2 primeiras colulas de cada resposta
		for (int i = 0; i < respSorteadas.length; i++) {
			respSorteadas[i][0] = respSorteadasCap[i][0];
			respSorteadas[i][1] = respSorteadasCap[i][random.nextInt(range) + minimo];
		}
		// fim - preenche as 2 primeiras colulas de cada resposta
		
		respSorteadasTmp = respSorteadas;
		
		// inicio - preenche as 2 colulas seguintes de cada resposta
		for (int i = 0; i < respSorteadas.length; i++) {
			for (int j = 2; j < respSorteadas[0].length; j++) {
				respSorteadas[i][j] = respSorteadasCap[i][random.nextInt(range) + minimo];
				cont++;
				for (int k = 1; k < cont; k++) {
					while (respSorteadas[i][j].equals(respSorteadasTmp[i][k])) {
						respSorteadas[i][j] = respSorteadasCap[i][random.nextInt(range) + minimo];
						k = 1;
					}
				}
				respSorteadasTmp[i][j] = respSorteadas[i][j];
			}
			cont = 1;
		}
		// fim - preenche as 2 colulas seguintes de cada resposta
		// fim - sortear 3 respostas alternativas aleatórias sem repetir
		
		String[][] respSorteadas2 = new String[5][4];
		String[][] respSorteadasTmp2 = new String[5][4];
		
		cont = 0;
				
		// inicio - define um range para o random
		maximo = 3;
		minimo = 0;
		range = maximo - minimo + 1;
		// fim - define um range para o random
		
		// inicio - preenche a primeira coluna das respostas
		for (int i = 0; i < respSorteadas2.length; i++) {
			respSorteadas2[i][0] = respSorteadas[i][random.nextInt(range) + minimo];
		}
		// fim - preenche a primeira coluna das respostas
		
		respSorteadasTmp2 = respSorteadas2;
		
		// inicio - ordena de forma aleatória a posição das respostas
		for (int i = 0; i < respSorteadas2.length; i++) {
			for (int j = 1; j < respSorteadas2[0].length; j++) {
				respSorteadas2[i][j] = respSorteadas[i][random.nextInt(range) + minimo];
				cont++;
				for (int k = 0; k < cont; k++) {
					while (respSorteadas2[i][j].equals(respSorteadasTmp2[i][k])) {
						respSorteadas2[i][j] = respSorteadas[i][random.nextInt(range) + minimo];
						k = 0;
					}
				}
				respSorteadasTmp2 = respSorteadas2;
			}
			cont = 0;
		}
		// fim - ordena de forma aleatória a posição das respostas
		
		// inicio - adiciona as respostas sorteadas na matriz de perguntas e respostas
		for (int i = 0; i < pergRespSorteadas.length; i++) {
			for (int j = 2; j < pergRespSorteadas[0].length; j++) {
				pergRespSorteadas[i][j] = respSorteadas2[i][j-2];
			}
		}
		// fim - adiciona as respostas sorteadas na matriz de perguntas e respostas
		
		return pergRespSorteadas;
	}
	// fim - metodo para sortear as 4 respostas

}
