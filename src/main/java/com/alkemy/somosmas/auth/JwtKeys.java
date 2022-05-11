//package com.alkemy.somosmas.auth;
//
//import java.io.File;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.security.KeyFactory;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
//import org.apache.tomcat.util.codec.binary.Base64;
//
//
//public class JwtKeys {
//
//	public static RSAPublicKey readPublicKey(File file) throws Exception {
//	    String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
//
//	    String publicKeyPEM = key
//	      .replace("-----BEGIN PUBLIC KEY-----", "")
//	      .replaceAll(System.lineSeparator(), "")
//	      .replace("-----END PUBLIC KEY-----", "");
//
//	    byte[] encoded = Base64.decodeBase64(publicKeyPEM);
//
//	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//	    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
//	    return (RSAPublicKey) keyFactory.generatePublic(keySpec);
//	}
//	
//	public RSAPrivateKey readPrivateKey(File file) throws Exception {
//	    String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
//
//	    String privateKeyPEM = key
//	      .replace("-----BEGIN PRIVATE KEY-----", "")
//	      .replaceAll(System.lineSeparator(), "")
//	      .replace("-----END PRIVATE KEY-----", "");
//
//	    byte[] encoded = Base64.decodeBase64(privateKeyPEM);
//
//	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
//	    return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
//	}
//}
