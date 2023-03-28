package fr.paris.lutece.plugins.vaultapi.rs;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Scanner;


public class VaultAPI {
	
	public static void createSecret(String firstname, String lastname, String email,String password, Date birthdate, String firm) {
		
		try {
            // Construct manually a JSON object in Java, for testing purposes an object with an object
			JSONObject data = new JSONObject();
            JSONObject secret = new JSONObject();
            secret.put("firstname", firstname);
            secret.put("lastname", lastname);
            secret.put("email", email);
            secret.put("password", password);
            secret.put("birthdate", birthdate.toString());
            data.put("data", secret);
            
       
            // URL and parameters for the connection, This particulary returns the information passed
            URL baseUrl = new URL("http://127.0.0.1:8200/v1/secret/data/");
            URL completeUrl = new URL(baseUrl,firm);

            HttpURLConnection httpConnection  = (HttpURLConnection) completeUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("X-Vault-Token", "hvs.rAUqJ9msZdS9AAiw49ftMzZF");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            // Writes the JSON parsed as string to the connection
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(data.toString().getBytes());
            Integer responseCode = httpConnection.getResponseCode();
            System.out.println("###########################################################################################");
            System.out.println(new Gson().toJson(data));    
            System.out.println("Données JSON envoyées. Statut : " + responseCode);
            System.out.println("###########################################################################################");

        } catch (Exception e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
		
	public static void selectSecret(String firm) {
		
		String inline = "";
		
		try {

            URL baseUrl = new URL("http://127.0.0.1:8200/v1/secret/data/");
            URL completeUrl = new URL(baseUrl,firm);
            
            HttpURLConnection conn = (HttpURLConnection) completeUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Vault-Token", "hvs.rAUqJ9msZdS9AAiw49ftMzZF");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } 
            else
			{
				//Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(completeUrl.openStream());
				while(sc.hasNext())
				{
					inline+=sc.nextLine();
				}
				sc.close();
			}
            
            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONObject jobj =  (JSONObject)parse.parse(inline);
            JSONArray jsonArray_1 = (JSONArray) jobj.get("data");
            for(int i=0;i<jsonArray_1.size();i++) {
            	JSONObject jsonObject_1 = (JSONObject)jsonArray_1.get(i);
            	JSONArray jsonArray_2 = (JSONArray)jsonObject_1.get("data");
            	System.out.println("\nPassword: " + jsonObject_1.get("password"));
            
            }
			conn.disconnect();
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
		
    }
		
	

}
