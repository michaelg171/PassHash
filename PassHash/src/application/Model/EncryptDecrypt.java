package application.Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptDecrypt {
	private Cipher cipher;
	private SecretKey sec;
	
	public EncryptDecrypt(SecretKey key, String Transform ) throws NoSuchAlgorithmException, NoSuchPaddingException{
		this.sec = key;
		Cipher.getInstance(Transform);
	}
	public void encrypt(String content, String filename){
		try {
			cipher.init(Cipher.ENCRYPT_MODE, sec);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[]iv = cipher.getIV();
		
		try(FileOutputStream fileOut = new FileOutputStream(filename);
		CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)){
				fileOut.write(iv);	
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String decrypt( String fileName) throws FileNotFoundException, IOException{
		String content="";
		
		try(FileInputStream fileIn = new FileInputStream(fileName)){
			byte[] fileIv = new byte[16];
			fileIn.read(fileIv);
			try {
				cipher.init(Cipher.DECRYPT_MODE, sec, new IvParameterSpec(fileIv));
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try (
	                CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
	                InputStreamReader inputReader = new InputStreamReader(cipherIn);
	                BufferedReader reader = new BufferedReader(inputReader)
	            ){
				StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	            content = sb.toString();
			}
		}
		return content;
	}
	
}
