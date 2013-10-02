package storage;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class getDisk {
	
	 String ERROR = new String("No SD");
	
	
	Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);


    public String getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return formatSize(availableBlocks * blockSize);
    }

    public String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return formatSize(totalBlocks * blockSize);
    }

    public String getAvailableExternalMemorySize() {
        if (isSDPresent) {
            File path = EXTERNAL_STORAGE_DIRECTORY();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return formatSize(availableBlocks * blockSize);
        } else {
            return ERROR;
        }
    }

    private static final File EXTERNAL_STORAGE_DIRECTORY()
    {
    	
    	File normal = new File("/sdcard");
    	File way1 = new File("/sdcard/external_sd/");
    	File way2 = new File("/mnt/external_sd/");
    	File way3 = new File("/mnt/extSdCard/");
    	File way4 = new File("/mnt/sdcard/external_sd");
    	File wayDefault = new File(Environment.getExternalStorageDirectory().getPath());
    	
    	
    	if(way4.exists())
    	{
    		return way4;
    	}
    	else if(way1.exists())
    	{
    		return way1;
    	}
    	else if(way2.exists())
    	{
    		return way2;
    	}
    	else if(way3.exists())
    	{
    		return way3;
    	}
    	else if (normal.exists())
    	{
    		return normal;
    	}
    	else
    	{
    		return wayDefault;
    	}
    	
    }


    public String getTotalExternalMemorySize() {
        if (isSDPresent) {
            File path = EXTERNAL_STORAGE_DIRECTORY();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return formatSize(totalBlocks * blockSize);
        } else {
            return ERROR;
        }
    }

    public String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = " Ko";
            size /= 1024;
            if (size >= 1024) {
                suffix = " Mo";
                size /= 1024;
                if (size >= 1024) {
                    suffix = " Go";
                    
                }
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            resultBuffer.delete(resultBuffer.length() - 2, resultBuffer.length());
            commaOffset -= 3;
        }
       

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }


}
