import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class Download {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите url-ссылку:");
            String urlString = reader.readLine();
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream in = new BufferedInputStream(url.openStream());

            long fileSize = urlConnection.getContentLength();
            String fileName = "loaded." + getFileExtension(urlString);

            File fileOut = new File(fileName);
            FileOutputStream ops = new FileOutputStream(fileOut);
            System.out.println("Началось скачивание");

            byte[] buffer = new byte[1024];
            double per;
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                ops.write(buffer, 0, count);
                fileOut = new File(fileName);
                String sizeFile = "", sizeFileOut = "";
                if(fileSize < 1024*1024) {
                    sizeFile = getFileKiloBytes(fileSize);
                    sizeFileOut = getFileKiloBytes(fileOut.length());
                }
                else if(fileSize >= 1024*1024) {
                    sizeFile = getFileMegaBytes(fileSize);
                    sizeFileOut = getFileMegaBytes(fileOut.length());
                }
                else if(fileSize < 1024) {
                    sizeFile = String.format("%.2f", (double)fileSize) + " b";
                    sizeFileOut = String.format("%.2f", (double)fileOut.length()) + " b";
                }
                per = ((double)fileOut.length()/(double)fileSize) * 100;
                String fD = String.format("%.2f", per);
                System.out.print("| " + sizeFileOut + " / " + sizeFile + " | " + fD + " % |");
                System.out.print("\r");
            }
            System.out.println();
            System.out.println("Завершение");

            reader.close();
            in.close();
            ops.close();
        } catch(IOException e) {
            System.out.println("Введен неправильный url");
        }
    }

    private static String getFileKiloBytes(long size) {
        String fD = String.format("%.2f", (double)size/1024);
        return fD + " Kb";
    }

    private static String getFileMegaBytes(long size) {
        String fD = String.format("%.2f", (double)size/(1024*1024));
        return fD + " Mb";
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

}
