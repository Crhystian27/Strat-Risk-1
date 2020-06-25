package co.mba.strat_risk.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "modulo1_table")
public final class Modulo1 {
    //Cambio en el modulo

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "param1")
    private String param1;
    @ColumnInfo(name = "param2")
    private String param2;

    @Ignore
    public Modulo1() {
    }

    public Modulo1(Integer id, String param1, String param2) {
        this.id = id;
        this.param1 = param1;
        this.param2 = param2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
