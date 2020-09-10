package co.mba.strat_risk.data.dto;

import com.google.gson.annotations.SerializedName;

public final class MetaTagsDTO {

    @SerializedName("referrer")
    private String referrer;

    public MetaTagsDTO() {
    }

    public String getReferrer() {
        return referrer;
    }
}
