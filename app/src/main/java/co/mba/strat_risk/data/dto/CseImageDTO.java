package co.mba.strat_risk.data.dto;

import com.google.gson.annotations.SerializedName;

public final class CseImageDTO {

    @SerializedName("src")
    private String src;

    public CseImageDTO() {
    }

    public String getSrc() {
        return src;
    }
}
