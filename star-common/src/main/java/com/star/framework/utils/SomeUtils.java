/**create by liuhua at 2017年6月25日 下午4:59:22**/
package com.star.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SomeUtils {

	public static void createFile(String dir, String fileName, String data) throws IOException{
		File f = new File(dir, fileName);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(f);
            fout.write(data.getBytes());
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
	}
}
