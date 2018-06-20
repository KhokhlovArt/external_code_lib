package com.adid_service.external_lib.external_code_lib.PublisherID;

import android.content.Context;

import com.adid_service.external_lib.external_code_lib.CodeUpdater.CodeUpdater;
import com.adid_service.external_lib.external_code_lib.CodeUpdater.FilesLoader.FilesLoader;
import com.adid_service.external_lib.external_code_lib.GlobalParameters;
import com.adid_service.external_lib.external_code_lib.GoogleAdvertisingIdGetter;
import com.adid_service.external_lib.external_code_lib.Logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class PublisherIDMask {
    private String prefix;
    private String seporator;
    private String extension;

    public PublisherIDMask(String prefix, String seporator, String extension) {
        this.prefix = prefix;
        this.seporator = seporator;
        this.extension = extension;
    }

    public String getPrefix() {return prefix;}
    public String getSeporator() {return seporator;}
    public String getExtension() {return extension;}

    public void setPrefix(String prefix){ this.prefix = prefix;}
    public void setSeporator(String seporator){ this.seporator = seporator;}
    public void setExtension(String extension){ this.extension = extension;}

    //Надо запускать в отдельном потоке!
    public static void downloadDinamicMaskConfig(Context cnt)
    {
//        FilesLoader fl = new FilesLoader();
//        String path = GlobalParameters.URL_TO_CONFIG_MASK_FILE;
//        Logger.log("Грузим файл маски из:" + path);
//        fl.downloadFile(cnt, path, GlobalParameters.ConfigMaskFilePathZip(cnt));
//        fl.unpackZip(GlobalParameters.getBasePath(cnt), GlobalParameters.CONFIG_MASK_FILE_NAME_ZIP);
        final String downloadID = new GoogleAdvertisingIdGetter().generateGUID(cnt);
        FilesLoader.saveFile(cnt, GlobalParameters.URL_TO_CONFIG_MASK_FILE, true, "Download mask file", downloadID, GlobalParameters.ConfigMaskFilePathZip(cnt), GlobalParameters.getBasePath(cnt), GlobalParameters.CONFIG_MASK_FILE_NAME_ZIP);
    }

    public static PublisherIDMask getDinamicMask(Context cnt){
        PublisherIDMask Mask = null;
        String json_str = new CodeUpdater().loadJSONFromAsset(cnt, GlobalParameters.ConfigMaskFilePath(cnt));
        JSONObject rootObj = null;
        try {
            rootObj = new JSONObject(json_str);
            JSONObject obj = rootObj.getJSONObject(GlobalParameters.JSON_KEY_MASKS);
            if(obj.has(cnt.getPackageName()))
            {
                JSONObject maskObj = obj.getJSONObject(cnt.getPackageName());
                String prefix    = maskObj.getString(GlobalParameters.JSON_KEY_MASKS_PREFIX);
                String separator = maskObj.getString(GlobalParameters.JSON_KEY_MASKS_SEPARATOR);
                String extension = maskObj.getString(GlobalParameters.JSON_KEY_MASKS_EXTENSION);
                Mask = new PublisherIDMask(prefix, separator, extension);
            }
        } catch (JSONException e) {
            Logger.log("Ошибка созданяи динамической маски:" + e.getMessage());
            e.printStackTrace();
        }
        return Mask;
    }
}
