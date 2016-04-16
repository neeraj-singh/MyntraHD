package com.neerajsingh.myntrahd.network.response;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private Long prodId;
    private String displayImg;
    private String youtubeURL;
    private String prodTitle;
    private Long mrp;
    private String sizeAvail;
    private Long currentViewers;
    private List<String> usersBidded= new ArrayList<String>();
    private List<Long> bidAmount= new ArrayList<Long>();


    public Long getProdId() {
        return prodId;
    }
    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }
    public String getDisplayImg() {
        return displayImg;
    }
    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }
    public String getYoutubeURL() {
        return youtubeURL;
    }
    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
    public String getProdTitle() {
        return prodTitle;
    }
    public void setProdTitle(String prodTitle) {
        this.prodTitle = prodTitle;
    }
    public Long getMrp() {
        return mrp;
    }
    public void setMrp(Long mrp) {
        this.mrp = mrp;
    }
    public String getSizeAvail() {
        return sizeAvail;
    }
    public void setSizeAvail(String sizeAvail) {
        this.sizeAvail = sizeAvail;
    }
    public Long getCurrentViewers() {
        return currentViewers;
    }
    public void setCurrentViewers(Long currentViewers) {
        this.currentViewers = currentViewers;
    }
    public List<String> getUsersBidded() {
        return usersBidded;
    }
    public void setUsersBidded(List<String> usersBidded) {
        this.usersBidded = usersBidded;
    }
    public List<Long> getBidAmount() {
        return bidAmount;
    }
    public void setBidAmount(List<Long> bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Product(Long prodId,String displayImg, String youtubeURL, String prodTitle, Long mrp, String sizeAvail,
                   Long currentViewers) {
        super();
        this.prodId=prodId;
        this.displayImg = displayImg;
        this.youtubeURL = youtubeURL;
        this.prodTitle = prodTitle;
        this.mrp = mrp;
        this.sizeAvail = sizeAvail;
        this.currentViewers = currentViewers;
    }

    public Product() {
        super();
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", displayImg='" + displayImg + '\'' +
                ", youtubeURL='" + youtubeURL + '\'' +
                ", prodTitle='" + prodTitle + '\'' +
                ", mrp=" + mrp +
                ", sizeAvail='" + sizeAvail + '\'' +
                ", currentViewers=" + currentViewers +
                ", usersBidded=" + usersBidded +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
