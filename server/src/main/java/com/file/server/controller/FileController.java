package com.file.server.controller;

import java.io.*;
import java.text.Normalizer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

	@RestController
	public class FileController {
		/**
		 * POST recibe una cadena y la almacena en una linea de un fichero
		 *
		 * @param str cadena recibida por parametro de url protocolo http
		 * @return texto despuesta mas status html
		 */
		@PostMapping(path = "file/write")
		@ResponseBody
		public ResponseEntity<String> addStr(@RequestParam String str) {
			// Bloque try-catch de autocierre para recoger las posiblles excepciones.
			try (FileWriter fw = new FileWriter("data.txt", true);
				BufferedWriter pw = new BufferedWriter(fw);) {
				// Escribe el texto recibido por argumento y salta de linea.
				pw.write(str.toLowerCase() + "\r\n");
				// Fuerza la escritura en el fichero
				fw.flush();
				return new ResponseEntity<>(HttpStatus.OK + ": Texto añadido.", HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND + ": Fichero no encontrado", HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException e) {
				return new ResponseEntity<>(e.getStatusCode() + ": Error al guardar texto.", e.getStatusCode());
			}
		}

		/**
		 * GET recibe una cadena y la compara con cada linea del fichero
		 *
		 * @param str cadena recibida por parametro de url protocolo http
		 * @return numero de lineas que contienen la cadena recibida
		 */
		@GetMapping(path = "file/read")
		@ResponseBody
		public ResponseEntity<String> getMatchToData(@RequestParam String str) {
			// Contador de coincidencias
			int cont = 0;

			try (FileReader fr = new FileReader("data.txt");
				 BufferedReader br = new BufferedReader(fr);) {
				// Lee la primera linea del archivo
				String data = br.readLine();
				// Mietras siga habiendo lineas escritas en el documento
				while (data != null) {
					//comprueba si data contiene str
					if (checkIsMatch(data, str)){
						//suma 1 al contador de coincidencias
						cont++;
					}
					// Lee la siguiente linea
					data = br.readLine();
				}
				return new ResponseEntity<>( HttpStatus.OK + " Numero de coincidencias: " + cont, HttpStatus.OK);
			} catch (FileNotFoundException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND + ": Fichero no encontrado", HttpStatus.NOT_FOUND);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND + ": Fichero no encontrado", HttpStatus.NOT_FOUND);
			} catch (HttpClientErrorException e) {
				return new ResponseEntity<>(e.getStatusCode() + ": Error al guardar.", e.getStatusCode());
			}
		}

		/**
		 * Comprueba si una cadena contiene una palabra
		 *
 		 * @param data cadena en la que buscaremos
		 * @param str	cadena que buscamos
		 * @return true si encuentra str en data
		 */
		public boolean checkIsMatch (String data, String str){
			return normalizador(data).toLowerCase().contains(normalizador(str).toLowerCase());
		}

		/**
		 * Normaliza una cadena
		 *
		 * @param str cadena que necesitamos normalizar
		 * @return cadena normalizada
		 */
		private static String normalizador(String str)
		{
			return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll(" ", "+");
		}
	}