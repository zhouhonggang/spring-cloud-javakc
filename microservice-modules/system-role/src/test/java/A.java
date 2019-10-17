import java.io.File;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-09-27 16:36
 */
public class A {

    public static void main(String[] args) {

        String path = "D:\\d9b1c30ded38a99d7a54d83ff014cd53\\hls\\aa";

        File file = new File(path);
        String[] array = file.list();
        for(String name: array)
        {
            String aa = "";
            String bb = name;
            name = name.substring(0, name.lastIndexOf("."));
            int number = Integer.valueOf(name);
            if(number >= 0 && number < 100)
            {
                if( (number / 10) == 0 ) {
                    aa = "00"+number+".ts";
                }
                else
                {
                    aa = "0"+number+".ts";
                }
                File files1 = new File(path + File.separator + bb);
                File files2 = new File(path + File.separator + aa);
                files1.renameTo(files2);
            }
        }

    }

}
