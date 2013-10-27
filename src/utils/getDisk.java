package utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class getDisk {
	
	 String ERROR = new String("error");
	
	public  boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    public  String getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return formatSize(availableBlocks * blockSize);
    }

    public  String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return formatSize(totalBlocks * blockSize);
    }
    
    
    public  long getAvailableInternalMemorySizeInt() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public  long getTotalInternalMemorySizeInt() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }
    
    
    public  long getAvailableExternalMemorySizeInt() {
    	if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } 
    	return 0;
    }

    public  long getTotalExternalMemorySizeInt() {
    	if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } 
    	return 0;
    }
    

    public  String getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return formatSize(availableBlocks * blockSize);
        } else {
            return ERROR;
        }
    }

    public  String getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return formatSize(totalBlocks * blockSize);
        } else {
            return ERROR;
        }
    }

    public  String formatSize(long size) {
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
            commaOffset -= 3;
        }

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
    
    
    
    
    public  long pourcentageFreeInternal()
    {
    	long caca = ((getAvailableInternalMemorySizeInt()*100)/getTotalInternalMemorySizeInt());
    	return caca;
    }
    
    public long pourcentageUseInternal()
    {
    	return 100-pourcentageFreeInternal();
    }
    
    public long pourcentageUseExternal()
    {
    	long caca = ((getAvailableExternalMemorySizeInt()*100)/getTotalExternalMemorySizeInt());
    	return 100-caca;
    }
    
    
    public String usageInternal()
    {
    	long caca = getTotalInternalMemorySizeInt() - getAvailableInternalMemorySizeInt();
    	return formatSize(caca);
    }


}
