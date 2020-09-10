package co.mba.strat_risk.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class PageMapDTO {

    @SerializedName("metatags")
    private List<MetaTagsDTO> meteatags;

    @SerializedName("cse_image")
    private List<CseImageDTO> cse_image;

    public PageMapDTO() {
    }

    public List<MetaTagsDTO> getMeteatags() {
        return meteatags;
    }

    public List<CseImageDTO> getCse_image() {
        return cse_image;
    }
}
