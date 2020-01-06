package com.example.my_second_app.entities.enums;
import java.util.Date;
import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static PackStatus getStatus(Integer numeral){
        for(PackStatus ds : PackStatus.values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }

    @TypeConverter
    public static int integerToStatus(PackStatus status) {
        return status.ordinal();
    }
    @TypeConverter
    public static PackType getType(Integer numeral){
        for(PackType ds : PackType.values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }

    @TypeConverter
    public static int integerToType(PackType Type) {
        return Type.ordinal();
    }
    @TypeConverter
    public static PackWeight getWeight(Integer numeral){
        for(PackWeight ds : PackWeight.values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }

    @TypeConverter
    public static int integerToWeight(PackWeight Weight) {
        return Weight.ordinal();
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

