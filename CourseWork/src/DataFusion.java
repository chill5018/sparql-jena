/*
 * Author: Dr E. Kapetanios
 * Last update: 3/3/2017
 */

import java.io.InputStream;
import java.util.Scanner;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
// import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;


public class DataFusion {
	
	static final String inputFileName1  = "camera.owl";
	static final String inputFileName2  = "FOAFexample.txt";
	
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
        
        // OPTIONAL: Validate the merged data model with instances about purchasers....
        // union.write(System.out);
	
        // Querying the data model including all data vocabularies
        
        Scanner scan = new Scanner(System.in);
        
        while (quit !='y') {
			
			System.out.println("Please choose a query" + "\n1. Query (Task 3.1)" + "\n2. Query (Task 3.2)" + 
														"\n3. Query (Task 3.3)" + "\n4. Query (Task 3.4)" + 
														"\n5. Query (Task 4)" + "\n6. Query (Task 5)" + "\n7. Query (Task 6)" +
														"\n8. Query (Task 7)" + "\n9. Query (Task 8)" + "\n10. Query (Task 9)");
			choice = scan.nextInt();

			Query query = QueryFactory.create();
			
			switch (choice) {
        
			case 1:
//				https://jena.apache.org/documentation/ontology/#general-concepts
//				Resource r = camera1.getResource( myNS + "DigitalCamera" );
//				OntClass cls = camera1.as( OntClass.class );

//				System.out.println("Task 3.1 selected....");
//				String queryStr1 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
//									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
//									"PREFIX owl: <camera.owl#> \n"+
//
//									"SELECT ?class  WHERE { owl:Class rdf:ID \"Camera\" . }";

				// Iterator over model1 == `camera.owl`
				StmtIterator iter = model1.listStatements();

				// print out the predicate, subject and object of each statement
				while (iter.hasNext()) {
					Statement stmt      = iter.nextStatement();  // get next statement
					Resource  subject   = stmt.getSubject();     // get the subject
					Property  predicate = stmt.getPredicate();   // get the predicate
					RDFNode   object    = stmt.getObject();      // get the object

					System.out.print(subject.toString());
					System.out.print(" " + predicate.toString() + " ");
					if (object instanceof Resource) {
//						System.out.print(object.toString());
					} else {
						// object is a literal
//						System.out.print(" \"" + object.toString() + "\"");
					}

//					System.out.println(" .");
				}
				String queryStr1 =	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  \n" +
									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n"+
									"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +


									"SELECT ?class ?subclass  WHERE { ?class rdf:type owl:Class; " +
									"        			 rdfs:subClassOf ?subclass	. }";

				query = QueryFactory.create(queryStr1);
//				System.out.println(queryStr1);
				break;
		
			case 2: 
				System.out.println("Task 3.2 selected....");
				// http://stackoverflow.com/questions/30672123/sparql-query-to-get-all-classes-that-are-subclass-of-a-property-class
				break;
		
			case 3: 
				System.out.println("Task 3.3 selected....");
				break;
		
			case 4:
				System.out.println("Task 3.4 selected....");
				break;
				
			case 5:
				System.out.println("Task 4 selected....");
				break;
			
			case 6:
				System.out.println("Task 5 selected....");
				break;
				
			case 7:
				System.out.println("Task 6 selected....");
				break;	
				
			case 8:
				System.out.println("Task 7 selected....");
				break;
				
			case 9:
				System.out.println("Task 8 selected....");
				break;
				
			case 10:
				System.out.println("Task 9 selected....");
				break;
		
			default:
				System.out.println("That was not a proper choice.");
			}


			//TODO Refactor

			// Local execution. --> Remote use sparql
			try ( QueryExecution qexec = QueryExecutionFactory.create(query, model1) ) {
				// Set the DBpedia specific timeout.

				// Execute.
				ResultSet rs = qexec.execSelect();

				/* You may also try the following in order to received the query result in more than one format.... */
				try {
					ResultSetRewindable results = ResultSetFactory.makeRewindable(rs);
					/*
					System.out.println("---- XML ----");
					ResultSetFormatter.outputAsXML(System.out, results);
					results.reset();
					*/

					System.out.println("---- Text ----");
					ResultSetFormatter.out(System.out, results);
					results.reset();

					/*
					System.out.println("\n---- CSV ----");
					ResultSetFormatter.outputAsCSV(System.out, results);
					results.reset();

					System.out.println("\n---- TSV ----");
					ResultSetFormatter.outputAsTSV(System.out, results);
					results.reset();

					System.out.println("\n---- JSON ----");
					ResultSetFormatter.outputAsJSON(System.out, results);
					results.reset();
					*/
				}

				finally { qexec.close(); }

				// ResultSetFormatter.out(System.out, rs, query);

				// qexec.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Would you like to quit (y/n)? ");
			input = scan.next().toLowerCase();
			quit = input.charAt(0);
		}
    }
}
