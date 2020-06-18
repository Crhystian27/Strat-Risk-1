package co.mba.strat_risk.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "modulo1_table")
public final class Modulo1 {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

}
