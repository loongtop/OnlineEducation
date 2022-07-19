package com.gkhy.ossservice.service.impl;

import com.gkhy.ossservice.service.OssService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Autowired
    private Storage storage;

    @Value("bucketname")
    String bucketName;
    @Value("subdirectory")
    String subdirectory;

    @Override
    public Mono<URL> uploadFile(FilePart filePart) {
        //Convert the file to a byte array
        final byte[] byteArray = convertToByteArray(filePart);

        //Prepare the blobId
        //BlobId is a combination of bucketName + subdirectiory(optional) + fileName
        final BlobId blobId = constructBlobId(bucketName, subdirectory, filePart.filename());

        return Mono.just(blobId)
                //Create the blobInfo
                .map(bId -> BlobInfo.newBuilder(blobId)
                        .setContentType("text/plain")
                        .build())
                //Upload the blob to GCS
                .doOnNext(blobInfo -> getStorage().create(blobInfo, byteArray))
                //Create a Signed "Path Style" URL to access the newly created Blob
                //Set the URL expiry to 10 Minutes
                .map(blobInfo -> createSignedPathStyleUrl(blobInfo, 10, TimeUnit.MINUTES));
    }

    private URL createSignedPathStyleUrl(BlobInfo blobInfo, int duration, TimeUnit timeUnit) {
        return getStorage()
                .signUrl(blobInfo, duration, timeUnit, Storage.SignUrlOption.withPathStyle());
    }

    /**
     * Construct Blob ID
     *
     * @param bucketName
     * @param subdirectory optional
     * @param fileName
     * @return BlobId
     */
    private BlobId constructBlobId(String bucketName,
                                   @Nullable String subdirectory,
                                   String fileName) {
        String filePath = new DateTime().toString("yyyy/MM/dd") + "/" + fileName;
        return Optional.ofNullable(subdirectory)
                .map(s -> BlobId.of(bucketName, subdirectory + "/" + filePath))
                .orElse(BlobId.of(bucketName, filePath));
    }

    /**
     * Here, we convert the file to a byte array to be sent to GCS Libraries
     *
     * @param filePart File to be used
     * @return Byte Array with all the contents of the file
     */
    @SneakyThrows
    private byte[] convertToByteArray(FilePart filePart) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            filePart.content()
                    .subscribe(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        log.trace("readable byte count:" + dataBuffer.readableByteCount());
                        dataBuffer.read(bytes);
                        DataBufferUtils.release(dataBuffer);
                        try {
                            bos.write(bytes);
                        } catch (IOException e) {
                            log.error("read request body error...", e);
                        }
                    });

            return bos.toByteArray();
        }
    }
}
