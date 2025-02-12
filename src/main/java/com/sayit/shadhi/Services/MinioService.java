package com.sayit.shadhi.Services;

import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Interfaces.MinioInterface;
import io.minio.DeleteObjectTagsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class MinioService implements MinioInterface {

    private  final MinioClient minioClient;

    @Override
    public String postImageToTheServer(InputStream inputStream ,  String objectName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

           ObjectWriteResponse objectWriteResponse =  minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .stream(inputStream ,inputStream.available() , -1 )
                            .object(objectName)
                            .build()
            );
           return objectName;
    }

    @Override
    public GeneralStatus deleteImageFromTheServer(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.deleteObjectTags(
                DeleteObjectTagsArgs
                        .builder()
                        .object(objectName)
                        .build()
        );
        return GeneralStatus.REMOVED;
    }

    @Override
    public List<String> addMultipleFileToServer() {
        return List.of();
    }

    @Override
    public List<HashMap<String, GeneralStatus>> deleteMultipleFileOfAnUser(long userId) {
        return List.of();
    }
}
