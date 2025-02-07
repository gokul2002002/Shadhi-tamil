package com.sayit.shadhi.Interfaces;

import com.sayit.shadhi.Enums.GeneralStatus;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

public interface MinioInterface {
    public GeneralStatus postImageToTheServer(InputStream inputStream , String objectName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    public GeneralStatus deleteImageFromTheServer(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    public List<String> addMultipleFileToServer();
    public List<HashMap<String , GeneralStatus>> deleteMultipleFileOfAnUser(long userId);
}
