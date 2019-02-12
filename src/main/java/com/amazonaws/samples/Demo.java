package com.amazonaws.samples;

//Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
//PDX-License-Identifier: MIT-0 (For details, see https://github.com/awsdocs/amazon-rekognition-developer-guide/blob/master/LICENSE-SAMPLECODE.)

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.TextDetection;
import java.util.List;



public class Demo {

	public static void main(String[] args) throws Exception {


		String photo = "teste2.jpg";
		String bucket = "leste-dauer-bucket";

		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-east-1").build();



		DetectTextRequest request = new DetectTextRequest()
				.withImage(new Image()
						.withS3Object(new S3Object()
								.withName(photo)
								.withBucket(bucket)));


		try {
			DetectTextResult result = rekognitionClient.detectText(request);
			List<TextDetection> textDetections = result.getTextDetections();

			System.out.println("Detected lines and words for " + photo);
			for (TextDetection text: textDetections) {
				if (text.getType().equals("WORD")){
					String placa = trataPlaca(text.getDetectedText());

					System.out.println("Detected: " + placa);
			//		System.out.println(text.getDetectedText());
			//		System.out.println("Confidence: " + text.getConfidence().toString());
		//			System.out.println("Id : " + text.getId());
					//System.out.println("Parent Id: " + text.getParentId());
		//			System.out.println("Type: " + text.getType());
					verificaRodizio(placa);
					System.out.println();

					

				}
			}
		} catch(AmazonRekognitionException e) {
			e.printStackTrace();
		}
	}


	public static String trataPlaca(String placa)
	{
		String letras = placa.substring(0, 3);
		String numeros = "";

		if (placa.length() == 7)			
			numeros = placa.substring(3, 7);
		else
			numeros = placa.substring(4, 8);

		return letras + "-" + numeros;
	}

	public static void verificaRodizio(String placa){

		char finalPlaca = placa.charAt(7);
		switch(finalPlaca){
		case('1'):
		case('2'):
			System.out.println("Porra, seu rodízio é de Segunda-feira");
		break;
		case('3'):
		case('4'):
			System.out.println("Porra, seu rodízio é de Terça-feira");
		break;
		case('5'):
		case('6'):
			System.out.println("Porra, seu rodízio é de Quarta-feira");
		break;
		case('7'):
		case('8'):
			System.out.println("Porra, seu rodízio é de Quinta-feira");
		break;
		case('9'):
		case('0'):
			System.out.println("Porra, seu rodízio é de Sexta-feira");
		break;
		}
	}	
}