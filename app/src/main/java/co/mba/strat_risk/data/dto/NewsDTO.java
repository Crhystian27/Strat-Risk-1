package co.mba.strat_risk.data.dto;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class NewsDTO {

    @SerializedName("kind")
    private  String kind;


    @SerializedName("items")
    private List<ItemsDTO> items;


    public NewsDTO() {
    }

    public String getKind() {
        return kind;
    }

    public List<ItemsDTO> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "kind='" + kind + '\'' +
                ", items=" + items +
                '}';
    }
}
