package pers.darren.tencent.ocr;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;

public class IDCardOCR {

    private static String secretId;
    private static String secretKey;
    private static final String cardSide = "FRONT";
    private static final String suffixPng = ".png";
    private static final String suffixTxt = ".txt";
    private static final String secretFile = "SecretFile.txt";
    private static final String fileDir = "/home/darren/Desktop/";
    private static final String filePrefix = "432524199202084910-";

    public static void main(final String[] args) {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(fileDir + secretFile));
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                if (i == 0) {
                    secretId = line;
                } else if (i == 1) {
                    secretKey = line;
                }
            }
            reader.close();

            final FileInputStream fis = new FileInputStream(fileDir + filePrefix + cardSide + suffixPng);
            final byte[] imgData = new byte[fis.available()];
            fis.read(imgData);
            fis.close();
            final String imgBase64 = Base64.encodeBase64String(imgData);
            final FileOutputStream fos = new FileOutputStream(fileDir + filePrefix + cardSide + suffixTxt);
            fos.write(imgBase64.getBytes("UTF-8"));
            fos.flush();
            fos.close();

            final Credential credential = new Credential(secretId, secretKey);

            final HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            final ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            final OcrClient client = new OcrClient(credential, "ap-guangzhou", clientProfile);

            final IDCardOCRRequest request = new IDCardOCRRequest();
            request.setCardSide(cardSide);
            final Map<String, Boolean> config = new HashMap<>(11);
            config.put("CropIdCard", true);
            config.put("CropPortrait", true);
            config.put("CopyWarn", true);
            config.put("BorderCheckWarn", true);
            config.put("ReshootWarn", true);
            config.put("DetectPsWarn", true);
            config.put("TempIdWarn", true);
            config.put("InvalidDateWarn", true);
            config.put("Quality", true);
            config.put("MultiCardDetect", true);
            config.put("ReflectWarn", true);
            request.setConfig(JSON.toJSONString(config));
            request.setImageBase64(imgBase64);

            final IDCardOCRResponse response = client.IDCardOCR(request);
            System.out.println(AbstractModel.toJsonString(response));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}