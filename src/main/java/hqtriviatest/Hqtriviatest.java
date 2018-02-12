/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hqtriviatest;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors.FieldDescriptor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
 *
 * @author hthoma
 */
public class Hqtriviatest {

    /*export GOOGLE_APPLICATION_CREDENTIALS='/Users/hthoma/Downloads/hqtrivia-cd24c82d4ea6.json'
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // Instantiates a client
    ImageAnnotatorClient vision = ImageAnnotatorClient.create();
    
    // The path to the image file to annotate
    String fileName = "/Users/hthoma/Desktop/HQ Trivia/hqtest.png"; // for example "./resources/wakeupcat.jpg";

    // Reads the image file into memory
    Path path = Paths.get(fileName);
    byte[] data = Files.readAllBytes(path);
    ByteString imgBytes = ByteString.copyFrom(data);

    // Builds the image annotation request
    List<AnnotateImageRequest> requests = new ArrayList<>();
    Image img = Image.newBuilder().setContent(imgBytes).build();
    Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
    AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
        .addFeatures(feat)
        .setImage(img)
        .build();
    requests.add(request);

    // Performs label detection on the image file
    BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
    List<AnnotateImageResponse> responses = response.getResponsesList();

    for (AnnotateImageResponse res : responses) {
      if (res.hasError()) {
        System.out.printf("Error: %s\n", res.getError().getMessage());
        return;
      }

      for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
         String qa =  annotation.getDescription();
          
          
      
        String lines[] = qa.split("\\r?\\n");
        int println = 1;
        String question = lines[0];
        String a1 , a2 , a3 = "";
        while(lines.length - println > 3 ){
        question = question + " " + lines[println];
        println++;
        }
        
        a1 = lines[println];
         println++;
        a2 = lines[println];
         println++;
        a3 = lines[println];
             
        System.out.println("QUESTION : " + question);
        System.out.println("ANSWER 1 : " + a1);
        System.out.println("ANSWER 2 : " + a2);
        System.out.println("ANSWER 3 : " + a3);
        customsearch cs = new customsearch();
        cs.toString();
        break;
        /*
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            
      // The text to analyze
      
      Document doc = Document.newBuilder()
          .setContent(question).setType(Document.Type.PLAIN_TEXT).build();

      // Detects the sentiment of the text
      Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

      System.out.printf("Text: %s%n", question);
      System.out.println(  "Sentiment: " + sentiment.getScore() + " " + sentiment.getMagnitude() + " ");
        break;
    }
    }
*/
        
        
}
    }
    }
}





