package com.adarsh.passwordmanager.utils

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

private const val AES_ALGORITHM = "AES"
private const val AES_TRANSFORMATION = "AES/ECB/PKCS5Padding"
private const val ENCRYPTION_KEY = "770A8A65DA156D24EE2A093277530142" // Replace with your actual encryption key

 fun encrypt(textToEncrypt: String): String {
    val cipher = Cipher.getInstance(AES_TRANSFORMATION)
    val secretKey: SecretKey = SecretKeySpec(ENCRYPTION_KEY.toByteArray(), AES_ALGORITHM)
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    val encryptedBytes = cipher.doFinal(textToEncrypt.toByteArray())
    return Base64.getEncoder().encodeToString(encryptedBytes)
}

 fun decrypt(encryptedText: String): String {
    val cipher = Cipher.getInstance(AES_TRANSFORMATION)
    val secretKey: SecretKey = SecretKeySpec(ENCRYPTION_KEY.toByteArray(), AES_ALGORITHM)
    cipher.init(Cipher.DECRYPT_MODE, secretKey)
    val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
    return String(decryptedBytes)
}
