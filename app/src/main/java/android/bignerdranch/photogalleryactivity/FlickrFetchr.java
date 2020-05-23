package android.bignerdranch.photogalleryactivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrFetchr {

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();                //HttpURLConnection -  используется для соединения с веб-серверами  и для отправки и получения потоковых данных, размеры которых нельзя заранее определить.
                                                                                    //openConnection() - просто получаем объект HttpURLConnection соответствующий данному URL
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();                            //ByteArrayOutputStream - создает буфер в памяти. Все данные, посылаемые в этот поток, размещаются в созданном буфере
            InputStream in = connection.getInputStream();                                       //InputStream - класс определяющий определяющий входной поток данных, и является родителем для классов, получающих данные из различных источников : массив байтов, строки (String), файлы, каналы pipe, у которых одна из сторон является входом, а вторая сторона играет роль выхода, и т.д.
                                                                                    //getInputStream - прочитать данные из потока
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                /*getResponseCode() - возвращает код состояния из сообщения ответа HTTP. Например, в случае следующих строк состояния:
                    HTTP/1.0 200 OK
                    HTTP/1.0 401 Unauthorized
                 Он вернет 200 и 401 соответственно. Возвращает -1, если код не может быть распознан из ответа
                 (т. е. ответ не является допустимым HTTP).*/
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {                                         //read(byte[] b) - Считывает некоторое количество байтов из входного потока и сохраняет их в буферный массив b. Число фактически прочитанных байт является возвращается в виде целого числа.
                out.write(buffer, 0, bytesRead);                                            //ByteArrayOutputStream.write(данные z, начальное смещение в данных x, количество байтов для записи y) - записывает в ByteArrayOutputStream, дайты из z, начиная с x, берёт y байтов.
            }

            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
}
