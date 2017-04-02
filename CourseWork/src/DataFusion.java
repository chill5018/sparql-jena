/*
 *
 * Submitter: Colin William Hill
 * w1628634@my.westminster.ac.uk
 * on: 02 April 2017
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;

import static java.util.Arrays.asList;


public class DataFusion {

	private static final String inputFileName1  = "camera.owl";
	private static final String inputFileName2  = "FOAFexample.txt";

	static public void main(String...argv)
    {

		// Variables for the While Loop to control a menu

		char quit = 'n';
		String input;
		int choice = 0;

		// Import the camera.owl file and ontology
		Model model1 = ModelFactory.createDefaultModel();
        InputStream in1 = FileManager.get().open( inputFileName1 );
        if (in1 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName1 + " not found");
        }
        model1.read(in1, "");


		// Import the FOAFexample.txt and data vocabulary
        Model model2 = ModelFactory.createDefaultModel();
        InputStream in2 = FileManager.get().open( inputFileName2 );
        if (in2 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName2 + " not found");
        }
        model2.read(in2, "");

     // Unify with the FOAF data vocabulary
        Model union = ModelFactory.createDefaultModel();
        union.add(model1);
        union.add(model2);

        //TASK 2: Adding camera instances as real-world Web URI's
        //		----The following adds some instances as described Web resources - Alternatively, you may edit the camera.owl file by
        // 		----adding the corresponding instances - It is not necessary to add all properties as dictated by the ontology -
        //		----suffices to declare the camera as being a member of one of the classes in the ontology, i.e., the predicate in
        //		----triple shall be rdf:type

        String camera_name_space = "http://www.xfront.com/owl/ontologies/camera/#";

        Resource camera1 = union.createResource("http://www.sony.co.uk/electronics/cyber-shot-compact-cameras/dsc-rx1rm2#product_details_default")
        		.addProperty(RDF.type, union.getResource(camera_name_space + "Digital"));

        Resource camera2 = union.createResource("http://www.amazon.co.uk/Polaroid-HD-Digital-Camera-Optical/dp/B00DTRGRM6/ref=sr_1_1?s=photo&ie=UTF8&qid=1458646010&sr=1-1-spons&keywords=digital+camera&psc=1")
                .addProperty(RDF.type, union.getResource(camera_name_space + "Digital"));

        Resource camera3 = union.createResource("http://www.amazon.co.uk/Sony-DSCW800-Digital-Compact-Optical/dp/B00IK01PJC/ref=sr_1_4?s=photo&ie=UTF8&qid=1458646010&sr=1-4&keywords=digital+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "Digital"));

        Resource camera4 = union.createResource("http://www.amazon.co.uk/Canon-1200D-Digital-Camera-3-5-5-6/dp/B00IE3UR08/ref=sr_1_3?s=electronics&ie=UTF8&qid=1458646158&sr=1-3&keywords=SLR+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "SLR"));

        Resource camera5 = union.createResource("http://www.amazon.co.uk/Nikon-Coolpix-L340-Bridge-Camera/dp/B00THKEKEQ/ref=sr_1_4?s=electronics&ie=UTF8&qid=1458646158&sr=1-4&keywords=SLR+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "SLR"));

        Resource camera6 = union.createResource("http://www.amazon.co.uk/Sony-DSCH400-Digital-Compact-Electronic/dp/B00IGL9PQA/ref=sr_1_9?s=electronics&ie=UTF8&qid=1458646158&sr=1-9&keywords=SLR+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "SLR"));

        Resource camera7 = union.createResource("http://www.amazon.co.uk/Panasonic-DMC-FZ72EB-K-Lumix-Bridge-Camera/dp/B00E0YFOKI/ref=sr_1_10?s=electronics&ie=UTF8&qid=1458646158&sr=1-10&keywords=SLR+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "SLR"));

        Resource camera8 = union.createResource("http://www.amazon.co.uk/Tonsee-Pixels-Screen-Digital-Camera/dp/B01ALFI3A2/ref=sr_1_6?s=electronics&ie=UTF8&qid=1458646388&sr=1-6&keywords=digital+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "Digital"));

        Resource camera9 = union.createResource("http://www.amazon.co.uk/Kodak-PIXPRO-AZ365-Fluorescent-Incandescent/dp/B00M8C7QRS/ref=sr_1_7?s=electronics&ie=UTF8&qid=1458646434&sr=1-7&keywords=digital+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "Digital"));

        Resource camera10 = union.createResource("http://www.amazon.co.uk/Vivitar-F128-14-1-megapixel-resolution-ultra-clear/dp/B015VLPQVU/ref=sr_1_8?s=electronics&ie=UTF8&qid=1458646434&sr=1-8&keywords=digital+camera")
                .addProperty(RDF.type, union.getResource(camera_name_space + "Digital"));

		// TASK 5: INSERT BELOW THE STATEMENTS FOR THIS TASK
    // FOR INSTANCE: Resource purchase1 = camera1.addProperty(model1.getProperty("Enter URI here..."), model1.getResource("Enter URI here....));

		String purchase_name_space = "https://schema.org/BuyAction#";

		Resource purchase1 = camera1.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dig.csail.mit.edu/2008/webdav/timbl/foaf.rdf#edd"));
		Resource purchase2 = camera2.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://hometown.aol.com/chbussler/foaf/chbussler.foaf#me"));
		Resource purchase3 = camera3.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dig.csail.mit.edu/2008/webdav/timbl/foaf.rdf#dj"));
		Resource purchase4 = camera4.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dbpedia.org/resource/John_Seely_Brown"));
		Resource purchase5 = camera5.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dbpedia.org/resource/John_Markoff"));
		Resource purchase6 = camera6.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dbpedia.org/resource/John_Gage"));
		Resource purchase7 = camera7.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dig.csail.mit.edu/2008/webdav/timbl/foaf.rdf#edd"));
		Resource purchase8 = camera8.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dbpedia.org/resource/John_Klensin"));
		Resource purchase9 = camera9.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://dig.csail.mit.edu/2007/wiki/people/RobertHoffmann#RMH"));
		Resource purchase10 = camera10.addProperty(model1.getProperty(purchase_name_space + "Agent"), model1.getResource("http://inamidst.com/sbp/foaf#Sean"));

        // OPTIONAL: Validate the merged data model with instances about purchasers....
//         union.write(System.out);

        // Querying the data model including all data vocabularies

        Scanner scan = new Scanner(System.in);

        while (quit !='y') {

			System.out.println("Please choose a query" + "\n1. Query (Task 3.1)" + "\n2. Query (Task 3.2)" +
														"\n3. Query (Task 3.3)" + "\n4. Query (Task 3.4)" +
														"\n5. Query (Task 4)" + "\n6. Query (Task 5)" + "\n7. Query (Task 6)" +
														"\n8. Query (Task 7)" + "\n9. Query (Task 8)" + "\n10. Query (Task 9)");
			choice = scan.nextInt();

			switch (choice) {

			case 1:
//				https://jena.apache.org/documentation/ontology/#general-concepts

				String queryStr1 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +


									"SELECT ?class ?subclass  WHERE { ?class rdf:type owl:Class; " +
									"        			 rdfs:subClassOf ?subclass	. }";

				executeInternalQuery(queryStr1, model1);
				break;

			case 2:
				System.out.println("Task 3.2 selected....");
				// http://stackoverflow.com/questions/30672123/sparql-query-to-get-all-classes-that-are-subclass-of-a-property-class
				String queryStr2 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
									"PREFIX camera: <http://www.xfront.com/owl/ontologies/camera/#> \n" +

									"SELECT  ?class WHERE { ?class rdfs:subClassOf camera:PurchaseableItem . }";

				executeInternalQuery(queryStr2, model1);
				break;

			case 3:
				System.out.println("Task 3.3 selected....");
				// http://stackoverflow.com/questions/30672123/sparql-query-to-get-all-classes-that-are-subclass-of-a-property-class
				String queryStr3 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +

									"SELECT ?object  ?domain ?range WHERE { ?object rdf:type owl:ObjectProperty ." +
																	"  ?object rdfs:domain ?domain ." +
																	"  ?object rdfs:range ?range }";

				executeInternalQuery(queryStr3, model1);
				break;

			case 4:
				System.out.println("Task 3.4 selected....");
				String queryStr4 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +

									"SELECT ?object  ?subPropertyOf WHERE { ?object rdf:type owl:ObjectProperty ." +
																		  "  ?object rdfs:subPropertyOf ?subPropertyOf }";

				executeInternalQuery(queryStr4, model1);
				break;

			case 5:
				System.out.println("Task 4 selected....");

				// We only want the Digital and SLR cameras not window so we need to filter the results
				// using a REGEX OR 'Digital|SLR'

				String queryStr5 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
														"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
														"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
														"PREFIX camera: <http://www.xfront.com/owl/ontologies/camera/#> \n"+

														"SELECT ?cameras ?class WHERE { ?cameras rdf:type  ?class . " +
														"                       FILTER(regex (str(?class), 'Digital|SLR' , 'i')) . }" ;



				executeInternalQuery(queryStr5, union);
				break;

			case 6:
				System.out.println("Task 5 selected....");
				union.write(System.out);
				break;

			case 7:
				System.out.println("Task 6 selected....");

				String queryStr6 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
									"PREFIX camera: <http://www.xfront.com/owl/ontologies/camera/#> \n"+
									"PREFIX j.0: <http://www.schema.org/BuyAction#> \n"+

									"SELECT ?cameras ?class ?BuyAction ?person " +
									" WHERE {  ?cameras ?BuyAction  ?person . " +
									"	       ?cameras rdf:type  ?class" +
									" FILTER(regex (str(?BuyAction), 'Agent', 'i')) .  }" ;

				executeInternalQuery(queryStr6, union);


				break;

			case 8:
				System.out.println("Task 7 selected....");

				String queryStr7 =  "PREFIX entity: <http://www.wikidata.org/entity/>\n" +
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
					"PREFIX hint: <http://www.bigdata.com/queryHints#>\n " +
					"PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n" +
					"PREFIX wikibase: <http://wikiba.se/ontology#> \n" +

					"SELECT ?predicate ?predicateLabel ?object ?objectLabel WHERE {\n" +
					"  {\n" +
					"    entity:Q62927 ?predicate ?object.\n" +
					"    ?property ?ref ?predicate.\n" +
					"    ?property rdf:type wikibase:Property.\n" +
					"    ?property rdfs:label ?predicateLabel.\n" +
					"  }\n" +
					"  ?object rdfs:label ?objectLabel.\n" +
					"  FILTER((LANG(?objectLabel)) = \"en\")\n" +
					"  FILTER((LANG(?predicateLabel)) = \"en\")\n" +
					"}\n" +
					"ORDER BY ?predicate ?object\n";

				// Where ?item is subclassOf wikiData:Camera
				// Show labels in english
				executeExternalQuery(queryStr7);
				break;

			case 9:
				System.out.println("Task 8 selected....");

				String queryStr8 =  "PREFIX entity: <http://www.wikidata.org/entity/>\n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
									"PREFIX hint: <http://www.bigdata.com/queryHints#>\n " +
									"PREFIX wdt: <http://www.wikidata.org/prop/direct/> \n" +
									"PREFIX wikibase: <http://wikiba.se/ontology#> \n" +

									"SELECT ?predicate ?predicateLabel ?object ?objectLabel WHERE {\n" +
									"  {\n" +
									"    entity:Q80 ?predicate ?object.\n" +
									"    ?property ?ref ?predicate.\n" +
									"    ?property rdf:type wikibase:Property.\n" +
									"    ?property rdfs:label ?predicateLabel.\n" +
									"  }\n" +
									"  ?object rdfs:label ?objectLabel.\n" +
									"  FILTER((LANG(?objectLabel)) = \"en\")\n" +
									"  FILTER((LANG(?predicateLabel)) = \"en\")\n" +
									"}\n" +
									"ORDER BY ?predicate ?object\n";

				executeExternalQuery(queryStr8);
				break;

			case 10:
				System.out.println("Task 9 selected....");
				/*
					Compare owl:Camera to (dbpedia, wikidata, or other sparql-endpoint) Class:Camera
					Based on the properties, mark the similarities
				 */
				// Manually Create List for camera comparison
				List<String> owlCamera = new ArrayList<>(asList("Body",  "Money", "Large-Format", "Camera", "Window", "Lens", "Digital", "Range", "PurchasableItem"));
				List<String> dbpediaCamera = new ArrayList<>(asList("Camera", "Electronic Device", "Lens", "View Finder", "Image Sensor", "Memory Card", "Flash", "Battery", "Central Processing Unit"));

				int similarityScore = getListSimilarities(owlCamera, dbpediaCamera);

				System.out.println("Owl Camera and DBpedia Camera contain "+ similarityScore+"/"+dbpediaCamera.size()+" similarities");

				break;

			default:
				System.out.println("That was not a proper choice.");
			}

			System.out.println("Would you like to quit (y/n)? ");
			input = scan.next().toLowerCase();
			quit = input.charAt(0);
		}
    }

    private static int getListSimilarities(List<String> list1, List<String> list2) {
		int similarityScore = 0;

		for (int i = 0; i < list2.size() ; i++) {
			for (int j = 0; j < list1.size(); j++) {
				if (list1.get(j).equalsIgnoreCase(list2.get(i))) {
					similarityScore++;
				}
			}
		}

		return similarityScore;
	}

    private static void executeInternalQuery(String query, Model data) {
		// Local execution. --> Remote use sparqlService
		try ( QueryExecution qexec = QueryExecutionFactory.create(query, data) ) {
			// Set the DBpedia specific timeout.

			// Execute.
			ResultSet rs = qexec.execSelect();

			printQueryResults(rs, qexec);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void executeExternalQuery(String query) {
		// Remote execution.
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query) ) {
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;


			// Execute.
			ResultSet rs = qexec.execSelect();

			printQueryResults(rs, qexec);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void printQueryResults(ResultSet rs, QueryExecution qexec) {
		try {
			ResultSetRewindable results = ResultSetFactory.makeRewindable(rs);

			System.out.println("---- Text ----");
			ResultSetFormatter.out(System.out, results);
			results.reset();

		}

		finally { qexec.close(); }
	}
}
