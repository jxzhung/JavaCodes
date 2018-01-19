package com.t600.biz.teacher_use_record;


import com.google.gson.*;
import org.junit.Test;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jzhung on 2017/7/26.
 */
public class UserRecordTest {

    @Test
    public void testData(){
        UseRecord useRecord = new UseRecord();
        useRecord.setUseRecordId(1);
        useRecord.setTeacherLocalId(1);
        useRecord.setTeacherName("张老师");
        useRecord.setSchoolName("实验中学");
        useRecord.setRecordDesception("老师上传了课件《滕王阁序》");
        useRecord.setRecordType(1);
        useRecord.setOccurTime(new Timestamp(System.currentTimeMillis()));
        useRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
        gsonBuilder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter());
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(useRecord);
        System.out.println(json);
    }

    public class TimestampTypeAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
        private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public JsonElement serialize(Timestamp ts, Type t, JsonSerializationContext jsc) {
            String dfString = format.format(new Date(ts.getTime()));
            return new JsonPrimitive(dfString);
        }
        public Timestamp deserialize(JsonElement json, Type t, JsonDeserializationContext jsc) throws JsonParseException {
            if (!(json instanceof JsonPrimitive)) {
                throw new JsonParseException("The date should be a string value");
            }

            try {
                Date date = format.parse(json.getAsString());
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
