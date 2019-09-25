package cn.xiaoyu.common;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date转成String
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2)
            throws IOException {
        // TODO Auto-generated method stub
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTEN);
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);

    }

}
