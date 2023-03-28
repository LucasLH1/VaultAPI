package fr.paris.lutece.plugins.vaultapi.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDriver implements Serializable {
	
	private static Vault _vault;
	
	
	public static Vault initDriver(Vault xVault) {
		
		try {
			VaultConfig config = new VaultConfig()
			        .address("http://127.0.0.1:8200")
			        .token("hvs.rAUqJ9msZdS9AAiw49ftMzZF")
			        .build();
			
			 xVault = new Vault(config);
			 
			 System.out.println("\n\n\nConnecté avec succès au serveur Vault ! ");
			
		} catch (VaultException e) {
			System.out.println("Erreur pour se connecter au serveur Vault");
			e.printStackTrace();
		}
		return xVault;

		
	}
	
	public static void getAllSecrets() {
		
		try {
			final List<String> value = VaultDriver.initDriver(_vault).logical()
			        .list("/secret/").getListData();


		} catch (VaultException e) {
			System.out.println("Erreur pour récupérer la valeur");
			e.printStackTrace();
		}

	}
	
	public static void getSecretByName(String secretName) {
		
		try {
			final List<String> valueString = VaultDriver.initDriver(_vault).logical()
					.read("/secret/"+secretName)
					.getListData();
		} catch (VaultException e) {
			System.out.println("Erreur pour récupérer le secret");
			e.printStackTrace();
		}
		
	}
	
	public static void getDetailsSecret(String secretName, String secretParam) {
		try {
			final String valueString = VaultDriver.initDriver(_vault).logical()
					.read("/secret"+secretName)
					.getData()
					.get(secretParam);
		} catch (VaultException e) {
			System.out.println("Erreur pour récupérer la valeur du secret");
			e.printStackTrace();
		}
	}
	
	
	public static void writeSecret(String secret, String value, String path) throws VaultException {
		final Map<String, Object> secrets = new HashMap<String, Object>();
		secrets.put(secret, value);
		// Write operation
		final LogicalResponse writeResponse = VaultDriver.initDriver(_vault).logical()
		                                        .write("secret/"+path, secrets);
	}
	
}
