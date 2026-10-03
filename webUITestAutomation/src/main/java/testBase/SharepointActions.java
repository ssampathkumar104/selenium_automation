//package testBase;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Collections;
//import java.util.Set;
//
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.FileEntity;
//
//import com.microsoft.aad.msal4j.ClientCredentialFactory;
//import com.microsoft.aad.msal4j.ClientCredentialParameters;
//import com.microsoft.aad.msal4j.ConfidentialClientApplication;
//import com.microsoft.aad.msal4j.IAuthenticationResult;
//import com.microsoft.aad.msal4j.IClientCredential;
//import com.microsoft.aad.msal4j.MsalException;
//import com.microsoft.aad.msal4j.SilentParameters;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.client.methods.CloseableHttpResponse;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class SharepointActions {
//	
//	private static final String CONFIG_FOLDER = System.getProperty("user.dir") + File.separator + 
//			"src" + File.separator + "main" + File.separator + "java" + File.separator +
//			"config" + File.separator;
//	
//	private static String configFolder() {
//		return Files.exists(Paths.get(CONFIG_FOLDER)) ? CONFIG_FOLDER
//				: System.getProperty("user.dir") + File.separator  + "config" + File.separator;
//	}
//	
//	/**
//	 * This method is for updating the sharepoint URL in json file,
//	 * 		so that we can access it at the time we upload the html to sharepoint
//	 * @param sharePointURL: URL of the sharepoint where we want to upload the html file
//	 */
//	public static void updateSharepointJSONFile(String sharePointURL) {
//		
//		// If the sharepoint url is not empty, then update that url in json file
//		if (!sharePointURL.equalsIgnoreCase("")) {
//			JSONParser parser = new JSONParser();
//			Object obj = null;
//			try {
//				obj = parser.parse(new FileReader(configFolder() + "sharepointDetails.json"));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.err.println("Please create a json file with name 'sharepointDetails.json' that contains all the sharepoint details at => " + configFolder());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			JSONObject sharepointDetails = (JSONObject) obj;
//
//			String sharePointURLFromJSON = sharepointDetails.get("url").toString();
//
//			if (sharePointURLFromJSON.equalsIgnoreCase("")) {
//
//				sharepointDetails.put("url", sharePointURL);
//			}
//
//			File file = new File(configFolder() + "sharepointDetails.json");
//
//			try {
//				FileWriter fileWriter = new FileWriter(file);
//
//				fileWriter.write(sharepointDetails.toJSONString());
//				fileWriter.flush();
//				fileWriter.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public static void uploadHTMLToSharePoint() {
//		
//		JSONParser parser = new JSONParser();
//		
//		try {
//			
//			// Read the json file
//			Object obj = parser.parse(new FileReader(configFolder() + "sharepointDetails.json"));
//			Set<String> scope = Collections.singleton("https://inforonline.sharepoint.com/.default");
//			
//			JSONObject sharepointDetails = (JSONObject) obj;
//			
//			// If the json file is not set in testplan then we will not upload the html/csv files to sharepoint
//			if (sharepointDetails.get("url").toString().equalsIgnoreCase("")) {
//				return;
//			}
//			
//			System.out.println("======= Certificate is at: " + sharepointDetails.get("cert_path").toString() + " ======");
//			
//			// Generate access token
//			String accessToken = generateAccessToken(sharepointDetails.get("client_id").toString(), sharepointDetails.get("cert_password").toString(), sharepointDetails.get("cert_path").toString(), scope, sharepointDetails.get("authority").toString());
//			
//			// Create http client to interact with sharepoint
//			CloseableHttpClient client = HttpClients.createDefault();
//			
//			String pathOfExtent = ThreadUtils.getExtentReportPath();
//			
//			// Get the name of the file
//			String nameOfHTMLFile = pathOfExtent.substring(pathOfExtent.lastIndexOf("\\") + 1);
//			
//			String url = sharepointDetails.get("url").toString();
//			
//			// Replace the %s with the name of HTML/CSV file
//			url = url.replace("%s", nameOfHTMLFile);
//			
//			// Create a HTTP Post request
//			HttpPost request = new HttpPost(url);
//			
//			File htmlFile = new File(ThreadUtils.getExtentReportPath());
//			
//			// Add the headers to the request
//			request.addHeader("Authorization", "Bearer " + accessToken);
//			request.addHeader("Content-Type", "text/html");
//			
//			FileEntity entity = new FileEntity(htmlFile, ContentType.create("text/html", "UTF-8"));
//			
//			// Add the html file entity to the request body
//			request.setEntity(entity);
//			
//			// Send the http request
//			CloseableHttpResponse response = client.execute(request);
//			
//			System.out.println("======= Response code for uploading file to sharepoint: " + response.getStatusLine().getStatusCode() + " =======");
//			
//			// If the responde code is 200 then the file os uploaded successfully
//			if (response.getStatusLine().getStatusCode() == 200) {
//				
//				System.out.println("======= Successfully uploaded the html to sharepoint =======");
//			} else {
//				System.out.println("======= Failed to Upload HTML file to the sharepoint =======");
//			}
//			
//			// Now let's reset the url in json 
//			sharepointDetails.put("url", "");
//			
//			File file = new File(configFolder() + "sharepointDetails.json");
//			
//			try {
//				FileWriter fileWriter = new FileWriter(file);
//				
//				fileWriter.write(sharepointDetails.toJSONString());
//	            fileWriter.flush();
//	            fileWriter.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("Please create a json file with name 'sharepointDetails.json' that contains all the sharepoint details at :" + configFolder());
//		}
//	}
//
//	public static String generateAccessToken(String client_id, String cert_password, String cert_path, Set<String> scope, String authority ) throws Exception {
//
//		System.out.println("======= Started generating access token =======");
//		
//		// Read the certificate
//	    File file = new File(cert_path);
//	    InputStream pkcs12Certificate = new FileInputStream(file); /* Containing PCKS12-formatted certificate*/
//	
//	    IClientCredential credential = ClientCredentialFactory.createFromCertificate(pkcs12Certificate, cert_password);
//	
//	    ConfidentialClientApplication cca =
//	            ConfidentialClientApplication
//	                    .builder(client_id, credential)
//	                    .authority(authority)
//	                    .build();
//	
//	    IAuthenticationResult result;
//	    try {
//	        SilentParameters silentParameters =
//	                SilentParameters
//	                        .builder(scope)
//	                        .build();
//	
//	        // Try to acquire token silently. This call will fail since the token cache does not
//	        // have a token for the application you are requesting an access token for
//	        result = cca.acquireTokenSilently(silentParameters).join();
//	    } catch (Exception ex) {
//	        if (ex.getCause() instanceof MsalException) {
//	
//	            ClientCredentialParameters parameters =
//	                    ClientCredentialParameters
//	                            .builder(scope)
//	                            .build();
//	
//	            // Try to acquire a token. If successful, you should see
//	            // the token information printed out to console
//	            result = cca.acquireToken(parameters).join();
//	        } else {
//	            // Handle other exceptions accordingly
//	            throw ex;
//	        }
//	    }
//	    
//	    System.out.println("======= Generated access token =======");
//	    return result.accessToken();
//	}
//}
