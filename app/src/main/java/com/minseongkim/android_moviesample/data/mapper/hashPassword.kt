package com.minseongkim.android_moviesample.data.mapper

import com.google.android.gms.common.util.Base64Utils
import java.security.DigestException
import java.security.MessageDigest

fun hashPassword(password: String):String {
    val hash: ByteArray
    try{
        val md = MessageDigest.getInstance("SHA-256")
        md.update(password.toByteArray())
        hash = md.digest()
    }catch (e: CloneNotSupportedException){
        throw DigestException("couldn't make digest of patial content")
    }
    return Base64Utils.encode(hash)
}