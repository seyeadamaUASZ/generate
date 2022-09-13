/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.pi.config.AppProperties;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.service.IUtilisateur;

/**
 *
 * @author asow
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/zk")
public class ZkFingerController {

    int fpWidth = 0;
    int fpHeight = 0;
    private byte[] lastRegTemp = new byte[2048];
    private int cbRegTemp = 0;
    private byte[][] regtemparray = new byte[3][2048];
    private boolean bRegister = false;
    private boolean bIdentify = true;
    private boolean succes;
    private int iFid = 0;
    private int nFakeFunOn = 1;
    private int enroll_idx = 0;
    private byte[] imgbuf = null;
    private byte[] template = new byte[2048];
    private int[] templateLen = new int[1];
    private boolean mbStop = true;
    private long mhDevice = 0;
    private long mhDB = 0;
    public int[] idEmpriente;
    private WorkThread workThread = null;

    @Autowired
    IUtilisateur userService;

    @Autowired
    private AppProperties app;

    //@GetMapping("/fingerInit")
    @RequestMapping(value = "/ourirlecapteur", method = RequestMethod.GET)
    public Object ZKFingerInit() {
        ServeurResponse response = new ServeurResponse();
        Map<String, Object> maps = new HashMap<>();
        FreeSensor();
        String msg = initFinger();
        cbRegTemp = 0;
        bRegister = false;
        bIdentify = false;
        iFid = 1;
        enroll_idx = 0;
        if (!bRegister) {
            enroll_idx = 0;
            bRegister = true;
        }
        response.setData(msg);
        response.setDescription(msg);
        maps.put("state", true);
        maps.put("msg", msg);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("data", maps);
        map.put("message", "");
        return response;
    }

    @RequestMapping(value = "/actionidentificationFinger", method = RequestMethod.GET)
    public Object ZKFingeridentification() {
        ServeurResponse response = new ServeurResponse();
        if (bRegister) {
            enroll_idx = 0;
            bRegister = false;
            response.setStatut(false);
            response.setData(enroll_idx);
            response.setDescription("enrollez vous d'abord!!");
        }
        if (!bIdentify) {
            bIdentify = true;
            response.setStatut(true);
            response.setData(bIdentify);
            response.setDescription("identification activé!!");
        }
        return response;
    }

    @RequestMapping(value = "/enrollementuser", method = RequestMethod.GET)
    public Object ZKFingerEnroll() {
        ServeurResponse response = new ServeurResponse();
        Map<String, Object> maps = new HashMap<>();
        if (enroll_idx == 3) {
            maps.put("state", true);
            maps.put("idEnrolle", enroll_idx);
            maps.put("id", iFid);
            response.setStatut(true);
            response.setData(iFid);
            response.setDescription("enrollement reussi!!");
        } else {
            maps.put("state", false);
            maps.put("idEnrolle", enroll_idx);
            maps.put("id", iFid);
            response.setStatut(false);
            response.setData(enroll_idx);
            response.setDescription("l'enrollement a échouer!!");

        }

        return maps;

    }

    @RequestMapping(value = "/identificationuser", method = RequestMethod.GET)
    public Object ZKFingeridenti() {
        ServeurResponse response = new ServeurResponse();
        Map<String, Object> maps = new HashMap<>();
        if (succes = true) {
            maps.put("state", true);
            maps.put("succes", succes);
            maps.put("id", iFid);
            response.setStatut(true);
            response.setData(iFid);
            response.setDescription("identification reussi !!");
        } else {
            maps.put("state", false);
            maps.put("succes", succes);
            maps.put("id", iFid);
            response.setStatut(false);
            response.setData(succes);
            response.setDescription("empreinte inconnu!!");

        }
        return maps;

    }

    @GetMapping("/findbyempriente/{id}")
    public ServeurResponse findById(@PathVariable String id) {
        ServeurResponse response = new ServeurResponse();
        Utilisateur uti = userService.findByIdEmpriente(id);
        if (uti != null) {
            response.setStatut(true);
            response.setDescription("récuperation réussi");
            response.setData(uti);
        } else {
            response.setStatut(false);
            response.setDescription("aucun donnee trouvee");
            response.setData(null);
        }
        return response;
    }

    private ServeurResponse OnExtractOK(byte[] template, int len) {
        ServeurResponse response = new ServeurResponse();
        if (bRegister) {
            int[] fid = new int[1];
            int[] score = new int[1];
            int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
            if (ret == 0) {
                bRegister = false;
                enroll_idx = 0;
                response.setStatut(false);
                response.setData(fid[0]);
                response.setDescription("the finger already enroll by " + fid[0] + ",cancel enroll");
            }
            if (enroll_idx > 0 && FingerprintSensorEx.DBMatch(mhDB, regtemparray[enroll_idx - 1], template) <= 0) {
                response.setStatut(true);
                response.setData(fid[0]);
                response.setDescription("please press the same finger 3 times for the enrollment");
            }
            System.arraycopy(template, 0, regtemparray[enroll_idx], 0, 2048);
            enroll_idx++;
            if (enroll_idx == 3) {
                int[] _retLen = new int[1];
                _retLen[0] = 2048;
                byte[] regTemp = new byte[_retLen[0]];

                if (0 == (ret = FingerprintSensorEx.DBMerge(mhDB, regtemparray[0], regtemparray[1], regtemparray[2], regTemp, _retLen))
                        && 0 == (ret = FingerprintSensorEx.DBAdd(mhDB, iFid, regTemp))) {
                    iFid++;
                    cbRegTemp = _retLen[0];
                    System.arraycopy(regTemp, 0, lastRegTemp, 0, cbRegTemp);
                    //Base64 Template
                    response.setStatut(true);
                    response.setData(fid[0]);
                    response.setDescription("enroll succ");

                } else {
                    response.setStatut(false);
                    response.setData(fid[0]);
                    response.setDescription("enroll fail, error code=" + ret);
                }
                bRegister = false;
            } else {
                response.setStatut(false);
                response.setData(fid[0]);

                System.out.print("You need to press the " + (3 - enroll_idx) + " times fingerprint");
                response.setDescription("You need to press the " + (3 - enroll_idx) + " times fingerprint");
            }
        } else {
            if (bIdentify) {
                int[] fid = new int[1];
                int[] score = new int[1];
                int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
                if (ret == 0) {
                    response.setStatut(true);
                    response.setData(fid[0]);
                    System.out.print(".........." + fid);
                    response.setDescription("Identify succ, fid=" + fid[0] + ",score=" + score[0]);
                } else {
                    response.setStatut(false);
                    response.setData(fid[0]);
                    response.setDescription("Identify fail, errcode=" + ret);
                    iFid = 0;

                }

            } else {
                if (cbRegTemp <= 0) {
                    response.setStatut(false);
                    response.setData(cbRegTemp);
                    response.setDescription("Please register first!");
                } else {
                    int ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, template);
                    if (ret > 0) {
                        response.setStatut(true);
                        response.setData(cbRegTemp);
                        response.setDescription("Verify succ, score=" + ret);
                    } else {
                        response.setStatut(true);
                        response.setData(cbRegTemp);
                        response.setDescription("Verify fail, ret=" + ret);
                    }
                }
            }
        }
        return response;
    }

    private void FreeSensor() {
        mbStop = true;
        try {		//wait for thread stopping
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (0 != mhDB) {
            FingerprintSensorEx.DBFree(mhDB);
            mhDB = 0;
        }
        if (0 != mhDevice) {
            FingerprintSensorEx.CloseDevice(mhDevice);
            mhDevice = 0;
        }
        FingerprintSensorEx.Terminate();
    }

    public static byte[] changeByte(int data) {
        return intToByteArray(data);
    }

    public static byte[] intToByteArray(final int number) {
        byte[] abyte = new byte[4];
        abyte[0] = (byte) (0xff & number);
        abyte[1] = (byte) ((0xff00 & number) >> 8);
        abyte[2] = (byte) ((0xff0000 & number) >> 16);
        abyte[3] = (byte) ((0xff000000 & number) >> 24);
        return abyte;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int number = bytes[0] & 0xFF;
        number |= ((bytes[1] << 8) & 0xFF00);
        number |= ((bytes[2] << 16) & 0xFF0000);
        number |= ((bytes[3] << 24) & 0xFF000000);
        return number;
    }

    public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight,
            String path) throws IOException {
        java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
        java.io.DataOutputStream dos = new java.io.DataOutputStream(fos);

        int w = (((nWidth + 3) / 4) * 4);
        int bfType = 0x424d;
        int bfSize = 54 + 1024 + w * nHeight;
        int bfReserved1 = 0;
        int bfReserved2 = 0;
        int bfOffBits = 54 + 1024;

        dos.writeShort(bfType);
        dos.write(changeByte(bfSize), 0, 4);
        dos.write(changeByte(bfReserved1), 0, 2);
        dos.write(changeByte(bfReserved2), 0, 2);
        dos.write(changeByte(bfOffBits), 0, 4);

        int biSize = 40;
        int biWidth = nWidth;
        int biHeight = nHeight;
        int biPlanes = 1;
        int biBitcount = 8;
        int biCompression = 0;
        int biSizeImage = w * nHeight;
        int biXPelsPerMeter = 0;
        int biYPelsPerMeter = 0;
        int biClrUsed = 0;
        int biClrImportant = 0;

        dos.write(changeByte(biSize), 0, 4);
        dos.write(changeByte(biWidth), 0, 4);
        dos.write(changeByte(biHeight), 0, 4);
        dos.write(changeByte(biPlanes), 0, 2);
        dos.write(changeByte(biBitcount), 0, 2);
        dos.write(changeByte(biCompression), 0, 4);
        dos.write(changeByte(biSizeImage), 0, 4);
        dos.write(changeByte(biXPelsPerMeter), 0, 4);
        dos.write(changeByte(biYPelsPerMeter), 0, 4);
        dos.write(changeByte(biClrUsed), 0, 4);
        dos.write(changeByte(biClrImportant), 0, 4);

        for (int i = 0; i < 256; i++) {
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(0);
        }

        byte[] filter = null;
        if (w > nWidth) {
            filter = new byte[w - nWidth];
        }

        for (int i = 0; i < nHeight; i++) {
            dos.write(imageBuf, (nHeight - 1 - i) * nWidth, nWidth);
            if (w > nWidth) {
                dos.write(filter, 0, w - nWidth);
            }
        }
        dos.flush();
        dos.close();
        fos.close();
    }

    private class WorkThread extends Thread {

        @Override
        public void run() {
            super.run();
            int ret = 0;
            while (!mbStop) {
                templateLen[0] = 2048;
                if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen))) {
                    if (nFakeFunOn == 1) {
                        byte[] paramValue = new byte[4];
                        int[] size = new int[1];
                        size[0] = 4;
                        int nFakeStatus = 0;
                        //GetFakeStatus
                        ret = FingerprintSensorEx.GetParameters(mhDevice, 2004, paramValue, size);
                        nFakeStatus = byteArrayToInt(paramValue);
                        if (0 == ret && (byte) (nFakeStatus & 31) != 31) {
                            return;
                        }
                    }
                    OnCatpureOK(imgbuf);
                    OnExtractOK(template, templateLen[0]);
                    String strBase64 = FingerprintSensorEx.BlobToBase64(template, templateLen[0]);
                }
            }
        }

    }

    private void OnCatpureOK(byte[] imgBuf) {
        long dateTime = new Date().getTime();
        // Delete the image file
        try {
            writeBitmap(imgBuf, fpWidth, fpHeight, app.getEmpriente() + "empreinte" + dateTime + ".bmp");
            //System.out.println("Delete Image ==" + file.GetName() + "" + newFile.Delete());
        } catch (IOException e) {
        }
    }

    public String initFinger() {
        if (0 != mhDevice) {
            return "le capteur est deja alumer";
        }
        int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
        cbRegTemp = 0;
        bRegister = false;
        bIdentify = false;
        iFid = 1;
        enroll_idx = 0;
        if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init()) {
            return "initialization failed!";
        }
        ret = FingerprintSensorEx.GetDeviceCount();
        if (ret < 0) {
            FreeSensor();
            return "No device connection!";
        }
        if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0))) {
            FreeSensor();
            return "Opens the device failed, RET =" + ret + "!";
        }
        if (0 == (mhDB = FingerprintSensorEx.DBInit())) {
            FreeSensor();
            return "Initialization Database Failed, RET =" + ret + "!";
        }
        //set fakefun off
        FingerprintSensorEx.SetParameters(mhDevice, 2002, changeByte(nFakeFunOn), 4);
        byte[] paramValue = new byte[4];
        int[] size = new int[1];
        //GetFakeOn
        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 2002, paramValue, size);
        nFakeFunOn = byteArrayToInt(paramValue);
        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
        fpWidth = byteArrayToInt(paramValue);
        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
        fpHeight = byteArrayToInt(paramValue);
        imgbuf = new byte[fpWidth * fpHeight];
        mbStop = false;
        WorkThread workThread = new WorkThread();
        workThread.start(); // Start thread * /

        return "capteur allumé";
    }

}
